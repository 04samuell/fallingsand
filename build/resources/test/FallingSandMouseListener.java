import java.awt.event.*;
import javax.swing.*;

public  class FallingSandMouseListener implements MouseListener, MouseMotionListener{
    
    private FallingSand fs;
    private int[][] grid;
    private Timer timer;

    public FallingSandMouseListener(FallingSand fs ,int[][] grid) {
        this.fs = fs;
        this.grid = grid;
        this.timer = new Timer(100, e -> fs.refreshGrid());
    }


    public void mousePressed(MouseEvent e) {
        updateGrid(e);
        fs.refreshGrid();
        timer.start();
    }

    public void updateGrid(MouseEvent e) {
        int x = e.getX() / FallingSand.CELL_SIZE;
        int y = e.getY() / FallingSand.CELL_SIZE;
        if(x-1 < 0) x++;
        grid[x-1][y-3] = 1;  // offset slightly due to difference between panel and frame.
    }

    public void mouseReleased(MouseEvent e) {
        timer.stop();
    }

    //To fulfill the interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}


    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged");
        updateGrid(e);
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }
    
}
