import java.awt.*;

public class Store extends Entity {
    protected Product type;
    protected int price;
    protected int maxStock;
    protected int stock;

    public Store(double x, double y, Product type, int price, int maxStock) {
        super(x, y);
        this.type = type;
        this.price = price;
        this.maxStock = maxStock;
        this.stock = maxStock;

    }

    @Override
    public void draw(Graphics2D g2d) {
        String text = type.getProductType() + "," + stock + "," + price;
        drawHelper(g2d, text, Color.ORANGE);
    }

    public String getProductType() {
        return type.getProductType();
    }

    public int getPrice(String itemType) throws IllegalStateException {
        if (!getProductType().contains(itemType)) {
            throw new IllegalStateException("Product not found");
        }
        return price;
    }

    @Override
    public void step() {

    }

    public void sell(String itemType) throws IllegalStateException {
        if (!getProductType().contains(itemType)) {
            throw new IllegalStateException("Product not found");
        }
        if (stock == 0) {
            throw new IllegalStateException("Out of Stock");
        }
        stock -= 1;
    }

    public void replenish() {
        stock = maxStock;
    }
}
