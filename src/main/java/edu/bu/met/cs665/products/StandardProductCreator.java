package edu.bu.met.cs665.products;

public class StandardProductCreator implements ProductCreator{
    //return a normal product
    @Override
    public BaseProduct CreateProduct(ProductNames.names name, int quantity) {
        return new StandardProduct(name,quantity);
    }
}
