package game;

import start.Frame;

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
		EndScreen endScreen = new EndScreen(pOneWins, pTwoWins, gamesPlayed);
		endScreen.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		Frame.removeAll();
		Frame.addPanel(endScreen);
	}
}
