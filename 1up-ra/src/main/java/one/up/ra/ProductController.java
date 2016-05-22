package one.up.ra;

import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @RequestMapping(value = "/product/{id}")
    public ResponseEntity getProductV0(@PathVariable String id, @RequestHeader(value = "Accept-Version", defaultValue = "-1") String apiVersion) {

        // When no version is given we respond with the latest version.
        if(Integer.parseInt(apiVersion) == -1) {
            return new ResponseEntity(getProductV2(id), HttpStatus.OK);
        }

        // When an endpoint isn't supported anymore we send a 410 Gone status code.
        if(Integer.parseInt(apiVersion) < 1) {
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
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Warning", Collections.singletonList("299 - Endpoint is deprecated. Product Version 1 will be supported until MM-DD-YYYY."));

        ProductV1 product = new ProductV1(id, "A Product, Version 1");
        return new ResponseEntity<ProductV1>(product, headers, HttpStatus.OK);

    }

    @RequestMapping(value = "/product/{id}", headers = "Accept-Version=2")
    public ProductV2 getProductV2(@PathVariable String id) {
        return findProductAsV2(id);
    }

    private ProductV2 findProductAsV2(String id) {
        Map attributes = new HashMap<String, String>();

        attributes.put("title", "A Product, Version 2");
        attributes.put("price", "50 EUR");
        attributes.put("size", "L");

        return new ProductV2(id, ProductType.Shirt, attributes);
    }

    @RequestMapping(value = "/product/{id}", headers = "Accept-Version=3")
    public ProductV3 getProductV3(@PathVariable String id) {
        return findProductAsV3(id);
    }

    private ProductV3 findProductAsV3(String id) {
        Map attributes = new HashMap<String, String>();

        attributes.put("title", "A Product, Version 3");
        attributes.put("price", "50 EUR");
        attributes.put("size", "L");

        return new ProductV3(id, new Category("1", "Shirt", new Category[0]), attributes);
    }

}
