package Asset1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ShoppingCart extends JFrame {
    private JTextField codeField, quantityField;
    private JTextArea displayArea;
    private double totalPrice;

    public ShoppingCart() {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Panel.background", Color.darkGray);
            UIManager.put("TextArea.background", Color.darkGray);
            UIManager.put("TextArea.foreground", Color.white);
            UIManager.put("Button.background", Color.darkGray);
            UIManager.put("Button.foreground", Color.white);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JLabel codeLabel = new JLabel("Product Code:");
        codeField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });

        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.darkGray);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(codeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(codeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(addToCartButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(generateBillButton, gbc);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.darkGray);
        displayArea.setForeground(Color.white);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.darkGray);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        pack(); 
    }

    private void addToCart() {
        String code = codeField.getText();
        String quantityText = quantityField.getText();

        if (code.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            BufferedReader reader = new BufferedReader(new FileReader("/home/vinxyyy/Desktop/New Projects/Asset1/products.txt"));
            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String existingCode = parts[0];

                if (existingCode.equals(code)) {
                    found = true;
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    double discount = Double.parseDouble(parts[3]);

                    double totalPriceForProduct = (price - (price * discount / 100)) * quantity;
                    totalPrice += totalPriceForProduct;

                    displayArea.append(name + " x " + quantity + " = Rs" + String.format("%.2f", totalPriceForProduct) + "\n");
                    break;
                }
            }

            reader.close();

            if (!found) {
                JOptionPane.showMessageDialog(this, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error adding product to cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateBill() {
        if (totalPrice == 0) {
            JOptionPane.showMessageDialog(this, "No products added to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String bill = "========================\n";
        bill += "       BILL DETAILS\n";
        bill += "========================\n";
        bill += "Total Price: Rs" + String.format("%.2f", totalPrice) + "\n";

        displayArea.append("\n" + bill);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("bill.txt"));
            writer.write(bill);
            writer.close();

            JOptionPane.showMessageDialog(this, "Bill generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error generating bill.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        codeField.setText("");
        quantityField.setText("");
        displayArea.setText("");
        totalPrice = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ShoppingCart gui = new ShoppingCart();
                gui.setVisible(true);
            }
        });
    }
}
