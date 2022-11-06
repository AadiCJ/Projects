import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class Launcher extends JFrame{
    LinkedHashMap<JButton, String> apps = new LinkedHashMap<JButton, String>();
    //hashmap to store a jbutton and related app name.
    Launcher(){
        setTitle("App Launcher");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//is this is closed, all Jframes closed.
        setLayout(new GridLayout(2, 3));
        apps.put(new JButton(), "Tic Tac Toe");
        apps.put(new JButton(), "Snake");
        apps.put(new JButton(), "Calculator");
        apps.put(new JButton(), "Word Counter");
        apps.put(new JButton(), "Colour Switcher");
        apps.put(new JButton(), "Paint");//add all keys: value pairs.
        for(JButton b : apps.keySet()){//loop throught keys.
            b.setText(apps.get(b));//set text.
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    for(JButton b : apps.keySet()){
                        if(b == e.getSource()){//if the source is a particular button
                            openApp(apps.get(b));//open the relevant app
                        }
                    }
                }
            });
            add(b);//add each button
        }
        pack();
        setLocationRelativeTo(null);//centre frame
        setVisible(true);
    }
    public void openApp(String in){
        switch(in){
            case "Tic Tac Toe":
                new TTC.TicTacToe();//open tic tac toe
                break;
            case "Snake":
                new Snake.SnakeFrame();//open snake
                break;
            case "Calculator":
                new Calculator.SwingShell();//open calculator
                break;
            case "Word Counter":
                new WordCounter.WordCounterFrame();//open wordcounter
                break;
            case "Colour Switcher":
                new ColorSwitcher.ColorSwitcherFrame();//this may give some errors
                //^ namely ColorSwitcher.ColorSwitcherFrame cannot be resolved to a type
                //this is cause due to a package and a file having the same name
                //in the Paint project, there is a colorswitcher file, the compiler may mistake
                //this ColorSwitcher package and that file, atleast thats what happened to me.
                //the error is fixed if you take the AppLauncher folder out of the Projects folder.
                break;
            case "Paint":
                new Paint.PaintFrame();
                break;
        }
    }
}
