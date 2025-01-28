import java.util.List;

public class ClosestStrategy extends Strategy {
    private Store prev;

    protected double distanceMetric(Position a, Position b) {
        return a.distanceTo(b);

    }

    @Override
    public String getStrategyType() {
        return "Cl";
    }

    @Override
    public void setNextTarget(Customer c, List<Store> StoreList) {
        double closest = Double.POSITIVE_INFINITY;
        for (Store s : StoreList) {
            if (s.getProductType().contains(c.getShoppingList().get(0).getProductType()) && s != prev) {
                double dist = distanceMetric(c.getPosition(), s.getPosition());
                if (dist < closest) {
                    closest = dist;
                    target = s;
                }
            }
        }
    }

    @Override
    public void tryToBuy(Customer c) {
        try {
            target.sell(c.getShoppingList().get(0).getProductType());
            c.getShoppingList().remove(0);
        } catch (IllegalStateException e) {
            prev = target;
        }
        target = null;
    }
}
