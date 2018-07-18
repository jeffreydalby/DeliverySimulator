package edu.bu.met.cs665.products;

//generic class to hold warm food edu.bu.met.cs665.products
public class WarmFood extends BaseProduct {
    WarmFood(){
        super();
    }
    //create the product and set it to keepWarm
    public WarmFood(ProductNames.names name, int quantity) {
        super(false, true, name, quantity);
    }
}
