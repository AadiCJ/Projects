package Snake;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame{
	/**
	 * creates a new JFrame and adds the GamePanel class to it.
	 */
	public SnakeFrame(){
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
