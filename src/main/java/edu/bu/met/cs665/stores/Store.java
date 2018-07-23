package edu.bu.met.cs665.stores;

import edu.bu.met.cs665.products.BaseProduct;
import edu.bu.met.cs665.products.Product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> stockItems = new ArrayList<>();
    private Point location;
    private String address;

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

    private String name;
    private List<StoreTypes.Type> storeClassification;

    public Store(String name){
        this.name = name;
    }

    public Store(String name, Point location, String address, List<Product> stockItems, List<StoreTypes.Type> storeClassification){
        this.name = name;
        this.location = location;
        this.address = address;
        this.stockItems = stockItems;
        this.storeClassification = storeClassification;
    }

    public void addStockItem(BaseProduct productToAdd){
        stockItems.add(productToAdd);
    }

    String getMenu(){
        StringBuilder returnStringBuilder = new StringBuilder();
        returnStringBuilder.append("The items on our menu are:\n");
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
