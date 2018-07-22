package edu.bu.met.cs665.orders;

import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.customers.Customer;
import edu.bu.met.cs665.products.ProductNames;
import edu.bu.met.cs665.stores.GenericStoreBuilder;
import edu.bu.met.cs665.stores.Store;
import edu.bu.met.cs665.stores.StoreTypes;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    @Test
    public void addItem() {
        GenericStoreBuilder genericStoreBuilder = new GenericStoreBuilder();
        List<StoreTypes.type> storeType = new ArrayList<>();
        storeType.add(StoreTypes.type.pizza);
        storeType.add(StoreTypes.type.southWestern);
        String name = "Bob's Pizza And Tacos";
        Store testStore = genericStoreBuilder.buildStore(name,new Point(10,234), "234 10th Ave.", storeType);

        Order testOrder = new Order(new Customer(new Point(100,1345),"Test Customer" ), testStore);
        testOrder.addItem(ProductNames.names.pepperoniPizza,1);
        testOrder.addItem(ProductNames.names.taco,3);
        Display.output(testOrder.toString());


    }
}