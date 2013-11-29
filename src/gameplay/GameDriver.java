package gameplay;

import game.GameSetup;
import game.Map;
import game.WinCondition;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import start.FrameDriver;


/**
 * @author Geoffrey Long
 * 
 * Provides a means of separating the logic from the panel.  Serves to 
 * separate the logic of both the ExplosionPanel and the MapPanel.  
 */
public class GameDriver implements ActionListener{

	private GameMaster gameMaster;
	private GameSetup gameSetup;
	private Map mapper;
	private Tile[][] map;
	private Cycle[] cycles;
	private MapPanel mapPanel;
	private Timer explosionTimer;
	
	/**
	 * Sets the class variables, instantiates the cycles, and starts 
	 * the game timer.
	 * 
	 * @param gameSetup  The setup of the game
	 * @param gameMaster  Controls the higher level functions of the game
	 */
	public GameDriver(GameSetup gameSetup, GameMaster gameMaster){
		this.gameMaster = gameMaster;
		this.gameSetup = gameSetup;
		mapper = gameSetup.getMap();
		map = mapper.getMap();
		
		initCycles();
		start();
	}
	
	private void initCycles(){
		Cycle cycleOne = new Cycle(mapper.getPOneXStart(), mapper.getPOneYStart(), 
				null, true, gameSetup.getPlayerColor(1));
		Cycle cycleTwo = new Cycle(mapper.getPTwoXStart(), mapper.getPTwoYStart(), 
				null, true, gameSetup.getPlayerColor(2));
		
		cycles = new Cycle[]{cycleOne, cycleTwo};

		this.map[cycleOne.getXPos()][cycleOne.getYPos()] = Tile.PONE;
		this.map[cycleTwo.getXPos()][cycleTwo.getYPos()] = Tile.PTWO;
	}
	
	private void start(){
		PlayerControl cont = new PlayerControl(cycles[0], cycles[1]);
		mapPanel = new MapPanel(gameSetup, gameMaster, cont);
		FrameDriver.startGame(mapPanel);
	}
	
	private void explosion(){
		explosionTimer = new Timer(33, new ActionListener(){
			int explosionCount = 0;
			ExplosionPanel exp = new ExplosionPanel(cycles, MapPanel.getIncrement());
			@Override
			public void actionPerformed(ActionEvent e) {
				if (explosionCount == 0){
					FrameDriver.explosion(exp);
				}
				
				if(explosionCount<80){
					explosionCount++;
					exp.updatePanel(explosionCount, getExplosionColors(explosionCount));
				}
				else{
					gameEnd();
					FrameDriver.removePanel(exp);
				}
			}
		});
		explosionTimer.start();
	}

	private void gameEnd(){
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

	/**
	 * This method will generate colors for the explosion.  
	 * @return LinkedList<Color> colors
	 */
	
	/* This is done by using randomizers 
	 * and distance from the player location.  When the timer has timed for a specific 
	 * amount of time the colors will shift to greyscale and will slowly fade out.  
	 */
	private static List<Color> getExplosionColors(int explosionCount){
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

	@Override
	/**
	 * Updates the map on a timer
	 */
	public void actionPerformed(ActionEvent e) {
		boolean cycleOne = true;
		for (Cycle cycle : cycles){
			if (cycles[0].getCurHeading()!=null && cycles[1].getCurHeading()!=null){
				mapPanel.disableButtons();
				cycle.travel();
				if (map[cycle.getXPos()][cycle.getYPos()]==Tile.WALL || 
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PONE ||
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PTWO){
					cycle.isAlive = false;
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
		if (cycles[0].isAlive && cycles[1].isAlive){
			mapPanel.updateMap(map);
		}
		else{
			gameMaster.timer.stop();
			explosion();
		}
	}
}
