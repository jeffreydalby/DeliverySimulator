package edu.bu.met.cs665.products;

//could be abstract to allow for future factories
//but this time we will create concrete directly
public class ProductsFactory {
    /**
     * Factory that allows us to return any type of product based on the name from the products enum
     *
     * @param name     - name of the product selected from the enum
     * @param quantity - quantity needed
     * @return - return the Product object that was created
     */
    public static Product createProduct(ProductNames.Names name, int quantity) {
        ColdFoodCreator coldFoodCreator = new ColdFoodCreator();
        WarmFoodCreator warmFoodCreator = new WarmFoodCreator();
        StandardProductCreator standardProductCreator = new StandardProductCreator();

        if (name.isKeepCold()) return coldFoodCreator.createProduct(name, quantity);
        else if (name.isKeepWarm()) return warmFoodCreator.createProduct(name, quantity);
        else if (name.getProductType() == ProductClassification.ProductType.reward) return new BirthDayBox();
        else return standardProductCreator.createProduct(name, quantity);
    }
}
