package edu.bu.met.cs665.stores;

import java.awt.*;
import java.util.List;

//Lets us build any combination of store by passing in a list of types of menu/products offered
//and the name of the store. Allows us to create different factories later should we want to
public class GenericStoreBuilder implements StoreBuilder {

    /**
     * Build a store and populate the menu based on the type of store
     *
     * @param name                -name of the store
     * @param location            -grid point of the store
     * @param address             -Store's address (based on grid point)
     * @param storeClassification -type of item(s) store carries
     * @return -store object with the proper menu already created
     */
    @Override
    public Store buildStore(String name, Point location, String address, List<StoreTypes.Type> storeClassification) {

        return new Store(name, location, address, StoreBuilder.getMenuItems(storeClassification), storeClassification);
    }


}
