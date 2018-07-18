package edu.bu.met.cs665.stores;

import edu.bu.met.cs665.products.BaseProduct;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<BaseProduct> stockItems = new ArrayList<>();
    private Point location;
    private String address;
    private String name;

    public Store(String name){
        this.name = name;
    }

    public void addStockItem(BaseProduct productToAdd){
        stockItems.add(productToAdd);
    }

}
