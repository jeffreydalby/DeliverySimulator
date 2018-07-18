package edu.bu.met.cs665.products;

public class ProductNames {
    //enum to avoid majic strings, in a real system we'd pull this from a database.

    public enum names {
        frozenDinner1("Frozen Dinner Special #1",true,false),
        frozenDinner2("Frozen Dinner Special #2",true,false),
        frozenDinner3("Frozen Dinner Special #2",true,false),
        frozenDinner4("Frozen Dinner Special #2",true,false),
        iceCreamCake("Ice Cream Cake",true,false),
        milkShake("Milk Shake",true,false),
        cake("Cake", false, false),
        chocolates("Box of Chocolates",false,false),
        roses("Bouguet of Roses",false,false),
        daisies("Bouquet of Daises",false,false),
        lilies("Bouquet of Lillies",false,false),
        pizza("Pizza",false,true),
        breadSticks("Bread Sticks",false,true),
        tacos("Tacos",false,true),
        fajitas("Fajitas",false,true),
        orangeChicken("Orange Chicken",false,true),
        beijingBeef("Beijin Beef",false,true),
        giftBox("Gift Box",false,false),
        paper("Case of Paper",false,false),
        toner("Toner Cartridge",false, false);

        private String nameToReturn;

        public boolean isKeepCold() {
            return keepCold;
        }

        public boolean isKeepWarm() {
            return keepWarm;
        }

        private boolean keepCold;
        private boolean keepWarm;

        names(String nameToReturn, boolean keepCold,boolean keepWarm){
            this.nameToReturn = nameToReturn;
            this.keepCold = keepCold;
            this.keepWarm = keepWarm;
        }

        @Override
        public String toString() {
            return this.nameToReturn;
        }
    }

}
