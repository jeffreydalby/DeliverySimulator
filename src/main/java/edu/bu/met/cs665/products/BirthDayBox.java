package edu.bu.met.cs665.products;

//could use an abstract factory here but it's just the one item and not needed
public class BirthDayBox implements Product{

    private boolean keepCold;
    private boolean keepWarm;
    private ProductNames.names productType;
    private int quantity;
    private BaseProduct boxOfChocolates;
    private BaseProduct flowerBouquet;

    public BirthDayBox(){
        this.boxOfChocolates =  ProductsFactory.createProduct(ProductNames.names.chocolates,1);
        this.flowerBouquet = ProductsFactory.createProduct(ProductNames.names.roses,1);
        this.quantity = 1;
        this.keepWarm = false;
        this.keepCold = false;
        this.productType = ProductNames.names.giftBox;
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
    public ProductNames.names getProductType() {
        return productType;
    }
}
