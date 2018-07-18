package edu.bu.met.cs665.products;

import org.junit.Assert;
import org.junit.Test;

public class BaseProductTest {

    //validate ir returns the proper string
    @Test
    public void toStringTest() {
        String productString = (new BaseProduct(ProductNames.names.frozenDinner1.isKeepCold(), ProductNames.names.frozenDinner1.isKeepWarm(), ProductNames.names.frozenDinner1, 2)).toString();
        Assert.assertEquals("2x " +ProductNames.names.frozenDinner1 + " *Cold Item",productString);
    }
}