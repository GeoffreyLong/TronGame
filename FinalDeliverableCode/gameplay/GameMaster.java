/**
 * @author Geoffrey Long
 * 
 * This class is responsible for controlling the high-level game functions.
 * These functions include 
 * initializing the MapPanel, starting the game, and ending the game.
 */
package gameplay;

import game.GameSetup;
import game.Map;

import java.awt.Color;

import javax.swing.Timer;

import start.Frame;

public class GameMaster {
	private Map map;
	private MapPanel mapPanel;
	private static Timer timer;
	private GameSetup gameSetup;
	
	
	public GameMaster(GameSetup gameSetup){
		this.gameSetup = gameSetup;
	}
	/**
	 * This will initialize the game.  
	 * It will do this by setting up the map and the cycle objects.
	 */
	public void gameInit(){
		mapPanel= Frame.start(gameSetup);
	}
	
	/**
	 * This will start the game.  
	 * This method kicks off a timer which will call MapPanel.updatePanel()
	 * through the GameTimer class.  This is run every 33 ms.  
	 * 33 ms was chosen as this corresponds to 30 frames per second, 
	 * which is a standard for gaming.
	 */
	public void gameStart(){
		int gameSpeed = 33;
		switch(gameSetup.getGameDifficulty()){
		case(0):
			gameSpeed = 200;
			break;
		case(1):
			gameSpeed = 150;
			break;
		case(2):
			gameSpeed = 100;
			break;
		case(3):
			gameSpeed = 75;
			break;
		case(4):
			gameSpeed = 50;
			break;
		case(5):
			gameSpeed = 33;
			break;
		case(6):
			gameSpeed = 25;
			break;
		case(7):
			gameSpeed = 20;
			break;
		case(8):
			gameSpeed = 15;
			break;
		case(9):
			gameSpeed = 8;
			break;
		case(10):
			gameSpeed = 3;
			break;
		default:
			gameSpeed = 33;
			break;
		}
		timer = new Timer(gameSpeed, new GameTimer(mapPanel));
		timer.start();
	}
	
	/**
	 * A call to this will stop the timer.  
	 * When the timer is stopped the player positions stop updating.  
	 * This will effectively end the game.
	 */
	public static void gameEnd(){
		timer.stop();
	}
}
