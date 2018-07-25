package edu.bu.met.cs665.products;

public interface ProductCreator {
    //base definition for the product factory
    BaseProduct createProduct(ProductNames.Names name, int quantity);
}
