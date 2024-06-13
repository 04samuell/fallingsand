import java.awt.event.*;

public  class FallingSandMouseListener implements MouseListener, MouseMotionListener{
    
    private FallingSand fs;

    public FallingSandMouseListener(FallingSand fs) {
        this.fs = fs;
    }


    public void mousePressed(MouseEvent e) {
        int row = e.getY() / FallingSand.CELL_SIZE;
        int col = e.getX() / FallingSand.CELL_SIZE;
        fs.sandPlaced(row-3, col); 
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged");
        
    }
    

    //To fulfill the interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    
}
