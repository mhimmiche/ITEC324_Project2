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

    private final int DELAY = 10;

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

    private MoveableShape shape;
    private ShapeIcon icon;



    public CarFrame() {
        movementPanel();
        movingFrameOn = true;
        currentShapes = new Stack<>();
        rand = new Random();
    }

    /**
     * Create the initial frame of the display
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
        carFrame.setVisible(true);
        return carFrame;
    }

    /**
     * Create the interface buttons for user interactions
     * @return JPanel with buttons
     */
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addObject();
            }
        });
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

    /**
     * Create the checkbox panel area in the display
     * @return checkboxes
     */
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

    /**
     * Create the movement area where objects move
     */
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
        //drawTest();
    }

    /**
     * Draw the person that can be moved
     */
    private void drawPerson() {
        MoveableShape shape = new PersonShape(0, 0, 100);
        ShapeIcon icon = new ShapeIcon(shape, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
        JLabel label = new JLabel(icon);
        movingArea.add(label);
        // Milliseconds between timer ticks
        int dx = rand.nextInt(2) - 1;
        int dy = rand.nextInt(2) - 1;
        Timer t = new Timer(DELAY, new
            ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    shape.translate(dx, dy, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
                    label.repaint();
                }
            });
        t.start();
    }

    /**
     * Draw the car that can be moved
     */
    private void drawCar() {
        MoveableShape shape = new CarShape(0, 0, 100);
        ShapeIcon icon = new ShapeIcon(shape, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
        JLabel label = new JLabel(icon);
        movingArea.add(label);
        // Milliseconds between timer ticks
        int dx = rand.nextInt(2) - 1;
        int dy = rand.nextInt(2) - 1;
        Timer t = new Timer(DELAY, new
                ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        shape.translate(dx, dy, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
                        label.repaint();
                    }
                });
        t.start();
    }

    /**
     * Draw the flower that can be moved
     */
    private void drawFlower() {
        MoveableShape shape = new FlowerShape(0, 0, 100);
        ShapeIcon icon = new ShapeIcon(shape, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
        JLabel label = new JLabel(icon);
        movingArea.add(label);
        // Milliseconds between timer ticks
        int dx = rand.nextInt(2) - 1;
        int dy = rand.nextInt(2) - 1;
        Timer t = new Timer(DELAY, new
                ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        shape.translate(dx, dy, MOVE_AREA_WIDTH, MOVE_AREA_HEIGHT);
                        label.repaint();
                    }
                });
        t.start();
    }

    /**
     * Add objects into the stack
     */
    private void addObject() {
        for (JCheckBox cb : checkBoxGr) {
            if (cb.isSelected()) {
                switch (cb.getText()) {
                    case "BLUE":
                        System.out.println(cb.getText());
                        drawPerson();
                        break;
                    case "RED":
                        drawCar();
                        break;
                    case "YELLOW":
                        drawFlower();
                    default:
                        break;
                }

            }
        }
    }


}