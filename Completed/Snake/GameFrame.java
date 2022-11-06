import javax.swing.JFrame;

public class GameFrame extends JFrame{
	/**
	 * creates a new JFrame and adds the GamePanel class to it.
	 */
	GameFrame(){
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
