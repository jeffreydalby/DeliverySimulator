package edu.bu.met.cs665.stores;
//not a true factory method pattern, just a utility to build out different types of stores;

import edu.bu.met.cs665.products.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public interface StoreBuilder {

    Store buildStore(String name, List<ProductClassification.type> storeType);

    //builds out menus, allowing for fusions of different menu types
    static List<Product> getMenuItems(List<ProductClassification.type> typesOfMenuItems) {
        //List to hold our potential menu
        List<Product> menuList = new ArrayList<>();

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
