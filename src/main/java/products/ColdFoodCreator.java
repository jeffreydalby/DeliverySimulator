package products;

//cold food factory
public class ColdFoodCreator implements ProductCreator {
    //make a new cold food product
    @Override
    public BaseProduct CreateProduct(ProductNames.names name, int quantity) {
        return new ColdFood(name,quantity);
    }
}
