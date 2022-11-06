package WordCounter;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
public class WordCounterFrame extends JFrame{
    JTextArea field = new JTextArea();//where the user types.
    JButton button = new JButton();
    JLabel dataLabel = new JLabel();//shows the number of words and chars
    public WordCounterFrame(){
        setTitle("Word Counter");
        setVisible(true);
        setSize(new Dimension(750, 780));//30 extra in height cause i couldnt use pack()
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//DISPOSE_ON_CLOSE used so that the applauncher isnt closed
        setLayout(null);//absolute positioning is used
        field.setSize(new Dimension(750, 700));
        button.setSize(new Dimension(50, 50));//setting size
        dataLabel.setSize(new Dimension(700, 50));
        button.setLocation(0, 700);
        dataLabel.setLocation(50, 700);//setting locations
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int[] all = countAll(field.getText());//gets an int[] from the countAll method
                dataLabel.setText(String.format("Words: %d      Characters: %d", all[0], all[1]));
                //^ displays all the data.
            }
        });
        add(field);
        add(button);
        add(dataLabel);
        setLocationRelativeTo(null);//centres the frame
    }
    /**
     * @param in the string which is to be analysed
     */
    public int[] countAll(String in){
        //out[0] is the number of spaces, out[1] is length
        int[] out = {0, in.trim().length()};//trimmed to get length
        if(in.length() < 1) return out;//to avoid an error where nothing is inputted, but 1 word is present
        for(int i = 0; i < in.length(); i++){//loop through string
            if(in.charAt(i) == ' ' && i+1 < in.length())//if current location is space
                if(Character.isLetterOrDigit(in.charAt(i+1)))//and next location is digit or char
                    out[0]++;//increase number of spaces
        }
        out[0]++;//add one to convert number of spaces to number of words.
        return out;
    }
}
