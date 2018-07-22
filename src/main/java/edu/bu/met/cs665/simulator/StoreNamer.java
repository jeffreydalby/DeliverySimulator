package edu.bu.met.cs665.simulator;

import edu.bu.met.cs665.stores.StoreTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreNamer {

    Map<StoreTypes.type,NameGenerator> behaviors = new HashMap<>();


    public StoreNamer(){
            behaviors.put(StoreTypes.type.pizza,new PizzaNameGenerator());
            behaviors.put(StoreTypes.type.chinese,new ChineseNameGenerator());
            behaviors.put(StoreTypes.type.flower, new FlowerNameGenerator());
            behaviors.put(StoreTypes.type.frozenDinner, new FrozenDinnerNameGenerator());
            behaviors.put(StoreTypes.type.desserts, new DessertsNameGenerator());
            behaviors.put(StoreTypes.type.southWestern, new SouthWesternNamesGenerator());
            behaviors.put(StoreTypes.type.officeSupply, new OfficeSupplyNameGenerator());
    }

    //we use a list to allow for hybrid stores
    public String getRandomName(List<StoreTypes.type> generatorTypes){
        NameGenerator generator;
        StringBuilder randomName = new StringBuilder();
        String myPrefix = ""; //standard means to not have extra character at the end
        for (StoreTypes.type generatorType:generatorTypes
             ) {
            generator = behaviors.get(generatorType);
            randomName.append(myPrefix);
            myPrefix = " & ";
            randomName.append(generator.getName());
        }
        return randomName.toString();

    }
}
