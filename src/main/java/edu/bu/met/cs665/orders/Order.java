package edu.bu.met.cs665.orders;

import edu.bu.met.cs665.customers.Customer;
import edu.bu.met.cs665.products.BaseProduct;
import edu.bu.met.cs665.products.ProductNames;
import edu.bu.met.cs665.products.ProductsFactory;
import edu.bu.met.cs665.stores.Store;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public boolean isNeedsCold() {
        return needsCold;
    }

    public boolean isNeedsWarm() {
        return needsWarm;
    }


    public Customer getCustomer() {
        return customer;
    }


    public Order(Customer customer, Store store) {
        this.customer = customer;
        this.store = store;
        currentOrderNumber++;
        this.orderNumber = currentOrderNumber;
    }

    public List<BaseProduct> getOrderItems() {
        return orderItems;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Store getStore() {
        return store;
    }

    private Customer customer;
    private boolean needsWarm;
    private static int currentOrderNumber;
    private int orderNumber;
    private List<BaseProduct> orderItems = new ArrayList<>();
    private boolean needsCold;
    private Store store;


    //create the object from teh factory and add to the orderItems list
    public void addItem(ProductNames.names itemToAdd, int quantity) {

        needsCold = itemToAdd.isKeepCold();
        needsWarm = itemToAdd.isKeepWarm();
        orderItems.add(ProductsFactory.createProduct(itemToAdd, quantity));
    }

    @Override
    public String toString() {
        //build out our list of order entries
        StringBuilder returnString = new StringBuilder();
        returnString.append("Ordered from: " + this.store.getName()+"\n");
        returnString.append("Located at: " + this.store.getAddress() +"\n");
        returnString.append("Customer Name: " + this.customer.getCustomerName() +"\n");
        returnString.append("Customer Address: " + this.customer.getAddress() +"\n");

        returnString.append("");

        String prefix="";
        for (BaseProduct product : orderItems
                ) {
            returnString.append(prefix + product);
            prefix ="\n";

        }
        return returnString.toString();
    }
}
