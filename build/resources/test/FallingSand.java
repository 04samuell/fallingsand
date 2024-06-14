import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FallingSand {

    private Color color = Color.red;
    public Grain[][] grid;
    private Image offScreenImage;
    private RainbowColourScheme rcs;
    private FallingSandGUI gui;
    private int i = 0;
    public boolean frenzy = false;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int CELL_SIZE = 10;

    public FallingSand() {
        rcs = new RainbowColourScheme();
        gui = new FallingSandGUI(this);
        gui.createGUI();
        this.grid = getZeroGrid();
        gui.frame.addMouseListener(new FallingSandMouseListener(this));
        gui.frame.addMouseMotionListener(new FallingSandMouseListener(this));
        ActionListener gridRefresher = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNextGrid();
            }
        };
        Timer timer = new Timer(15, gridRefresher);
        timer.start();
    }

    public Grain[][] getZeroGrid() {
        Grain[][] newGrid = new Grain[(HEIGHT / CELL_SIZE)-7][(WIDTH / CELL_SIZE)];
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                newGrid[i][j] = null;
            }
        }
        return newGrid;
    }

    public void sandPlaced(int row, int col) {
        if(i % 15 == 0) color = rcs.getNextColor();
        grid[row][col] = new Grain(this.color);
        i++;
    }

    public void getNextGrid() {
        Grain[][] newGrid = getZeroGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                determinePlacement(newGrid, i, j);
            }
        }
        grid = newGrid;
        drawGrid();
    }

    public void determinePlacement(Grain[][] newGrid, int i, int j) {
        if (grid[i][j] != null && i < grid.length - 1 && grid[i + 1][j] == null) { // if cell is sand and cell below is
            newGrid[i + 1][j] = grid[i][j];
        } else if(grid[i][j] != null && i < grid.length -1 && j > 0 && j < grid[i].length-1 && grid[i+1][j+1] == null && grid[i+1][j-1] == null) { // if in bounds and cell below taken but either side of cell below is empty
            if(Math.random() < 0.5) {
                newGrid[i + 1][j + 1] = grid[i][j];
            } else {
                newGrid[i + 1][j - 1] = grid[i][j];
            }
        } else if (grid[i][j] !=  null && i < grid.length -1 && j < grid[i].length-1 && grid[i+1][j+1] == null) { // if in bounds and cell below taken but left of cell below is empty
            newGrid[i + 1][j + 1] = grid[i][j];
        } else if (grid[i][j] != null && i < grid.length -1 && j > 0 && grid[i+1][j-1] == null) { // if in bounds and cell below taken but right of cell below is empty
            newGrid[i + 1][j - 1] = grid[i][j];
        }else if(grid[i][j] != null) {
            newGrid[i][j] = grid[i][j];
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
                if (grid[i][j] != null) {
                    Grain grain = grid[i][j];
                    shadeCell(offScreenGraphics, grain.getColor(), i, j);
                }
            }
        }

        Graphics panelGraphics = gui.panel.getGraphics();
        panelGraphics.drawImage(offScreenImage, 0, 0, gui.panel);

        offScreenGraphics.dispose();
        panelGraphics.dispose();
    }




    public String toString() {
        return "Welcome to Falling Sand! Click and drag to place sand.";
    }
}
