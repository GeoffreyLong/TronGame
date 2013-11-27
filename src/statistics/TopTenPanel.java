package statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class TopTenPanel extends JPanel{
	
	private Connection conn;
	
	JScrollPane pane = new JScrollPane();
	JTable table;
	
	public TopTenPanel(Connection conn){
		this.conn = conn;
		initComponents();
		initLayout();	
	}
	
	private void initComponents(){
		try{ 
			Statement rank = conn.createStatement();
			rank.executeUpdate("SET @rank = 0");
			
        		Statement stmt = conn.createStatement();  
        		ResultSet result = stmt.executeQuery("SELECT @rank := @rank + 1 AS Rank, userName, totalScore, numberWins, numberLosses FROM authentication.allStats ORDER BY totalScore DESC LIMIT 10");
            		ResultSetMetaData md = result.getMetaData();
            
            		Object[] columns = {"Rank", "Username", "Total Score", "Number of Wins", "Number of Losses"};

            		Object[][] data = new Object[100][10];
            
            		int i = 0;
            
            		while(result.next()){
            	
            			for(int j = 0 ; j < 5; j++){
            		
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
	
	private void initLayout(){
		//setLayout(null);
		add(new JScrollPane(table));
	}

}
