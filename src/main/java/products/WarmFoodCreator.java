package products;

public class WarmFoodCreator implements ProductCreator {
    @Override
    public BaseProduct CreateProduct(ProductNames.names name, int quantity) {
        return new WarmFood(name,quantity);
    }
}
