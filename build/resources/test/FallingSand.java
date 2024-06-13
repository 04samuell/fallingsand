import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FallingSand {

    private JFrame frame;
    private Color color = Color.red;
    private JPanel panel;
    private int[][] grid;
    private Image offScreenImage;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int CELL_SIZE = 10;

    public FallingSand() {
        createGUI();
        frame.addMouseListener(new FallingSandMouseListener(this));
        ActionListener gridRefresher = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNextGrid();
            }
        };
        Timer timer = new Timer(15, gridRefresher);
        timer.start();
    }

    public void createGUI() {
        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT-4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel = new JPanel();
        
        frame.getContentPane().add(panel);
        offScreenImage = panel.createImage(WIDTH, HEIGHT);
        panel.setBackground(Color.black);
        this.grid = getZeroGrid();
    }

    public int[][] getZeroGrid() {
        int[][] newGrid = new int[(HEIGHT / CELL_SIZE)-4][(WIDTH / CELL_SIZE)];
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                newGrid[i][j] = 0;
            }
        }
        return newGrid;
    }

    public void sandPlaced(int row, int col) {
        grid[row][col] = 1;
    }

    public void getNextGrid() {
        int[][] newGrid = getZeroGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                determinePlacement(newGrid, i, j);
            }
        }
        grid = newGrid;
        drawGrid();
    }

    public void determinePlacement(int[][] newGrid, int i, int j) {
        if (grid[i][j] == 1 && i < grid.length - 1 && grid[i + 1][j] == 0) { // if cell is sand and cell below is
            newGrid[i + 1][j] = 1;
        } else if(grid[i][j] == 1 && i < grid.length -1 && j > 0 && j < grid[i].length-1 && grid[i+1][j+1] == 0 && grid[i+1][j-1] == 0) { // if in bounds and cell below taken but either side of cell below is empty
            if(Math.random() < 0.5) {
                newGrid[i + 1][j + 1] = 1;
            } else {
                newGrid[i + 1][j - 1] = 1;
            }
        } else if (grid[i][j] == 1 && i < grid.length -1 && j < grid[i].length-1 && grid[i+1][j+1] == 0) { // if in bounds and cell below taken but left of cell below is empty
            newGrid[i + 1][j + 1] = 1;
        } else if (grid[i][j] == 1 && i < grid.length -1 && j > 0 && grid[i+1][j-1] == 0) { // if in bounds and cell below taken but right of cell below is empty
            newGrid[i + 1][j - 1] = 1;
        }else if(grid[i][j] == 1) {
            newGrid[i][j] = 1;
        }
    }

    public void shadeCell(Graphics g, Color color, int i, int j) {
        g.setColor(color);
        g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void drawGrid() {
        Graphics offScreenGraphics = offScreenImage.getGraphics();
        offScreenGraphics.clearRect(0, 0, WIDTH, HEIGHT);
        offScreenGraphics.setColor(Color.black);
        offScreenGraphics.fillRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    shadeCell(offScreenGraphics, color, i, j);
                }
            }
        }

        Graphics panelGraphics = panel.getGraphics();
        panelGraphics.drawImage(offScreenImage, 0, 0, panel);

        offScreenGraphics.dispose();
        panelGraphics.dispose();
    }
}
