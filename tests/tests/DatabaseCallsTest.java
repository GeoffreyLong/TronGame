/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * DatabaseCalls Testing Class
 */

package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import java.sql.*;

import start.DatabaseCalls;
import start.Connect;

public class DatabaseCallsTest {
	private String testName = "ashleyTest";
	private String testPassword = "1234ABcd";
	
	@Test
	public void testCreateUser() {
		Connection testConnection = Connect.connect();
		DatabaseCalls testDatabase = new DatabaseCalls(testConnection);
		
		//testing for creating and user that already exists, only way to check
		assertEquals(testDatabase.createUser(testName,testPassword), false);
	}
	
	@Test
	public void testLogin() {
		Connection testConnection = Connect.connect();
		DatabaseCalls testDatabase = new DatabaseCalls(testConnection);
		
		//test logging into test login account
		assertEquals(testDatabase.login(testName, testPassword), true);
	}
}