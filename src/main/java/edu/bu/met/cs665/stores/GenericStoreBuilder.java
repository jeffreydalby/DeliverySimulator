package edu.bu.met.cs665.stores;

import edu.bu.met.cs665.geography.Address;
import edu.bu.met.cs665.products.ProductClassification;

import java.awt.*;
import java.util.List;

//Lets us build any combination of store by passing in a list of types of menu/products offered
//and the name of the store.
public class GenericStoreBuilder implements StoreBuilder{
    @Override
    public Store buildStore(String name, List<ProductClassification.type> typeOfStore) {
        Point randomLocation = Address.getRandomGridPoint();
        String streetName = Address.getAddress(randomLocation);
        Store newStore = new Store(name, randomLocation,streetName,StoreBuilder.getMenuItems(typeOfStore)) ;
        return newStore;
    }
}
