package edu.bu.met.cs665.orders;

import edu.bu.met.cs665.customers.Customer;
import edu.bu.met.cs665.products.Product;
import edu.bu.met.cs665.products.ProductNames;
import edu.bu.met.cs665.products.ProductsFactory;
import edu.bu.met.cs665.stores.Store;

import java.util.ArrayList;
import java.util.List;

//orders made by customers
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

    private Customer customer; //customer that placed the order
    private boolean needsWarm; //does it need to be kept warm
    private static int currentOrderNumber; //order number counter spread across all orders
    private int orderNumber; //this orders order number
    private List<Product> orderItems = new ArrayList<>(); //items ordered
    private boolean needsCold; //does it need to be kept cold
    private Store store; //store that has the order items.

    /**
     * create the object from the factory and add to the orderItems list
     *
     * @param itemToAdd - what items is being orderd
     * @param quantity  - how many of the item
     */
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


        String prefix = "";
        for (Product product : orderItems
                ) {
            returnString.append(prefix);
            returnString.append(product);
            prefix = "\n";

        }
        return returnString.toString();
    }
}
