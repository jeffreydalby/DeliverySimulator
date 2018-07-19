package edu.bu.met.cs665.customers;

import java.util.ArrayList;
import java.util.List;

//Singleton Pattern because we only want one instance of our Customer list
public class SystemCustomers {

    private static SystemCustomers customersInstance;

    public List<Customer> getCustomers() {
        return customers;
    }

    private List<Customer> customers = new ArrayList<>();

    private SystemCustomers() {}

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static synchronized SystemCustomers getInstance(){
        if (customersInstance == null){
            customersInstance = new SystemCustomers();
        }
        return customersInstance;
    }


}
