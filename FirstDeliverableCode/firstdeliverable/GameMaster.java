/**
 * @author Geoffrey Long
 * 
 * This class is responsible for controlling the high-level game functions.
 * These functions include 
 * initializing the MapPanel, starting the game, and ending the game.
 */
package firstdeliverable;

import javax.swing.Timer;

public class GameMaster {
	private Map map;
	private MapPanel mapPanel;
	private static Timer timer;
	
	public void gameInit(){
		map = new Map();
		mapPanel= Frame.start(map);
	}		
	public void gameStart(){
		timer = new Timer(33, new GameTimer(mapPanel));
		timer.start();
	}
	public static void gameEnd(){
		timer.stop();
	}
}
