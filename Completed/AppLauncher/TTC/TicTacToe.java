package TTC;
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
/**
 * Contains all functional code.
 */
public class TicTacToe implements ActionListener {
	/**
	 * List of all win conditions. If any are met, the win boolean is switched to true.
	 */
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
	public boolean win = false;//turns to true if a win condition is met
	/**
	 * All parts of the swing application.
	 */
	JFrame frame = new JFrame();//main frame.
	JButton[] buttons = new JButton[9];//an array of all buttons.
	JPanel text = new JPanel();//JPanel which displays win/loss/whose turn.
	JPanel buttonPanel = new JPanel();//JPanel which contains all buttons.
	JLabel textField = new JLabel();//a JLabel, part of the text JPanel.
	boolean xTurn;//if it's player x's turn, it is set to true.
	JButton reset = new JButton();//resets the game.
	Random random = new Random();//used to decide the player that starts (O/X).
	/**
	 * Sets up the swing application
	 * Decides the person that starts
	 */
	public TicTacToe(){

		int turn = random.nextInt(2);
		xTurn = turn == 1;
		if(xTurn) textField.setText("X's Turn");
		else textField.setText("O's Turn");

		frame.setSize(800, 1000);
		frame.setTitle("Tic Tac Toe");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

	/**
	 * Has code related to buttons and winning.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e==null) return;//if e is null, exit this.
		for (int i = 0; i < 9; i++) {//checking which button is pressed
			if (e.getSource() == buttons[i]) {
				if (xTurn) {//if x's turn, 
					xTurn = false;
					textField.setText("O's Turn");//turn changes to O
					buttons[i].setText("X");//text in button is set to X
					buttons[i].setEnabled(false);//button cannot be pressed again
					for (int[] arr : winConditions) check(arr);//loops through winconditions and checks if any are achieved.
				} else {//similar to X.
					xTurn = true;
					textField.setText("X's Turn");
					buttons[i].setText("O");
					buttons[i].setEnabled(false);
					for (int[] arr : winConditions) check(arr);
				}
			}
			if(e.getSource() == reset){//if reset button is pressed,
				for(JButton but : buttons){//reset all buttons
					but.setText("");
					but.setBackground(Color.black);
					but.setEnabled(true);
				}
				int turn = random.nextInt(2);
				xTurn = turn == 1;
				if(xTurn) textField.setText("X's Turn");
				else textField.setText("O's Turn");//setting the new Player.
			}
		}
	}
	/**
	 * @param in a win condition from the win conditions array.
	 * checks if the inputed win condition has been achieved.
	 */
	public void check(int[] in){
		int pos1 = in[0];
		int pos2 = in[1];
		int pos3 = in[2];//store the values of the array into individual integers.
		String out;
		if(buttons[pos1].getText().equalsIgnoreCase(buttons[pos2].getText()) && buttons[pos2].getText().equalsIgnoreCase(buttons[pos3].getText()) && !buttons[pos2].getText().equals("")){
			//above line checks if the text in all three buttons is the same, if it is win is set to true.
			out = buttons[pos1].getText();//gets text in the button, i.e. the winner.
			textField.setText(out + " is the winner.");//the text in JLabel is set.
			win = true;//win is set to true.
			for(int i : in) buttons[i].setBackground(new Color(51, 204, 51));//loop through certain buttons, changing their colour to win.
			for(JButton but : buttons ) but.setEnabled(false);//disables all buttons.
		}
		boolean draw = true;//draw is always set to true.
		for(JButton but : buttons) if(but.getText().equals("")) draw = false;//if the text in any button is empty, draw is set to false.
		if(draw && !win) textField.setText("Tie");//if draw is true and win is false, then the game has tied.
	}
}