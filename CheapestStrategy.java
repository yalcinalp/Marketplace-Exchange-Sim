import java.util.List;

public class CheapestStrategy extends Strategy {
    @Override
    public String getStrategyType() {
        return "Ch";
    }

    @Override
    public void setNextTarget(Customer c, List<Store> storeList) {
        int cheapest = Integer.MAX_VALUE;
        for (Store s : storeList) {
            String t = c.getShoppingList().get(0).getProductType();
            try {
                int p = s.getPrice(t);
                if (p < cheapest) {
                    cheapest = p;
                    target = s;
                }
            } catch (IllegalStateException e) {

            }
        }
    }

    @Override
    public void tryToBuy(Customer c) {
        try {
            target.sell(c.getShoppingList().get(0).getProductType());
            c.getShoppingList().remove(0);
            target = null;
        } catch (IllegalStateException e) {
        }
    }

}
