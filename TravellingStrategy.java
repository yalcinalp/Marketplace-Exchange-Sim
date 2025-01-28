import java.util.ArrayList;
import java.util.List;

public class TravellingStrategy extends Strategy {
    private ArrayList<Store> visited;

    public TravellingStrategy() {
        super();
        visited = new ArrayList<Store>();
    }

    @Override
    public String getStrategyType() {
        return "Tr";
    }

    @Override
    public void setNextTarget(Customer c, List<Store> storeList) {
        double closest = Double.POSITIVE_INFINITY;
        for (Store s : storeList) {
            if (!visited.contains(s)) {
                double dist = c.getPosition().distanceTo(s.getPosition());
                if (dist < closest) {
                    closest = dist;
                    target = s;
                }
            }
        }
        if (closest == Double.POSITIVE_INFINITY) {
            visited = new ArrayList<Store>();
        }
    }

    @Override
    public void tryToBuy(Customer c) {
        List<Product> sl = c.getShoppingList();
        for (int i = 0; i < sl.size(); i++) {
            if (target.getProductType().contains(sl.get(i).getProductType())) {
                try {
                    target.sell(sl.get(i).getProductType());
                    sl.remove(i);
                    return;
                } catch (IllegalStateException e) {
                    break;
                }
            }
        }
        visited.add(target);
        target = null;
    }

}
