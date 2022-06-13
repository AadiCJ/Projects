import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Aadi Jha. 2135hrs 12/06/22. Conway's Game of Life.

public class Project implements ActionListener{
    static private final int SIDE = 800;
    static private final int BOX_SIDE = 10;
    private Dimension size;
    
    public static final int GAME_UNITS = (SIDE*SIDE)/(BOX_SIDE * BOX_SIDE);
    private JPanel[] panels = new JPanel[GAME_UNITS];
    private JPanel main = new JPanel();
    private JButton[] buttons = new JButton[GAME_UNITS];
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");

    JFrame frame = new JFrame("Conway's Game of Life");

    public static pos[] alivePos = new pos[GAME_UNITS];
    public pos[] positions = new pos[GAME_UNITS];

    Project(){
        frame.setLayout(new GridBagLayout());
        GridBagConstraints resetC = new GridBagConstraints();
        resetC.gridx = 7;
        resetC.gridy = 0;
        size = new Dimension(SIDE, SIDE);        
        
        main.setMinimumSize(size);
        main.setPreferredSize(size);
        main.setMaximumSize(size);

        frame.setResizable(false);
        frame.add(main);
        frame.add(startButton);
        frame.add(resetButton, resetC);
        startButton.addActionListener(this);
        resetButton.addActionListener(this);
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(new GridLayout((int) Math.sqrt(GAME_UNITS), (int) Math.sqrt(GAME_UNITS)));
        
        //Game Panels & parts
        for(int i = 0; i < GAME_UNITS; i++){
            //panels
            panels[i] = new JPanel();
            panels[i].setBackground(Color.black);
            panels[i].setSize(BOX_SIDE, BOX_SIDE);    
            panels[i].setLayout(new BorderLayout());
            
            //buttons
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.black);
            buttons[i].addActionListener(this);
            buttons[i].setSize(panels[i].getSize());
            panels[i].add(buttons[i], BorderLayout.CENTER);            

            main.add(panels[i]);
        }

        for(int y = 0, i = 0; y < SIDE; y += BOX_SIDE){
            for(int x = 0; x < SIDE; x += BOX_SIDE){
                positions[i] = new pos((x+10)/10, (y+10)/10);
                i++;
            }
        }

         //end lines.
        frame.pack();
        frame.setForeground(Color.black);
        frame.setVisible(true);
    }

    private void simStart(){
        
    }
    private void simReset(){
        for(int i = 0; i < GAME_UNITS; i++){
            panels[i].setBackground(Color.black);
            buttons[i].setBackground(panels[i].getBackground());
            buttons[i].setForeground(panels[i].getBackground());
        }
        alivePos = new pos[GAME_UNITS];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) simStart();
        if(e.getSource() == resetButton) simReset();
        for(int i = 0; i < GAME_UNITS; i++){
            if(e.getSource() == buttons[i]){
                if(panels[i].getBackground() == Color.white){
                    setDead(i);                   
                }else if(panels[i].getBackground() == Color.black){
                   setAlive(i);
                }
            }
        }
    }

    public void setAlive(int i){
        panels[i].setBackground(Color.white);
        buttons[i].setBackground(panels[i].getBackground());
        buttons[i].setForeground(panels[i].getBackground());
        alivePos[i] = positions[i];
        System.out.println(alivePos[i].toString());
    }

    public void setDead(int i){
        panels[i].setBackground(Color.black);
        buttons[i].setBackground(panels[i].getBackground());
        buttons[i].setForeground(panels[i].getBackground());
        alivePos[i] = null;  
        System.out.println(alivePos[i].toString());
    }


    public static void main(String[] args){
        new Project();
    }
}

class pos{
    public int x, y;
    int[][] checkArr= new int[][]{
        {x-1 , y-1},{x, y-1},{x+1, y-1},
        {x-1 , y  },         {x+1, y  },
        {x-1 , y+1},{x, y+1},{x+1, x+1}//3x3 array of checking
    };
    pos(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return String.format("%d %d", x, y);
    }//debugging

    public int returnLive(){
        int live = 0;
        for(int i = 0; i < Project.GAME_UNITS; i++){
            for(int j = 0; j < checkArr.length; j++){
                if((Project.alivePos[i].x == checkArr[j][1]) && (Project.alivePos[i].y == checkArr[j][2])){
                    live++;
                }
            }
        }
        return live;
    }

}