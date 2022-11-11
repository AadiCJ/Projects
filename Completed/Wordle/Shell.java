package Wordle;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Color;
/**
 * Shell
 */
public class Shell extends JFrame{
    public static void main(String[] args) {
        new Shell();
    }
    public Shell() {
        getContentPane().setBackground(Color.black);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Wordle");
        add(new Wordle());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}