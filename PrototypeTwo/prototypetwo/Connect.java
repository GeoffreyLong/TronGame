package prototypetwo;

import java.sql.*;
 
 
public class Connect{
    public static void main(String[] args) {
          
    	String url = "jdbc:mysql://ec2-54-201-70-38.us-west-2.compute.amazonaws.com";
        String dbName = "/authentication";
        String driver = "com.mysql.jdbc.Driver"; 
        String userName = "root";
        String password = "ecse321";
        
        String query = "SELECT * FROM authentication.login";
        
        try {
        	Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-54-201-70-225.us-west-2.compute.amazonaws.com:3306/authentication?user=team7&password=ecse321"); 
            System.out.println("Connected to the Database");
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
            	String usName = rs.getString("userName");
            	String pswd = rs.getString("password");
            	
            	System.out.println(usName + "\t" + pswd);
            }
            
            System.out.println();
            
            DatabaseCalls call = new DatabaseCalls(conn);
            
            System.out.println(call.login("rishabh", "ecse321"));
            
            
            conn.close();
          
        } catch (Exception e) {
              e.printStackTrace();
          }
   }
    
    public static Connection connect(){
    	
    	String driver = "com.mysql.jdbc.Driver";
    	Connection conn = null;
    	
    	try{
    	Class.forName(driver).newInstance();
        conn = DriverManager.getConnection("jdbc:mysql://ec2-54-201-70-225.us-west-2.compute.amazonaws.com:3306/authentication?user=team7&password=ecse321"); 
    
    	}
    	
    	catch(Exception e){
    		
    	}
    	
    	return conn;
    }
}