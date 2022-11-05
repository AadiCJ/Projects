package Paint;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ColorSwitcher extends JFrame{
    final int WIDTH = 500;
    final int HEIGHT = 500;
    JPanel colorViewer = new JPanel();
    JSlider r = new JSlider(JSlider.HORIZONTAL, 255, 255);
    JSlider g = new JSlider(JSlider.HORIZONTAL, 255, 255);
    JSlider b = new JSlider(JSlider.HORIZONTAL, 255, 255);
    JLabel rL = new JLabel("Red: 255");
    JLabel gL = new JLabel("Green: 255");
    JLabel bL = new JLabel("Blue: 255");
    int R = 255;
    int G = 255;
    int B = 255;
    JButton submit = new JButton();
    ColorSwitcher(){
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(WIDTH, HEIGHT);
        setTitle("Color Switcher");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        colorViewer.setPreferredSize(new Dimension(WIDTH/2-25, HEIGHT/2-25));
        colorViewer.setLocation(0, 0);
        colorViewer.setBackground(Color.white);
        add(colorViewer);
        add(rL);
        add(gL);
        add(bL);
        r.setLocation(0, HEIGHT/2+5);
        r.setMinorTickSpacing(5);
        r.setMajorTickSpacing(25);
        r.setPaintTicks(true);
        r.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                R = r.getValue();
                colorViewer.setBackground(new Color(R, G, B));
                rL.setText("Red: " + r.getValue());
            }
        });
        add(r);
        g.setLocation(0, r.getY()+5);
        g.setMinorTickSpacing(5);
        g.setMajorTickSpacing(25);
        g.setPaintTicks(true);
        g.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                G = g.getValue();
                colorViewer.setBackground(new Color(R, G, B));
                gL.setText("Green: " + g.getValue());
            }
        });
        add(g);
        b.setLocation(0 , g.getY()+5);
        b.setMinorTickSpacing(5);
        b.setMajorTickSpacing(25);
        b.setPaintTicks(true);
        b.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                B = b.getValue();
                colorViewer.setBackground(new Color(R, G, B));
                bL.setText("Blue: " + b.getValue());
            }
        });
        add(b);
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                PaintFrame.setColor(new int[]{R, G, B}, "");
            }
        });
        submit.setText("Submit");
        add(submit);
        setVisible(true);
    }
}
