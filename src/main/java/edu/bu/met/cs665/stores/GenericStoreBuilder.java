package edu.bu.met.cs665.stores;

import java.awt.*;
import java.util.List;

//Lets us build any combination of store by passing in a list of types of menu/products offered
//and the name of the store.
public class GenericStoreBuilder implements StoreBuilder{
    @Override
    public Store buildStore(String name, Point location, String streetName, List<StoreTypes.type> typeOfStore) {

        Store newStore = new Store(name, location,streetName,StoreBuilder.getMenuItems(typeOfStore),typeOfStore) ;
        return newStore;
    }


}
