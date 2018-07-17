package products;

public interface ProductCreator {

    BaseProduct CreateProduct(ProductNames.names name, int quantity);
}
