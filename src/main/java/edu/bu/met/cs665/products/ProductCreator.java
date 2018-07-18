package edu.bu.met.cs665.products;

public interface ProductCreator {

    BaseProduct CreateProduct(ProductNames.names name, int quantity);
}
