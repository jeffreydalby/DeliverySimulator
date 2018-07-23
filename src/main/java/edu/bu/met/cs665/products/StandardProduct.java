package edu.bu.met.cs665.products;

class StandardProduct extends BaseProduct {
    StandardProduct(){
        super();
    }
    //create a product that is fine at room temp
    StandardProduct(ProductNames.Names name, int quantity){
        super(false,false, name,quantity);
    }

}
