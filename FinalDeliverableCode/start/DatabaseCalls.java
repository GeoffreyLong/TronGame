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
		String query2 = "INSERT INTO authentication.allStats (userName, totalScore, numberWins, numberLosses) VALUES (\'" + userName + "\', 0, 0, 0)";
		
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
		
		
		try{
			System.out.println(query);
			System.out.println(query2);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate(query2);
			pass = true;
		}
		
		catch(Exception e){
			e.printStackTrace();
			pass = false;
		}
		
		return pass;
		
	}


}
