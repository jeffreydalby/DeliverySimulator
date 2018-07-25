package edu.bu.met.cs665.products;

//cold food factory
public class ColdFoodCreator implements ProductCreator {
    /**
     * make a new cold food product
     *
     * @param name     -name of the cold food item
     * @param quantity - quantity of the item
     * @return - the created cold food object as a BaseProduct
     */
    @Override
    public BaseProduct createProduct(ProductNames.Names name, int quantity) {
        return new ColdFood(name, quantity);
    }
}
