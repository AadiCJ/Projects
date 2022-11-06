package Snake;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
/**
 * contains all functional code.
 * ActionListener is implemented for the javax.swing.Timer.
 */
public class GamePanel extends JPanel implements ActionListener{
	/**
	 * constants
	 */
	static final int SCREEN_WIDTH = 600;//width of the JFrame.
	static final int SCREEN_HEIGHT = 600;//height of the JFrame.
	static final int UNIT_SIZE = 25;//size of one unit.
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;//total number of units.
	static final int DELAY = 80;//delay of the timer.
	final int[] x = new int[GAME_UNITS];//x positions of snake.
	final int[] y = new int[GAME_UNITS];//y positions of snake.

	int bodyParts = 5;//body parts of snake, increased if an apple is eaten.
	int applesEaten;//total number of apples eaten, works as score.
	int appleX;//x position of apple.
	int appleY;//y position of apple.
	char direction = 'R';//direction of the snake.
	boolean running = false;//set to true of the game is running.
	Timer timer;//javax.swing.Timer.
	Random random;//java.util.Random.
	/**
	 * sets size of Frame, initialises the random object, adds key listener.
	 */
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));//setting size
		this.setBackground(Color.black);//setting background colour
		this.setFocusable(true);//setting if the JPanel is focusable(for KeyListener)
		this.addKeyListener(new MyKeyAdapter());//adding new KeyListener. MyKeyAdapter is an embeded class.
		startGame();//calls start game method.
	}
	public void startGame() {
		running = true;//sets running to true.
		newApple();//generates a new apple
		timer = new Timer(DELAY, this);//creates the timer
		timer.start();//starts the timer
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);//clears screen
		draw(g);//calls draw method
	}	
	public void draw(Graphics g) {
		if(running) {
			/*for(int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}*///this code can be used to draw a grid.
			g.setColor(Color.red);//sets colour of graphics object to red for apple.
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);//fills an oval at the apple positions.
			
			g.setFont(new Font("Arial", Font.BOLD, 35));//to draw score
			FontMetrics metrics = getFontMetrics(g.getFont());//to centre the text
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
			//in the above line, the centred text is drawn.

			for(int i = 0; i < bodyParts; i++) {//draws all parts of snake.
				if(i == 0) {//if the part is the head.
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}else {//if the part is not head.
					g.setColor(new Color(45, 180, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			}else gameOver(g);//if the game is not running, game over;
		}

	public void newApple() {//creating new apple positions
		appleX = random.nextInt((int) SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;//creates a random location on x axis
		appleY = random.nextInt((int) SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;//same is x, but for y.
		
	}

	/**
	 * move is called from within the ActionListener, part of the swing.Timer.
	 */
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];//shifting every bodypart(idk why this here, i cant remember)
			y[i] = y[i-1];
		}
		switch(direction) {//moves the snakes head depending on the direction.
		case 'U':
			y[0] = y[0] - UNIT_SIZE;//if the direction is up, the y is decreased by one UNIT_SIZE
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;//opposite of U.
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;//if the direction is right, x is increased by one UNIT_SIZE
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;//opposite of R
			break;
		}
	}
	/**
	 * checks if position of apple and snakes head overlap
	 */
	public void checkApple() {
		if(x[0] == appleX && y[0] == appleY) {//if the co-ordinates overlap,
			bodyParts++;//the snake gets longer
			applesEaten++;//score increases
			newApple();//and a new apple is generated
		}
		
	}
	/**
	 * checks for collisions that will kill the snake, like with body and walls.
	 */
	public void checkCollisions() {
		//check for head-body collision
		for(int i = bodyParts; i > 0; i--) 
			if((x[0] == x[i]) && (y[0] == y[i])) running = false;//the game stops running.
		
		//check for head-border(x) collision
		if(x[0] < 0 || x[0] > SCREEN_WIDTH) running = false;
		
		//check for head-border(y) collision
		if(y[0] < 0 || y[0] > SCREEN_HEIGHT) running = false;
		
		if(!running) timer.stop();//finally if the snake has infact died, the timer is stopped.
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);//all of this is to display the Game Over text, centered and in red.
		g.setFont(new Font("Arial", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());//
		g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
		g.setFont(new Font("Arial", Font.BOLD, 35));
		metrics = getFontMetrics(g.getFont());//it also displays the score.
		g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
	}
	/**
	 * this is the method called by the ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();//always repaints
		if(running){
			move();//calls move
			checkApple();//checks if an apple has been eaten
			checkCollisions();//checks for collisions
			
		}
	}
	/**
	 * embeded class MyKeyAdapter.
	 * It handles the key inputs.
	 */
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') direction = 'L';//if the snake is not moving right, set to left
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') direction = 'R';//if the snake is not moving left, set to right
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') direction = 'U';//if the snake is not moving down, set to up
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') direction = 'D';//if the snake is not moving up, set to down
				break;
			}
		}
	}

}
