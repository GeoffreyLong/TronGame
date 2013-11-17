/**
 * @author Geoffrey Long
 * 
 * This class is responsible for the gui for the gameplay.  
 * The map is instantiated to the map that is passed in.
 * The gui begins by populating the panel with this map and the player cycles.  
 * The players will remain stagnant until both press buttons corresponding to 
 * updating their headings.  Once both players have selected a heading then the 
 * game officially starts.  The players will be free to move around the map.  
 * They will continue moving until they hit a wall or a trail.  Once this happens 
 * then an explosion will emerge from the crashed cycle, and a player will be declared 
 * and the outcome will be passed back to the frame, then to EndGame.
 */

package gameplay;

import game.GameSetup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import start.Frame;

public class MapPanel extends JPanel implements KeyListener {
	private Tile[][] map;
	private int xSize;
	private int ySize;
	PlayerControl cont;
	Cycle[] cycles;
	private boolean gameStart = true;
	private Timer explosionTimer;
	private int explosionCount;
	private int xOffset;
	private int yOffset;
	private int increment = 5;
	
	/**
	 * Instantiate all the class variables that are necessary for game function.  
	 * @param map
	 */
	public MapPanel(GameSetup gameSetup){
		Map mapper = gameSetup.getMap();
		map = mapper.getMap();
		xSize = mapper.getXSize();
		ySize = mapper.getYSize();
		
		xOffset = (Frame.getXSize() - xSize) / 2;
		yOffset = (Frame.getYSize() - ySize) / 2;
		
		setBounds(0,0,Frame.getXSize(),Frame.getYSize());
		
		Cycle cycleOne = new Cycle(100, 400, null, true, gameSetup.getPlayerColor(1));
		Cycle cycleTwo = new Cycle(400, 400, null, true, gameSetup.getPlayerColor(2));
		cycles = new Cycle[]{cycleOne, cycleTwo};
		cont = new PlayerControl(cycleOne, cycleTwo);
		
		this.map[cycleOne.getXPos()][cycleOne.getYPos()] = Tile.PONE;
		this.map[cycleTwo.getXPos()][cycleTwo.getYPos()] = Tile.PTWO;
		
		addKeyListener(this);
		setVisible(true);
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	/**
	 * Method used as standard in many swing applications.
	 */
	public Dimension getPreferredSize() {
        return new Dimension(Frame.getXSize(),Frame.getYSize());
	}
	
	/**
	 * This method is responsible for the brunt of the gameplay.  
	 * It is called every time the timer times out.  
	 * It will perform the following functions: check to see if the cycles 
	 * have set an initial start heading; check to see if the cycles are in bounds;
	 * call the explosion method if the cycles are not in bounds; 
	 * move the cycles as per their heading; 
	 * set the map at the cycle's new location to out of bounds; 
	 * and call to repaint to update the graphics.
	 */
	public void updateMap(){
		this.requestFocusInWindow();
		boolean cycleOne = true;
		for (Cycle cycle : cycles){
			if (cycles[0].getCurHeading()!=null && cycles[1].getCurHeading()!=null){
				cycle.travel(increment);
				if (map[cycle.getXPos()][cycle.getYPos()]==Tile.WALL || 
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PONE ||
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PTWO){
					GameMaster.gameEnd();
					cycle.isAlive = false;
					explosion();
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
	 * This method is standard in swing graphics.  It is called every time 
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
				for (int i=0; i<xSize; i+=5){
					for (int j=0; j<ySize; j+=5){
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
						g.fillRect(i+xOffset, j+yOffset, 5, 5);
					}
				}
			}
			else if (!cycle.isAlive){
				for (Color color : getExplosionColors()){
					g.setColor(color);
					g.fillOval(xOffset+cycle.getXPos()-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
							yOffset+cycle.getYPos()-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
							(int)(explosionCount*Math.random()), 
							(int)(explosionCount*Math.random()));
				}
			}
		}
	}
	
	/**
	 * This will generate colors for the explosion.  This is done by using randomizers 
	 * and distance from the player location.  When the timer has timed for a specific 
	 * amount of time the colors will shift to greyscale and will slowly fade out.  
	 * @return LinkedList<Color> colors
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
	 * This method is called when one or more cycles have crashed.  
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
						//Frame.endGame(1);
					}
					else if (cycles[1].isAlive){
						//Frame.endGame(2);
					}
					else{
						//Frame.endGame(3);
					}
				}
			}
		});
		explosionTimer.start();
	}
	
	/**
	 * This method will get the KeyEvents from the KeyListener and will pass 
	 * them to the PlayerControl to make the appropriate changes to the player heading.
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
}
