package statistics;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;


public class PlayerHistoryPanel extends JPanel{
	
	private Connection conn;
	private String username;
	
	JScrollPane pane = new JScrollPane();
	JTable table;
	
	public PlayerHistoryPanel(Connection conn, String username ){
		this.conn = conn;
		this.username = username;
		makeComponents();
		makeLayout();	
	}
	
	private void makeComponents(){
		try{  
            Statement stmt = conn.createStatement();  
            ResultSet result = stmt.executeQuery("SELECT opponent, numberGames, numberWins, numberLosses FROM authentication.playerHistory WHERE userName = '" + username + "'");
            ResultSetMetaData md = result.getMetaData();
             
            Object[] columns = {"Opponent", "Number of Games", "Number of Wins", "Number of Losses"};
            
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
