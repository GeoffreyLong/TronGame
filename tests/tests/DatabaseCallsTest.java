/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * DatabaseCalls Testing Class
 */

package tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import start.Connect;
import start.DatabaseCalls;

public class DatabaseCallsTest {
	private String testName = "ashleyTest";
	private String testPassword = "1234ABcd";
	private String wrongName = "wdfwffffef"; // and random name will be suitable
	private String tooShort = "2aB*";
	private String missingCapital = "2234sdf)";
	private String missingNumber = "hwjbSFF&";
	private String missingChar = "1232sfdS";
	private String acceptableString = "123bdwdSSF&^";
	
	@Test
	public void testCreateUser() {
		//Connection testConnection = Connect.connect();
		DatabaseCalls testDatabase = new DatabaseCalls();
		
		//testing for creating and user that already exists, only way to check
		assertEquals(testDatabase.createUser(testName,testPassword), false);
	}
	
	@Test
	public void testLogin() {
		//Connection testConnection = Connect.connect();
		DatabaseCalls testDatabase = new DatabaseCalls();
		
		//test login incorrect passwords of different types, and correct login
		assertEquals(testDatabase.login(testName, tooShort), false);
		assertEquals(testDatabase.login(testName, missingCapital), false);
		assertEquals(testDatabase.login(testName, missingNumber), false);
		assertEquals(testDatabase.login(testName, missingChar), false);
		assertEquals(testDatabase.login(testName, acceptableString), false);
	
		//test login with incorrect name and passwords of different types
		assertEquals(testDatabase.login(wrongName, tooShort), false);
		assertEquals(testDatabase.login(wrongName, missingCapital), false);
		assertEquals(testDatabase.login(wrongName, missingNumber), false);
		assertEquals(testDatabase.login(wrongName, missingChar), false);
		assertEquals(testDatabase.login(wrongName, acceptableString), false);
		
		//test logging into test login account, correct password
		assertEquals(testDatabase.login(testName, testPassword), true);
	}
}