package edu.bu.met.cs665.products;

public interface Product {

    boolean isKeepCold();
    boolean isKeepWarm();
    int getQuantity();
    void setQuantity(int quantity);
   // ProductNames.Names productName = ProductNames.Names.chocolates;
    ProductNames.Names getProductType();
}
