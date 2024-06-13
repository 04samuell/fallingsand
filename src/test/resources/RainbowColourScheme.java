import java.awt.Color;

public class RainbowColourScheme {

    private int steps;

    /**
     * Get a color from the rainbow color scheme based on a position.
     * @param position
     * @return
     */
    public static Color getRainbowColor(double position) {
        // Calculate hue based on position (0 to 1 for a full circle)
        float hue = (float) (position % 1.0);

        // Set saturation and lightness to maximum for vibrant colors
        float saturation = 1.0f;
        float lightness = 0.5f;

        // Convert HSL to RGB
        return Color.getHSBColor(hue, saturation, lightness);
    }

    public Color getNextColor() {
        int steps = 360;
        
        double stepSize = 1.0 / steps;
        Color color = getRainbowColor(position);
        return color;

        // Iterate through steps and get the corresponding color
        for (int i = 0; i < steps; i++) {
            double position = i * stepSize;
            Color color = getRainbowColor(position);
            System.out.println(color);
        }
    }
}

