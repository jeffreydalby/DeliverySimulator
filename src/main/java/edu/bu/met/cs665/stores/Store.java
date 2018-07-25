package edu.bu.met.cs665.stores;

import edu.bu.met.cs665.products.BaseProduct;
import edu.bu.met.cs665.products.Product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Store objects for the system
public class Store {


    //public getters
    public List<Product> getStockItems() {
        return stockItems;
    }

    public Point getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Store(String name){
        this.name = name;
    }

    private List<Product> stockItems = new ArrayList<>(); //items the store carries
    private Point location; //grid point of the store
    private String address; //Store's address (based on grid point)
    private String name; //name of the store
    private List<StoreTypes.Type> storeClassification; //type of item(s) store carries

    //TODO Another constructor with too many parameters it seems...consider builder.

    /**
     * Store constructor
     * @param name -name of the store
     * @param location -grid point of the store
     * @param address -Store's address (based on grid point)
     * @param stockItems -items the store carries
     * @param storeClassification -type of item(s) store carries
     */
    public Store(String name, Point location, String address, List<Product> stockItems, List<StoreTypes.Type> storeClassification){
        this.name = name;
        this.location = location;
        this.address = address;
        this.stockItems = stockItems;
        this.storeClassification = storeClassification;
    }

    /**
     * Add item to stock
     * @param productToAdd - Product to add to inventory
     */
    public void addStockItem(BaseProduct productToAdd){
        stockItems.add(productToAdd);
    }

    /**
     * Builds out a menu/inventory list
     * @return - String containing the list of items the store carries
     */

    String getMenu(){
        StringBuilder returnStringBuilder = new StringBuilder();
        returnStringBuilder.append("The items on our menu are:\n");
        //we are okay with the string concatination inside the lambda because it is easier to read.
        stockItems.forEach(item->returnStringBuilder.append(item.getProductName() + "\n"));
        return returnStringBuilder.toString().substring(0,returnStringBuilder.length()-1);
    }

    @Override
    public String toString() {
        StringBuilder storeTypes = new StringBuilder();
        String prefix ="";
        for (StoreTypes.Type storeType:storeClassification
             ) {
            storeTypes.append(prefix);
            storeTypes.append(storeType.toString());
            prefix = ",";
        }

        return "Name='" + name + '\'' +
                "\nAddress='" + address + '\'' +
                "\nStore item Types= " + storeTypes.toString() +
                "\n" + this.getMenu();
    }
}
