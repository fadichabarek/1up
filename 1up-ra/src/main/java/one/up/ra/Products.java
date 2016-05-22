package one.up.ra;

import java.util.HashMap;
import java.util.Map;

public class Products {
    public static ProductV1 findProductAsV1(String id) {
        return new ProductV1(id, "A Product, Version 1");
    }

    public static ProductV2 findProductAsV2(String id) {
        Map attributes = new HashMap<String, String>();

        attributes.put("title", "A Product, Version 2");
        attributes.put("price", "50 EUR");
        attributes.put("size", "L");

        return new ProductV2(id, ProductType.Shirt, attributes);
    }

    public static ProductV3 findProductAsV3(String id) {
        Map attributes = new HashMap<String, String>();

        attributes.put("title", "A Product, Version 3");
        attributes.put("price", "50 EUR");
        attributes.put("size", "L");

        return new ProductV3(id, new Category("1", "Shirt", new Category[0]), attributes);
    }
}
