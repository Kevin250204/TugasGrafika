import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class InitialsTransformation extends JFrame {

    public InitialsTransformation() {
        super("Initials Transformation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw initial letter 'A'
        g2d.setColor(Color.black);
        drawLetterA(g2d, 50, 150);

        // Draw target letter 'B'
        g2d.setColor(Color.blue);
        drawLetterB(g2d, 200, 150);
    }

    private void drawLetterA(Graphics2D g2d, int x, int y) {
        // Draw the shapes for letter 'A'
        GeneralPath letterA = new GeneralPath();
        letterA.moveTo(x, y);
        letterA.lineTo(x + 20, y + 40);
        letterA.lineTo(x + 40, y);
        letterA.moveTo(x + 10, y + 20);
        letterA.lineTo(x + 30, y + 20);
        g2d.draw(letterA);
    }

    private void drawLetterB(Graphics2D g2d, int x, int y) {
        // Draw the shapes for letter 'B'
        GeneralPath letterB = new GeneralPath();
        letterB.moveTo(x, y);
        letterB.lineTo(x, y + 40);
        letterB.curveTo(x, y + 40, x + 20, y + 20, x, y);
        letterB.moveTo(x, y + 20);
        letterB.lineTo(x + 20, y + 20);
        g2d.draw(letterB);
    }

    private void transformAToB(Graphics2D g2d) {
        // Apply affine transformations to transform 'A' to 'B'
        AffineTransform transform = new AffineTransform();
        transform.translate(100, 0); // Translate 'A' horizontally
        transform.rotate(Math.PI / 4, 50, 150); // Rotate 'A' by 45 degrees around (50, 150)
        g2d.transform(transform); // Apply the transformation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InitialsTransformation frame = new InitialsTransformation();
            frame.setVisible(true);
        });
    }
}
