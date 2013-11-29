package game;

import java.awt.Color;

import user.Player;

/** 
 * @author Geoffrey Long
 * 
 * Provides a temporary storage medium to interface between the SetupPanel, 
 * the MapChooser, the GameMaster, and the MapPanel.  
 */
public class GameSetup {
	/*
	 * The information that 
	 * this class stores includes the map, the player colors, and the game difficulty.  
	 * The information is set via the user's actions on the SetupPanel and 
	 * MapChooser.  It is piped to the MapPanel when the game starts where 
	 * it acts on the MapPanel to alter the appearance.  It is also used 
	 * in GameMaster to set the refresh rate and therefore the speed of the 
	 * cycle.
	 */
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
	
	/**
	 * Get the MapHandler
	 * @return mapHandle  An instance of the MapHandler class
	 */
	public MapHandler getMapHandler(){
		return mapHandle;
	}
	
	/**
	 * Return the game difficulty
	 * @return gameDifficulty  The difficulty (speed of the cycle movement)
	 */
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
	
	/**
	 * Get the current Map from mapHandle
	 * @return map  A Map object
	 */
	public Map getMap(){
		return mapHandle.getMap();
	}
	
	/**
	 * Reset the map, reset based on the current map selection.
	 */
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
	
	/**
	 * Set the color of the player one cycle.
	 * @param color  A new Color
	 */
	public void setPOneColor(Color color){
		colorPOne = color;
	}
	
	/**
	 * Set the color of the player two cycle.
	 * @param color  A new Color
	 */
	public void setPTwoColor(Color color){
		colorPTwo = color;
	}
	
	/**
	 * Get the color of the cycle
	 * @param playerNum  The number of the player
	 * @return The color of the cycle corresponding to playerNum
	 */
	public Color getPlayerColor(int playerNum){
		if (playerNum == 1){
			return this.colorPOne;
		}
		else{
			return this.colorPTwo;
		}
	}
	
	/**
	 * Set the increment
	 * @param increment  The number of pixels from which every map size and cycle travel distance is determined  
	 */
	public void setIncrement(int increment){
		this.increment = increment;
	}
	/**
	 * Get the increment
	 * @return increment  The number of pixels from which every map size and cycle travel distance is determined  
	 */
	public int getIncrement(){
		return increment;
	}
}
