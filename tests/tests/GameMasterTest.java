/** 
 * @author Ashley Simpson
 * @version 2013/11/20
 * GameMaster Testing Class
 */

package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import game.GameSetup;
import user.Player;
import gameplay.GameMaster;
import game.WinCondition;

public class GameMasterTest {
	private int testPlayerOne = 1;
	private int testPlayerTwo = 2;
	private Player playerOne = new Player(testPlayerOne);
	private Player playerTwo = new Player(testPlayerTwo);
	private GameSetup testGameSetup;
	private GameMaster testGameMaster;
	private int playerOneWins = 0;
	private int playerTwoWins = 0;
	private int gamesPlayed = 0;
	
	@Test
	public void testGameEndConditions() {
		testGameSetup = new GameSetup(playerOne, playerTwo);
		testGameMaster = new GameMaster(testGameSetup);
		
		// test base condition
		assertEquals(gamesPlayed, testGameMaster.getGamesPlayed());
		assertEquals(playerOneWins, testGameMaster.getPlayerOneWins());
		assertEquals(playerTwoWins, testGameMaster.getPlayerTwoWins());
		
		//test player one win
		testGameMaster.endGameConditions(WinCondition.PONE_WIN);
		playerOneWins++;
		gamesPlayed++;
		assertEquals(gamesPlayed, testGameMaster.getGamesPlayed());
		assertEquals(playerOneWins, testGameMaster.getPlayerOneWins());
		assertEquals(playerTwoWins, testGameMaster.getPlayerTwoWins());
		
		// test player two win
		testGameMaster.endGameConditions(WinCondition.PTWO_WIN);
		playerTwoWins++;
		gamesPlayed++;
		assertEquals(gamesPlayed, testGameMaster.getGamesPlayed());
		assertEquals(playerOneWins, testGameMaster.getPlayerOneWins());
		assertEquals(playerTwoWins, testGameMaster.getPlayerTwoWins());
		
		// test tie
		testGameMaster.endGameConditions(WinCondition.TIE);
		gamesPlayed++;
		assertEquals(gamesPlayed, testGameMaster.getGamesPlayed());
		assertEquals(playerOneWins, testGameMaster.getPlayerOneWins());
		assertEquals(playerTwoWins, testGameMaster.getPlayerTwoWins());
	}
	
	@Test
	public void testDifficultyChange() {
		testGameSetup = new GameSetup(playerOne, playerTwo);
		testGameMaster = new GameMaster(testGameSetup);
		
		// test game difficulty 4
		testGameSetup.decrementGameDifficulty();
		testGameMaster.changeDifficulty();
		assertEquals(50, testGameMaster.getGameSpeed());
		
		// test game difficulty 3
		testGameSetup.decrementGameDifficulty();
		testGameMaster.changeDifficulty();
		assertEquals(75, testGameMaster.getGameSpeed());
		
		// test game difficulty 2
		testGameSetup.decrementGameDifficulty();
		testGameMaster.changeDifficulty();
		assertEquals(100, testGameMaster.getGameSpeed());
		
		// test game difficulty 1
		testGameSetup.decrementGameDifficulty();
		testGameMaster.changeDifficulty();
		assertEquals(150, testGameMaster.getGameSpeed());
		
		// test game difficulty 0
		testGameSetup.decrementGameDifficulty();
		testGameMaster.changeDifficulty();
		assertEquals(200, testGameMaster.getGameSpeed());
	}
}