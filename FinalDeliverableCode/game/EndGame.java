package game;

import start.Frame;
import start.FrameDriver;

public class EndGame {
	private int pOneWins;
	private int pTwoWins;
	private int gamesPlayed;
	
	public EndGame(int pOneWins, int pTwoWins, int gamesPlayed){
		this.pOneWins = pOneWins;
		this.pTwoWins = pTwoWins;
		this.gamesPlayed = gamesPlayed;
	}
	public void initPanel(){
		FrameDriver.endGame(pOneWins, pTwoWins, gamesPlayed);
	}
}
