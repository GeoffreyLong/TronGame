package start;

import java.sql.*;
 
 
public class Connect{
   
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
