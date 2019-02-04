import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paint {
    private JFrame frame;
    private JPanel canvas;
    private Painter painter;
    private Color drawingColor;

    public static void main(String[] args) {
        new Paint();
    }

    private Paint() {
        setFrame();
        setPanel();
        setButtons();
        frame.setVisible(true);
    }

    private void setButtons() {
        JPanel buttonsPanel = new JPanel();
        frame.add(BorderLayout.EAST,buttonsPanel);

        JButton drawingColorButton = new JButton("Press here to choose drawing colors.");
        drawingColorButton.addActionListener(e -> {
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

        JButton backgroundColorButton = new JButton("Press here to choose background color.");
        backgroundColorButton.addActionListener(e -> {
            JColorChooser colorPanel = new JColorChooser();
            class okListener implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.setBackground(colorPanel.getColor());
                }
            }
            colorPanel.setPreviewPanel(new JPanel());
            JDialog colorSetter = JColorChooser.createDialog(null,"Pick a color",false,colorPanel,
                    new okListener(),null);
            colorSetter.setVisible(true);
        });

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
        JButton clear = new JButton("Clear the canvas");
        clear.addActionListener(e ->{
            canvas.revalidate();
            canvas.repaint();
        });

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
        buttonsPanel.add(lineButton);
        buttonsPanel.add(ovalButton);
        buttonsPanel.add(rectangleButton);
        buttonsPanel.add(drawingColorButton);
        buttonsPanel.add(backgroundColorButton);
        buttonsPanel.add(clear);
    }

    private void setPanel() {
        canvas = new JPanel();
        drawingColor = new Color(0,0,0);
        canvas.setBackground(new Color(16, 248, 255));
        frame.add(BorderLayout.CENTER, canvas);
        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                painter.paint(canvas.getGraphics(),drawingColor,mouseEvent);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
            }
        });
    }

    private void setFrame() {
        frame = new JFrame("Click and drag the mouse to draw the selected shape.");
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
