import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ProductManagementGUI extends JFrame {
    private JTextField codeField, nameField, priceField, discountField;
    private JTextArea displayArea;

    public ProductManagementGUI() {
        setTitle("Product Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set dark mode appearance
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

        // Initialize components
        JLabel codeLabel = new JLabel("Product Code:");
        codeField = new JTextField(10);

        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField(20);

        JLabel priceLabel = new JLabel("Product Price:");
        priceField = new JTextField(10);

        JLabel discountLabel = new JLabel("Product Discount:");
        discountField = new JTextField(10);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editProduct();
            }
        });

        JButton showButton = new JButton("Show All");
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showProducts();
            }
        });

        // Panel to hold input fields and buttons
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
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(discountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(discountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        inputPanel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(editButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        inputPanel.add(showButton, gbc);

        // Display area for showing products
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.darkGray);
        displayArea.setForeground(Color.white);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to the frame
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.darkGray);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        pack(); // Adjust window size based on content
    }

    private void addProduct() {
        String code = codeField.getText();
        String name = nameField.getText();
        String price = priceField.getText();
        String discount = discountField.getText();

        if (code.isEmpty() || name.isEmpty() || price.isEmpty() || discount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt", true));
            writer.write(code + "," + name + "," + price + "," + discount);
            writer.newLine();
            writer.close();

            JOptionPane.showMessageDialog(this, "Product added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            codeField.setText("");
            nameField.setText("");
            priceField.setText("");
            discountField.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error adding product.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        String code = codeField.getText();

        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the product code to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            File inputFile = new File("products.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String existingCode = parts[0];

                if (existingCode.equals(code)) {
                    found = true;
                } else {
                    writer.write(currentLine + System.lineSeparator());
                }
            }

            reader.close();
            writer.close();

            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                JOptionPane.showMessageDialog(this, "Product deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting product.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editProduct() {
        String code = codeField.getText();
        String name = nameField.getText();
        String price = priceField.getText();
        String discount = discountField.getText();

        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the product code to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            File inputFile = new File("products.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String existingCode = parts[0];

                if (existingCode.equals(code)) {
                    writer.write(code + "," + name + "," + price + "," + discount + System.lineSeparator());
                    found = true;
                } else {
                    writer.write(currentLine + System.lineSeparator());
                }
            }

            reader.close();
            writer.close();

            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                JOptionPane.showMessageDialog(this, "Product updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error editing product.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showProducts() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            StringBuilder products = new StringBuilder();

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                products.append("Code: ").append(parts[0]).append(", Name: ").append(parts[1])
                        .append(", Price: ").append(parts[2]).append(", Discount: ").append(parts[3])
                        .append("\n");
            }

            reader.close();

            if (products.length() > 0) {
                displayArea.setText(products.toString());
            } else {
                displayArea.setText("No products found.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading products.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProductManagementGUI gui = new ProductManagementGUI();
                gui.setVisible(true);
            }
        });
    }
}
