import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class SolarSystemAnimation extends frame {

    private int windowWidth;
    private int windowHeight;
    private double planetOrbitRadius = 200; // Distance between sun and planet
    private double planetAngle = 0; // Initial angle of the planet

    public SolarSystemAnimation(int width, int height) {
        super("Solar System Animation");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);

        windowWidth = width;
        windowHeight = height;

        // Start the animation timer
        Timer timer = new Timer(30, e -> {
            // Update the angle of the planet for animation
            planetAngle += Math.toRadians(1); // 1 degree per frame
            if (planetAngle >= 2 * Math.PI) {
                planetAngle -= 2 * Math.PI; // Wrap around at 360 degrees
            }
            repaint(); // Redraw the animation
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear the window
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, windowWidth, windowHeight);

        // Translate to the center of the window
        g2d.translate(windowWidth / 2, windowHeight / 2);

        // Draw the sun at the origin
        double sunRadius = 30; // Sun's radius
        g2d.setColor(Color.yellow);
        g2d.fillOval((int) (-sunRadius), (int) (-sunRadius), (int) (2 * sunRadius), (int) (2 * sunRadius));

        // Calculate the position of the planet based on the current angle
        double planetX = planetOrbitRadius * Math.cos(planetAngle);
        double planetY = planetOrbitRadius * Math.sin(planetAngle);

        // Draw the planet
        double planetRadius = 10; // Planet's radius
        g2d.setColor(Color.blue);
        g2d.fillOval((int) (planetX - planetRadius), (int) (planetY - planetRadius), (int) (2 * planetRadius), (int) (2 * planetRadius));

        // Calculate the position of the point closest to the sun on the planet
        double closestPointX = planetX - planetRadius * Math.cos(planetAngle);
        double closestPointY = planetY - planetRadius * Math.sin(planetAngle);

        // Draw the point closest to the sun
        g2d.setColor(Color.red);
        g2d.fillOval((int) (closestPointX - 2), (int) (closestPointY - 2), 4, 4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SolarSystemAnimation frame = new SolarSystemAnimation(400, 400);
            frame.setVisible(true);
        });
    }
}
