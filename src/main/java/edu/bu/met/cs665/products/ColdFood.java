package edu.bu.met.cs665.products;

class ColdFood extends BaseProduct {

    ColdFood(){
        super();
    }
    //make a new cold food
    ColdFood(ProductNames.Names productType, int quantity) {
        super(true, false, productType, quantity);
    }
}
