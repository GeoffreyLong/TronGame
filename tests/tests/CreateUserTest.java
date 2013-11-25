/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * CreateUser Testing Class
 */

package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import user.CreateUser;
import user.Player;

public class CreateUserTest {
	private Player testPlayer;
	private int playerNum = 1;
	private String tooShort = "2aB*";
	private String missingCapital = "2234sdf)";
	private String missingNumber = "hwjbSFF&";
	private String missingChar = "1232sfdS";
	private String acceptableString = "123bdwdSSF&^";
	
	@Test
	public void checkPasswordTest() {
		testPlayer = new Player(playerNum);
		CreateUser testCreateUser = new CreateUser(testPlayer);
		
		// test missing char
		assertEquals(false, testCreateUser.checkPassword(missingChar));
		
		// test missing capital
		assertEquals(false, testCreateUser.checkPassword(missingCapital));
		
		// test missing number
		assertEquals(false, testCreateUser.checkPassword(missingNumber));
				
		// test too short
		assertEquals(false, testCreateUser.checkPassword(tooShort));
				
		// test good password
		assertEquals(true, testCreateUser.checkPassword(acceptableString));
		
		// test empty string
		assertEquals(false, testCreateUser.checkPassword(""));
	}
}