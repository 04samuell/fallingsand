import java.awt.event.*;

public  class FallingSandMouseListener implements MouseListener{
    
    private FallingSand fs;
    private int[][] grid;

    public FallingSandMouseListener(FallingSand fs ,int[][] grid) {
        this.fs = fs;
        this.grid = grid;
    }


    public void mousePressed(MouseEvent e) {
        int x = e.getX() / FallingSand.CELL_SIZE;
        int y = e.getY() / FallingSand.CELL_SIZE;
        grid[x][y] = 1;
        fs.refreshGrid();
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse released");
    }

    //To fulfill the interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
}
