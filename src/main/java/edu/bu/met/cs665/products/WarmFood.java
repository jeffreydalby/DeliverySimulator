package edu.bu.met.cs665.products;

//generic class to hold warm food edu.bu.met.cs665.products
class WarmFood extends BaseProduct {
    WarmFood(){
        super();
    }
    //create the product and set it to keepWarm
    WarmFood(ProductNames.Names name, int quantity) {
        super(false, true, name, quantity);
    }
}
