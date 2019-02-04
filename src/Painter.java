import java.awt.*;
import java.awt.event.MouseEvent;

public interface Painter {
    public void paint(Graphics graphics, Color drawingColor, MouseEvent me);
}
