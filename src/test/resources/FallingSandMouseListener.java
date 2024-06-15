import java.awt.event.*;

public  class FallingSandMouseListener implements MouseListener, MouseMotionListener{
    
    private FallingSand fs;
    private static final float PROBABILITY = 0.65f;
    private static final int ERROR = 3;

    public FallingSandMouseListener(FallingSand fs) {
        this.fs = fs;
    }

    public void mousePressed(MouseEvent e) {
        if(fs.frenzy) {
            fs.placingSand = true;
            fs.frenzyCounter++;
        }
        int row = e.getY() / FallingSand.CELL_SIZE;
        int col = e.getX() / FallingSand.CELL_SIZE;
        handleSand(row - ERROR, col); 
    }

    public void mouseReleased(MouseEvent e) {
        if(fs.frenzy) {
            fs.placingSand = false;
            fs.frenzyCounter = 0;
        } 
    }

    public void mouseDragged(MouseEvent e) {
        if(e.getX() < 0 || e.getX() < 0 || e.getY() < 30 || e.getX() >= FallingSand.WIDTH || e.getY() >= FallingSand.HEIGHT-40) return;
        int row = e.getY() / FallingSand.CELL_SIZE;
        int col = e.getX() / FallingSand.CELL_SIZE;
        handleSand(row - ERROR, col);
    }

    public void handleSand(int row, int col) {
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

    }
    
    //To fulfill the interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    

    
}
