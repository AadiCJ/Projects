package Calculator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;
/**
 * this project used to contain two files, one swing shell and one which contained all the
 * add, subtract, etc methods.
 * i combined them both but was too lazy to change the name.
 */
public class SwingShell extends JFrame{
	JLabel outLabel = new JLabel();//where the output is displayed
	JTextField in1 = new JTextField();//input 1
	JTextField in2 = new JTextField();//input 2

	JButton[] buttons = new JButton[6];//all JButtons
	String[] strings = {"+", "-", "÷", "×", "%", "^"};//The names and functions of JButtons

	public static void main(String[] args) {
		new SwingShell();//creates new object, used for debugging
	}
	public SwingShell(){
		setTitle("Calculator");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//DISPOSE_ON_CLOSE so that applauncher isnt closed
		setLayout(new GridLayout(3, 3));//doesn't look very good tbh
		in2.setPreferredSize(new Dimension(150, 150));//only one needs to be set, cause of how gridlayout works
		add(in1);
		add(in2);
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton(strings[i]);//setting text using constructor
			buttons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(!isDouble(in1.getText()) && !isDouble(in2.getText())) return;//they are not doubles, stop
					switch(((JButton)e.getSource()).getText()){
						case "+":
							outLabel.setText(add(in1.getText(), in2.getText()));//add
							break;
						case "÷":
							outLabel.setText(divide(in1.getText(), in2.getText()));//divite
							break;
						case "-":
							outLabel.setText(subtract(in1.getText(), in2.getText()));//subtract
							break;
						case "×":
							outLabel.setText(multiply(in1.getText(), in2.getText()));//multiply
							break;
						case "%":
							outLabel.setText(remainder(in1.getText(), in2.getText()));//remainder
							break;
						case "^":
							outLabel.setText(exponent(in1.getText(), in2.getText()));//exponent
							break;
					}
				}
			});
			add(buttons[i]);
		}
		add(outLabel);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	//Actual calculations
	/**
	 * @param in1 dividend
	 * @param in2 divisor
	 */
	private String divide(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 / do2);
    }
	/**
	 * @param in1 first string to be multiplied
	 * @param in2 second string to be multiplied.
	 */
    private String multiply(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 * do2);
    }
	/**
	 * @param in1 first string to be added
	 * @param in2 second string to be added.
	 */
    private String add(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 + do2);
    }
	/**
	 * @param in1 first string to be subtracted
	 * @param in2 second string to be subtracted.
	 */
    private String subtract(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 - do2);
    }
	/**
	 * @param in1 first string dividend
	 * @param in2 second string divisor.
	 */
	private String remainder(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
		return String.valueOf(do1 % do2);
	}
	/**
	 * @param in1 first string base
	 * @param in2 second string indice.
	 */
	private String exponent(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
		return String.valueOf(Math.pow(do1, do2));
	}
	/**
	 * @param in the string which is to be parsed.
	 */
	private boolean isDouble(String in){
		try{
			Double.parseDouble(in);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}