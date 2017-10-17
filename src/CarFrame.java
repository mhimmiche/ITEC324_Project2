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

    private JPanel movingArea;
    private JPanel moveable;

    private JButton addButton;
    private JButton showButton;
    private JButton exitButton;
    private JButton removeButton;
    private JButton subFrameHideButton;
    private JButton subFrameExitButton;

    private Timer timer;





    /**
     * Create the initial frame of the vending machine
     * @return vending machine frame
     */
    public JFrame carFrame() {
        JFrame carFrame = new JFrame("Mehdi Himmiche | Moving Frames");
        carFrame.setLayout(new BorderLayout());

        JPanel userInt = interfaceButtons();
        userInt.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        carFrame.getContentPane().add(userInt, BorderLayout.NORTH);
        int xSize = carFrame.getContentPane().getWidth();
        JPanel moveable = movementPanel(xSize);
        carFrame.getContentPane().add(moveable, BorderLayout.CENTER);

        carFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //carFrame.pack();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenHeight = (int) (toolkit.getScreenSize().getHeight() * .75);
        carFrame.setSize(new Dimension(screenHeight, screenHeight));
        carFrame.setLocationRelativeTo(null);
        carFrame.setVisible(true);
        return carFrame;
    }

    private JPanel interfaceButtons() {
        JPanel userInter = new JPanel();
        userInter.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        userInter.setLayout(new GridLayout(0,5,10,10));
        showButton = new JButton("Show");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveable.setVisible(true);
            }
        });
        userInter.add(showButton);
        addButton = new JButton("Add");
        userInter.add(addButton);
        removeButton = new JButton("Remove");
        userInter.add(removeButton);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        userInter.add(exitButton);
        userInter.add(checkBoxPanel());
        return userInter;
    }

    private JPanel checkBoxPanel() {
        JPanel checkBoxes = new JPanel();
        checkBoxGr = new ArrayList<>();
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

    private JPanel movementPanel(int xSize) {
        moveable = new JPanel();
        moveable.setLayout(new BorderLayout());
        moveable.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        movingArea = new JPanel();
        moveable.add(movingArea, BorderLayout.CENTER);
        JPanel buttonArea = new JPanel();
        subFrameExitButton = new JButton("Exit");
        subFrameExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        subFrameHideButton = new JButton("Hide");
        subFrameHideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveable.setVisible(false);
            }
        });
        buttonArea.setLayout(new GridLayout(0,2,10,10));
        buttonArea.add(subFrameHideButton);
        buttonArea.add(subFrameExitButton);
        moveable.add(buttonArea, BorderLayout.SOUTH);
        drawTest();
        int ySize = (int) (xSize * .6);
        moveable.setPreferredSize(new Dimension(xSize, ySize));
        return moveable;
    }

    private void drawTest() {
        MoveableShape shape = new CarShape(50, 50, 10);
        ShapeIcon icon = new ShapeIcon(shape, 10, 5);
        JLabel label = new JLabel(icon);
        movingArea.add(label);
        final int DELAY = 10;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, new
            ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    shape.translate(1, 0);
                    label.repaint();
                }
            });
        t.start();
    }


}