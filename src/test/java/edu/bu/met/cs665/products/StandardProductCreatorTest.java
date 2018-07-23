package edu.bu.met.cs665.products;

import org.junit.Assert;
import org.junit.Test;

public class StandardProductCreatorTest {

    @Test
    public void createProduct() {
        StandardProductCreator standardProductCreator = new StandardProductCreator();
        //makes sure our factory method creates the proper object
        String productString = (standardProductCreator.createProduct(ProductNames.Names.chocolates,1)).toString();
        Assert.assertEquals("1x " +ProductNames.Names.chocolates,productString);
    }
}