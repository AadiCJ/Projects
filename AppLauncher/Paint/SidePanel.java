package AppLauncher.Paint;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.LinkedHashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class SidePanel extends JPanel{
    static final int WIDTH = 100;
    static final int HEIGHT = 750;
    JButton[] buttons = new JButton[10];
    JButton clear = new JButton();
    JButton fill = new JButton();
    JSlider brush = new JSlider(JSlider.VERTICAL, 0, 25, 12);
    LinkedHashMap<String, int[]> colors = new LinkedHashMap<String, int[]>();
    SidePanel(){
        setBackground(Color.white);
        setSize(WIDTH, HEIGHT);
        colors.put("Black", new int[]{0, 0, 0});
        colors.put("White", new int[]{255, 255, 255});
        colors.put("Red", new int[]{255, 0, 0});
        colors.put("Green", new int[]{0, 255, 0});
        colors.put("Blue", new int[]{0, 0, 255});
        colors.put("Orange", new int[]{255, 127, 0});
        colors.put("Purple", new int[]{37, 24, 88});
        colors.put("Yellow", new int[]{235, 193, 6});
        colors.put("Eraser", new int[]{-1, -1, -1});
        colors.put("ColorSwitcher", new int[]{-1, -1, -1});
        clear.setText("Clear");
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Frame.clearAll();
            }
        });
        add(clear);
        setLayout(new FlowLayout());
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new JButton();
            buttons[i].setText((String)colors.keySet().toArray()[i]);
            int[] here =(int[])colors.values().toArray()[i];
            buttons[i].setForeground(Color.white);
            if(here[0] != -1) buttons[i].setBackground(new Color(here[0], here[1], here[2]));
            if(colors.keySet().toArray()[i].equals("White") || colors.keySet().toArray()[i].equals("Eraser") || colors.keySet().toArray()[i].equals("ColorSwitcher")) buttons[i].setForeground(Color.black);
            buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Frame.setColor(colors.get(((JButton)e.getSource()).getText()), ((JButton)e.getSource()).getText());    
                }
            });
            add(buttons[i]);
        }
        fill.setText("Fill");
        fill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Frame.fillBack();
            }
        });
        add(fill);
        setSize(WIDTH ,HEIGHT);
        brush.setMajorTickSpacing(2);
        brush.setPaintLabels(true);
        brush.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                Frame.setBrushSize(brush.getValue());
            }
        });
        add(brush);
    }
}