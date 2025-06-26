package actionHandlers;

import java.awt.event.*;
import javax.swing.*;

public class MenuHandler implements ActionListener {
    private JCheckBox items;
    private String name;
    private int price;
    private int amount;
    private int quantity;
    private String itemSummary = "";

    public MenuHandler(JCheckBox items, int price, String name) {
        this.items = items;
        this.price = price;
        this.name = name;
    }

    public void actionPerformed(ActionEvent ae) {
        if (items.isSelected()) {
            String input = JOptionPane
                    .showInputDialog("Enter the quantity for " + name + "\nPrice : " + price + "\u09F3");
            if (input == null || input.trim().equals("")) {
                items.setSelected(false);
                return;
            }
            try {
                quantity = Integer.parseInt(input.trim());
                if (quantity < 1) {
                    JOptionPane.showMessageDialog(null, "Quantity must be at least 1.");
                    items.setSelected(false);
                    return;
                }
                amount = price * quantity;
                itemSummary = "Item: " + name + ", Quantity: " + quantity + ", Price: \u09F3" + price
                        + ", \nAmount: \u09F3"
                        + amount + "\n";
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter a valid number");
                items.setSelected(false);
            }
        } else {
            itemSummary = "";
        }
    }

    public String getItemSummary() {
        return itemSummary;
    }

    public int getAmount() {
        return amount;
    }

    public JCheckBox getCheckBox() {
        return items;
    }

    public void reset() {
        quantity = 0;
        amount = 0;
        itemSummary = "";
    }
}