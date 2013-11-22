package start;

import java.sql.*;

public class DatabaseCalls {
	
	private Connection conn;
	
	public DatabaseCalls(Connection conn){
		this.conn = conn;
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
        		e.printStackTrace();
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
				System.out.println(rs.getString("password"));
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
		
		String query = "UPDATE authentication.allStats SET totalScore = totalScore + " + score1 + " WHERE userName = \'" + userName1 + "\'";
		String query2 = "UPDATE authentication.allStats SET totalScore = totalScore + " + score1 + " WHERE userName = \'" + userName2 + "\'";
		
		String wins1 = "";
		String loses1 = "";
		String wins2 = "";
		String loses2 = "";
		
		if(score1 > score2){
			wins1 = "UPDATE authentication.allStats SET numberWins = numberWins + " + score1 + "WHERE userName = \'" + userName1 + "\'";
			loses1 = "UPDATE authentication.allStats SET numberWins = numberLosses + " + score2 + "WHERE userName = \'" + userName1 + "\'";
			wins2 = "UPDATE authentication.allStats SET numberWins = numberWins + " + score2 + "WHERE userName = \'" + userName2 + "\'";
			loses2 = "UPDATE authentication.allStats SET numberWins = numberLosses + " + score1 + "WHERE userName = \'" + userName2 + "\'";
		}
		
		else if(score1 < score2){
			
		}
		
		
		try{
			System.out.println(query);
			System.out.println(query2);
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate(query2);
			
			Statement stmt3 = conn.createStatement();
			stmt3.executeUpdate(wins1);
			
			Statement stmt4 = conn.createStatement();
			stmt4.executeUpdate(loses1);
			
			Statement stmt5 = conn.createStatement();
			stmt5.executeUpdate(wins2);
			
			Statement stmt6 = conn.createStatement();
			stmt6.executeUpdate(loses2);
			
			
			pass = true;
		}
		
		catch(Exception e){
			e.printStackTrace();
			pass = false;
		}
		
		return pass;
		
	}


}
