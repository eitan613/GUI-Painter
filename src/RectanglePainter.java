import java.awt.*;
import java.awt.event.MouseEvent;

public class RectanglePainter implements Painter {
    @Override
    public void paint(Graphics graphics, Color drawingColor, MouseEvent me) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(drawingColor);
        g2.fillRect(me.getX() , me.getY(), 50, 50);
    }
}
