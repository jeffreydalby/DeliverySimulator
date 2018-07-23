package edu.bu.met.cs665.products;

public interface ProductCreator {

    BaseProduct createProduct(ProductNames.Names name, int quantity);
}
