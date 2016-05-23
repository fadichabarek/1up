package one.up.ra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@Service
public class ProductController {

    private final CounterService counterService;

    @Autowired
    public ProductController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(value = "/product/{id}")
    public ResponseEntity getProductV0(@PathVariable String id, @RequestHeader(value = "Accept-Version", defaultValue = "-1") String apiVersion) {

        // When no accept header is given we respond with the latest version.
        if(Integer.parseInt(apiVersion) == -1) {
            return new ResponseEntity(getProductV2(id), HttpStatus.OK);
        }

        // When an endpoint isn't supported anymore we send a 410 Gone status code.
        if(Integer.parseInt(apiVersion) < 1) {
            counterService.increment("api.gone.product.id");
            return new ResponseEntity(HttpStatus.GONE);
        }

        // When an endpoint version is used that has never been supported we send a 406 Not Acceptable.
        if(Integer.parseInt(apiVersion) > 3) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        // When a version should be supported but there is no handler we send a 500 Internal Server Error.
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @RequestMapping(value = "/product/{id}", headers = "Accept-Version=1")
    public ResponseEntity<ProductV1> getProductV1(@PathVariable String id) {
        // When an endpoint is deprecated we warn the client with an HTTP Warning Header.
        counterService.increment("api.deprecated.product.id");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Warning", Collections.singletonList("299 - Endpoint is deprecated. Product Version 1 will be supported until MM-DD-YYYY."));

        return new ResponseEntity<ProductV1>(Products.findProductAsV1(id), headers, HttpStatus.OK);

    }

    @RequestMapping(value = "/product/{id}", headers = "Accept-Version=2")
    public ProductV2 getProductV2(@PathVariable String id) {
        // A range of versions is supported by the Accept-Version Header.
        counterService.increment("api.ok.product.id");
        return Products.findProductAsV2(id);
    }

    @RequestMapping(value = "/product/{id}", headers = "Accept-Version=3")
    public ProductV3 getProductV3(@PathVariable String id) {
        counterService.increment("api.ok.product.id");
        return Products.findProductAsV3(id);
    }

}
