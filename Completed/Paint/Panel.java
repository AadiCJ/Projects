import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;
import java.awt.Color;
/**
 * the drawing region of the paint application.
 */
public class Panel extends JPanel{
    Dimension total = new Dimension(750, 750);//total width and height of the JPanel
    Color color = new Color(255, 0, 0);//current colour drawing with.
    int size = 15;//size of the brush
    Panel(){
        setSize(total);
        setVisible(true);
        setBackground(Color.white);
        addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent e) {
                //checks when the mouse is dragged and puts an oval there.
                Graphics g = (Graphics2D) getGraphics();
                g.setColor(color);//oval has a color of this.color.
                g.fillOval(e.getX(), e.getY(), size, size);//oval has a size of this.size
            }
            @Override
            public void mouseMoved(MouseEvent e){}
            
        });
        addMouseListener(new MouseListener(){
            @Override
            public void mousePressed(MouseEvent e) {
                //checks when the mouse is pressed and puts an oval there.
                Graphics g = (Graphics2D) getGraphics();
                g.setColor(color);//oval has color of this.color
                g.fillOval(e.getX(), e.getY(), size, size);//oval has size of this.size
            }
            @Override
            public void mouseClicked(MouseEvent e){}          
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}//unnecesarry methods, that need to be overriden
        });
    }
    void setColor(int[] arr){//setColor sets this.color to a new color corresponding to the inputed
        //array
        color = new Color(arr[0], arr[1], arr[2]);
    }
    void setColor(Color color){//overloaded method, sets to color to a color.
        this.color = color;
    }
    void setBrushSize(int size){//sets size of the brush, as per input size.
        this.size = size;
    }
    void fillBack(){//fills background with this.color
        this.setBackground(color);
    }
    void clearAll(){//clears the entire painting area.
        repaint();
        setBackground(Color.white);
    }
}
