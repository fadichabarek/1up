package one.up.ra;

import java.util.Map;

public class ProductV3 {

    private final String id;

    public Category getCategory() {
        return category;
    }

    private final Category category;
    private final Map<String, String> attributes;

    public ProductV3(String id, Category category, Map<String, String> attributes) {
        this.id = id;
        this.category = category;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

}
