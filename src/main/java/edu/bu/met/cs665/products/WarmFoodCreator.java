package edu.bu.met.cs665.products;

//warm food factory
public class WarmFoodCreator implements ProductCreator {
    /**
     * make a new warm food product
     *
     * @param name     -name of the warm food item
     * @param quantity - quantity of the item
     * @return - the created warm food object as a BaseProduct
     */
    @Override
    public BaseProduct createProduct(ProductNames.Names name, int quantity) {
        return new WarmFood(name, quantity);
    }
}
