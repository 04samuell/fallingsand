import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FallingSand {

    private Color grainColor = Color.red;
    private Color backGroundColor = Color.black;
    public Grain[][] grid;
    private RainbowColourScheme rcs;
    private FallingSandGUI gui;
    private int numberSandPlaced = 0;
    public boolean frenzy = false;
    public boolean placingSand = false;
    public int frenzyCounter;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final int CELL_SIZE = 10;

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
        if(numberSandPlaced % 15 == 0) grainColor = rcs.getNextColor();
        grid[row][col] = new Grain(this.grainColor);
        numberSandPlaced++;
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
        if(frenzy && placingSand) {
            backGroundColor = Color.white;
        } else {
            backGroundColor = Color.black;
        }
        //System.out.println(frenzy + " " + placingSand);
        //System.out.println(backGroundColor); 
        Graphics offScreenGraphics = gui.offScreenImage.getGraphics();
        offScreenGraphics.clearRect(0, 0, WIDTH, HEIGHT);
        offScreenGraphics.setColor(backGroundColor);
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

    public String toString() {
        String result = "";
        result += "*".repeat(60) + "\n\n";
        result += "   Welcome to Falling Sand! Click and drag to place sand :)";
        result += "\n\n" + "*".repeat(60);
        return result;
    }
}
