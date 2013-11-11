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

package prototype;

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

import prototype.Cycle.Heading;

public class MapPanel extends JPanel implements KeyListener {
	private int[][] map;
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
	public MapPanel(Map map){
		this.map = map.getMap();
		this.xSize = map.getXSize();
		this.ySize = map.getYSize();
		
		xOffset = (Frame.getXSize() - xSize) / 2;
		yOffset = (Frame.getYSize() - ySize) / 2;
		
		Cycle cycleOne = new Cycle(100, 400, null, true, Color.RED);
		Cycle cycleTwo = new Cycle(400, 400, null, true, Color.BLUE);
		cycles = new Cycle[]{cycleOne, cycleTwo};
		cont = new PlayerControl(cycleOne, cycleTwo);
		
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
		for (Cycle cycle : cycles){
			if (cycles[0].getCurHeading()!=null && cycles[1].getCurHeading()!=null){
				gameStart = false;
				cycle.travel(increment);
				if (map[cycle.getXPos()][cycle.getYPos()]!=0){
					GameMaster.gameEnd();
					cycle.isAlive = false;
					explosion();
				}
				else{
					map[cycle.getXPos()][cycle.getYPos()]=1;
				}
				repaint();
			}
		}
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
		if (gameStart){
			for (int i=0; i<xSize; i++){
				for (int j=0; j<ySize; j++){
					if (map[i][j]==1){
						g.setColor(Color.BLACK);
					}
					else{
						g.setColor(Color.WHITE);
					}
					g.fillRect(i+xOffset, j+yOffset, 1, 1);
				}
			}
			g.setColor(cycles[0].getColor());
			g.fillRect(cycles[0].getXPos() + xOffset, cycles[0].getYPos() + yOffset, increment, increment);
			g.setColor(cycles[1].getColor());
			g.fillRect(cycles[1].getXPos() + xOffset, cycles[1].getYPos() + yOffset, increment, increment);
		}
		else{
			for (Cycle cycle : cycles){
				if (cycle.isAlive){
					g.setColor(cycle.getColor());
					g.fillRect(cycle.getXPos()+xOffset, cycle.getYPos()+yOffset, increment, increment);
				}
				else{
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
						Frame.endGame(1);
					}
					else if (cycles[1].isAlive){
						Frame.endGame(2);
					}
					else{
						Frame.endGame(3);
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
