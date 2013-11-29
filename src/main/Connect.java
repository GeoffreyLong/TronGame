package main;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
 

/*
 * 
 * @author Rishabh Tandon
 * @version v1.0
 * 
 * 
 * This class is responsible for connecting to the database.
 * It provides a static method Connect() which helps with this and can be called from any class to connect to the database.
 * 
 */
 
public class Connect{
	
	/*
	 * 
	 * This method is used to connect to the database from any other class.
	 * Method uses the Oracle MySQL J Connector as driver provided by Oracle to connect to the database hosted on Amazon Web Services 
	 * Ubuntu 12.04 LTE Server. 
	 * 
	 * @param none
	 * 
	 * @return Connection This is the connection to the database
	 * 
	 * 
	 */
   
   public static Connection connect(){
    	
    	String driver = "com.mysql.jdbc.Driver";
    	Connection conn = null;
    	
    	try{
    	Class.forName(driver).newInstance();
    	conn = DriverManager.getConnection("jdbc:mysql://ec2-54-201-70-225.us-west-2.compute.amazonaws.com:3306/authentication?user=team7&password=ecse321"); 
    
    	}
    	
    	catch(Exception e){
    		JFrame frame = new JFrame("Error");
			JOptionPane.showMessageDialog(frame, "Could not connect to the database. Please check your connection");
    		
    	}
    	
    	return conn;
    }
   
   
}
