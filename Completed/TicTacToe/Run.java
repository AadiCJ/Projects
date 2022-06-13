import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Run {
	public static void main(String[] args) {
		new TicTacToe();
	}
}

class TicTacToe implements ActionListener {
	int[][] winConditions = new int[][]{
		{0, 1, 2},
		{3, 4, 5},
		{6, 7, 8},
		{0, 3, 6},
		{1, 4, 7},
		{2, 5, 8},
		{0, 4, 8},
		{2, 4, 6}
	};
	JFrame frame = new JFrame();
	JButton[] buttons = new JButton[9];
	JPanel text = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLabel textField = new JLabel();
	boolean xTurn;
	JButton reset = new JButton();
	Random random = new Random();
	TicTacToe(){

		int turn = random.nextInt(2);
		xTurn = turn == 1;
		if(xTurn) textField.setText("X's Turn");
		else textField.setText("O's Turn");
		frame.setSize(800, 1000);
		frame.setTitle("Tic Tac Toe");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 0, 0));

		text.setLocation(0,0);
		text.setSize(800, 100);
		text.setBackground(Color.black);
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setAlignmentX(JLabel.CENTER);
		textField.setForeground(new Color(51, 232, 38));
		text.add(textField);
		frame.add(text);

		buttonPanel.setBounds(0,100, 0, 900);
		buttonPanel.setSize(700, 700);
		buttonPanel.setLayout(new GridLayout(3,3));
		buttonPanel.setForeground(new Color(51, 235, 38));

		reset.setLocation(705, 105);
		reset.setSize(75, 25);
		reset.setText("Reset");
		reset.setBackground(Color.GREEN);
		reset.addActionListener(this);

		for(int i = 0; i < 9; i++){
			buttons[i] = new JButton();
			buttons[i].setSize(100, 100);
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font("Ink Free", Font.BOLD, 75));
			buttons[i].setForeground(new Color(51, 235, 38));
			buttons[i].setBackground(Color.black);
			buttonPanel.add(buttons[i]);
		}

		frame.add(reset);
		frame.add(buttonPanel);
		frame.setVisible(true);
	}
	public boolean win = false;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e!=null) {

			for (int i = 0; i < 9; i++) {
				if (e.getSource() == buttons[i]) {
					if (xTurn) {
						textField.setText("O's Turn");
						buttons[i].setText("X");
						buttons[i].setEnabled(false);
						xTurn = false;
						for (int[] arr : winConditions) check(arr);

					} else {
						textField.setText("X's Turn");
						buttons[i].setText("O");
						buttons[i].setEnabled(false);
						xTurn = true;
						for (int[] arr : winConditions) check(arr);
					}
				}
				if(e.getSource() == reset){
					for(JButton but : buttons){
						but.setText("");
						but.setBackground(Color.black);
						but.setEnabled(true);
					}
					textField.setText("");
					int turn = random.nextInt(2);
					xTurn = turn == 1;
					if(xTurn) textField.setText("X's Turn");
					else textField.setText("O's Turn");
				}
			}
		}
	}
	public void check(int[] in){
		int pos1 = in[0];
		int pos2 = in[1];
		int pos3 = in[2];
		String out;
		if(buttons[pos1].getText().equalsIgnoreCase(buttons[pos2].getText()) && buttons[pos2].getText().equalsIgnoreCase(buttons[pos3].getText()) && !buttons[pos2].getText().equals("")){
			out = buttons[pos1].getText();
			textField.setText(out + " is the winner.");
			win = true;
			for(int i : in){
				buttons[i].setBackground(new Color(51, 204, 51));
			}
			for(JButton but : buttons ) but.setEnabled(false);
		}
		boolean draw = true;
		for(JButton but : buttons) if(but.getText().equals("")) draw = false;
		if(draw && !win) textField.setText("Tie");
	}
}