package edu.bu.met.cs665.products;

//generic class to hold warm food edu.bu.met.cs665.products
class WarmFood extends BaseProduct {
    WarmFood() {
        super();
    }

    /**
     * Warm food object set super class to keepWarm
     *
     * @param productType - type of warm food
     * @param quantity    - quantity of warm food
     */
    WarmFood(ProductNames.Names productType, int quantity) {
        super(false, true, productType, quantity);
    }
}

