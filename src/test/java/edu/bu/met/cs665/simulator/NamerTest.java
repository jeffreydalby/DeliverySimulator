package edu.bu.met.cs665.simulator;

import edu.bu.met.cs665.stores.StoreTypes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NamerTest {

    @Test
    public void getRandomName() {
        StoreNamer namer = new StoreNamer();
        List<StoreTypes.type> storeItemTypes = new ArrayList<>();
        storeItemTypes.add(StoreTypes.type.pizza);
        storeItemTypes.add(StoreTypes.type.southWestern);
        System.out.println(namer.getRandomName(storeItemTypes));

    }
}