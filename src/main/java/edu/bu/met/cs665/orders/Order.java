package edu.bu.met.cs665.orders;

import edu.bu.met.cs665.customers.Customer;
import edu.bu.met.cs665.products.Product;
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
        Order.currentOrderNumber++;
        this.orderNumber = currentOrderNumber;
    }

    public List<Product> getOrderItems() {
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
    private List<Product> orderItems = new ArrayList<>();
    private boolean needsCold;
    private Store store;


    //create the object from teh factory and add to the orderItems list
    public void addItem(ProductNames.Names itemToAdd, int quantity) {

        needsCold = itemToAdd.isKeepCold();
        needsWarm = itemToAdd.isKeepWarm();
        orderItems.add(ProductsFactory.createProduct(itemToAdd, quantity));
    }

    @Override
    public String toString() {
        //build out our list of order entries
        StringBuilder returnString = new StringBuilder();
        returnString.append("Ordered from: ");
        returnString.append(this.store.getName());
        returnString.append("\n");
        returnString.append("Located at: ");
        returnString.append(this.store.getAddress());
        returnString.append("\n");
        returnString.append("Customer Name: ");
        returnString.append(this.customer.getCustomerName());
        returnString.append("\n");
        returnString.append("Customer Address: ");
        returnString.append(this.customer.getAddress());
        returnString.append("\n");
        returnString.append("Customer's Birthday Today: ");
        returnString.append(this.customer.isBirthDay());
        returnString.append("\n");


        String prefix="";
        for (Product product : orderItems
                ) {
            returnString.append(prefix);
            returnString.append(product);
            prefix ="\n";

        }
        return returnString.toString();
    }
}
