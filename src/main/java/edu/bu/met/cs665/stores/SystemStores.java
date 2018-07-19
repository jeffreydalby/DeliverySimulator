package edu.bu.met.cs665.stores;

import java.util.ArrayList;
import java.util.List;

//Uses Singleton Pattern because we only want one list of stores for the system

public class SystemStores {

    public List<Store> getStores() {
        return stores;
    }

    private static SystemStores storesInstance;
    private SystemStores(){}
    private List<Store> stores = new ArrayList<>();

    public void addStore(Store store){
        stores.add(store);
    }

    public static synchronized SystemStores getInstance(){
        if (storesInstance == null){
            storesInstance = new SystemStores();
        }
        return storesInstance;
    }

}
