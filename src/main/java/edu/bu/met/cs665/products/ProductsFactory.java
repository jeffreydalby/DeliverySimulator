package edu.bu.met.cs665.products;

//could be abstract to all for future factories
//but this time we will create concrete directly
public class ProductsFactory {

    public static Product createProduct(ProductNames.Names name, int quantity){
        ColdFoodCreator coldFoodCreator = new ColdFoodCreator();
        WarmFoodCreator warmFoodCreator = new WarmFoodCreator();
        StandardProductCreator standardProductCreator = new StandardProductCreator();

        if (name.isKeepCold()) return coldFoodCreator.createProduct(name,quantity);
        else if (name.isKeepWarm()) return warmFoodCreator.createProduct(name,quantity);
        else if(name.getProductType() == ProductClassification.ProductType.reward) return new BirthDayBox();
        else return standardProductCreator.createProduct(name,quantity);
    }
}
