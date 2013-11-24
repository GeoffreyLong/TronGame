package gameplay;

import game.EndGame;
import game.EndScreen;
import game.GameSetup;
import game.Map;
import game.WinCondition;

import java.awt.Color;

import javax.swing.Timer;

import start.Frame;

/**
 * @author Geoffrey Long
 * 
 * Provides control of the high-level game functions.
 * These functions include 
 * initializing the MapPanel, setting the game difficulty, 
 * starting the game, running the game, and ending the game.
 */
public class GameMaster {
	private Map map;
	private MapPanel mapPanel;
	public static Timer timer;
	private GameSetup gameSetup;
	private int playerOneWins;
	private int playerTwoWins;
	private int gamesPlayed;
	
	public GameMaster(GameSetup gameSetup){
		this.gameSetup = gameSetup;
	}
	/**
	 * Initialize the game by adding mapPanel to the Frame
	 */
	public void gameInit(){
		mapPanel = Frame.start(gameSetup, this);
	}
	
	/**
	 * This method will start the game by updating the MapPanel and game state 
	 * via the use of a timer.  The speed at which the timer times out, and 
	 * therefore the speed of the gameplay is modulated by the gameDifficulty 
	 * attribute of GameSetup.  
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
			gameInit();
			gameStart();
		}
	}
	
	// Added by Ashley to seperate the logic from game initializations
	private void endGameConditions(WinCondition winCond) {
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
	
	// getter for Ashley testing
	public int getPlayerOneWins() {
		return playerOneWins;
	}
	
	// getter for Ashley testing
	public int getPlayerTwoWins() {
		return playerTwoWins;
	}
	
	// getter for Ashley testing
	public int getGamesPlayed() {
		return gamesPlayed;
	}
}
