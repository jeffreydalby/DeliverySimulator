package edu.bu.met.cs665.stores;
//not a true factory method pattern, just a utility to build out different types of stores;

import edu.bu.met.cs665.products.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//leaves potential to have specific type store builders vs just generic we have now.
public interface StoreBuilder {

    Store buildStore(String name, Point location, String streetName, List<StoreTypes.type> typeOfStores);

    //builds out menus, allowing for fusions of different menu types
    static List<Product> getMenuItems(List<StoreTypes.type> storeType) {
        //List to hold our potential menu
        List<Product> menuList = new ArrayList<>();

        //build a list of types of items the store carries based on store classification
        List<ProductClassification.type> typesOfMenuItems = new ArrayList<>();
        for (StoreTypes.type typeOfStore: storeType
             ) {
            typesOfMenuItems.add(ProductClassification.type.valueOf(typeOfStore.toString()));
        }
        //create the menu by filtering out just the types of items for this store
        //the use our factory to create the objects and add to the menu
        for (ProductClassification.type typeOfMenu : typesOfMenuItems
                ) {
            Stream.of(ProductNames.names.values())
                    .filter(name -> name.getType() == typeOfMenu)
                    .forEach(name -> menuList.add(ProductsFactory.createProduct(name, 0)));
        }
        //As part of the delivery program all stores have a gift box option for birthdays.
        menuList.add(ProductsFactory.createProduct(ProductNames.names.giftBox,1));

        return menuList;
    }

}
