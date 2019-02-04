import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paint extends JFrame {
    private Painter painter;
    private Color drawingColor = new Color(0,0,0);

    public static void main(String[] args) {
        new Paint();
    }
    class CanvasPanel extends JPanel {

        public CanvasPanel() {
            setBackground(new Color(16, 248, 255));
        }
    }
    private Paint() {
        setSize(600, 800);
        JPanel canvas = new CanvasPanel();
        add(BorderLayout.CENTER, canvas);

        JButton instructions = new JButton("Click and drag the mouse to draw the selected shape. Press here to " +
                "choose colors.");
        instructions.addActionListener(e -> {
            JColorChooser colorPanel = new JColorChooser();
            class okListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingColor = colorPanel.getColor();
                }
            }
            colorPanel.setPreviewPanel(new JPanel());
            JDialog colorSetter = JColorChooser.createDialog(null,"Pick a color",false,colorPanel,
                    new okListener(),null);
            colorSetter.setVisible(true);
        });

        add(BorderLayout.SOUTH, instructions);

        JButton lineButton = new JButton("Click to draw lines.");
        lineButton.addActionListener(e -> {
            painter = new LinePainter();
        });

        JButton ovalButton = new JButton("Click to draw ovals.");
        ovalButton.addActionListener(e -> {
            painter = new OvalPainter();
        });

        JButton rectangleButton = new JButton("Click to draw rectangles.");
        rectangleButton.addActionListener(e -> {
            painter = new RectanglePainter();
        });

        add(BorderLayout.EAST, lineButton);
        add(BorderLayout.NORTH, ovalButton);
        add(BorderLayout.WEST, rectangleButton);

        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                painter.paint(canvas.getGraphics(),drawingColor,mouseEvent);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
            }
        });
        setSize(1000,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
