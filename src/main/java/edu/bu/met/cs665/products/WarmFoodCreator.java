package edu.bu.met.cs665.products;
//warm food factory
public class WarmFoodCreator implements ProductCreator {
    //return a warm food product
    @Override
    public BaseProduct CreateProduct(ProductNames.names name, int quantity) {
        return new WarmFood(name,quantity);
    }
}