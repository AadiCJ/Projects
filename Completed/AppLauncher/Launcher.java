import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class Launcher extends JFrame{
    LinkedHashMap<JButton, String> apps = new LinkedHashMap<JButton, String>();
    Launcher(){
        setTitle("App Launcher");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 3));
        apps.put(new JButton(), "Tic Tac Toe");
        apps.put(new JButton(), "Snake");
        apps.put(new JButton(), "Calculator");
        apps.put(new JButton(), "Word Counter");
        apps.put(new JButton(), "Colour Switcher");
        apps.put(new JButton(), "Paint");
        for(JButton b : apps.keySet()){
            b.setText(apps.get(b));
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    for(JButton b : apps.keySet()){
                        if(b == e.getSource()){
                            openApp(apps.get(b));
                        }
                    }
                }
            });
            add(b);
        }
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void openApp(String in){
        switch(in){
            case "Tic Tac Toe":
                new TTC.TicTacToe();
                break;
            case "Snake":
                new Snake.SnakeFrame();
                break;
            case "Calculator":
                new Calculator.SwingShell();
                break;
            case "Word Counter":
                new WordCounter.WordCounterFrame();
                break;
            case "Colour Switcher":
                new ColorSwitcher.ColorSwitcherFrame();
                break;
            case "Paint":
                new Paint.PaintFrame();
                break;
        }
    }
}