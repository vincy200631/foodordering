package foodordering;

import java.awt.*;
import javax.swing.*;

public class OnlineFoodOrderingSystem extends JFrame {

    String[] foodNames = {
        "Chicken Biryani",
        "Veg Biryani",
        "Chicken Fried Rice",
        "Burger",
        "Pizza",
        "Dosa"
    };

    double[] prices = {
        180,
        130,
        150,
        120,
        250,
        80
    };

    JTextArea cartArea;
    JLabel totalLabel;

    double total = 0;
    int orderNumber = 1001;

    public OnlineFoodOrderingSystem() {

        setTitle("Online Food Ordering System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // ---------------- FOOD MENU ----------------

        JPanel menuPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        menuPanel.setBorder(
            BorderFactory.createTitledBorder("Food Menu")
        );

        for (int i = 0; i < foodNames.length; i++) {

            int index = i;

            JButton foodButton = new JButton(
                foodNames[i] + " - ₹" + prices[i]
            );

            foodButton.addActionListener(e -> addFood(index));

            menuPanel.add(foodButton);
        }

        // ---------------- CART ----------------

        JPanel cartPanel = new JPanel(new BorderLayout(10, 10));
        cartPanel.setBorder(
            BorderFactory.createTitledBorder("Shopping Cart")
        );

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        cartArea.setFont(new Font("Arial", Font.PLAIN, 15));

        cartPanel.add(
            new JScrollPane(cartArea),
            BorderLayout.CENTER
        );

        // ---------------- BOTTOM PANEL ----------------

        JPanel bottomPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        totalLabel = new JLabel("Total: ₹0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton clearButton = new JButton("Clear Cart");
        JButton orderButton = new JButton("Place Order");

        clearButton.addActionListener(e -> clearCart());
        orderButton.addActionListener(e -> placeOrder());

        bottomPanel.add(totalLabel);
        bottomPanel.add(clearButton);
        bottomPanel.add(orderButton);

        cartPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );

        // ---------------- ADD PANELS ----------------

        mainPanel.add(menuPanel);
        mainPanel.add(cartPanel);

        add(mainPanel);
    }

    // Add food to cart
    void addFood(int index) {

        String input = JOptionPane.showInputDialog(
            this,
            "Enter quantity for " + foodNames[index] + ":"
        );

        if (input == null) {
            return;
        }

        try {

            int quantity = Integer.parseInt(input);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be greater than 0."
                );
                return;
            }

            double itemTotal = prices[index] * quantity;

            cartArea.append(
                foodNames[index]
                + " x "
                + quantity
                + " = ₹"
                + itemTotal
                + "\n"
            );

            total += itemTotal;

            totalLabel.setText(
                String.format("Total: ₹%.2f", total)
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid quantity."
            );
        }
    }

    // Clear cart
    void clearCart() {

        cartArea.setText("");
        total = 0;

        totalLabel.setText("Total: ₹0.00");
    }

    // Place order
    void placeOrder() {

        if (total == 0) {

            JOptionPane.showMessageDialog(
                this,
                "Your cart is empty!"
            );

            return;
        }

        String[] paymentMethods = {
            "UPI",
            "Card",
            "Cash on Delivery"
        };

        String payment = (String) JOptionPane.showInputDialog(
            this,
            "Select Payment Method:",
            "Payment",
            JOptionPane.QUESTION_MESSAGE,
            null,
            paymentMethods,
            paymentMethods[0]
        );

        if (payment == null) {
            return;
        }

        JOptionPane.showMessageDialog(
            this,
            "ORDER PLACED SUCCESSFULLY!\n\n"
            + "Order ID: " + orderNumber
            + "\nTotal Amount: ₹"
            + String.format("%.2f", total)
            + "\nPayment: " + payment
            + "\n\nThank you for ordering!"
        );

        orderNumber++;

        clearCart();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            OnlineFoodOrderingSystem app =
                new OnlineFoodOrderingSystem();

            app.setVisible(true);
        });
    }
}
