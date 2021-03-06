package one.up.ra;

import java.util.Map;

public class ProductV2 {

    private final String id;
    private final ProductType type;
    private final Map<String, String> attributes;

    public ProductV2(String id, ProductType type, Map<String, String> attributes) {
        this.id = id;
        this.type = type;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public ProductType getType() {
        return type;
    }
}
