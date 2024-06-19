import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Class to construct the main GUI for the Falling Sand game. 
 */
public class FallingSandGUI {

    private FallingSand fs;
    public JFrame frame;
    public JPanel panel;
    public Image offScreenImage;

    private JCheckBoxMenuItem thickMenuItem;
    private JRadioButtonMenuItem greyMenuItem;
    private JRadioButtonMenuItem yellowMenuItem;

    private static final boolean DEBUGGING = true;

    /**
     * Constructor for the FallingSandGUI class.
     * Stores a reference to the FallingSand instance.
     * @param fs
     */
    public FallingSandGUI(FallingSand fs) {
        this.fs = fs;
    }

    /**
     * Creates the GUI for the sand to be placed.
     */
    public void createGUI() {
        frame = new JFrame("Falling Sand");
        setIcon();
        frame.setSize(FallingSand.WIDTH, FallingSand.HEIGHT - 4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel = new JPanel();
        frame.getContentPane().add(panel);
        offScreenImage = panel.createImage(FallingSand.WIDTH, FallingSand.HEIGHT);
        panel.setBackground(Color.black);
        createAndAddMenuBar();
        frame.setVisible(true);
        frame.addMouseListener(new FallingSandMouseListener(fs));
        frame.addMouseMotionListener(new FallingSandMouseListener(fs));

    }

    /**
     * Helper method in setting up GUI.
     * Creates and adds the menu bar to the frame.
     */
    private void createAndAddMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        
        thickMenuItem = new JCheckBoxMenuItem("Thick Sand");
        greyMenuItem = new JRadioButtonMenuItem("Grey Sand");
        yellowMenuItem = new JRadioButtonMenuItem("Yellow Sand");
        clearMenuItem.addActionListener(new ClearMenuItemListener());
        thickMenuItem.addActionListener(new ThickSandListener());
        greyMenuItem.addActionListener(new GreySandListener());
        yellowMenuItem.addActionListener(new YellowSandListener());
        menuBar.add(clearMenuItem);
        menuBar.add(thickMenuItem);
        menuBar.add(greyMenuItem);
        menuBar.add(yellowMenuItem);
        frame.setJMenuBar(menuBar);
    }

    /**
     * Helper method in setting up GUI. 
     * Sets the icon for the frame.
     */
    private void setIcon() {
        try{
            String filepath;
            if(DEBUGGING) {
                filepath = "test/resources/sandIcon.png"; // Path to icon for debugging.
            } else {
                filepath = "sandIcon.png"; // Path to icon for jar creation.
            }
            Image icon = ImageIO.read(FallingSandGUI.class.getClassLoader().getResourceAsStream(filepath));
            frame.setIconImage(icon);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listener for the clear button.
     * Clears the grid of sand.
     */
    public class ClearMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fs.grid = fs.getZeroGrid();
        }
    }

    /**
     * Listener for the thick sand button.
     * Changes the thickness of the sand.
     */
    public class ThickSandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (fs.thick) {
                fs.thick = false;
            } else {
                fs.thick = true;
            }
        }
    }

    /**
     * Listener for the grey sand button.
     * Changes the color of the sand to grey.
     */
    public class GreySandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fs.yellow = false;
            yellowMenuItem.setSelected(false);
            if (fs.grey) {
                fs.grey = false;
            } else {
                fs.grey = true;
            }
        }
    }

    /**
     * Listener for the yellow sand button. 
     * Changes the color of the sand to yellow.
     */
    public class YellowSandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fs.grey = false;
            greyMenuItem.setSelected(false);
            if(fs.yellow) {
                fs.yellow = false;
            } else {
                fs.yellow = true;
            }
        }
    }

}
