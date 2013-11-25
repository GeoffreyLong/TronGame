package gameplay;

import game.EndGame;
import game.GameSetup;
import game.Map;
import game.WinCondition;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
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
	private int xOffset;
	private int yOffset;
	private JLabel changeSize;
	private JButton minus;
	private JButton plus;
	private GameMaster gameMaster;
	private boolean haveExplosion = false;
	private Map mapper;
	private GameSetup gameSetup;
	private int increment;
	
	/**
	 * Instantiate all the class variables that are necessary for game function.  
	 * @param map
	 */
	public MapPanel(GameSetup gameSetup, GameMaster gameMaster){
		this.gameMaster = gameMaster;
		this.gameSetup = gameSetup;

		mapper = gameSetup.getMap();
		map = mapper.getMap();
		xSize = mapper.getXSize();
		ySize = mapper.getYSize();
		
		increment = gameSetup.getIncrement();
		
		initCycles();
		initComponents();
		initLayout();
	}
	
	public void initCycles(){
		Cycle cycleOne = new Cycle(mapper.getPOneXStart(), mapper.getPOneYStart(), 
				null, true, gameSetup.getPlayerColor(1));
		Cycle cycleTwo = new Cycle(mapper.getPTwoXStart(), mapper.getPTwoYStart(), 
				null, true, gameSetup.getPlayerColor(2));
		
		cycles = new Cycle[]{cycleOne, cycleTwo};
		cont = new PlayerControl(cycleOne, cycleTwo);

		this.map[cycleOne.getXPos()][cycleOne.getYPos()] = Tile.PONE;
		this.map[cycleTwo.getXPos()][cycleTwo.getYPos()] = Tile.PTWO;
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
	}
	public void initLayout(){
		xOffset = (Frame.getXSize() - xSize*increment) / 2;
		yOffset = (Frame.getYSize() - ySize*increment) / 2;
		
		setBounds(0,0,Frame.getXSize(),Frame.getYSize());
		setLayout(null);
		
		add(changeSize);
		add(plus);
		add(minus);
		
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
	public void updateMap(){
		this.requestFocusInWindow();
		boolean cycleOne = true;
		for (Cycle cycle : cycles){
			if (cycles[0].getCurHeading()!=null && cycles[1].getCurHeading()!=null){
				minus.setEnabled(false);
				plus.setEnabled(false);
				cycle.travel();
				if (map[cycle.getXPos()][cycle.getYPos()]==Tile.WALL || 
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PONE ||
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PTWO){
					gameMaster.timer.stop();
					cycle.isAlive = false;
					if (!haveExplosion){
						explosion();
						haveExplosion = true;
					}
				}
				else{
					if (cycleOne){
						map[cycle.getXPos()][cycle.getYPos()]=Tile.PONE;
					}
					else {
						map[cycle.getXPos()][cycle.getYPos()]=Tile.PTWO;
					}
				}
			}
			cycleOne = false;
		}
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
		for(Cycle cycle:cycles){
			if (cycles[0].isAlive && cycles[1].isAlive){
				super.paintComponent(g);
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
								g.setColor(cycles[0].getColor());
								break;
							case PTWO:
								g.setColor(cycles[1].getColor());
								break;
						}
						g.fillRect(i*increment+xOffset, j*increment+yOffset, increment, increment);
					}
				}
			}
			else if (!cycle.isAlive){
				for (Color color : getExplosionColors()){
					g.setColor(color);
					g.fillOval(xOffset+cycle.getXPos()*increment-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
							yOffset+cycle.getYPos()*increment-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
							(int)(explosionCount*Math.random()), 
							(int)(explosionCount*Math.random()));
				}
			}
		}
	}
	
	/**
	 * This method will generate colors for the explosion.  
	 * @return LinkedList<Color> colors
	 */
	 
	/* This is done by using randomizers 
	 * and distance from the player location.  When the timer has timed for a specific 
	 * amount of time the colors will shift to greyscale and will slowly fade out.  
	 */
	private List<Color> getExplosionColors(){
		LinkedList<Color> colors = new LinkedList<Color>();
		for (int i = 0; i<explosionCount; i++){
			int iOffset = Math.abs(i-explosionCount/2);
			for (int j=0; j<explosionCount; j++){
				int jOffset = Math.abs(j-explosionCount/2);
				int color = (int) (20*Math.random());
				if (!((iOffset+jOffset)>explosionCount/5)){
					if (explosionCount < 30){
						if (color<15){
							if (iOffset > explosionCount/6 || jOffset > explosionCount/6){
								colors.add(Color.BLACK);
							}
							else{
								colors.add(Color.RED);
							}
						}
						if (color>=15){
							if (iOffset > explosionCount/6 || jOffset > explosionCount/6){
								colors.add(Color.GRAY);
							}
							else{
								colors.add(Color.ORANGE);
							}
						}
					}
					else{
						int hexDiffs = 62-explosionCount;
						if(hexDiffs<=0){
							hexDiffs = 0;
						}
						else{
							hexDiffs /= 2;
						}
						hexDiffs = (int) (Math.random()*hexDiffs);
						int hex = 0;
						for (int k=0; k<6; k++){
							hex += (int) (hexDiffs*Math.pow(16, k));
						}
						hex = ~hex;
						Color smoke = new Color(hex);
						colors.add(smoke);
					}
				}
			}
		}
		return colors;
	}
	
	/**
	 * This method is the engine for the explosion graphics.  
	 * It provides a timer separate from the game timer which will 
	 * update the map to provide an explosion animation.
	 */
	 /* This method is called when one or more cycles have crashed.  
	 * It will start a timer that will generate an explosion graphic.  
	 * When the explosionCounter passes a threshold the explosion will stop, 
	 * and the win condition will be piped to the Frame class which will 
	 * call GameEnd.
	 */
	private void explosion(){
		Color transparent = new Color(0,0,0,0);
		cycles[0].setColor(transparent);
		cycles[1].setColor(transparent);
		explosionCount = 0;
		explosionTimer = new Timer(33, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(explosionCount<80){
					explosionCount++;
					repaint();
				}
				else{
					explosionTimer.stop();
					if (cycles[0].isAlive){
						gameMaster.endGame(WinCondition.PONE_WIN);
					}
					else if (cycles[1].isAlive){
						gameMaster.endGame(WinCondition.PTWO_WIN);
					}
					else{
						gameMaster.endGame(WinCondition.TIE);
					}
				}
			}
		});
		explosionTimer.start();
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
