package edu.bu.met.cs665.simulator;

public interface NameGenerator {

    enum NameTypes {customer,
        pizza,
        chinese,
        flower,
        frozenDinner,
        desserts,
        southWestern,
        officeSupply,}

    static String[][] peopleNames= {{"Bob","Smith"},{"Jack","Parker"},{"Sophia","Johnson"},
            {"Jacob","Williams"},{"Emma", "Brown"}, {"Mason","Jones"},{"Emily","Miller"},{"Madison","Wilson"},
            {"Daniel","Anderson"},{"Ella","Garcia"},{"Joshua","Taylor"},{"Grace","Hernandez"},
            {"Andrew","Moore"},{"Zoey","Lee"},{"Ryan","Perez"}};

    static String [][] pizzaNames={{"Pizza","Pizza"},{"Luigi's","Parlor"},{"Hot Stone", "Oven"},
            {"Momma's", "Pizzeria"},{"Tony's","Pizza Express"}};
    static String[][] chineseNames={{"China","Wok"},{"PeKing","Garden"},{"Szechuan","Grill"},
            {"Asian","House"},{"Mandarin","Buffet"}};
    static String[][] flowerNames ={{"Rainbow","Bouquet"},{"Mom's","Garden"},{"Secret","Pedals"},
            {"Happy","Florist"},{"May","Flowers"}};
    static String[][] frozenDinnerNames={{"Easy","Dinners"}, {"Ready", "Meals"},{"Home","Chef"}};
    static String[][] dessertsNames= {{"The Ice Cream", "Shop"},{"Ma's Candy", "Store"},{"Frank's Sugar", "Parlor"}};
    static String[][] southWesternNames ={{"Paco's", "Tacos"}, {"Southwestern", "Grill"}, {"Burritos","Cocina"}};
    static String[][] officeSupplyNames = {{"Office", "Supply"}, {"Office", "Depot"}};

    String getName();
}
