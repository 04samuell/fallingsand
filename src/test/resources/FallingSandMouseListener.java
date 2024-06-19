import java.awt.event.*;

/**
 * Class to handle the mouse events for the Falling Sand game.
 */
public  class FallingSandMouseListener implements MouseListener, MouseMotionListener{
    
    private FallingSand fs;
    private static final float PROBABILITY = 0.65f;
    private static final int ERROR = 3;

    /**
     * Constructor for the FallingSandMouseListener class.
     * @param fs the FallingSand instance.
     */
    public FallingSandMouseListener(FallingSand fs) {
        this.fs = fs;
    }

    /**
     * Method to handle the mouse pressed event.
     * 
     * @param e the mouse event.
     */
    public void mousePressed(MouseEvent e) {
        int row = e.getY() / FallingSand.CELL_SIZE;
        int col = e.getX() / FallingSand.CELL_SIZE;
        handleSand(row - ERROR, col); 
    }

    /**
     * Method to handle the mouse dragged event.
     * 
     * @param e the mouse event.
     */
    public void mouseDragged(MouseEvent e) {
        if(e.getX() < 0 || e.getX() < 0 || e.getY() < 30 || e.getX() >= FallingSand.WIDTH || e.getY() >= FallingSand.HEIGHT-40) return;
        int row = e.getY() / FallingSand.CELL_SIZE;
        int col = e.getX() / FallingSand.CELL_SIZE;
        handleSand(row - ERROR, col);
    }


    /**
     * Method to handle the click location.
     * Places sand at the given location and randomly placed sand in surrounding locations
     * based on the value of PROBABILITY.
     * 
     * @param row the row of the grid clicked on.
     * @param col the column of the grid clicked on.
     */
    private void handleSand(int row, int col) {
        if(fs.grid[row][col] != null) return; // if already sand there, don't place more
        fs.sandPlaced(row, col);

        if(Math.random() < PROBABILITY && row < fs.grid.length-1) {
            fs.sandPlaced(row+1, col);
        }
        if(Math.random() < PROBABILITY && row < fs.grid.length-1 && col < fs.grid[0].length-1) {
            fs.sandPlaced(row+1, col+1);
        }
        if(Math.random() < PROBABILITY && row < fs.grid.length-1 && col > 0) {
            fs.sandPlaced(row+1, col-1);
        }
        if(Math.random() < PROBABILITY && col < fs.grid[0].length-1) {
            fs.sandPlaced(row, col+1);
        }
        if(Math.random() < PROBABILITY && col > 0) {
            fs.sandPlaced(row, col-1);
        }
        if(Math.random() < PROBABILITY && row > 1) {
            fs.sandPlaced(row-1, col);
        }
        if(Math.random() < PROBABILITY && row > 1 && col < fs.grid[0].length-1) {
            fs.sandPlaced(row-1, col+1);
        }
        if(Math.random() < PROBABILITY && row > 1 && col > 0) {
            fs.sandPlaced(row-1, col-1);
        }
        if(!fs.thick) return;
        if(Math.random() < PROBABILITY && row < fs.grid.length-2 && col > 1) {
            fs.sandPlaced(row+2, col-2);
        }
        if(Math.random() < PROBABILITY && row < fs.grid.length-2 && col > 0) {
            fs.sandPlaced(row+2, col-1);
        }
        if(Math.random() < PROBABILITY && row < fs.grid.length-2) {
            fs.sandPlaced(row+2, col);
        }
        if(Math.random() < PROBABILITY && row < fs.grid.length-1 && col < fs.grid[0].length-1) {
            fs.sandPlaced(row+2, col+1);
        }
        if(Math.random() < PROBABILITY && row < fs.grid.length-1 && col < fs.grid[0].length-2) {
            fs.sandPlaced(row+2, col+2);
        }
        if(Math.random() < PROBABILITY && row > 1 && col > 1) {
            fs.sandPlaced(row-2, col-2);
        }
        if(Math.random() < PROBABILITY && row > 1 && col > 0) {
            fs.sandPlaced(row-2, col-1);
        }
        if(Math.random() < PROBABILITY && row > 1) {
            fs.sandPlaced(row-2, col);
        }
        if(Math.random() < PROBABILITY && row > 1 && col < fs.grid[0].length-1) {
            fs.sandPlaced(row-2, col+1);
        }
        if(Math.random() < PROBABILITY && row > 1 && col < fs.grid[0].length-2) {
            fs.sandPlaced(row-2, col+2);
        }
        if(Math.random() < PROBABILITY && col > 1) {
            fs.sandPlaced(row, col-2);
        }
        if(Math.random() < PROBABILITY && col < fs.grid[0].length-2) {
            fs.sandPlaced(row, col+2);
        }
        if(Math.random() < PROBABILITY && row > 0 && col > 1) {
            fs.sandPlaced(row-1, col-2);
        }
        if(Math.random() < PROBABILITY && row > 0 && col < fs.grid[0].length-2) {
            fs.sandPlaced(row-1, col+2);
        }
        if(Math.random() < PROBABILITY && row > 0 && col > 1) {
            fs.sandPlaced(row+1, col-2);
        }
        if(Math.random() < PROBABILITY && row > 0 && col < fs.grid[0].length-2) {
            fs.sandPlaced(row+1, col+2);
        }

    }
    
    //To fulfill the interface
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    

    
}
