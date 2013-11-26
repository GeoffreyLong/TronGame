package gameplay;

import game.GameSetup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Geoffrey Long
 * 
 * Provides a an update to the MapPanel at every timedout call of the 
 * timer in GameMaster.
 */
public class GameTimer implements ActionListener {
	GameDriver gameDriver;
	
	/** 
	 * Constructor adds an instance of MapPanel as a class variable, 
	 * performing this action allows mapPanel to be used every time 
	 * the timer times out.
	 * @param mapPanel
	 */
	public GameTimer(GameSetup gameSetup, GameMaster gameMaster) {
		gameDriver = new GameDriver(gameSetup, gameMaster);
	}

	/**
	 * Calls MapPanel.updateMap() every time the timer times out
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		gameDriver.update();
	}

}
