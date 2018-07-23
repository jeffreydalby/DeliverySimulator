package edu.bu.met.cs665.products;

//could use an abstract factory here but it's just the one item and not needed
public class BirthDayBox implements Product{

    private boolean keepCold;
    private boolean keepWarm;
    private ProductNames.Names productType;
    private int quantity;
    private Product boxOfChocolates;
    private Product flowerBouquet;

    BirthDayBox(){
        this.boxOfChocolates =  ProductsFactory.createProduct(ProductNames.Names.chocolates,1);
        this.flowerBouquet = ProductsFactory.createProduct(ProductNames.Names.roses,1);
        this.quantity = 1;
        this.keepWarm = false;
        this.keepCold = false;
        this.productType = ProductNames.Names.giftBox;
    }


    @Override
    public boolean isKeepCold() {
        return keepCold;
    }

    @Override
    public boolean isKeepWarm() {
        return keepWarm;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public ProductNames.Names getProductName() {
        return productType;
    }

    @Override
    public String toString() {
        return "1x Gift box containing:\n"
                +"     " + this.boxOfChocolates.toString() + "\n"
                + "     " + this.flowerBouquet;
    }
}
