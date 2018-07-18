package edu.bu.met.cs665.products;

import org.junit.Assert;
import org.junit.Test;

public class WarmFoodCreatorTest {

    @Test
    public void createProduct() {
        WarmFoodCreator warmFoodCreator = new WarmFoodCreator();
        //makes sure our factory method creates the proper object
        String productString = (warmFoodCreator.CreateProduct(ProductNames.names.pepperoniPizza,2)).toString();
        Assert.assertEquals("2x " +ProductNames.names.pepperoniPizza + " *Warm Item",productString);

    }
}