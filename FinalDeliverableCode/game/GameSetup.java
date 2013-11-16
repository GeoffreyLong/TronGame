package game;

import gameplay.Map;

import java.awt.Color;

import user.Player;

public class GameSetup {
	Player playerOne;
	Player playerTwo;
	private int gameDifficulty = 5;
	private Map map = new Map();
	private Color colorPOne = Color.RED;
	private Color colorPTwo = Color.BLUE;
	
	public GameSetup(Player playerOne, Player playerTwo){
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public int getGameDifficulty(){
		return this.gameDifficulty;
	}
	public void setGameDifficulty(int gameDifficulty){
		this.gameDifficulty = gameDifficulty;
	}
	
	public Map getMap(){
		return this.map;
	}
	public void setMap(){
		
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
