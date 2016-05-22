package one.up.ra;

import java.util.Map;

public class ProductV2 {

    private final String id;
    private final Map<String, String> attributes;

    public ProductV2(String id, Map<String, String> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
