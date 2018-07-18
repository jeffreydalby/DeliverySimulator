package edu.bu.met.cs665.products;

//could be abstract to all for future factories
//but this time we will create concrete directly
public class ProductsFactory {

    public static BaseProduct createProduct(ProductNames.names name, int quantity){
        ColdFoodCreator coldFoodCreator = new ColdFoodCreator();
        WarmFoodCreator warmFoodCreator = new WarmFoodCreator();
        StandardProductCreator standardProductCreator = new StandardProductCreator();

        if (name.isKeepCold()) return coldFoodCreator.CreateProduct(name,quantity);
        else if (name.isKeepWarm()) return warmFoodCreator.CreateProduct(name,quantity);
        else return standardProductCreator.CreateProduct(name,quantity);
    }
}
