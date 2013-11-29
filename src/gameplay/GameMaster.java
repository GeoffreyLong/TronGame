package gameplay;

import game.EndGame;
import game.GameSetup;
import game.WinCondition;

import javax.swing.Timer;

/**
 * @author Geoffrey Long
 * 
 * Provides control of the high-level game functions.
 * These functions include 
 * initializing the MapPanel, setting the game difficulty, 
 * starting the game, running the game, and ending the game.
 */
public class GameMaster {
	public Timer timer;
	public Timer explosionTimer;
	private GameSetup gameSetup;
	private int playerOneWins = 0;
	private int playerTwoWins = 0;
	private int gamesPlayed;
	private int gameSpeed;
	
	public GameMaster(GameSetup gameSetup){
		this.gameSetup = gameSetup;
	}
	
	/**
	 * This method will start the game by updating the MapPanel and game state 
	 * via the use of a timer.  The speed at which the timer times out, and 
	 * therefore the speed of the gameplay is modulated by the gameDifficulty 
	 * attribute of GameSetup.  
	 */
	public void gameStart(){
		gameSpeed = 33;
		
		changeDifficulty();
		
		timer = new Timer(gameSpeed, new GameDriver(gameSetup, this));
		timer.start();
	}
	
	public void changeDifficulty() {
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
	}
	
	/** 
	 * This method stops the timer which stops player movement 
	 * and therefore the game
	 */
	public void endGame(WinCondition winCond){
		endGameConditions(winCond);
		
		if (playerOneWins >= 2 || playerTwoWins >= 2){
			EndGame endScreen = new EndGame(playerOneWins, playerTwoWins, gamesPlayed);
			endScreen.initPanel();
		}
		else{
			gameSetup.resetMap();
			gameStart();
		}
	}

	// Added by Ashley to seperate the logic from game initializations
	public void endGameConditions(WinCondition winCond) {
		gamesPlayed++;
		switch(winCond){
		case PONE_WIN:
			playerOneWins++;
			break;
		case PTWO_WIN:
			playerTwoWins++;
			break;
		case TIE:	
			break;
		}
	}
	
	// getter for playerOneWins, Ashley testing
	public int getPlayerOneWins() {
		return playerOneWins;
	}
	
	// getter for playerTwoWins, Ashley testing
	public int getPlayerTwoWins() {
		return playerTwoWins;
	}
	
	// getter for gamesPlayed, Ashley testing
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	// getter for the gameSpeed
	public int getGameSpeed() {
		return gameSpeed;
	}
}
