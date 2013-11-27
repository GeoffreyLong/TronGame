/** 
 * @author Ashley Simpson
 * @version 2013/11/25
 * Player Testing Class
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import user.Player;

public class PlayerTest {
	private Player testPlayer;
	private int playerNum = 1;
	private String testGetUsername = "anonymous";
	private int testGetPlayerNum = 1;
	private String testSetUsername = "testname";
	
	@Test
	public void testGetters() {
		testPlayer = new Player(playerNum);
		
		assertEquals(testGetUsername, testPlayer.getUserName());
		assertEquals(testGetPlayerNum, testPlayer.getPlayerNumber());
	}
	
	@Test
	public void testSetters() {
		testPlayer = new Player(playerNum);
		
		testPlayer.setUserName(testSetUsername);
		assertEquals(testSetUsername, testPlayer.getUserName());
	}
}