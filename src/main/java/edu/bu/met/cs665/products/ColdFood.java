package edu.bu.met.cs665.products;

class ColdFood extends BaseProduct {

    ColdFood(){
        super();
    }

    /**
     * cold food object set super class to keepCold
     *
     * @param productType - type of cold food
     * @param quantity    - quantity of cold food
     */
    ColdFood(ProductNames.Names productType, int quantity) {
        super(true, false, productType, quantity);
    }
}
