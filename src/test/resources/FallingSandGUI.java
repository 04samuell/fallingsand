import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FallingSandGUI {

    private FallingSand fs;
    public JFrame frame;
    public JPanel panel;
    public Image offScreenImage;
    
    public FallingSandGUI(FallingSand fs) {
        this.fs = fs;
    }

        
    public void createGUI() {
        frame = new JFrame();
        frame.setSize(FallingSand.WIDTH, FallingSand.HEIGHT-4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel = new JPanel();
        frame.getContentPane().add(panel);
        offScreenImage = panel.createImage(FallingSand.WIDTH, FallingSand.HEIGHT);
        panel.setBackground(Color.black);
        createAndAddMenuBar();
        frame.setVisible(true);
        
    }

    public void createAndAddMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        JCheckBoxMenuItem frenzyMenuItem = new JCheckBoxMenuItem("Frenzy");
        clearMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fs.grid = fs.getZeroGrid();
            }
        });
        frenzyMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(frenzyMenuItem.isSelected()) {
                    fs.frenzy = false;
                } else {
                    fs.frenzy = true;
                }
            }
        });
        menuBar.add(clearMenuItem);
        menuBar.add(frenzyMenuItem);
        frame.setJMenuBar(menuBar);
    }
}
