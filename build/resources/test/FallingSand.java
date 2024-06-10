import java.awt.*;
import javax.swing.*;

public class FallingSand {

    private JFrame frame;
    private JPanel panel;
    private int[][] grid;
    public static final int WIDTH = 800; 
    public static final int HEIGHT = 600;
    public static final int CELL_SIZE = 10;
    
    public FallingSand() {
        createGUI();
        frame.addMouseListener(new FallingSandMouseListener(this, grid));
    }

    public void createGUI() {
        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        defineGrid();
    }

    public void defineGrid() {
        grid = new int[WIDTH/10][HEIGHT/10];
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length ; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void refreshGrid() {
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length ; j++) {
                if (grid[i][j] == 1) {
                    panel.getGraphics().setColor(Color.BLACK);
                    panel.getGraphics().fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                }
            }
        }
        //panel.repaint();
    }
}
