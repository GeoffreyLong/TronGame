package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseCalls {
	
	private String driver = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
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
