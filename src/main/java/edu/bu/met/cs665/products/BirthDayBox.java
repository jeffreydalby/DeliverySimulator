package edu.bu.met.cs665.products;

//could use an abstract factory here but it's just the one item and not needed
public class BirthDayBox implements Product {



    //public getters and setters
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


    private boolean keepCold; //does it need to be cold
    private boolean keepWarm; //does it need to be warm
    private ProductNames.Names productType; //what type of product is this
    private int quantity; //how many are needed
    private Product boxOfChocolates;  //gift boxes contain a box of chocolate
    private Product flowerBouquet; //gift boxes contain flowers

    /**
     * create a new birthday box
     */
    BirthDayBox() {
        this.boxOfChocolates = ProductsFactory.createProduct(ProductNames.Names.chocolates, 1);
        this.flowerBouquet = ProductsFactory.createProduct(ProductNames.Names.roses, 1);
        this.quantity = 1;
        this.keepWarm = false;
        this.keepCold = false;
        this.productType = ProductNames.Names.giftBox;
    }

    //string to output the box and its contents
    @Override
    public String toString() {
        return "1x Gift box containing:\n"
                + "     " + this.boxOfChocolates.toString() + "\n"
                + "     " + this.flowerBouquet;
    }
}
