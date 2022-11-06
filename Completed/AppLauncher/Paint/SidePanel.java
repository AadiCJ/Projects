package Paint;
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
/**
 * has all the controls for the paint application, such as eraser, colorswitcher, brush size, etc
 */
public class SidePanel extends JPanel{
    static final int WIDTH = 100;//width of panel
    static final int HEIGHT = 750;//height of the panel.
    JButton[] buttons = new JButton[10];//all the buttons in the panel.
    JButton clear = new JButton();//button that clears the background when pressed.
    JButton fill = new JButton();//button that fills the background with the selected color when pressed.
    JSlider brush = new JSlider(JSlider.VERTICAL, 0, 25, 12);//changes brush size.
    LinkedHashMap<String, int[]> colors = new LinkedHashMap<String, int[]>();
    //the above is a linkedhashmap which has Strings corresponding to color names and 
    //int arrays corresponding to their RGB values.
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
        colors.put("Eraser", new int[]{-1, -1, -1});//no color is assigned to it, when pressed
        //the Frame.java looks at the special string an sets color to the background color of the painting
        //region
        colors.put("ColorSwitcher", new int[]{-1, -1, -1});//no colour is assinged to it, when pressed
        //the Frame.java looks at the special string and creates a new object of the ColorSwitcher.java file.
        //setting all the values in the linkedhashmap
        clear.setText("Clear");//this clears the entire screen when pressed.
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                PaintFrame.clearAll();//this static method calls the Panel.clearAll() method(non static)
            }
        });
        add(clear);
        setLayout(new FlowLayout());//to arrange the buttons 
        for(int i = 0; i < buttons.length; i++){//looping through the buttons array.
            buttons[i] = new JButton();
            buttons[i].setText((String)colors.keySet().toArray()[i]);//set text of button to value in the keys of hashmap
            buttons[i].setForeground(Color.white);//set text colour to white
            int[] here =(int[])colors.values().toArray()[i];//get the value of the array and set 
            //the colour of the button to the value of the array.
            if(here[0] != -1) buttons[i].setBackground(new Color(here[0], here[1], here[2]));
            if(colors.keySet().toArray()[i].equals("White") || colors.keySet().toArray()[i].equals("Eraser") || colors.keySet().toArray()[i].equals("ColorSwitcher")) 
                buttons[i].setForeground(Color.black);//if the colour is white, or it is the colorswitcher or eraser, set text to black
            buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    PaintFrame.setColor(colors.get(((JButton)e.getSource()).getText()), ((JButton)e.getSource()).getText());    
                    //set the colour of the paint panel to whatever button is clicked.
                }
            });
            add(buttons[i]);//add the button.
        }
        fill.setText("Fill");
        fill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                PaintFrame.fillBack();//a button that fills the background with the selected colour.
            }
        });
        add(fill);
        brush.setMajorTickSpacing(2);//setting tick spacing.
        brush.setPaintLabels(true);
        brush.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                PaintFrame.setBrushSize(brush.getValue());//setting brush size.
            }
        });
        add(brush);//add brush size setter.
    }
}