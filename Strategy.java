import java.util.List;

public abstract class Strategy {
    protected Store target;

    public Boolean isTargetSet() {
        return target != null;
    }

    public Position getTargetPosition() {
        return target.getPosition();
    }

    public abstract String getStrategyType();

    public abstract void setNextTarget(Customer c, List<Store> storeList);

    public abstract void tryToBuy(Customer c);

}
