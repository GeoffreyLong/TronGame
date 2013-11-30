package gameplay;

import game.GameSetup;
import game.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.Frame;
import styleelements.StyledLabel;
import styleelements.StyledPanel;

/**
 * @author Geoffrey Long
 * 
 * Provides the graphical representation of the state of the game, 
 * and provides the user the ability to interface with the game.
 */
public class GamePanel extends StyledPanel implements KeyListener, ActionListener {
	private Tile[][] map;
	private int xSize;
	private int ySize;
	PlayerControl cont;
	Cycle[] cycles;
	private static int xOffset;
	private static int yOffset;
	private JLabel changeSize;
	private JButton minus;
	private JButton plus;
	private Map mapper;
	private GameSetup gameSetup;
	private static int increment;
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
	public GamePanel(GameSetup gameSetup, GameMaster gameMaster, PlayerControl cont){
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
	
	/**
	 * Standard panel method to initialize the components
	 */
	private void initComponents(){
		changeSize = new StyledLabel("Change the size of the map", 15);
		changeSize.setBounds(10,10,300,30);
		
		plus = new JButton("+");
		plus.setBounds(30,50,50,30);
		plus.addActionListener(this);
		
		minus = new JButton("-");
		minus.setBounds(110,50,50,30);
		minus.addActionListener(this);
		
		playerOneLabel = new StyledLabel("Player One Wins", 11);
		playerOneLabel.setBounds(Frame.getXSize()/2 - 110,10,150,20);
		
		playerOneScore = new StyledLabel(Integer.toString(gameMaster.getPlayerOneWins()), 13);
		playerOneScore.setBounds(Frame.getXSize()/2 - 60,30,50,20);
		
		playerTwoLabel = new StyledLabel("Player Two Wins", 11);
		playerTwoLabel.setBounds(Frame.getXSize()/2 + 15,10,150,20);
		
		playerTwoScore = new StyledLabel(Integer.toString(gameMaster.getPlayerTwoWins()), 13);
		playerTwoScore.setBounds(Frame.getXSize()/2 + 60,30,50,20);
	}
	
	/**
	 * Standard panel method to initialize the layout and add the components
	 */
	private void initLayout(){
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
	 * This method refreshes the map with the map object that is passed in  
	 * it then repaints the panel with that map.  
	 * It also requests focus so that the keyListener functions.
	 * 
	 * @param map  New iteration of the Map object
	 */
	public void updateMap(Tile[][] map){
		this.map = map;
		this.requestFocusInWindow();
		repaint();
	}
	

	/**
	 * This method will paint the current state of the game. 
	 * @param g  Graphics 
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
	
	/**
	 * Get the xOffset
	 * @return  The x coordinate of the start of the map (upper left corner)
	 */
	public static int getXOffset(){
		return xOffset;
	}
	/**
	 * Get the yOffset
	 * @return  The y coordinate of the start of the map (upper left corner)
	 */
	public static int getYOffset(){
		return yOffset;
	}
	/**
	 * Return the increment
	 * @return increment  A set number of pixels which serves to 
	 * set the size of the objects and the travel distance of the cycle
	 */
	public static int getIncrement(){
		return increment;
	}
	
	/**
	 * Disable the minus and plus buttons
	 */
	public void disableButtons(){
		minus.setEnabled(false);
		plus.setEnabled(false);
	}
	/**
	 * This method will get the KeyEvents from the KeyListener and will pass 
	 * them to the PlayerControl to make the appropriate changes 
	 * to the player heading.
	 * @param e  KeyEvent
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
	/**
	 * Resize the map according to input from the plus and minus buttons
	 */
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
