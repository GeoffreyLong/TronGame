package game;

import java.sql.Connection;

import main.Connect;
import main.DatabaseCalls;
import main.FrameDriver;
import user.LoginPanel;

/**
 * @author Geoffrey Long
 * 
 * Provides a call to push statistics and the instantiation of EndScreen
 */
public class EndGame {
	private int pOneWins;
	private int pTwoWins;
	private int gamesPlayed;
	private int updatePlayerOne;
	private int updatePlayerTwo;
	
	/**
	 * Set class variables and push the game end statistics 
	 * 
	 * @param pOneWins  Number of player one wins this round
	 * @param pTwoWins  Number of player two wins this round
	 * @param gamesPlayed  Number of games played this round
	 */
	public EndGame(int pOneWins, int pTwoWins, int gamesPlayed){
		this.pOneWins = pOneWins;
		this.pTwoWins = pTwoWins;
		this.gamesPlayed = gamesPlayed;
		
		Connection conn = Connect.connect();
		
		if(pOneWins == 2) {
			updatePlayerOne = 1;
			updatePlayerTwo = 0;
		} else {
			updatePlayerOne = 0;
			updatePlayerTwo = 1;
		}
		
		/*
		 *A DatabaseCalls object created which is used to push statistics to the database
		 * 
		 */

		
		DatabaseCalls call = new DatabaseCalls();
		
		if(!LoginPanel.player1.equals("") && !LoginPanel.player2.equals("")){
			call.pushStatistics(LoginPanel.player1, updatePlayerOne, LoginPanel.player2, updatePlayerTwo);
		}
	
	}
	
	/**
	 * Initialize the End Screen
	 */
	public void initPanel(){
		FrameDriver.endTheGame(pOneWins, pTwoWins, gamesPlayed);
	}
}
