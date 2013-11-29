package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/*
 * 
 * @author Rishabh Tandon
 * @version v1.0
 * 
 * This class is responsible for all of the calls that are made to the database.
 * The constructor initializes the connection to the database which is then used in the methods to create a statement. The
 * statement then runs a query or an update on the database which in our case is hosted on the Amazon Web Services on a Ubuntu Server 12.04 LTE.
 * The LAMP server consists of the database authentication which has 3 tables: login, allStats and playerHistory and the information from them
 * is extracted as per required.
 * 
 */



public class DatabaseCalls {
	
	private String driver = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	
	/*
	 * 
	 * Constructor for class DatabaseCalls. 
	 * Initializes the connection to the database and pops up an error message if unable to connect to the database
	 * @param none
	 * 
	 */
	
	
	public DatabaseCalls(){
		
		try{
			
			Class.forName(driver).newInstance();
	    	this.conn = DriverManager.getConnection("jdbc:mysql://ec2-54-201-70-225.us-west-2.compute.amazonaws.com:3306/authentication?user=team7&password=ecse321"); 
		}
		
		catch(Exception e){
			JFrame frame = new JFrame("Error");
			JOptionPane.showMessageDialog(frame, "Could not connect to the database. Please check your connection");
		
		}
	}
	
	
	/*
	 * 
	 * This method creates an account in the database by running an update statement on the login and allStats table.
	 * The method uses Connection 'conn' to create a statement which runs the update statement on the tables. The statements use
	 * specific MySQL statements to update the tables but follow the same convention INSERT INTO table (.......) VALUES (.......).
	 * The account is created only if the username is unique. If that's not the case the method gives a pop up telling the user
	 * that the username already exists.
	 * 
	 *  @param username The desired username of the player. Should be unique
	 *  @param password The desired password for the player. Must contain at least 1 uppercase, 1 lowercase, 1 number and 1 non-alphanumeric 
	 *  character and be at least 8 characters long
	 *  
	 *  @return boolean True if the update was successful and false otherwise
	 *  
	 * 
	 */
	
	
	public boolean createUser(String userName, String password){

		boolean pass = false;
		
		String query = "INSERT INTO authentication.login (userName, password) VALUES (\'" + userName + "\',\'" +  password + "\')";
		String query2 = "INSERT INTO authentication.allStats (userName, numberWins, numberLosses) VALUES (\'" + userName + "\', 0, 0)";
		
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate(query2);
			
			pass = true;
		} 
        
        	catch (SQLException e) {
			//System.out.println("User already exists");
        		//e.printStackTrace();
        		pass = false;
		}
		
