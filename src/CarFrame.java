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
    private ArrayList<JCheckBox> checkBoxGr;
    private JCheckBox redCheckBox;
    private JCheckBox blueCheckBox;
    private JCheckBox yellowCheckBox;
    private JButton addButton;
    private JButton showButton;
    private JButton exitButton;
    private JButton removeButton;


    /**
     * Create the initial frame of the vending machine
     * @return vending machine frame
     */
    public JFrame carFrame() {
        JFrame vendFrame = new JFrame("Mehdi Himmiche | Moving Frames");
        vendFrame.setLayout(new BorderLayout());

        JPanel userInt = interfaceButtons();
        userInt.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vendFrame.getContentPane().add(userInt, BorderLayout.NORTH);

        vendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendFrame.pack();
        vendFrame.setVisible(true);
        return vendFrame;
    }

    private JPanel interfaceButtons() {
        JPanel userInter = new JPanel();
        userInter.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        userInter.setLayout(new GridLayout(0,5,10,10));
        showButton = new JButton("Show");
        userInter.add(showButton);
        addButton = new JButton("Add");
        userInter.add(addButton);
        removeButton = new JButton("Remove");
        userInter.add(removeButton);
        exitButton = new JButton("Exit");
        userInter.add(exitButton);
        userInter.add(checkBoxPanel());
        return userInter;
    }

    private JPanel checkBoxPanel() {
        JPanel checkBoxes = new JPanel();
        checkBoxGr = new ArrayList<JCheckBox>();
        redCheckBox = new JCheckBox("RED");
        blueCheckBox = new JCheckBox("BLUE");
        yellowCheckBox = new JCheckBox("YELLOW");
        checkBoxGr.add(redCheckBox);
        checkBoxGr.add(blueCheckBox);
        checkBoxGr.add(yellowCheckBox);
        checkBoxes.setLayout(new GridLayout(3,0,10,10));
        for (JCheckBox cb : checkBoxGr) {
            checkBoxes.add(cb);
        }
        return checkBoxes;
    }

    private JPanel movementPanel() {
        JPanel moveable = new JPanel();
        moveable.setVisible(false);
        
        return moveable;
    }


}