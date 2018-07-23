package edu.bu.met.cs665.simulator;

import edu.bu.met.cs665.ClockTicker;
import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.customers.Customer;
import edu.bu.met.cs665.customers.SystemCustomers;
import edu.bu.met.cs665.deliverysystem.DeliveryDriver;
import edu.bu.met.cs665.deliverysystem.DeliveryVehicle;
import edu.bu.met.cs665.deliverysystem.Dispatch;
import edu.bu.met.cs665.geography.Address;
import edu.bu.met.cs665.stores.GenericStoreBuilder;
import edu.bu.met.cs665.stores.Store;
import edu.bu.met.cs665.stores.StoreTypes;
import edu.bu.met.cs665.stores.SystemStores;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SetupSystem {

    public void createSimulation(int numStores, int numDrivers, int numCustomers, int numOrders, int milliSecondsBetweenOrders) {

        //first create the stores
        createSimulationStores(numStores);
        //create the customers
        createCustomers(numCustomers);

        //create the drivers
        createDrivers(numDrivers);
        //launch order simulator in separate thread once we are all set up the orders
        startOrderSimulator(numOrders, milliSecondsBetweenOrders);

    }

    private void startOrderSimulator(int numOrders, int milliSecondsBetweenOrders) {
        OrderSimulator simulatorInstance = OrderSimulator.getInstance();
        simulatorInstance.startSimulation(numOrders,milliSecondsBetweenOrders);
    }

    public static void stopSimulation(){
        Dispatch dispatchInstance = Dispatch.getInstance();
        ClockTicker clockTickerInstance = ClockTicker.getClockTickerInstance();

        Display.output("No More work to do, shutting down.");
        Display.output("Sending Drivers Home");
        for (DeliveryVehicle delivieryDriver:Dispatch.getDriverMap().values()
             ) {delivieryDriver.getDriverThread().interrupt(); }
        Display.output("Stopping dispatcher");
        dispatchInstance.getDispatchThread().interrupt();
        Display.output("Stopping Clock");
        clockTickerInstance.getClockTickerThread().interrupt();
        Display.output("Simulation Complete"
                + "\nElapsed Time =" + clockTickerInstance.getSystemClock()
                + "\nShutting Down");

    }

    private void createDrivers(int numDrivers) {
        DeliveryDriver deliveryDriver;
        PeopleNameGenerator peopleNameGenerator = new PeopleNameGenerator();
        Random rnd = new Random();
        //30% chance car has cooler most have heaters
        boolean hasCooler = rnd.nextInt(10) < 3;
        boolean hasHeater = rnd.nextInt(10) < 8;

        //we always create one driver with a heater and a cooler to make sure we have that
        deliveryDriver = new DeliveryDriver(peopleNameGenerator.getName(),Address.getRandomGridPoint(),true,true);
        //start him up
        deliveryDriver.startDriver();
        //Display.output("Created new driver: " +deliveryDriver );

        for (int i = 0; i < numDrivers -1 ; i++) {
            deliveryDriver = new DeliveryDriver(peopleNameGenerator.getName(),Address.getRandomGridPoint(),hasHeater,hasCooler);
            //start our driver going and register them
            deliveryDriver.startDriver();
            //get new randoms for next time around
            hasCooler = rnd.nextInt(10) < 3;
            hasHeater = rnd.nextInt(10) < 3;
            Display.output("Created new driver: \n" + deliveryDriver.toString());
        }
    }

    private void createCustomers(int numCustomers) {
        PeopleNameGenerator peopleNameGenerator = new PeopleNameGenerator();
        SystemCustomers systemCustomers = SystemCustomers.getInstance();
        for (int i = 0; i < numCustomers; i++) {
            systemCustomers.addCustomer(new Customer(Address.getRandomGridPoint(), peopleNameGenerator.getName()));
        }

    }

    private void createSimulationStores(int numStores) {
        SystemStores systemStores = SystemStores.getInstance();
        boolean hybridStore;
        Random rnd = new Random();
        StoreNamer storeNamer = new StoreNamer();
        GenericStoreBuilder genericStoreBuilder = new GenericStoreBuilder();
        List<StoreTypes.Type> storeType; //holder for ProductType fo store to build
        Point storeLocation;
        Store newStore;


        for (int i = 0; i < numStores; i++) {
            //give an 90 percent change of not hybrid (only when random int = 0)
            storeType = getStoreType(rnd.nextInt(10) < 1);
            storeLocation = Address.getRandomGridPoint();
            newStore = genericStoreBuilder.buildStore(storeNamer.getRandomName(storeType), storeLocation, Address.getAddress(storeLocation), storeType);
            systemStores.addStore(newStore);
            Display.output("Created new store: \n" + newStore);
        }

    }

    private List<StoreTypes.Type> getStoreType(boolean hybridStore) {
        //get our enums as a list of values
        List<StoreTypes.Type> storeTypes = Arrays.asList(StoreTypes.Type.values());
        //shuffle the list.
        Collections.shuffle(storeTypes);
        //return first element if not hybrid otherwise return top two elements
        //note there is NO LOGIC involved so you could end up with a taco/office supply store...which is funny.
        return storeTypes.subList(0, hybridStore ? 2 : 1);

    }


}
