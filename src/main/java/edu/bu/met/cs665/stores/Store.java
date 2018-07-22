package edu.bu.met.cs665.stores;

import edu.bu.met.cs665.products.BaseProduct;
import edu.bu.met.cs665.products.Product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> stockItems = new ArrayList<Product>();
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
    private List<StoreTypes.type> storeClassification;

    public Store(String name){
        this.name = name;
    }

    public Store(String name, Point location, String address, List<Product> stockItems, List<StoreTypes.type> storeClassification){
        this.name = name;
        this.location = location;
        this.address = address;
        this.stockItems = stockItems;
        this.storeClassification = storeClassification;
    }

    public void addStockItem(BaseProduct productToAdd){
        stockItems.add(productToAdd);
    }

    public String getMenu(){
        StringBuilder returnStringBuilder = new StringBuilder();
        returnStringBuilder.append("The items on our menu are:\n");
        stockItems.forEach(item->returnStringBuilder.append(item.getProductType() + "\n"));
        return returnStringBuilder.toString();
    }

    @Override
    public String toString() {
        String storeTypes = "";
        for (StoreTypes.type storeType:storeClassification
             ) {
            storeTypes+=storeType.toString() + " ";
        }

        String returnString = "Store{name='" + name + '\'' +
                ", location=" + location +
                ", address='" + address + '\'' +
                ", store types= " + storeTypes +
                '}';

        return returnString;
    }
}
