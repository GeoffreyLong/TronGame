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

public class GameMasterTest {
	private int testPlayerOne = 1;
	private int testPlayerTwo = 2;
	private Player playerOne = new Player(testPlayerOne);
	private Player playerTwo = new Player(testPlayerTwo);
	private GameSetup testGameSetup;
	
	public GameMasterTest (){
		testGameSetup = new GameSetup(playerOne, playerTwo);
	}
	
	@Test
	public void testGameInit() {
		
	}
	
}