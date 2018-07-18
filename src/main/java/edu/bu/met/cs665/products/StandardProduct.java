package edu.bu.met.cs665.products;

public class StandardProduct extends BaseProduct {
    StandardProduct(){
        super();
    }
    //create a product that is fine at room temp
    public StandardProduct(ProductNames.names name, int quantity){
        super(false,false, name,quantity);
    }

}
