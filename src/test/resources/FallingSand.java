import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
        frame.addMouseListener(new FallingSandMouseListener(this));
        ActionListener gridRefresher = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNextGrid();
            }
        };
        Timer timer = new Timer(1, gridRefresher);
        timer.start();
    }

    public void createGUI() {
        frame = new JFrame();
        frame.setSize(WIDTH, HEIGHT-4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setBackground(Color.black);
        frame.getContentPane().add(panel);
        this.grid = getEmptyGrid();
    }

    public int[][] getEmptyGrid() {
        int[][] newGrid = new int[(HEIGHT / CELL_SIZE)-4][(WIDTH / CELL_SIZE)-4];
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
        int[][] newGrid = getEmptyGrid();
        newGrid = getEmptyGrid();
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
        } else if(grid[i][j] == 1) {
            newGrid[i][j] = 1;
        }
    }

    public void shadeCell(Color color, int i, int j) {
        Graphics g = panel.getGraphics();
        g.setColor(color);
        g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void drawGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    shadeCell(color, i, j);
                } else {
                    shadeCell(Color.black, i, j);
                }
            }
        }
    }
}
