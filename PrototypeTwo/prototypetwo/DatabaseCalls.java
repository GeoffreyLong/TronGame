package prototypetwo;

import java.sql.*;

public class DatabaseCalls {
	
	private Connection conn;
	
	public DatabaseCalls(Connection conn){
		this.conn = conn;
	}
	
	public boolean createUser(String userName, String password){

		boolean pass = false;
		
		String query = "INSERT INTO authentication.login (userName, password) VALUES (\'" + userName + "\',\'" +  password + "\')";
		
		try{
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			pass = true;
		} 
        
        	catch (SQLException e) {
			//System.out.println("User already exists");
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


}
