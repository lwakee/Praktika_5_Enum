import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private Category category;
    private List<Product> products;

    public Catalog(Category category) {
        this.category = category;
        this.products = new ArrayList<>();
    }

    public Category getCategory() {
        return category;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return category.getDisplayName();
    }
}