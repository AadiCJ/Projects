package Calculator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class SwingShell extends JFrame{
	JLabel outLabel = new JLabel();
	JTextField in1 = new JTextField();
	JTextField in2 = new JTextField();
	JButton[] buttons = new JButton[6];
	String[] strings = {"+", "-", "÷", "×", "%", "^"};

	public static void main(String[] args) {
		new SwingShell();
	}
	public SwingShell(){
		setTitle("Calculator");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(3, 3));
		in2.setPreferredSize(new Dimension(150, 150));
		add(in1);
		add(in2);
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton(strings[i]);
			buttons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(!isDouble(in1.getText()) && !isDouble(in2.getText())) return;
					switch(((JButton)e.getSource()).getText()){
						case "+":
							outLabel.setText(add(in1.getText(), in2.getText()));
							break;
						case "÷":
							outLabel.setText(divide(in1.getText(), in2.getText()));
							break;
						case "-":
							outLabel.setText(subtract(in1.getText(), in2.getText()));
							break;
						case "×":
							outLabel.setText(multiply(in1.getText(), in2.getText()));
							break;
						case "%":
							outLabel.setText(remainder(in1.getText(), in2.getText()));
							break;
						case "^":
							outLabel.setText(exponent(in1.getText(), in2.getText()));
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
	private String divide(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 / do2);
    }
    private String multiply(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 * do2);
    }
    private String add(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 + do2);
    }
    private String subtract(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
        return String.valueOf(do1 - do2);
    }
	private String remainder(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
		return String.valueOf(do1 % do2);
	}
	private String exponent(String in1, String in2){
		double do1 = Double.parseDouble(in1), do2 = Double.parseDouble(in2);
		return String.valueOf(Math.pow(do1, do2));
	}
	private boolean isDouble(String in){
		try{
			Double.parseDouble(in);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}