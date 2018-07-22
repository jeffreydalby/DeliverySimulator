package edu.bu.met.cs665.stores;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GenericStoreBuilderTest {

    @Test
    public void buildStore() {
        GenericStoreBuilder genericStoreBuilder = new GenericStoreBuilder();
        List<StoreTypes.type> storeType = new ArrayList<>();
        storeType.add(StoreTypes.type.pizza);
        storeType.add(StoreTypes.type.southWestern);
        String name = "Bob's Pizza And Tacos";
        Store testStore = genericStoreBuilder.buildStore(name,new Point(10,234), "234 10th Ave.", storeType);
        System.out.println(testStore);
        System.out.println(testStore.getMenu());
        Assert.assertEquals(name,testStore.getName());
    }
}