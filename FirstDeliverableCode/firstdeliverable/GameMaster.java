/**
 * @author Geoffrey Long
 * 
 * This class is responsible for controlling the high-level game functions.
 * These functions include 
 * initializing the MapPanel, starting the game, and ending the game.
 */
package firstdeliverable;

public class GameMaster {
	private Map map;
	private MapPanel mapPanel;
	
	public void gameInit(){
		map = new Map();
		mapPanel= Frame.start(map);
	}		
	public void gameStart(){

	}
	public static void gameEnd(){

	}
}
