package statistics;

import javax.swing.*;

import java.awt.*;
import java.sql.*;
import java.util.Vector;


public class AllStatsPanel extends JPanel{
	
	private Connection conn;
	
	JScrollPane pane = new JScrollPane();
	JTable table;
	
	public AllStatsPanel(Connection conn){
		this.conn = conn;
		makeComponents();
		makeLayout();	
	}
	
	private void makeComponents(){
		try{  
            Statement stmt = conn.createStatement();  
            ResultSet result = stmt.executeQuery("SELECT * FROM authentication.allStats");
            ResultSetMetaData md = result.getMetaData();
            
            Object[] columns = {"Rank", "Username", "Number of Wins", "Number of Losses"};

            Object[][] data = new Object[100][100];
            
            int i = 0;
            
            while(result.next()){
            	
            	for(int j = 0 ; j < 4; j++){
            		
            		data[i][j] = result.getString(j + 1);
            	}
            	
            	i ++;
            }

            table = new JTable(data, columns);
            table.setFillsViewportHeight(true);
  
        }  
        catch(SQLException sqle){  
            System.out.println(sqle);  
            sqle.printStackTrace();  
        }  
		
	}
	
	private void makeLayout(){
		//setLayout(null);
		add(new JScrollPane(table));
	}

}
