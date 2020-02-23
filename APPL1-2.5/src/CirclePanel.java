// ******************************************************************
// CirclePanel.java
//
// A panel with a circle drawn in the center and buttons on the
// bottom that move the circle.
// ******************************************************************
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class CirclePanel extends JPanel
{
    private final int CIRCLE_SIZE = 50;
    private int x,y;
    private Color c;
    private int w,h;
    private JPanel buttonPanel;
    private JButton left;
    private JButton right;
    private JButton up;
    private JButton down;

    //---------------------------------------------------------------
    // Set up circle and buttons to move it.
    //---------------------------------------------------------------
    public CirclePanel(int width, int height)
    {
        this.w = width;
        this.h = height;

        // Set coordinates so circle starts in middle
        x = (width/2)-(CIRCLE_SIZE/2);
        y = (height/2)-(CIRCLE_SIZE/2);
        c = Color.green;
        // Need a border layout to get the buttons on the bottom
        this.setLayout(new BorderLayout());
        // Create buttons to move the circle
        left = new JButton("Left");
        right = new JButton("Right");
        up = new JButton("Up");
        down = new JButton("Down");
        // Add listeners to the buttons
        left.addActionListener(new MoveListener(-20,0));
        right.addActionListener(new MoveListener(20,0));
        up.addActionListener(new MoveListener(0,-20));
        down.addActionListener(new MoveListener(0,20));

        // Add shortcut
        left.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK), "left");
        left.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                left.doClick();
            }
        });

        right.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK), "right");
        right.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                right.doClick();
            }
        });

        up.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK), "up");
        up.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                up.doClick();
            }
        });

        down.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK), "down");
        down.getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                down.doClick();
            }
        });

        // Tooltips
        left.setToolTipText("Move the circle to left: 20");
        right.setToolTipText("Move the circle to right: 20");
        up.setToolTipText("Move the circle to up: 20");
        down.setToolTipText("Move the circle to down: 20");

        // Need a panel to put the buttons on or they'll be on
        // top of each other.
        buttonPanel = new JPanel();
        buttonPanel.add(left);
        buttonPanel.add(right);
        buttonPanel.add(up);
        buttonPanel.add(down);
        // Add the button panel to the bottom of the main panel
        this.add(buttonPanel, "South");
    }
    //---------------------------------------------------------------
    // Draw circle on CirclePanel
    //---------------------------------------------------------------
    public void paintComponent(Graphics page)
    {
        super.paintComponent(page);
        page.setColor(c);
        page.fillOval(x,y,CIRCLE_SIZE,CIRCLE_SIZE);
    }
    //---------------------------------------------------------------
    // Class to listen for button clicks that move circle.
    //---------------------------------------------------------------
    private class MoveListener implements ActionListener
    {
        private int dx;
        private int dy;
        //---------------------------------------------------------------
        // Parameters tell how to move circle at click.
        //---------------------------------------------------------------
        public MoveListener(int dx, int dy)
        {
            this.dx = dx;
            this.dy = dy;

            validateButton();
        }
        //---------------------------------------------------------------
        // Change x and y coordinates and repaint.
        //---------------------------------------------------------------
        public void actionPerformed(ActionEvent e)
        {
            x += dx;
            y += dy;
            repaint();

            validateButton();
        }

        public void validateButton() {
            if (dx != 0) {
                left.setEnabled(x + dx > 0);
                right.setEnabled(x + dx < w - CIRCLE_SIZE);
            }
            if (dy != 0) {
                up.setEnabled(y + dy > 0);
                down.setEnabled(y + dy < h - CIRCLE_SIZE);
            }
        }
    }
}