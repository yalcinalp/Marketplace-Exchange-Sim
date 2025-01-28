import java.awt.*;
import java.util.ArrayList;

class Customer extends Entity {
    protected Strategy strategy;
    protected ArrayList<Product> shoppingList;

    public Customer(double x, double y, Strategy strategy, ArrayList<Product> shoppingList) {
        super(x, y);
        this.strategy = strategy;
        this.shoppingList = shoppingList;
    }

    @Override
    public void draw(Graphics2D g2d) {
        String text = strategy.getStrategyType();
        for (int i = 0; i < 3; i++) {
            try {
                text += "," + shoppingList.get(i).getProductType();
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        drawHelper(g2d, text, Color.GRAY);
    }

    @Override
    public void step() {

        if (shoppingList.isEmpty()) {
            if (position.getIntX() < Common.getWindowWidth() - position.getIntX()) {
                position.setX(position.getX() - Common.getCustomerMovementSpeed());
            } else {
                position.setX(position.getX() + Common.getCustomerMovementSpeed());
            }
        } else if (!strategy.isTargetSet()) {

            strategy.setNextTarget(this, Common.getStoreList());

        } else {
            double dist = this.getPosition().distanceTo(strategy.getTargetPosition());
            if (dist <= 2 * Common.getEntityRadius()) {
                strategy.tryToBuy(this);
            } else {
                double x = strategy.getTargetPosition().getX() - this.getPosition().getX();
                double y = strategy.getTargetPosition().getY() - this.getPosition().getY();
                if (dist > Common.getCustomerMovementSpeed()) {
                    double vx = x / dist * Common.getCustomerMovementSpeed();
                    double vy = y / dist * Common.getCustomerMovementSpeed();
                    getPosition().setX(getPosition().getX() + vx);
                    getPosition().setY(getPosition().getY() + vy);
                } else {
                    getPosition().setX(strategy.getTargetPosition().getX());
                    getPosition().setY(strategy.getTargetPosition().getY());
                }

            }
        }
    }

    public ArrayList<Product> getShoppingList() {
        return shoppingList;
    }
}
