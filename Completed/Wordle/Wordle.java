package Wordle;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import javax.swing.JLabel;
/**
 * Wordle
 */
public class Wordle extends JPanel{
    String word = "";
    final int[] x ={100, 200, 300, 400, 500};
    JLabel errLabel = new JLabel("This is the error label.");
    LinkedHashMap<Integer, String> inputs = new LinkedHashMap<Integer, String>();
    int counter = 0;
    JTextField inputField = new JTextField(); 
    ArrayList<String> words = new ArrayList<String>();
    ArrayList<String> validIn = new ArrayList<String>();
    /**
     * Method for debugging
     */
    // public static void main(String[] args) {
    //     new Wordle();
    // }
    public Wordle() {
        inputs.put(75, "");
        inputs.put(175, "");
        inputs.put(275, "");
        inputs.put(375, "");
        inputs.put(475, "");
        inputs.put(575, "");
        initFrame();
        
        setBackground(Color.black);
        Scanner wordsReader = new Scanner(getClass().getResourceAsStream("words.txt"));
        Scanner validInReader = new Scanner(getClass().getResourceAsStream("valid-inputs.txt"));
        while(wordsReader.hasNext()) words.add(wordsReader.next());
        while(validInReader.hasNext()) validIn.add(validInReader.next());

        Random r = new Random();
        errLabel.setFont(getFont());
        setFont(new Font("monospace", Font.PLAIN, 50));
        
        
        int pos = r.nextInt(words.size());
        word = words.get(pos);
        System.out.println(word);
    }
    private void initFrame(){
        setPreferredSize(new Dimension(625, 600));
        setLayout(null);

        inputField.setSize(75, 25);
        inputField.setLocation((600/2)-25, 575);
        add(inputField);

        errLabel.setSize(275, 25);
        errLabel.setLocation(0, 575);
        errLabel.setForeground(Color.white);
        add(errLabel);

        getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("pressed ENTER"), "enter");
        getActionMap().put("enter", new AbstractAction() {
            public void actionPerformed(ActionEvent e){
                String temp = inputField.getText();
                inputField.setText("");
                if(temp.length() != 5) {
                    errLabel.setText("Input has to be 5 letters");
                    return;
                }
                if(validIn.contains(temp.toLowerCase())) check(temp);
                else errLabel.setText("The input does not exist in valid inputs.");
            }
        });
    }
    private void check(String input){
        input = input.toUpperCase();
        
        int y = (Integer) inputs.keySet().toArray()[counter];
        inputs.replace(y, "", input);
        counter++;
        Graphics g = getGraphics();
        for(int i = 0; i < input.length(); i++) {
            if(input.toLowerCase().charAt(i) == word.charAt(i)) {
                g.setColor(Color.green);
                g.drawString(String.valueOf(input.charAt(i)), x[i], y);
            }
            else if(word.contains(String.valueOf(input.toLowerCase().charAt(i)))){
                g.setColor(Color.orange);
                g.drawString(String.valueOf(input.charAt(i)), x[i], y);
            }
            else{
                g.setColor(Color.white);
                g.drawString(String.valueOf(input.charAt(i)), x[i], y);
            }            
        }
        if(counter == 6)lose();
        if(input.equalsIgnoreCase(word)) win();
        
    }
    private void win(){
        Graphics g = getGraphics();
        paint(g);
        g.setColor(Color.blue);
        g.drawString("YOU WIN", 0, 300);
    }
    private void lose(){
        Graphics g = getGraphics();
        paint(g);
        g.setColor(Color.red);
        g.drawString("YOU LOSE", 0, 300);
    }
}