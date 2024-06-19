import java.awt.Color;

/**
 * Class to represent a grain of sand in the Falling Sand game.
 */
public class Grain {
    
    private Color color; // A grain has a single attribute, color.

    /**
     * Constructor for the Grain instnace.
     * 
     * @param color the colour of the grain.
     */
    public Grain(Color color) {
        this.color = color;
    }

    /**
     * Get the color of the grain.
     * 
     * @return the grains color.
     */
    public Color getColor() {
        return color;
    }

}