		return pass;
		
		
	}
	
	
	/*
	 * 
	 * This method checks if the user records exists in the table login. 
	 * This information is used to proceed further with the game. The method uses the COnnection 'conn' to create a statement which runs 
	 * a query on the table login. The MySQL statement selects the row where the username equals the input and then checks if it is same 
	 * as the inputted password. If that's the case, the user can proceed or else method gives a pop up to check the password.
	 * 
	 * @param username The username of the player
	 * @param password The password of the player
	 * 
	 * @return boolean True if the username-password combination is correct and false otherwise
	 * 
	 * 
	 */
	
	
	public boolean login(String userName, String password){

		boolean pass = false;
		String check = "";
		
		String query = "SELECT * FROM authentication.login WHERE userName= \'" + userName + "\'";
		
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()){
				//System.out.println(rs.getString("password"));
				check = rs.getString("password");
			}
			
			if(check.equals(password)){
				pass = true;
			}
			
			
		} 
        
       	        catch (SQLException e) {
			System.out.println("Check your username/password");
		}
		
		return pass;
		
		
	}
	
	/*
	 * 
	 * This method pushes the statistics to the database. 
	 * The method takes as input both usernames of the logged in players and their respective scores and stores them in the tables allStats
	 * and playerHistory in database authentication. The method uses Connection 'conn' to create a statement which is then used to run an update 
	 * or a query on the table. A bunch of statements is used for two players and their scores. If an account does not exist in playerHistory
	 * already, it is created using the INSERT statement. For this first a check statement is run and then the row is created in the table.
	 * 
	 * @param username1 The username of the first player as a String
	 * @param score1 The score of the first player as int
	 * @param username2 The username of the second player as a String
	 * @param score2 The score of the second player as int
	 * 
	 * @return boolean True if the push is successful, and false otherwise
	 * 
	 * 
	 */
	
	public boolean pushStatistics(String userName1, int score1, String userName2, int score2){
		
		boolean pass = false;
		String one = "1";
		String zero = "0";
		String check = "";
		
		String wins1 = "UPDATE authentication.allStats SET numberWins = numberWins + " + score1 + " WHERE userName = \'" + userName1 + "\'";
		String loses1 = "UPDATE authentication.allStats SET numberLosses = numberLosses + " + score2 + " WHERE userName = \'" + userName1 + "\'";
		String wins2 = "UPDATE authentication.allStats SET numberWins = numberWins + " + score2 + " WHERE userName = \'" + userName2 + "\'";
		String loses2 = "UPDATE authentication.allStats SET numberLosses = numberLosses + " + score1 + " WHERE userName = \'" + userName2 + "\'";
		String totalScore = "UPDATE authentication.allStats SET totalScore = (numberWins + numberLosses) * (numberWins/numberLosses)";
		
		String queryCheck = "SELECT EXISTS (SELECT * FROM authentication.playerHistory WHERE userName= \'" + userName1 + "\' AND opponent = \'" + userName2 + "\')";
		
		String updateQuery1 = "UPDATE authentication.playerHistory SET numberWins = numberWins + " + score1 + ", numberLosses = numberLosses + " + score2 + ", numberGames = numberGames + " + (score1 + score2) + " WHERE userName = \'" + userName1 + "\' AND authentication.playerHistory.opponent = \'" + userName2 + "\'";
		String updateQuery2 = "UPDATE authentication.playerHistory SET numberWins = numberWins + " + score2 + ", numberLosses = numberLosses + " + score1 + ", numberGames = numberGames + " + (score1 + score2) + " WHERE userName = \'" + userName2 + "\' AND authentication.playerHistory.opponent = \'" + userName1 + "\'";
		String createQuery1 = "INSERT INTO authentication.playerHistory (userName, opponent, numberGames, numberWins, numberLosses) VALUES (\'" + userName1 + "\', \'" + userName2 + "\', " + (score1 + score2) + ", " + score1 + ", " + score2 + ")";
		String createQuery2 = "INSERT INTO authentication.playerHistory (userName, opponent, numberGames, numberWins, numberLosses) VALUES (\'" + userName2 + "\', \'" + userName1 + "\', " + (score1 + score2) + ", " + score2 + ", " + score1 + ")";
		
		System.out.println(updateQuery1);
		System.out.println(createQuery1);
		
		try{
			
			Statement stmt3 = conn.createStatement();
			stmt3.executeUpdate(wins1);
			
			Statement stmt4 = conn.createStatement();
			stmt4.executeUpdate(loses1);
			
			Statement stmt5 = conn.createStatement();
			stmt5.executeUpdate(wins2);
			
			Statement stmt6 = conn.createStatement();
			stmt6.executeUpdate(loses2);
			
			Statement stmtScore = conn.createStatement();
			stmtScore.executeUpdate(totalScore);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryCheck);
			
			if(rs.next()){
				check = rs.getString(1);
			}
			
			if(check.equals(one)){
				Statement stmt7 = conn.createStatement();
				stmt7.executeUpdate(updateQuery1);
				
				Statement stmt8 = conn.createStatement();
				stmt8.executeUpdate(updateQuery2);
			}
			
			else if(check.equals(zero)){
				Statement stmt9 = conn.createStatement();
				stmt9.executeUpdate(createQuery1);
				
				Statement stmt10 = conn.createStatement();
				stmt10.executeUpdate(createQuery2);
			}
			
			
			pass = true;
		}
		
		catch(Exception e){
			e.printStackTrace();
			pass = false;
		}
		
		return pass;
		
	}


}
