package edu.bu.met.cs665.stores;

import edu.bu.met.cs665.products.ProductClassification;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericStoreBuilderTest {

    @Test
    public void buildStore() {
        GenericStoreBuilder genericStoreBuilder = new GenericStoreBuilder();
        List<ProductClassification.type> storeType = new ArrayList<>();
        storeType.add(ProductClassification.type.pizza);
        storeType.add(ProductClassification.type.southWestern);
        String name = "Bob's Pizza And Tacos";
        Store testStore = genericStoreBuilder.buildStore(name,storeType);
        System.out.println(testStore);
        System.out.println(testStore.getMenu());
        Assert.assertEquals(name,testStore.getName());
    }
}