package gameplay;

import game.GameSetup;
import game.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import start.Frame;

/**
 * @author Geoffrey Long
 * 
 * Provides the graphical representation of the state of the game, 
 * and provides the user the ability to interface with the game.
 */
public class MapPanel extends JPanel implements KeyListener, ActionListener {
	private Tile[][] map;
	private int xSize;
	private int ySize;
	PlayerControl cont;
	Cycle[] cycles;
	private Timer explosionTimer;
	private int explosionCount;
	private static int xOffset;
	private static int yOffset;
	private JLabel changeSize;
	private JButton minus;
	private JButton plus;
	private boolean haveExplosion = false;
	private Map mapper;
	private GameSetup gameSetup;
	private static int increment;
	private List<Color> explosionColors;
	private Cycle curCycle;
	private boolean isExplosion = false;
	private JLabel playerOneLabel;
	private JLabel playerTwoLabel;
	private JLabel playerOneScore;
	private JLabel playerTwoScore;
	private GameMaster gameMaster;
	
	/**
	 * Instantiate all the class variables that are necessary for game function.  
	 * @param map
	 */
	public MapPanel(GameSetup gameSetup, GameMaster gameMaster, PlayerControl cont){
		this.gameSetup = gameSetup;
		this.cont = cont;
		
		mapper = gameSetup.getMap();
		map = mapper.getMap();
		xSize = mapper.getXSize();
		ySize = mapper.getYSize();
		
		this.gameMaster = gameMaster;
		
		increment = gameSetup.getIncrement();
		
		initComponents();
		initLayout();
	}
	
	public void initComponents(){
		changeSize = new JLabel("Change the size of the map");
		changeSize.setBounds(10,10,200,30);
		
		plus = new JButton("+");
		plus.setBounds(30,50,50,30);
		plus.addActionListener(this);
		
		minus = new JButton("-");
		minus.setBounds(110,50,50,30);
		minus.addActionListener(this);
		
		playerOneLabel = new JLabel("Player One Wins");
		playerOneLabel.setBounds(Frame.getXSize()/2 - 110,10,100,20);
		
		playerOneScore = new JLabel(Integer.toString(gameMaster.getPlayerOneWins()));
		playerOneScore.setBounds(Frame.getXSize()/2 - 60,30,50,20);
		
		playerTwoLabel = new JLabel("Player One Wins");
		playerTwoLabel.setBounds(Frame.getXSize()/2 + 10,10,100,20);
		
		playerTwoScore = new JLabel(Integer.toString(gameMaster.getPlayerTwoWins()));
		playerTwoScore.setBounds(Frame.getXSize()/2 + 60,30,50,20);
	}
	
	public void initLayout(){
		xOffset = (Frame.getXSize() - xSize*increment) / 2;
		yOffset = (Frame.getYSize() - ySize*increment) / 2;
		
		setBounds(0,0,Frame.getXSize(),Frame.getYSize());
		setLayout(null);
		
		add(changeSize);
		add(plus);
		add(minus);
		add(playerOneLabel);
		add(playerTwoLabel);
		add(playerOneScore);
		add(playerTwoScore);
		
		addKeyListener(this);
		setVisible(true);
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	/**
	 * This method is set to execute on a timer.  
	 * It checks the user's cycle location to see if it is valid 
	 * and increments the location according to the current cycle heading.
	 */
	public void updateMap(Tile[][] map){
		this.map = map;
		this.requestFocusInWindow();
		repaint();
	}
	
	public void explosion(int explosionCount, List<Color> explosionColors, Cycle cycle){
		this.explosionCount = explosionCount;
		this.explosionColors = explosionColors;
		isExplosion = true;
		this.curCycle = cycle;
		repaint();
	}
	
	/**
	 * This method will paint the current state of the game.  
	 */

	/*
	 *This method is standard in swing graphics.  It is called every time 
	 * the panel is considered to be invalidated.  This is when the window has been 
	 * resized or a painting function has been called.  Traditionally this would 
	 * include a call to super.paintComponents(g), however, by omitting this call
	 * bounds for the paintComponents do not have to be established, thereby eliminating
	 * the need to handle extra cases.  
	 * 
	 * This method has several cases: 
	 * 1) When the game has not started yet: This is the case where the players 
	 * have yet to pick their position, or when the panel has first been painted.  
	 * The panel is painted white with black borders.  The player objects are also 
	 * painted at this time.  
	 * 2) When the cycles are both alive: The trails will be painted behind the 
	 * cycles at each movement.  
	 * 3) When one or more cycles are no longer alive: The explosion method will be called. 
	 * This will paint an expanding explosion on the map at the crashed player's location.  
	 * The explosion colors are generated using the getExplosionColors method.  
	 * The colors will then be painted on the map at randomized locations near the 
	 * crashed cycle location.  These colors will fill random ellipse sizes and shapes.
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (!isExplosion){
			for (int i=0; i<xSize; i++){
				for (int j=0; j<ySize; j++){
					switch(map[i][j]){
						case WALL:
							g.setColor(Color.BLACK);
							break;
						case EMPTY:
							g.setColor(Color.WHITE);
							break;
						case PONE:
							g.setColor(gameSetup.getPlayerColor(1));
							break;
						case PTWO:
							g.setColor(gameSetup.getPlayerColor(2));
							break;
					}
					g.fillRect(i*increment+xOffset, j*increment+yOffset, increment, increment);
				}
			}
		}
	}
	
	public static int getXOffset(){
		return xOffset;
	}
	public static int getYOffset(){
		return yOffset;
	}
	public static int getIncrement(){
		return increment;
	}
	public void disableButtons(){
		minus.setEnabled(false);
		plus.setEnabled(false);
	}
	/**
	 * This method will get the KeyEvents from the KeyListener and will pass 
	 * them to the PlayerControl to make the appropriate changes 
	 * to the player heading.
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		cont.setHeading(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("+")){
			if (xSize*(increment+1)<= this.getWidth()*0.8 && 
					ySize*(increment+1)<= this.getHeight()*0.8){
				increment++;
				minus.setEnabled(true);
				gameSetup.setIncrement(increment);
			}
			else{
				plus.setEnabled(false);
			}
			xOffset = (Frame.getXSize() - xSize*increment) / 2;
			yOffset = (Frame.getYSize() - ySize*increment) / 2;
			repaint();
		}
		if (e.getActionCommand().equals("-")){
			if (increment>1){
				increment --;
				plus.setEnabled(true);
				gameSetup.setIncrement(increment);
			}
			else{
				minus.setEnabled(false);
			}
			xOffset = (Frame.getXSize() - xSize*increment) / 2;
			yOffset = (Frame.getYSize() - ySize*increment) / 2;
			repaint();
		}
	}
}
