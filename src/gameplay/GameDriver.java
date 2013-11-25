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

public class GameDriver {

	private GameMaster gameMaster;
	private GameSetup gameSetup;
	private Map mapper;
	private Tile[][] map;
	Cycle[] cycles;
	private MapPanel mapPanel;
	private int explosionCount = 0;
	private Timer explosionTimer;
	
	public GameDriver(GameSetup gameSetup, GameMaster gameMaster){
		this.gameMaster = gameMaster;
		this.gameSetup = gameSetup;
		mapper = gameSetup.getMap();
		map = mapper.getMap();
		
		initCycles();
	}
	
	public void initCycles(){
		Cycle cycleOne = new Cycle(mapper.getPOneXStart(), mapper.getPOneYStart(), 
				null, true, gameSetup.getPlayerColor(1));
		Cycle cycleTwo = new Cycle(mapper.getPTwoXStart(), mapper.getPTwoYStart(), 
				null, true, gameSetup.getPlayerColor(2));
		
		PlayerControl cont = new PlayerControl(cycleOne, cycleTwo);
		
		mapPanel = new MapPanel(gameSetup, cont);
		FrameDriver.startGame(mapPanel);
		
		cycles = new Cycle[]{cycleOne, cycleTwo};

		this.map[cycleOne.getXPos()][cycleOne.getYPos()] = Tile.PONE;
		this.map[cycleTwo.getXPos()][cycleTwo.getYPos()] = Tile.PTWO;
	}
	
	public void update(){
		boolean cycleOne = true;
		for (Cycle cycle : cycles){
			if (cycles[0].getCurHeading()!=null && cycles[1].getCurHeading()!=null){
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
			explosion();
			gameMaster.timer.stop();
		}
	}
	
	private void explosion(){
		explosionTimer = new Timer(33, new ActionListener(){
			int explosionCount = 0;
			private int buffer = 50;
			ExplosionPanel exp = new ExplosionPanel(cycles, MapPanel.getIncrement(), buffer);
			@Override
			public void actionPerformed(ActionEvent e) {
				if (explosionCount == 0){
					FrameDriver.explosion(exp, MapPanel.getXOffset()-buffer, MapPanel.getYOffset()-buffer);
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
	 *//*
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
	}*/
}
