import java.awt.*;
import java.awt.geom.*;

public class RotationExample extends Frame {
    private int windowHeight;

    // Earth's position and size
    private double earthX = 150;
    private double earthY = 150;
    private double earthSize = 20;

    // Sun's position and size
    private double sunX = 150;
    private double sunY = 150;
    private double sunSize = 50;

    // Angle of rotation for Earth around the Sun
    private double rotationAngle = 0;

    RotationExample(int height) {
        addWindowListener(new MyFinishWindow());
        windowHeight = height;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Real coordinate transformation
        AffineTransform yUp = new AffineTransform();
        yUp.setToScale(1, -1);
        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(40, windowHeight - 50);
        yUp.preConcatenate(translate);
        g2d.transform(yUp);

        // Thick lines
        g2d.setStroke(new BasicStroke(3.0f));

        // Draw Sun
        Ellipse2D.Double sun = new Ellipse2D.Double(sunX - sunSize / 2, sunY - sunSize / 2, sunSize, sunSize);
        g2d.setColor(Color.YELLOW);
        g2d.fill(sun);

        // Draw Earth
        double earthCenterX = earthX + Math.cos(rotationAngle) * 100;
        double earthCenterY = earthY + Math.sin(rotationAngle) * 100;
        Ellipse2D.Double earth = new Ellipse2D.Double(earthCenterX - earthSize / 2, earthCenterY - earthSize / 2, earthSize, earthSize);
        g2d.setColor(Color.BLUE);
        g2d.fill(earth);

        // Coordinate system
        drawSimpleCoordinateSystem(300, 300, g2d);
    }

    public static void drawSimpleCoordinateSystem(int xmax, int ymax, Graphics2D g2d) {
        int xOffset = 0;
        int yOffset = 0;
        int step = 20;
        String s;
        Font fo = g2d.getFont();
        int fontSize = 13;
        Font fontCoordSys = new Font("serif", Font.PLAIN, fontSize);
        AffineTransform flip = new AffineTransform();
        flip.setToScale(1, -1);
        AffineTransform lift = new AffineTransform();
        lift.setToTranslation(0, fontSize);
        flip.preConcatenate(lift);
        Font fontUpsideDown = fontCoordSys.deriveFont(flip);
        g2d.setFont(fontUpsideDown);

        // x-axis
        g2d.drawLine(xOffset, yOffset, xmax, yOffset);
        for (int i = xOffset + step; i <= xmax; i = i + step) {
            g2d.drawLine(i, yOffset - 2, i, yOffset + 2);
            g2d.drawString(String.valueOf(i), i - 7, yOffset - 30);
        }

        // y-axis
        g2d.drawLine(xOffset, yOffset, xOffset, ymax);

        s = "  ";
        for (int i = yOffset + step; i <= ymax; i = i + step) {
            g2d.drawLine(xOffset - 2, i, xOffset + 2, i);
            if (i > 99) {
                s = "";
            }
            g2d.drawString(s + String.valueOf(i), xOffset - 25, i - 20);
        }

        g2d.setFont(fo);
    }

    public static void main(String[] argv) {
        int height = 300;
        RotationExample f = new RotationExample(height);
        f.setTitle("Earth Rotation around Sun");
        f.setSize(400, 400);
        f.setVisible(true);

        // Rotation animation
        while (true) {
            f.rotationAngle += 0.01; // Increase the angle for rotation
            f.repaint(); // Redraw the frame
            try {
                Thread.sleep(50); // Delay to control the speed of rotation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
