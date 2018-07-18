package edu.bu.met.cs665.products;

//base class to create edu.bu.met.cs665.products from
public class BaseProduct implements Product {
    public boolean isKeepCold() {
        return keepCold;
    }


    public boolean isKeepWarm() {
        return keepWarm;
    }


    public ProductNames.names getProductType() {
        return productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private boolean keepCold;
    private boolean keepWarm;
    private ProductNames.names productType;
    private int quantity;

    public BaseProduct() {
    }

    public BaseProduct(boolean keepCold, boolean keepWarm, ProductNames.names productType, int quantity) {
        this.keepCold = keepCold;
        this.keepWarm = keepWarm;
        this.productType = productType;
        this.quantity = quantity;
    }

    //Return in the form af a cash register receipt.
    @Override
    public String toString() {
        return this.quantity + "x " + this.productType + (keepCold ? " *Cold Item":"") + (keepWarm ? " *Warm Item":"");
    }
}
