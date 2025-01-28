import javax.swing.*;

import java.util.List;

public class SimulationRunner {
    private JFrame window = new JFrame();
    private Display display = new Display();
    private static int lastReplenishment = 0;

    private static void stepAllEntities() {
        List<Customer> customerList = Common.getCustomerList();
        int entityRadius = Common.getEntityRadius();
        for (int i = 0; i < Common.getCustomerNo(); i++) {
            Customer c = customerList.get(i);
            if (c.getPosition().getIntX() < -2 * entityRadius || c.getPosition().getIntX() > Common.getWindowWidth()
                    ||
                    c.getPosition().getIntY() < -2 * entityRadius
                    || c.getPosition().getY() > Common.getWindowHeight()) {
                customerList.set(i, Common.createCustomer());
            }
        }

        List<Store> storeList = Common.getStoreList();
        for (Store s : storeList) {
            s.step();
        }
        for (Customer c : customerList) {
            c.step();
        }
        lastReplenishment += 1;
        if (lastReplenishment == Common.getStoreReplenishmentFrequency()) {
            lastReplenishment = 0;
            for (Store s : storeList) {
                s.replenish();
            }
        }
    }

    private static void createAndShowGUI() {
        SimulationRunner runner = new SimulationRunner();
        runner.window.add(runner.display);
        runner.window.setTitle(Common.getTitle());
        runner.window.setSize(Common.getWindowWidth(), Common.getWindowHeight());
        runner.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runner.window.setResizable(false);
        runner.window.setLocationRelativeTo(null);
        runner.window.setVisible(true);

        new Timer(5, actionEvent -> {
            stepAllEntities();
            runner.display.repaint();
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulationRunner::createAndShowGUI);
    }
}
