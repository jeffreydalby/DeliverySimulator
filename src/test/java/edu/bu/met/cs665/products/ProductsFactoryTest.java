package edu.bu.met.cs665.products;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductsFactoryTest {

    @Test
    public void createProduct() {
        String productString = (ProductsFactory.createProduct(ProductNames.names.pizza,2)).toString();
        Assert.assertEquals("2x " +ProductNames.names.pizza + " *Warm Item",productString);
    }
}