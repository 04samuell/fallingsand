import java.awt.Color;

/**
 * A class to generate a rainbow color scheme.
 *
 */
public class RainbowColourScheme {

    private int steps = 150; // arbitrary starting point for the rainbow color scheme
    double stepSize = 1.0 / steps;

    /**
     * Get a color from the rainbow color scheme based on a position.
     * @param position
     * @return
     */
    public Color getRainbowColor(double position) {
        float hue = (float) (position % 1.0); // Position is a value between 0 and 1
        float saturation = 1.0f;
        float lightness = 0.5f;
        return Color.getHSBColor(hue, saturation, lightness);
    }

    
    /**
     * Get the next color in the rainbow color scheme.
     * @return the next color in the rainbow color scheme
     */
    public Color getNextColor() {
        if(steps == 360) steps = 0;
        double position = steps * stepSize;
        Color color = getRainbowColor(position);
        steps++;
        return color;
    }
}