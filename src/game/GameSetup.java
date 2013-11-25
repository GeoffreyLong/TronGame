package game;

import java.awt.Color;

import user.Player;

/** 
 * @author Geoffrey Long
 * 
 * Provides a temporary storage medium to interface between the SetupPanel, 
 * the MapChooser, the GameMaster, and the MapPanel.  The information that 
 * this class stores includes the map, the player colors, and the game difficulty.  
 * The information is set via the user's actions on the SetupPanel and 
 * MapChooser.  It is piped to the MapPanel when the game starts where 
 * it acts on the MapPanel to alter the appearance.  It is also used 
 * in GameMaster to set the refresh rate and therefore the speed of the 
 * cycle.
 */
public class GameSetup {
	Player playerOne;
	Player playerTwo;
	private int gameDifficulty = 5;
	private MapHandler mapHandle = new MapHandler();
	private Color colorPOne = Color.RED;
	private Color colorPTwo = Color.BLUE;
	private int increment = 5;
	
	/**
	 * Constructor for the GameSetup.
	 * @param playerOne
	 * @param playerTwo
	 */
	public GameSetup(Player playerOne, Player playerTwo){
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public MapHandler getMapHandler(){
		return mapHandle;
	}
	public int getGameDifficulty(){
		return this.gameDifficulty;
	}
	/**
	 * Increases the game difficulty setting by 1
	 */
	public void incrementGameDifficulty(){
		gameDifficulty++;
	}
	/**
	 * Decreases the game difficulty setting by 1
	 */
	public void decrementGameDifficulty(){
		gameDifficulty--;
	}
	public Map getMap(){
		return mapHandle.getMap();
	}
	public void resetMap(){
		switch(mapHandle.getMap().getMapNumber()){
		case 0:
			mapHandle.setMapOne();
			break;
		case 1:
			mapHandle.setMapTwo();
			break;
		case 2:
			mapHandle.setMapThree();
			break;
		default:
			mapHandle.setMapOne();
			break;
		}
	}
	public void setPOneColor(Color color){
		colorPOne = color;
	}
	public void setPTwoColor(Color color){
		colorPTwo = color;
	}
	public Color getPlayerColor(int playerNum){
		if (playerNum == 1){
			return this.colorPOne;
		}
		else{
			return this.colorPTwo;
		}
	}
	public void setIncrement(int i){
		increment = i;
	}
	public int getIncrement(){
		return increment;
	}
}
