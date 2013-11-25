package statistics;

import javax.swing.*;

import java.awt.*;
import java.sql.*;


public class TopTenPanel extends JPanel{
	
	
	public TopTenPanel(Connection conn){
		this.conn = conn;
		initComponents();
		initLayout();	
	}
	
	private void initComponents(){
		
		Statement rank = conn.createStatement();
		rank.executeUpdate("SET @rank = 0");
		
		Statement stmt = conn.createStatement();  
          	ResultSet result = stmt.executeQuery("SELECT @rank := @rank + 1 AS Rank, userName, totalScore, numberWins, numberLosses FROM authentication.allStats ORDER BY totalScore DESC LIMIT 10");
            	ResultSetMetaData md = result.getMetaData();

	}
	
	private void initLayout(){
		
	}

}
