package one.up.ra;

public class Category {

    private final String id;
    private final String title;
    private final Category[] subcategories;

    public Category(String id, String title, Category[] subcategories) {
        this.id = id;
        this.title = title;
        this.subcategories = subcategories;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Category[] getSubcategories() {
        return subcategories;
    }
}
