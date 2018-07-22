package edu.bu.met.cs665.products;

public interface Product {

    boolean isKeepCold();
    boolean isKeepWarm();
    int getQuantity();
    void setQuantity(int quantity);
   // ProductNames.names productName = ProductNames.names.chocolates;
    ProductNames.names getProductType();
}
