package one.up.ra;

public class ProductV1 {
    private final String id;
    private final String title;

    public ProductV1(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
