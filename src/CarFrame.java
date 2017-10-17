import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Vendor frame to create the GUI of the vending machine.
 *
 * @author Mehdi Himmiche
 */
public class CarFrame {

    private int MAX_PRODUCT_NUM = 25;


    // Create a hashmap to store the products and a combo box for each
    private HashMap<String, JComboBox> updateInv = new HashMap<>();
    // decided the maximum value per product that can be stored is MAX_PRODUCT_NUM - can change it later easily by modifying a single parameter
    private Integer[] productValues = new Integer[MAX_PRODUCT_NUM + 1];
    private double balance = 0;
    private double price = 0;
    private double change = 0;
    private double moneyInMachine = 0;
    private String productSelected; // Determine the product selected
    private ArrayList<JButton> productButtons; // Store the buttons in an arraylist - used an arraylist to allow admin to add products
    // Declaring the labels and buttons
    private JLabel balanceLabel;
    private JLabel priceLabel;
    private JLabel changeLabel;
    private JLabel prodBoughtLabel;
    private JLabel messageLabel;
    private JButton vendButton;
    private JButton cancelButton;
    private JButton adminButton;
    private JButton centButton = new JButton("$0.01");
    private JButton nickelButton = new JButton("$0.05");
    private JButton dimeButton = new JButton("$0.10");
    private JButton quarterButton = new JButton("$0.25");
    private JTextField username;
    private JPasswordField password;
    private JLabel loginLabel;
    private JFrame loginFrame;
    private JFrame adminOps;
    private JLabel currentMoneyLabel;

    /**
     * Create the initial frame of the vending machine
     * @return vending machine frame
     */
    public JFrame carFrame() {
        JFrame vendFrame = new JFrame("Mehdi Himmiche | Vending Maching");
        vendFrame.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("<html>Welcome to my vending machine! Please make a selection.<br>Mehdi Himmiche</html>", SwingConstants.CENTER);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vendFrame.getContentPane().add(infoLabel, BorderLayout.NORTH);
        JPanel input = interactionArea();
        vendFrame.getContentPane().add(input, BorderLayout.EAST);
        JPanel message = messageArea();
        vendFrame.getContentPane().add(message, BorderLayout.SOUTH);

        vendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendFrame.pack();
        vendFrame.setVisible(true);
        return vendFrame;
    }


    /**
     * Create the interaction area on the side of the vending machine (vend, cancel, add coins)
     * @return interaction buttons
     */
    private JPanel interactionArea() {
        JPanel userInter = new JPanel();
        userInter.setBorder(BorderFactory.createEmptyBorder(10, 10, 25, 15));
        userInter.setLayout(new GridLayout(6,0, 10,10));
        JPanel insertMoney = moneyButtons();
        userInter.add(insertMoney);
        balanceLabel = new JLabel(String.format("<html><b>Balance</b><br>$%.2f</html>", balance));
        userInter.add(balanceLabel);
        priceLabel = new JLabel("<html><b>Price</b><br>$0.00</html>");
        userInter.add(priceLabel);
        vendButton = new JButton("Vend");
        userInter.add(vendButton);
        cancelButton = new JButton("Cancel");
        userInter.add(cancelButton);
        adminButton = new JButton("Admin");
        userInter.add(adminButton);
        return userInter;
    }

    /**
     * Create the buttons for each available coin
     * @return coin buttons
     */
    private JPanel moneyButtons() {
        JPanel moneyIn = new JPanel();
        moneyIn.setLayout(new GridLayout(2,2,2,2));
        moneyIn.add(centButton);
        moneyIn.add(nickelButton);
        moneyIn.add(dimeButton);
        moneyIn.add(quarterButton);
        return moneyIn;
    }

