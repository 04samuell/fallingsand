import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Class to represent the grid of sand grains in the Falling Sand game.
 */
public class FallingSand {

    public Grain[][] grid;
    private Color grainColor = Color.red;
    private int numberSandPlaced = 0;
    public boolean thick = false;
    public boolean grey = false;
    public boolean yellow = false;

    private RainbowColourScheme rcs;
    private FallingSandGUI gui;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final int CELL_SIZE = 10;

    /**
     * Constructor for the FallingSand class.
     */
    public FallingSand() {
        rcs = new RainbowColourScheme();
        gui = new FallingSandGUI(this);
        gui.createGUI();
        this.grid = getZeroGrid();
        ActionListener gridRefresher = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNextGrid();
            }
        };
        Timer timer = new Timer(15, gridRefresher);
        timer.start();
    }


    /**
     * Creates a grid of sand where each element is null.
     * 
     * @return the null grid.
     */
    public Grain[][] getZeroGrid() {
        Grain[][] newGrid = new Grain[(HEIGHT / CELL_SIZE)-7][(WIDTH / CELL_SIZE)];
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                newGrid[i][j] = null;
            }
        }
        return newGrid;
    }


    /**
     * Method to place sand on the grid.
     * 
     * @param row the row of the sand to be placed.
     * @param col the column of the sand to be placed
     */
    public void sandPlaced(int row, int col) {
        if(grey) {
            grid[row][col] = new Grain(Color.gray);
            return;
        }
        if(yellow) {
            grid[row][col] = new Grain(new Color(200, 200, 100));
            return;
        }
        if(numberSandPlaced % 15 == 0) grainColor = rcs.getNextColor();  
        grid[row][col] = new Grain(this.grainColor);
        numberSandPlaced++;
    }


    /**
     * Method to get the next grid (frame of the simulation).
     * Loops through the current grid and determines the position of the sand for the next frame.
     */
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


    /**
     * Helper method for getNextGrid.
     * Determines the placement of the sand for the next frame based off
     * the previous frame. If the sand has nothing below it, it will fall.
     * 
     * @param newGrid the new grid to be updated.
     * @param i the row index of the current sand grain we are considering .
     * @param j the column index of the current sand grain we are considering.
     */
    private void determinePlacement(Grain[][] newGrid, int i, int j) {
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


    /**
     * Method to draw the grid.
     * Loops through the grid and shades each cell to the offScreenImage.
     * Finally, draws the result to the screen.
     */
    public void drawGrid() {
        Graphics offScreenGraphics = gui.offScreenImage.getGraphics();
        offScreenGraphics.clearRect(0, 0, WIDTH, HEIGHT);
        offScreenGraphics.setColor(Color.BLACK);
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
        panelGraphics.drawImage(gui.offScreenImage, 0, 0, gui.panel);
        offScreenGraphics.dispose();
        panelGraphics.dispose();
    }

    /**
     * Method to shade a cell on the grid.
     *  
     * @param g the graphics object we are drawing to.
     * @param color the colour of the sand grain to draw.
     * @param i the row index of the current sand grain we are considering.
     * @param j the column index of the current sand grain we are considering.
     */
    private void shadeCell(Graphics g, Color color, int i, int j) {
        g.setColor(color);
        g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * String representation of the FallingSand class.
     * Printed at the start of the program.
     */
    public String toString() {
        String result = "";
        result += "*".repeat(60) + "\n\n";
        result += "   Welcome to Falling Sand! Click and drag to place sand :)";
        result += "\n\n" + "*".repeat(60);
        return result;
    }
    
}
