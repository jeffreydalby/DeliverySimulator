package products;

//generic class to hold warm food products
public class WarmFood extends BaseProduct {
    WarmFood(){
        super();
    }
    //create the product and set it to keepWarm
    public WarmFood(ProductNames.names productType, int quantity) {
        super(false, true, productType, quantity);
    }
}
