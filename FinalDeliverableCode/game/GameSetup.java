package game;

import gameplay.Map;

import java.awt.Color;

import user.Player;

public class GameSetup {
	Player playerOne;
	Player playerTwo;
	private int gameDifficulty = 5;
	private MapHandler mapHandle = new MapHandler();
	private Color colorPOne = Color.RED;
	private Color colorPTwo = Color.BLUE;
	
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
	public void incrementGameDifficulty(){
		gameDifficulty++;
	}
	public void decrementGameDifficulty(){
		gameDifficulty--;
	}
	public Map getMap(){
		return mapHandle.getMap();
	}
	public void setMap(){
		
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
}
