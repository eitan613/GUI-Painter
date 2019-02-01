import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

//Eitan Adler

public class Paint extends JFrame {

    public static void main(String[] args) {
        Paint p = new Paint();
    }
    class CanvasPanel extends JPanel {

        public CanvasPanel() {
            setBackground(new Color(16, 248, 255));
        }

        @Override
        public void paint(Graphics g) { // auto called when needed
            super.paint(g);

            Graphics2D g2 = (Graphics2D) g;
            if (drawnHistory.size() <= 1) {
                return;
            }
            Point prev = null;
            g2.setStroke(new BasicStroke(5));
            for (Point p : drawnHistory) {
                if (prev != null) {
                    g2.drawLine(prev.x, prev.y, p.x, p.y);//should draw all shapes not just lines
                }
                prev = p;
            }
        }
    }
    public Paint() {
        setSize(600, 800);
        JPanel canvas = new CanvasPanel();
        add(BorderLayout.CENTER, canvas);

        JButton instructions = new JButton("Click and drag the mouse to draw the selected shape. Press here to " +
                "choose colors.");
        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        add(BorderLayout.SOUTH, instructions);

        JButton lineButton = new JButton("Click to draw lines.");
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   //enums or polymorphism
                drawLine = true;
                drawOval = false;
                drawRectangle = false;
            }
        });

        JButton ovalButton = new JButton("Click to draw ovals.");
        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLine = false;
                drawOval = true;
                drawRectangle = false;
            }
        });

        JButton rectangleButton = new JButton("Click to draw rectangles.");
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLine = false;
                drawOval = false;
                drawRectangle = true;
            }
        });

        add(BorderLayout.EAST, lineButton);
        add(BorderLayout.NORTH, ovalButton);
        add(BorderLayout.WEST, rectangleButton);


        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
               if(drawOval){
                   Graphics2D g2 = (Graphics2D) canvas.getGraphics();
                   g2.setColor(drawingColor);
                   g2.fillOval(me.getX() , me.getY(), 50, 50);
               }
               if (drawLine){
                   if (prev != null) {
                       Graphics2D g2 = (Graphics2D) canvas.getGraphics();
                       g2.setStroke(new BasicStroke(5));
                       g2.setColor(drawingColor);
                       g2.drawLine(me.getX(), me.getY(), prev.x, prev.y);
                   }
                   prev = me.getPoint();
                   drawnHistory.add(prev);
               }
               if (drawRectangle){
                   Graphics2D g2 = (Graphics2D) canvas.getGraphics();
                   g2.setColor(drawingColor);
                   g2.fillRect(me.getX(),me.getY(),50,50);
               }
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });
        setSize(1000,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    Color drawingColor = new Color(0,0,0);
    boolean drawLine, drawOval, drawRectangle;
    LinkedList<Point> drawnHistory = new LinkedList<>();
    Point prev;
}
