import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;
import java.awt.Color;
public class Panel extends JPanel{
    Dimension total = new Dimension(750, 750);
    Color color = new Color(255, 0, 0);
    int size = 15;
    Panel(){
        setSize(total);
        setVisible(true);
        setBackground(Color.white);
        addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = (Graphics2D) getGraphics();
                g.setColor(color);
                g.fillOval(e.getX(), e.getY(), size, size);
                
            }

            @Override
            public void mouseMoved(MouseEvent e){}
            
        });
        addMouseListener(new MouseListener(){
            @Override
            public void mousePressed(MouseEvent e) {
                Graphics g = (Graphics2D) getGraphics();
                g.setColor(color);
                g.fillOval(e.getX(), e.getY(), size, size);
            }
            @Override
            public void mouseClicked(MouseEvent e){}          
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    void setColor(int[] arr){
        color = new Color(arr[0], arr[1], arr[2]);
    }
    void setColor(Color color){
        this.color = color;
    }
    void setBrushSize(int size){
        this.size = size;
    }
    void fillBack(){
        this.setBackground(color);
    }
    void clearAll(){
        repaint();
        setBackground(Color.white);
    }
}
