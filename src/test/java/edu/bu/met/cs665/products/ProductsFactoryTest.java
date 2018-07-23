package edu.bu.met.cs665.products;

import org.junit.Assert;
import org.junit.Test;

public class ProductsFactoryTest {

    @Test
    public void createProduct() {
        String productString = (ProductsFactory.createProduct(ProductNames.Names.pepperoniPizza,2)).toString();
        Assert.assertEquals("2x " +ProductNames.Names.pepperoniPizza + " *Warm Item",productString);
    }
}