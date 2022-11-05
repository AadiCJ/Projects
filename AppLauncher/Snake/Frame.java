package AppLauncher.Snake;
import javax.swing.JFrame;

public class Frame extends JFrame{
	public Frame(){
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
