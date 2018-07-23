package edu.bu.met.cs665.simulator;

//Singleton..only one order simulator per instance

import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.customers.Customer;
import edu.bu.met.cs665.customers.SystemCustomers;
import edu.bu.met.cs665.deliverysystem.Delivery;
import edu.bu.met.cs665.deliverysystem.Dispatch;
import edu.bu.met.cs665.orders.Order;
import edu.bu.met.cs665.products.Product;
import edu.bu.met.cs665.products.ProductNames;
import edu.bu.met.cs665.stores.Store;
import edu.bu.met.cs665.stores.SystemStores;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OrderSimulator implements Runnable {

    public Thread getOrderSimulatorThread() {
        return this.orderSimulatorThread;
    }

    private static OrderSimulator orderSimulatorInstance;
    private Thread orderSimulatorThread;
    private OrderSimulator(){}
    private int milliSecondsBetweenOrders;
    private int numOrders;

    public boolean isCreatingOrders() {
        return creatingOrders;
    }

    private boolean creatingOrders;


    public static synchronized OrderSimulator getInstance(){
        if(orderSimulatorInstance == null) orderSimulatorInstance = new OrderSimulator();
        return orderSimulatorInstance;
    }

    void startSimulation(int numOrders, int millisecondsBetweenOrders){
        this.numOrders = numOrders;
        this.milliSecondsBetweenOrders = millisecondsBetweenOrders;
        this.orderSimulatorThread = new Thread(OrderSimulator.getInstance());
        this.orderSimulatorThread.start();

    }

    @Override
    public void run() {


        Random rnd = new Random();
        Dispatch systemDispatcher = Dispatch.getInstance();
        Delivery delivery;
        Customer customer;
        Store store;
        Order order;
        List<Product> orderItems;
        boolean isBirthday;


        //loop through the number of orders we want to create

        for (int i = 0; i < this.numOrders; i++) {
            creatingOrders = true;
            if(Thread.currentThread().isInterrupted()) break;

            //pick a random store
            store = SystemStores.getInstance().getStores().get(rnd.nextInt(SystemStores.getInstance().getStores().size()));
            //get the items we can order from the store
            //last item in the list is always gift box and we don't want people to be able to order that
            //so size -1;
            orderItems = store.getStockItems().subList(0,store.getStockItems().size()-1);
            //shuffle that list up so we can get some randomness;
            Collections.shuffle(orderItems);

            customer = SystemCustomers.getInstance().getCustomers().get(rnd.nextInt(SystemCustomers.getInstance().getCustomers().size()));

            order = new Order(customer,store);

            //randomly decide if it is the persons birthday 20% .
            isBirthday = rnd.nextInt(10) < 2;
            if(isBirthday) {
                order.addItem(ProductNames.Names.giftBox,1);
                customer.setBirthDay(true);}

            //add a random number of things to the order
            int numOrderItems = (rnd.nextInt(orderItems.size())+1);
            for (int j = 0; j < numOrderItems; j++) {
                //allow a random amount up to 5.
                order.addItem(orderItems.get(j).getProductName(),(rnd.nextInt(5)+1));
            }

            systemDispatcher.placeOrder(order);

            //We are going to sleep for the requested time between orders
            try {
                Thread.sleep(milliSecondsBetweenOrders);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Display.output("Order System exited");
            }

        }
        creatingOrders = false;
        Display.output("All orders created");



    }
}
