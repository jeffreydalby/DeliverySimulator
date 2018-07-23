package edu.bu.met.cs665.stores;
//not a true factory method pattern, just a utility to build out different types of stores;

import edu.bu.met.cs665.products.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//leaves potential to have specific ProductType store builders vs just generic we have now.
public interface StoreBuilder {

    Store buildStore(String name, Point location, String streetName, List<StoreTypes.Type> typeOfStores);

    //builds out menus, allowing for fusions of different menu types
    static List<Product> getMenuItems(List<StoreTypes.Type> storeType) {
        //List to hold our potential menu
        List<Product> menuList = new ArrayList<>();

        //build a list of types of items the store carries based on store classification
        List<ProductClassification.ProductType> typesOfMenuItems = new ArrayList<>();
        for (StoreTypes.Type typeOfStore: storeType
             ) {
            typesOfMenuItems.add(ProductClassification.ProductType.valueOf(typeOfStore.toString()));
        }
        //create the menu by filtering out just the types of items for this store
        //the use our factory to create the objects and add to the menu
        for (ProductClassification.ProductType productTypeOfMenu : typesOfMenuItems
                ) {
            Stream.of(ProductNames.Names.values())
                    .filter(name -> name.getProductType() == productTypeOfMenu)
                    .forEach(name -> menuList.add(ProductsFactory.createProduct(name, 0)));
        }
        //As part of the delivery program all stores have a gift box option for birthdays.
        menuList.add(ProductsFactory.createProduct(ProductNames.Names.giftBox,1));

        return menuList;
    }

}
