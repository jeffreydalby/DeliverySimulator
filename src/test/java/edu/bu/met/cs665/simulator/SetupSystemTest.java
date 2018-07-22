package edu.bu.met.cs665.simulator;

import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.customers.SystemCustomers;
import edu.bu.met.cs665.deliverysystem.DeliveryVehicle;
import edu.bu.met.cs665.deliverysystem.Dispatch;
import edu.bu.met.cs665.stores.SystemStores;
import org.junit.Test;

public class SetupSystemTest {

    @Test
    public void createSimulation() {
        SetupSystem testSystem = new SetupSystem();
        Dispatch dispatch = Dispatch.getInstance();
        testSystem.createSimulation(dispatch,10,10,30,100, 500);
        SystemStores systemStores = SystemStores.getInstance();
        SystemCustomers systemCustomers = SystemCustomers.getInstance();
        Display.output(systemStores.toString());
        Display.output(systemCustomers.toString());
        Display.output(dispatch.toString());

        //kill all the driver threads
        Display.output("Killing Driver Threads");
        for (DeliveryVehicle delivieryDriver:dispatch.getDriverMap().values()
             ) {
            delivieryDriver.getDriverThread().interrupt(); }
        //kill dispatch thread
        Display.output("Killing dispatch thread");
        dispatch.getDispatchThread().interrupt();


    }
}