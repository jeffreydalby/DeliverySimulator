package edu.bu.met.cs665.stores;

import edu.bu.met.cs665.stores.products.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//Lets us build any combination of store by passing in a list of types of menu/products offered
//and the name of the store. Allows us to create different factories later should we want to
public class GenericStoreBuilder {

    /**
     * Build a store and populate the menu based on the type of store
     *
     * @param name                -name of the store
     * @param location            -grid point of the store
     * @param address             -Store's address (based on grid point)
     * @param storeClassification -type of item(s) store carries
     * @return -store object with the proper menu already created
     */

    public Store buildStore(String name, Point location, String address, List<StoreTypes.Type> storeClassification) {

        return new Store(name, location, address, this.getMenuItems(storeClassification), storeClassification);
    }

    //builds out menus, allowing for fusions of different menu types
    private List<Product> getMenuItems(List<StoreTypes.Type> storeType) {
        //List to hold our potential menu
        List<Product> menuList = new ArrayList<>();


        //build a list of types of items the store carries based on store classification
        List<ProductClassification.ProductType> typesOfMenuItems = new ArrayList<>();
        for (StoreTypes.Type typeOfStore : storeType
        ) {
            typesOfMenuItems.add(ProductClassification.ProductType.valueOf(typeOfStore.toString()));
        }
        //create the menu by filtering out just the types of items for this store
        //the use our factory to create the objects and add to the menu
        for (ProductClassification.ProductType productTypeOfMenu : typesOfMenuItems
        ) {
            Stream.of(ProductNames.Names.values())
                    .filter(name -> name.getProductType() == productTypeOfMenu)
                    .forEach(name -> menuList.add(this.productFactoryMethod(name, 0)));
        }
        //As part of the delivery program all stores have a gift box option for birthdays.
        menuList.add(this.productFactoryMethod(ProductNames.Names.giftBox, 1));

        return menuList;
    }

    /**
     * Factory Method that allows us to return any type of product based on the name from the products enum
     *
     * @param name     - name of the product selected from the enum
     * @param quantity - quantity needed
     * @return - return the Product object that was created
     */
    private Product productFactoryMethod(ProductNames.Names name, int quantity) {

        if (name.isKeepCold()) return new ColdFood(name, quantity);
        else if (name.isKeepWarm()) return new WarmFood(name, quantity);
        else if (name.getProductType() == ProductClassification.ProductType.reward) return new BirthDayBox();
        else return new StandardProduct(name, quantity);
    }


}
