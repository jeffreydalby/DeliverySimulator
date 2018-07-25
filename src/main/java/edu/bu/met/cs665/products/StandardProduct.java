package edu.bu.met.cs665.products;

class StandardProduct extends BaseProduct {
    StandardProduct(){
        super();
    }
    /**
     * Standard food object set super class leave keepCold and keepWarm false
     * @param productType - type of standard product
     * @param quantity - quantity of standard product
     */
    StandardProduct(ProductNames.Names productType, int quantity){
        super(false,false, productType,quantity);
    }

}
