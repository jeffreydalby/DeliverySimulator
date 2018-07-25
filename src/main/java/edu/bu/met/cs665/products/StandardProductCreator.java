package edu.bu.met.cs665.products;

public class StandardProductCreator implements ProductCreator {
    /**
     * make a new standard product
     *
     * @param name     -name of the standard product
     * @param quantity - quantity of the item
     * @return - the created product as a BaseProduct
     */
    @Override
    public BaseProduct createProduct(ProductNames.Names name, int quantity) {
        return new StandardProduct(name, quantity);
    }
}
