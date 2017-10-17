import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 * Vendor frame to create the GUI of the vending machine.
 *
 * @author Mehdi Himmiche
 */
public class CarFrame {

    private static final int MOVE_AREA_WIDTH = 600;
    private static final int MOVE_AREA_HEIGHT = 600;

    private ArrayList<JCheckBox> checkBoxGr;
    private JCheckBox redCheckBox;
    private JCheckBox blueCheckBox;
    private JCheckBox yellowCheckBox;

    private JFrame movingFrame;
    private JPanel movingArea;

    private JButton addButton;
    private JButton showButton;
    private JButton exitButton;
    private JButton removeButton;
    private JButton subFrameHideButton;
    private JButton subFrameExitButton;

    private boolean movingFrameOn;

    private Stack<JLabel> currentShapes;

    private Random rand;



    public CarFrame() {
        movementPanel();
        movingFrameOn = true;
        currentShapes = new Stack<>();
        rand = new Random();
    }

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
        carFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        carFrame.pack();
        System.out.println(carFrame.getSize().getHeight());
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
                if (!movingFrameOn) {
                    movementPanel();
                    movingFrameOn = true;
                }
                movingFrame.setVisible(true);
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

    private void movementPanel() {
        movingFrame = new JFrame();
        movingFrame.setVisible(false);
        movingFrame.setLayout(new BorderLayout());
        movingFrame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        movingArea = new JPanel();
        movingArea.setPreferredSize(new Dimension(MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT));
        movingFrame.add(movingArea, BorderLayout.CENTER);
        JPanel buttonArea = new JPanel();
        subFrameExitButton = new JButton("Exit");
        subFrameExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingFrameOn = false;
                movingFrame.dispose();
            }
        });
        subFrameHideButton = new JButton("Hide");
        subFrameHideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingFrame.setVisible(false);
            }
        });
        buttonArea.setLayout(new GridLayout(0,2,10,10));
        buttonArea.add(subFrameHideButton);
        buttonArea.add(subFrameExitButton);
        movingFrame.add(buttonArea, BorderLayout.SOUTH);
        movingFrame.setSize(new Dimension(MOVE_AREA_WIDTH + 20, MOVE_AREA_HEIGHT + 100));
        drawTest();
    }

    private void drawTest() {
        MoveableShape shape = new PersonShape(100, 100, 100);
        ShapeIcon icon = new ShapeIcon(shape, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
        JLabel label = new JLabel(icon);
        movingArea.add(label);
        final int DELAY = 10;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, new
            ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    shape.translate(0, 0, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
                    label.repaint();
                }
            });
        t.start();
    }

    private void addObject() {
        for (JCheckBox cb : checkBoxGr) {
            if (cb.isSelected()) {
                int x = rand.nextInt(MOVE_AREA_WIDTH);
                int y = rand.nextInt(MOVE_AREA_HEIGHT);
                int size = rand.nextInt(100) + 75;
                MoveableShape shape;
                ShapeIcon icon;
                JLabel label;
                switch (cb.getText()) {
                    case "BLUE":
                        shape = new PersonShape(x, y, size);
                        icon = new ShapeIcon(shape, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
                        label = new JLabel(icon);
                        currentShapes.push(label);
                        break;
                    case "RED":
                        shape = new CarShape(x, y, size);
                        icon = new ShapeIcon(shape, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
                        label = new JLabel(icon);
                        currentShapes.push(label);
                        break;
                    default:
                        break;
                }

            }
        }
    }


}