package game;

import java.sql.Connection;

import start.Connect;
import start.DatabaseCalls;
import start.FrameDriver;
import user.LoginGUI;

public class EndGame {
	private int pOneWins;
	private int pTwoWins;
	private int gamesPlayed;
	private int updatePlayerOne;
	private int updatePlayerTwo;
	
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
		
		DatabaseCalls call = new DatabaseCalls(conn);
		call.pushStatistics(LoginGUI.player1, updatePlayerOne, LoginGUI.player2, updatePlayerTwo);
	}
	public void initPanel(){
		FrameDriver.endGame(pOneWins, pTwoWins, gamesPlayed);
	}
}
