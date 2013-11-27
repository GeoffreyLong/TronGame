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
	
	public EndGame(int pOneWins, int pTwoWins, int gamesPlayed){
		this.pOneWins = pOneWins;
		this.pTwoWins = pTwoWins;
		this.gamesPlayed = gamesPlayed;
		
		Connection conn = Connect.connect();
		
		DatabaseCalls call = new DatabaseCalls(conn);
		call.pushStatistics(LoginGUI.player1.getUserName(), pOneWins, LoginGUI.player2.getUserName(), pTwoWins);
	}
	public void initPanel(){
		FrameDriver.endGame(pOneWins, pTwoWins, gamesPlayed);
	}
}
