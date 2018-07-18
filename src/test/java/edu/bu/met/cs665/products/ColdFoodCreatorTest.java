package edu.bu.met.cs665.products;

import org.junit.Assert;
import org.junit.Test;

public class ColdFoodCreatorTest {

    @Test
    public void createProduct() {
        ColdFoodCreator coldFoodCreator = new ColdFoodCreator();
        String productString = (coldFoodCreator.CreateProduct(ProductNames.names.frozenDinner1,2)).toString();
        Assert.assertEquals("2x " +ProductNames.names.frozenDinner1 + " *Cold Item",productString);

    }
}