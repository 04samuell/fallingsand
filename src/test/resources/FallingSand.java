import java.awt.*;
import javax.swing.*;


public class FallingSand {

    private JFrame frame;
    private Color color = Color.red;
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
        frame.getContentPane().add(panel);
        panel.setBackground(Color.black);
        defineGrid();
    }

    public void defineGrid() {
        grid = new int[WIDTH / CELL_SIZE][HEIGHT / CELL_SIZE];
        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length ; j++) {
                grid[i][j] = 0;
            }
        }
    }



    public void refreshGrid() {  
        boolean falling = true; 
        while(falling) {
            falling = false;
            for (int i = 0; i < grid.length ; i++) {
                for (int j = 0; j < grid[i].length ; j++) {
                    if (grid[i][j] == 1 && j < grid[i].length-1) {
                        grid[i][j] = 0;
                        //shadeGrid(Color.black, i, j);
                        grid[i][j+1] = 1;
                        falling = true;
                        drawGrid();
                    }
                }
        }
        
        }
    }

    public void shadeGrid(Color color, int i, int j) {
        Graphics g = panel.getGraphics();
        g.setColor(color);
        g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void drawGrid() {
        for(int i = 0 ; i < grid.length ; i++) {
            for(int j = 0 ; j < grid[i].length ; j++) {
                if(grid[i][j] == 1) {
                    shadeGrid(color, i, j);
                }
            }
        }
    }
}
