/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * GameSetup Testing Class
 */

package tests;

import game.GameSetup;
import game.Map;

import java.awt.Color;

import user.Player;

public class GameSetupTest {
	private Player playerOne;
	private Player playerTwo;
	private int playerNumberOne = 1;
	private int playerNumberTwo = 2;
	private int testDifficulty;
	private int checkDifficulty = 5; //stand difficulty setting
	private Color colorOne;
	private Color colorTwo;
	private Map testMap;
	private int mapXSize = 20;
	private int mapYSize = 20;
	
	@Test
	public void testDecrementGameDifficulty() {
		playerOne = new Player(playerNumberOne);
		playerTwo = new Player(playerNumberTwo);
		GameSetup testGameSetup = new GameSetup(playerOne, playerTwo);
		
		// check to see if the difficulty is the standard difficulty
		testDifficulty = testGameSetup.getGameDifficulty();
		assertEquals(testDifficulty, checkDifficulty);

		// decrement difficulty down to 0 and check
		// check 4
		testGameSetup.decrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 4;
		assertEquals(testDifficulty, checkDifficulty);
		// check 3
		testGameSetup.decrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 3;
		assertEquals(testDifficulty, checkDifficulty);
		// check 2
		testGameSetup.decrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 2;
		assertEquals(testDifficulty, checkDifficulty);
		// check 1
		testGameSetup.decrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 1;
		assertEquals(testDifficulty, checkDifficulty);
		// check 0
		testGameSetup.decrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 0;
		assertEquals(testDifficulty, checkDifficulty);
	}
	
	@Test
	public void testIncrementDifficulty() {
		playerOne = new Player(playerNumberOne);
		playerTwo = new Player(playerNumberTwo);
		GameSetup testGameSetup = new GameSetup(playerOne, playerTwo);
		
		// decrease difficulty down to 0
		testGameSetup.decrementGameDifficulty(); // down to 4
		testGameSetup.decrementGameDifficulty(); // down to 3
		testGameSetup.decrementGameDifficulty(); // down to 2
		testGameSetup.decrementGameDifficulty(); // down to 1
		testGameSetup.decrementGameDifficulty(); // down to 0
		
		// check difficulty at 0
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 0;
		assertEquals(testDifficulty, checkDifficulty);
		
		// increment difficulty up to 5 and check
		// check 1
		testGameSetup.incrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 1;
		assertEquals(testDifficulty, checkDifficulty);
		// check 3
		testGameSetup.incrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 2;
		assertEquals(testDifficulty, checkDifficulty);
		// check 2
		testGameSetup.incrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 3;
		assertEquals(testDifficulty, checkDifficulty);
		// check 1
		testGameSetup.incrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 4;
		assertEquals(testDifficulty, checkDifficulty);
		// check 0
		testGameSetup.incrementGameDifficulty();
		testDifficulty = testGameSetup.getGameDifficulty();
		checkDifficulty = 5;
		assertEquals(testDifficulty, checkDifficulty);		
	}
	
	@Test
	public void testColorChanging() {
		playerOne = new Player(playerNumberOne);
		playerTwo = new Player(playerNumberTwo);
		GameSetup testGameSetup = new GameSetup(playerOne, playerTwo);
		
		testGameSetup.setPOneColor(colorOne.CYAN);
		testGameSetup.setPTwoColor(colorTwo.BLUE);
		Color checkOne = testGameSetup.getPlayerColor(playerNumberOne);
		Color checkTwo = testGameSetup.getPlayerColor(playerNumberTwo);
		
		assertEquals(checkOne, colorOne.CYAN);
		assertEquals(checkTwo, colorTwo.BLUE);
	}
	
	@Test
	public void testMapHandling() {
		playerOne = new Player(playerNumberOne);
		playerTwo = new Player(playerNumberTwo);
		GameSetup testGameSetup = new GameSetup(playerOne, playerTwo);
		testMap = new Map(mapXSize, mapYSize);
		
		// test game map one handling
		testGameSetup.getMapHandler().setMapOne();
		testGameSetup.resetMap();
		testMap.setMapNumber(0);
		assertEquals(testMap.getMapNumber(), testGameSetup.getMapHandler().getMap().getMapNumber());
		
		// test game map two handling
		testGameSetup.getMapHandler().setMapTwo();
		testGameSetup.resetMap();
		testMap.setMapNumber(1);
		assertEquals(testMap.getMapNumber(), testGameSetup.getMapHandler().getMap().getMapNumber());
		
		// test game map three handling
		testGameSetup.getMapHandler().setMapThree();
		testGameSetup.resetMap();
		testMap.setMapNumber(2);
		assertEquals(testMap.getMapNumber(), testGameSetup.getMapHandler().getMap().getMapNumber());		
	}
}