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

import main.FrameDriver;


/**
 * @author Geoffrey Long
 * 
 * Provides a means of separating the logic from the panel.  Serves to 
 * separate the logic of both the ExplosionPanel and the GamePanel.  
 */
public class GameDriver implements ActionListener{

	private GameMaster gameMaster;
	private GameSetup gameSetup;
	private Map mapper;
	private Tile[][] map;
	private Cycle[] cycles;
	private GamePanel gamePanel;
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
	
	/**
	 * Initialize the Csycle objects and place their location on the Map object
	 */
	private void initCycles(){
		Cycle cycleOne = new Cycle(mapper.getPOneXStart(), mapper.getPOneYStart(), 
				null, true, gameSetup.getPlayerColor(1));
		Cycle cycleTwo = new Cycle(mapper.getPTwoXStart(), mapper.getPTwoYStart(), 
				null, true, gameSetup.getPlayerColor(2));
		
		cycles = new Cycle[]{cycleOne, cycleTwo};

		this.map[cycleOne.getXPos()][cycleOne.getYPos()] = Tile.PONE;
		this.map[cycleTwo.getXPos()][cycleTwo.getYPos()] = Tile.PTWO;
	}
	
	/**
	 * Start the game by adding the game panel
	 */
	private void start(){
		PlayerControl cont = new PlayerControl(cycles[0], cycles[1]);
		gamePanel = new GamePanel(gameSetup, gameMaster, cont);
		FrameDriver.startGame(gamePanel);
	}
	
	/**
	 * Start a timer which will create an ExplosionPanel and regulate its 
	 * behavior.  When the explosion stops, the timer will call gameEnd().
	 */
	private void explosion(){
		explosionTimer = new Timer(33, new ActionListener(){
			int explosionCount = 0;
			ExplosionPanel exp = new ExplosionPanel(cycles, GamePanel.getIncrement());
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

	/**
	 * Pass the win condition to gameMaster
	 */
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
	private static List<Color> getExplosionColors(int explosionCount){
		/* This is done by using randomizers 
		 * and distance from the player location.  When the timer has timed for a specific 
		 * amount of time the colors will shift to greyscale and will slowly fade out.  
		 */
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
		/*
		 * This method is responsible for tracking the cycle positions, 
		 * updating them, and then updating the map according to the 
		 * new positions.  
		 */
		
		//allows the setting of specific PONE or PTWO tiles
		boolean cycleOne = true;
		
		//Iterate through the cycles
		for (Cycle cycle : cycles){
			//This if statement stops the movement of the cycle 
			//if an initial heading has not been chosen
			if (cycles[0].getCurHeading()!=null && cycles[1].getCurHeading()!=null){
				
				//Disable the + and - buttons on the GamePanel so the map
				//is not resized mid round
				gamePanel.disableButtons();
				
				//Increment the cycle position
				cycle.travel();
				
				//If the cycle is dead then set the isAlive to false
				if (map[cycle.getXPos()][cycle.getYPos()]==Tile.WALL || 
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PONE ||
						map[cycle.getXPos()][cycle.getYPos()]==Tile.PTWO){
					cycle.isAlive = false;
				}
				else{
					//else set the tile to be the cycle tail
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
		//If both cycles are still alive then update the map with the 
		//new map version
		if (cycles[0].isAlive && cycles[1].isAlive){
			gamePanel.updateMap(map);
		}
		//Else stop the game and trigger and explosion
		else{
			gameMaster.timer.stop();
			explosion();
		}
	}
}
