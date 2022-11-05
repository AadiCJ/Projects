package WordCounter;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
public class WordCounterFrame extends JFrame{
    JTextArea field = new JTextArea();
    JButton button = new JButton();
    JLabel dataLabel = new JLabel();
    public WordCounterFrame(){
        setTitle("Word Counter");
        setVisible(true);
        setSize(new Dimension(750, 780));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        field.setSize(new Dimension(750, 700));
        button.setSize(new Dimension(50, 50));
        button.setLocation(0, 700);
        dataLabel.setSize(new Dimension(700, 50));
        dataLabel.setLocation(50, 700);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int[] all = countAll(field.getText());
                dataLabel.setText(String.format("Words: %d      Characters: %d", all[0], all[1]));
            }
        });
        add(field);
        add(button);
        add(dataLabel);
        setLocationRelativeTo(null);
    }
    public int[] countAll(String in){
        int[] out = {0, in.trim().length()};
        if(in.length() < 1) return out;
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) == ' ' && i+1 < in.length()){
                if(Character.isLetterOrDigit(in.charAt(i+1))){
                    out[0]++;
                }
            }
        }
        out[0]++;
        return out;
    }
}
