package products;

public class ColdFood extends BaseProduct {

    ColdFood(){
        super();
    }

    public ColdFood(ProductNames.names productType, int quantity) {
        super(false, true, productType, quantity);
    }
}
