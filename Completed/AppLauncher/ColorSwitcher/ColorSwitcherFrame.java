package ColorSwitcher;
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

/**
 * this JFrame is run when the ColorSwitcher option is clicked in the paint application
 */
public class ColorSwitcherFrame extends JFrame{
    final int WIDTH = 500;//width of the frame
    final int HEIGHT = 500;//height of the frame.
    JPanel colorViewer = new JPanel();//the JPanel where the color is shown
    JSlider r = new JSlider(JSlider.HORIZONTAL, 255, 255);
    JSlider g = new JSlider(JSlider.HORIZONTAL, 255, 255);
    JSlider b = new JSlider(JSlider.HORIZONTAL, 255, 255);//3 sliders corresponding to RGB
    JLabel rL = new JLabel("Red: 255");
    JLabel gL = new JLabel("Green: 255");
    JLabel bL = new JLabel("Blue: 255");//labels showing current value of r, g and b
    int R = r.getValue();
    int G = g.getValue();
    int B = b.getValue();//values of the JSliders.
    JButton submit = new JButton();//to actually change the colour in the paint app
    ColorSwitcherFrame(){
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));//box layout is used
        setSize(WIDTH, HEIGHT);
        setTitle("Color Switcher");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//DISPOSE_ON_CLOSE is used so that the applauncher application is not closed
        colorViewer.setPreferredSize(new Dimension(WIDTH/2-25, HEIGHT/2-25));//to ensure the colorViewer panel is smaller than the actual JFrame
        colorViewer.setLocation(0, 0);
        colorViewer.setBackground(new Color(R, G, B));//set the background in relaton the RGB values
        add(colorViewer);
        add(rL);
        add(gL);
        add(bL);
        r.setLocation(0, HEIGHT/2+5);
        r.setMinorTickSpacing(5);
        r.setMajorTickSpacing(25);//set spacing
        r.setPaintTicks(true);//add
        r.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                R = r.getValue();
                colorViewer.setBackground(new Color(R, G, B));
                rL.setText("Red: " + r.getValue());
            }//changes the text of the label that shows the value of the slider and changes the value of R
        });//a similar process is followed for the other two primary colours
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
        setVisible(true);
    }
}
