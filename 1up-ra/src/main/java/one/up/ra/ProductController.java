package one.up.ra;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @RequestMapping(value = "/product/{id}", headers = "X-Api-Version=1")
    public ProductV1 getProductV1(@PathVariable String id) {
        return new ProductV1(id, "A Product, Version 1");
    }

    @RequestMapping(value = "/product/{id}", headers = "X-Api-Version=2")
    public ProductV2 getProductV2(@PathVariable String id) {
        return findProductAsV2(id);
    }

    private ProductV2 findProductAsV2(String id) {
        Map attributes = new HashMap<String, String>();

        attributes.put("title", "A Product, Version 2");
        attributes.put("price", "50 EUR");
        attributes.put("size", "L");

        return new ProductV2(id, attributes);
    }

}
