package edu.bu.met.cs665.products;

public class ProductNames {
    //enum to avoid majic strings, in a real system we'd pull this from a database.
    //Adding a new item here will propegate it through the whole system, meaning
    //and a new type of pizza and it will become a possible menu item for any pizza shop/order
    public enum names {
        frozenDinner1("Frozen Dinner Special #1",true,false,ProductClassification.type.frozenDinner),
        frozenDinner2("Frozen Dinner Special #2",true,false,ProductClassification.type.frozenDinner),
        frozenDinner3("Frozen Dinner Special #3",true,false,ProductClassification.type.frozenDinner),
        frozenDinner4("Frozen Dinner Special #4",true,false,ProductClassification.type.frozenDinner),
        iceCreamCake("Ice Cream Cake",true,false,ProductClassification.type.desserts),
        milkShake("Milk Shake",true,false,ProductClassification.type.desserts),
        cake("Cake", false, false,ProductClassification.type.desserts),
        chocolates("Box of Chocolates",false,false,ProductClassification.type.desserts),
        roses("Bouguet of Roses",false,false,ProductClassification.type.flower),
        daisies("Bouquet of Daises",false,false,ProductClassification.type.flower),
        lilies("Bouquet of Lillies",false,false,ProductClassification.type.flower),
        pepperoniPizza("Pepperoni Pizza",false,true,ProductClassification.type.pizza),
        cheesePizza("Cheese Pizza",false,true,ProductClassification.type.pizza),
        supremePizza("Supreme Pizza",false,true,ProductClassification.type.pizza),
        sausagePizza("Sausage Pizza",false,true,ProductClassification.type.pizza),
        breadSticks("Bread Sticks",false,true,ProductClassification.type.pizza),
        taco("Taco",false,true,ProductClassification.type.southWestern),
        fajitas("Fajitas",false,true,ProductClassification.type.southWestern),
        burrito("Burrito",false,true,ProductClassification.type.southWestern),
        nachos("Nachos",false,true,ProductClassification.type.southWestern),
        orangeChicken("Orange Chicken",false,true,ProductClassification.type.chinese),
        beijingBeef("Beijing Beef",false,true,ProductClassification.type.chinese),
        friedRice("Fried Rice",false,true,ProductClassification.type.chinese),
        eggRoll("Egg Roll",false,true,ProductClassification.type.chinese),
        giftBox("Gift Box",false,false,ProductClassification.type.reward),
        paper("Case of Paper",false,false,ProductClassification.type.officeSupply),
        toner("Toner Cartridge",false, false,ProductClassification.type.officeSupply),
        pencils("Pencils",false, false,ProductClassification.type.officeSupply),
        pens("Pens Cartridge",false, false,ProductClassification.type.officeSupply);

        private String nameToReturn;

        public boolean isKeepCold() {
            return keepCold;
        }

        public boolean isKeepWarm() {
            return keepWarm;
        }
        public ProductClassification.type getType() {
            return type;
        }

        private boolean keepCold;
        private boolean keepWarm;
        private ProductClassification.type type;

        names(String nameToReturn, boolean keepCold,boolean keepWarm, ProductClassification.type type){
            this.nameToReturn = nameToReturn;
            this.keepCold = keepCold;
            this.keepWarm = keepWarm;
            this.type = type;
        }

        @Override
        public String toString() {
            return this.nameToReturn;
        }
    }

}
