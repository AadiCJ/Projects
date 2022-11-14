import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
public class Shell extends JFrame{
    public static void main(String[] args) {
        new Shell();
    }
    public Shell() {
        setTitle("Login App");
        setPreferredSize(new Dimension(750, 750));
        setLayout(new BorderLayout());
        pack();
        setLocationRelativeTo(null);
;    }
}
