package Prototype2;

import java.sql.*;
 
 
public class Connect{
    public static void main(String[] args) {
          
    	String url = "jdbc:mysql://ec2-54-201-70-38.us-west-2.compute.amazonaws.com";
        String dbName = "/authentication";
        String driver = "com.mysql.jdbc.Driver"; 
        String userName = "root";
        String password = "ecse321";
        
        try {
        	Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-54-201-70-225.us-west-2.compute.amazonaws.com:3306/authentication?user=team7&password=ecse321"); 
            System.out.println("Connected to the Database");
            conn.close();
          
        } catch (Exception e) {
              e.printStackTrace();
          }
   }
}