import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import Asset1.ProductManagement;
import Asset1.ShoppingCart;
public class Mark1 extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    private void showBillingSystem(){
        JFrame BillingFrame=new JFrame("Billing System");
        BillingFrame.setTitle("Billing System");
        BillingFrame.setResizable(true);
        JPanel billPanel=new JPanel();
        billPanel.setLayout(new BorderLayout());
        billPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
    
        JLabel bill=new JLabel("Billing System \n\n Main Menu");
        bill.setFont(new Font("Jokerman",Font.ITALIC,24));
        bill.setHorizontalAlignment(JLabel.CENTER);
        billPanel.add(bill, BorderLayout.NORTH);
    
        JPanel options=new JPanel();
        options.setLayout(new FlowLayout());
        JButton LoginButton=new JButton("ADMIN LOGIN");
        LoginButton.setFont(new Font("Algerian", Font.BOLD,14));
        JButton salesButton=new JButton("SALES LOGIN");
        salesButton.setFont(new Font("Algerian", Font.BOLD,14));
        options.add(new JLabel());
        options.add(new JLabel());
        options.add(LoginButton);
        options.add(salesButton);
    
        billPanel.add(options, BorderLayout.CENTER);
        //add action buttons
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ProductManagement pro1=new ProductManagement();
                pro1.main(null);
            }
        });
        salesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ShoppingCart sho1=new ShoppingCart();
                sho1.main(null);
            }
        });
        BillingFrame.add(billPanel, BorderLayout.NORTH);
        BillingFrame.add(LoginButton, BorderLayout.WEST);
        BillingFrame.add(salesButton, BorderLayout.EAST);
        BillingFrame.pack();
        BillingFrame.setLocationRelativeTo(null);
        BillingFrame.setVisible(true);
    }
    public Mark1(){
        setTitle("Mark 1");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //main panel
        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        //welcome
        JLabel welcome=new JLabel("MaRk 1 Application Model\n\n");
        welcome.setFont(new Font("Algerian", Font.ITALIC,24));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcome, BorderLayout.NORTH);

        //options
        JPanel options=new JPanel();
        options.setLayout(new FlowLayout());
        JButton loginButton=new JButton("LOGIN");
        loginButton.setFont(new Font("Algerian", Font.BOLD,14));
        JButton registerButton=new JButton("REGISTER");
        registerButton.setFont(new Font("Algerian", Font.BOLD,14));
        options.add(new JLabel());
        options.add(new JLabel());
        options.add(loginButton);
        options.add(registerButton);

        //add options to main panel
        mainPanel.add(options, BorderLayout.CENTER);

        //add action buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showLoginForm();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showRegistrationForm();
            }
        });
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void showLoginForm(){
        JFrame loginFrame=new JFrame("LoGiN sYStem!!!");
        loginFrame.setTitle("LoGIn sYSteM!!!");
        loginFrame.setResizable(true);
        loginFrame.setSize(500,500);
        loginFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //login panel
        JPanel loginPanel=new JPanel();
        loginPanel.setLayout(new GridLayout(5,5,30,30));
        JLabel usernameLabel=new JLabel("USERNAME:");
        JLabel passwordLabel=new JLabel("PASSWORD:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        //login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Perform login validation here
                if (isCredentialsValid(username, password)) {
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                    showBillingSystem();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Incorrect username or password!");
                }
            }
        });

        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.add(loginButton, BorderLayout.SOUTH);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
    
    private void showRegistrationForm(){
        JFrame registrationFrame=new JFrame("Registration System");
        registrationFrame.setTitle("REGISTRATION SYSTEM");
        registrationFrame.setResizable(true);
        registrationFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel registratioPanel=new JPanel();
        registratioPanel.setLayout(new GridLayout(7,2,5,10));
        registratioPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        JLabel usernamLabel = new JLabel("USERNAME:");
        JLabel passwordLabel= new JLabel("PASSWORD:");
        JLabel emailLabel= new JLabel("EMAIL");
        JLabel phoneLabel= new JLabel("MOBILE NO.");
        JTextField usernameField= new JTextField(15);
        JPasswordField passwordField= new JPasswordField(15);
        JTextField emailField= new JTextField(15);
        JTextField phoneField = new JTextField(15);
        JLabel passwordRulesLabel = new JLabel("<html>Rules: At least 8 characters, <br>including uppercase, lowercase, and special characters.</html>");
        JLabel emailRulesLabel = new JLabel("Rules: Must be a valid email address.");
        JLabel phoneRulesLabel = new JLabel("Rules: Must be a valid mobile number.");
        registratioPanel.add(usernamLabel);
        registratioPanel.add(usernameField);
        registratioPanel.add(passwordLabel);
        registratioPanel.add(passwordField);
        registratioPanel.add(emailLabel);
        registratioPanel.add(emailField);
        registratioPanel.add(phoneLabel);
        registratioPanel.add(phoneField);
        registratioPanel.add(passwordRulesLabel);
        registratioPanel.add(new JLabel());
        registratioPanel.add(emailRulesLabel);
        registratioPanel.add(new JLabel());
        registratioPanel.add(phoneRulesLabel);
        registratioPanel.add(new JLabel());

        JButton registerButton=new JButton("REGISTER");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                String phone = phoneField.getText();

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(registrationFrame, "Invalid email format!");
                    return;
                }

                if (!isValidMobileNumber(phone)) {
                    JOptionPane.showMessageDialog(registrationFrame, "Invalid mobile number format!");
                    return;
                }

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(registrationFrame, "Please fill in all fields!");
                } else {
                    if (isUserRegistered(username)) {
                        JOptionPane.showMessageDialog(registrationFrame, "Username already exists!");
                    } else if (!isPasswordValid(password)) {
                        JOptionPane.showMessageDialog(registrationFrame, "Invalid password format!\n"
                                + "Password must contain at least 8 characters, including uppercase, lowercase, and special characters.");
                    } else {
                        registerUser(username, password);
                        JOptionPane.showMessageDialog(registrationFrame, "Registration Successful!");
                        registrationFrame.dispose();
                    }
                }
            }
        });

        registrationFrame.add(registratioPanel, BorderLayout.CENTER);
        registrationFrame.add(registerButton, BorderLayout.SOUTH);
        registrationFrame.pack();
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.setVisible(true);
    }

    private boolean isValidEmail(String email) {
        // Email validation regex pattern
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }

    private boolean isValidMobileNumber(String phone) {
        // Mobile number validation regex pattern
        String regex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        return phone.matches(regex);
    }

    private boolean isUserRegistered(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPasswordValid(String password) {
        // Password validation regex pattern
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    private void registerUser(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCredentialsValid(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(username) && (line = br.readLine()) != null && line.equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        try{
            File file=new File("users.txt");
            if(!file.exists()){
                file.createNewFile();
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e){
                    e.printStackTrace();
                }
                new Mark1().setVisible(true);
            }
        });
    }
}