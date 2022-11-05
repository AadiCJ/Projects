package AppLauncher.Paint;
import javax.swing.JFrame;
public class Frame extends JFrame{
	SidePanel sp = new SidePanel();
	static Panel p = new Panel();
	public static void main(String[] args){
		new Frame();
	}
	public Frame(){
		setTitle("Paint");
		setLayout(null);
		setSize(750+sp.getWidth(), 750);
		sp.setLocation(750, 0);
		p.setLocation(0, 0);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(p);
		add(sp);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	static void setColor(int[] arr, String special){
		if(arr[0] == -1 && special.equals("ColorSwitcher")){
			openSwitcher();
			return;
		}
		else if(special.equals("Eraser")){
			p.setColor(p.getBackground());
			return;
		}
		p.setColor(arr);
	}
	static void openSwitcher(){
		new ColorSwitcher();
	}
	static void setBrushSize(int in){
		p.setBrushSize(in);
	}
	static void fillBack(){
		p.fillBack();
	}
	static void clearAll(){
		p.clearAll();
	}
}