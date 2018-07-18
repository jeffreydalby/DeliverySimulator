package edu.bu.met.cs665.orders;

import edu.bu.met.cs665.customers.Customer;
import edu.bu.met.cs665.products.BaseProduct;
import edu.bu.met.cs665.products.ProductNames;
import edu.bu.met.cs665.products.ProductsFactory;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<BaseProduct> orderItems = new ArrayList<>();
    private boolean needsCold;
    private boolean needsWarm;
    private static int orderNumber;
    private Customer customer;

    public Order(Customer customer){
        customer = customer;
        orderNumber++;
    }

    //create the object from teh factory and add to the orderItems list
    public void addItem(ProductNames.names itemToAdd, int quantity){

            needsCold = itemToAdd.isKeepCold();
            needsWarm = itemToAdd.isKeepWarm();
            orderItems.add(ProductsFactory.createProduct(itemToAdd,quantity));
    }

    @Override
    public String toString() {
        //build out our list of order entries
        StringBuilder returnString = new StringBuilder();

        returnString.append("");
        for (BaseProduct product:orderItems
             ) {
            returnString.append(product +"\n");

        }
        return returnString.toString();
    }
}
