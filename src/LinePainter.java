import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LinePainter implements Painter {

    private Point prev;

    @Override
    public void paint(Graphics graphics, Color drawingColor, MouseEvent mouseEvent) {
        if (prev != null) {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setStroke(new BasicStroke(5));
            g2.setColor(drawingColor);
            g2.drawLine(mouseEvent.getX(), mouseEvent.getY(), prev.x, prev.y);
        }
        prev = mouseEvent.getPoint();
    }
}