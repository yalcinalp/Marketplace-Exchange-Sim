import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;

public class Common {
    private static final String title = "Economics 101";
    private static final int windowWidth = 1650;
    private static final int windowHeight = 1000;

    private static final int entityRadius = 20; // radius of drawn entity (customer or store)

    private static final int storeNo = 10; // number of stores in the simulation
    private static final int customerNo = 10; // number of customers

    private static final int stockReplenishmentFrequency = 3000; // number of stepAllEntities calls before replenishing
                                                                 // all stores

    private static final int foodBottomPrice = 20;
    private static final int foodCeilingPrice = 50;
    private static final int electronicsBottomPrice = 200;
    private static final int electronicsCeilingPrice = 2000;
    private static final int LuxuryBottomPrice = 5000;
    private static final int LuxuryCeilingPrice = 10000;

    private static final int minimumShoppingListLength = 5;
    private static final int maximumShoppingListLength = 10;

    private static final int stockStorageMin = 15; // minimum size of storage available for a store
    private static final int stockStorageMax = 25; // maximum number of storage available for a store

    private static final int customerMovementSpeed = 2;
    private static final Font font = new Font("Verdana", Font.BOLD, 20);

    public static String getTitle() {
        return title;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static int getEntityRadius() {
        return entityRadius;
    }

    public static int getStoreNo() {
        return storeNo;
    }

    public static int getCustomerNo() {
        return customerNo;
    }

    public static int getStoreReplenishmentFrequency() {
        return stockReplenishmentFrequency;
    }

    public static Font getFont() {
        return font;
    }

    public static int getCustomerMovementSpeed() {
        return customerMovementSpeed;
    }

    private static final List<Store> storeList = new ArrayList<>();
    private static final List<Customer> customerList = new ArrayList<>();

    public static List<Store> getStoreList() {
        return storeList;
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static Product chooseProduct() {
        switch (randInt(0, 2)) {
            case 0:
                return new FoodProduct();
            case 1:
                return new ElectronicsProduct();
            default:
                return new LuxuryProduct();
        }
    }

    public static Customer createCustomer() {
        double x = randInt(0, windowWidth - 2 * entityRadius);
        double y = randInt(0, windowHeight - 2 * entityRadius);
        Strategy strategy;
        switch (randInt(0, 2)) {
            case 0:
                strategy = new CheapestStrategy();
                break;
            case 1:
                strategy = new ClosestStrategy();
                break;
            default:
                strategy = new TravellingStrategy();
        }
        ArrayList<Product> shoppingList = new ArrayList<Product>();
        int shoppingListLength =  randInt(minimumShoppingListLength, maximumShoppingListLength);
        for (int i = 0; i < shoppingListLength; i++) {
            shoppingList.add(chooseProduct());
        }

        return new Customer(x, y, strategy, shoppingList);

    }

    private static int currentStoreType = 0;

    public static Store createStore() {
        double x = randInt(0, windowWidth - 2 * entityRadius);
        double y = randInt(0, windowHeight - 2 * entityRadius);
        int maxStock = randInt(stockStorageMin, stockStorageMax);
        int price;
        Product type;
        switch (currentStoreType) {
            case 0:
                type = new FoodProduct();
                price = randInt(foodBottomPrice, foodCeilingPrice);
                break;
            case 1:
                type = new ElectronicsProduct();
                price = randInt(electronicsBottomPrice, electronicsCeilingPrice);
                break;
            default:
                type = new LuxuryProduct();
                price = randInt(LuxuryBottomPrice, LuxuryCeilingPrice);
                break;

        }
        currentStoreType = (currentStoreType + 1) % 3;
        return new Store(x, y, type, price, maxStock);
    }

    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    static {
        for (int i = 0; i < storeNo; i++) {
            storeList.add(createStore());
        }

        for (int i = 0; i < customerNo; i++) {
            customerList.add(createCustomer());
        }
    }

}