    /**
     * Create the area at the bottom of the vending machine with general information.
     * @return general information tab
     */
    private JPanel messageArea() {
        JPanel message = new JPanel();
        message.setLayout(new GridLayout(0,2,5,5));
        JPanel changePanel = new JPanel();
        changePanel.setBorder(BorderFactory.createEmptyBorder(10,10,25,5));
        changePanel.setLayout(new GridLayout(0,2));
        changeLabel = new JLabel("<html><b>Change</b><br>$0.00</html>");
        changePanel.add(changeLabel);
        prodBoughtLabel = new JLabel("<html><b>Product Bought:</b><br>N/A</html>");
        changePanel.add(prodBoughtLabel);
        message.add(changePanel);
        messageLabel = new JLabel("Insert Coins to Purchase Product",SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()));
        message.add(messageLabel);
        return message;
    }

    /**
     * add money to available balance
     * @param aBalance coins inserted
     */
    private void addToBalance(double aBalance) {
        balance += aBalance;
        String out = String.format("<html><b>Balance</b><br>$%.2f</html>", balance);
        balanceLabel.setText(out);
    }

    /**
     * Update the label of the balance
     */
    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("<html><b>Balance</b><br>$%.2f</html>", balance));
    }

    /**
     * Update the label of the price section
     * @param aPrice price of a product
     */
    private void updatePriceLabel(double aPrice) {
        price = aPrice;
        String out = String.format("<html><b>Price</b><br>$%.2f</html>", price);
        priceLabel.setText(out);
    }

    /**
     * Create the admin frame that pops up to login
     */
    private void createAdminPanel() {
        username = new JTextField(8);
        password = new JPasswordField(8);
        JPanel loginArea = adminLoginPanels();
        JPanel buttons = adminLoginButtons();
        loginFrame = new JFrame();
        loginFrame.setLayout(new BorderLayout());
        loginFrame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        loginLabel = new JLabel("Please log in as administrator", SwingConstants.CENTER);
        loginFrame.getContentPane().add(loginLabel, BorderLayout.NORTH);
        loginFrame.getContentPane().add(loginArea, BorderLayout.CENTER);
        loginFrame.getContentPane().add(buttons, BorderLayout.SOUTH);
        loginFrame.setVisible(true);
        loginFrame.pack();
    }

    /**
     * Create the login buttons
     * @return panel with login buttons
     */
    private JPanel adminLoginButtons() {
        JPanel loginButtons = new JPanel();
        JButton login = new JButton("Login");
        loginButtons.add(login);
        loginButtons.add(Box.createHorizontalStrut(15));
        JButton cancel = new JButton("Cancel");
        loginButtons.add(cancel);
        return loginButtons;
    }

    /**
     * text areas to input username and password to login
     * @return JPanel with login inputs
     */
    private JPanel adminLoginPanels() {
        JPanel admin = new JPanel();
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Username:"));
        usernamePanel.add(Box.createHorizontalStrut(15));
        usernamePanel.add(username);
        usernamePanel.add(Box.createHorizontalStrut(15));
        JPanel pwPanel = new JPanel();
        pwPanel.add(new JLabel("Password:"));
        pwPanel.add(Box.createHorizontalStrut(15));
        pwPanel.add(password);
        admin.add(usernamePanel);
        admin.add(pwPanel);
        return admin;
    }

    /**
     * Create the frame that pops up once an admin has successfully logged in
     */
    private void adminOperations() {
        adminOps = new JFrame("Administrator Frame");
        adminOps.getRootPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTabbedPane adminPane = new JTabbedPane();
        adminPane.addTab("Withdraw $", withdrawMoney());
        adminPane.addTab("Update Inventory", updateInventoryPanel());
        adminOps.getContentPane().add(adminPane);
        adminOps.setVisible(true);
        adminOps.pack();
    }

    /**
     * Create the panel to allow admin to withdraw money from the vending machine
     * @return withdraw panel
     */
    private JPanel withdrawMoney() {
        JPanel withdraw = new JPanel();
        currentMoneyLabel = new JLabel(String.format("<html>Current Available Money in Machine:<b>$%.2f</html>", moneyInMachine));
        withdraw.add(currentMoneyLabel);
        withdraw.add(Box.createHorizontalStrut(15));
        JButton withdrawButton = new JButton("withdraw funds");
        withdraw.add(withdrawButton);
        return withdraw;
    }

    /**
     * create the panel to allow the admin to update the quantity of each product
     * @return product update panel
     */
    private JPanel updateInventoryPanel() {
        for (int i = 0; i < productValues.length; i++) {
            productValues[i] = i;
        }
        JPanel update = new JPanel();
        update.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        update.setLayout(new BoxLayout(update,BoxLayout.Y_AXIS));
        for (String prod : updateInv.keySet()) {
            JPanel row = new JPanel();
            row.add(new JLabel(prod + ":"));
            row.add(Box.createHorizontalStrut(15));
            row.add(updateInv.get(prod));
            update.add(row);
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        JButton updateInv = new JButton("Update Inventory");
        buttonPanel.add(updateInv, BorderLayout.EAST);
        update.add(buttonPanel);
        return update;
    }


}