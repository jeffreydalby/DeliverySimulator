package edu.bu.met.cs665.products;

public class ColdFood extends BaseProduct {

    ColdFood(){
        super();
    }
    //make a new cold food
    public ColdFood(ProductNames.names productType, int quantity) {
        super(true, false, productType, quantity);
    }
}
