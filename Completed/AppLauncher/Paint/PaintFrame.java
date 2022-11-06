package Paint;
import javax.swing.JFrame;
/**
 * the JFrame in which the entire paint application is held.
*/
public class PaintFrame extends JFrame{
	SidePanel sp = new SidePanel();//SidePanel extends JPanel
	static Panel p = new Panel();//Panel extends JPanel.
	public static void main(String[] args){
		new PaintFrame();//creates new frame.
	}
	public PaintFrame(){
		setTitle("Paint");
		setLayout(null);//no layout manager, as absolute positioning was used.
		setSize(750+sp.getWidth(), 750);//sp.getWidth() is used as the width needs to be accounted for 
		sp.setLocation(750, 0);//location of the sidePanel.
		p.setLocation(0, 0);//location of panel
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//DISPOSE_ON_CLOSE used so that applauncher isnt closed
		add(p);
		add(sp);
		setLocationRelativeTo(null);//centering the frame/
		setVisible(true);
	}
	static void setColor(int[] arr, String special){//setColor static method.
		if(arr[0] == -1 && special.equals("ColorSwitcher")){//if color switcher is pressed,
			//an array with -1 values and a String special = "ColorSwitcher" is passed in
			openSwitcher();
			return;//method execution stops because otherwise p.setColor({-1, -1, -1}); would be called
			//leading to an error.
		}
		else if(arr[0] == -1 && special.equals("Eraser")){//same as colorswitcher, but for eraser
			p.setColor(p.getBackground());
			return;
		}
		p.setColor(arr);
	}
	static void openSwitcher(){
		new ColorSwitcher();//creates a new object.
	}
	static void setBrushSize(int in){
		p.setBrushSize(in);//static methods are used so that other classes can call them, and the 
		//JPanel panel can be affected.
	}
	static void fillBack(){
		p.fillBack();
	}
	static void clearAll(){
		p.clearAll();
	}
}