package statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * 
 * @author Rishabh Tandon
 * @version V1.0
 * 
 * This class creates the panel to show the top ten players (highest score) who have played the game.
 * The class first connects to the database and then a JTable. The JTable is then populated with the data from the MySQL table. The column data is stored 
 * in Object[] and row data is stored in Object[][]. A loop is then run to populate the JTable with the data in the array.
 * 
 * 
 */



public class TopTenPanel extends JPanel{
	
	
	/*
	 * Initializes everything.
	 * 
	 */
	
	
	private Connection conn;
	
	JScrollPane pane = new JScrollPane();
	JTable table;
	
	
	/**
	 * 
	 * Constructor for the class.
	 * Initializes the components and the layout for Panel
	 * 
	 * @param conn Connection to the database
	 * 
	 * 
	 */
	
	
	public TopTenPanel(Connection conn){
		this.conn = conn;
		initComponents();
		initLayout();	
	}
	
	
	
	/**
	 * 
	 * This method initializes the components for the JPanel.
	 * The JTable is created and is populated with data. The column names are stored in an Object[] and the data of the rows is stored in an
	 * a 2 D array Object[][] by running 2 indented for loops. This data is used in the JTable. 
	 * 
	 * @param none
	 * 
	 * @return void 
	 * 
	 * 
	 */
	
	
	
	private void initComponents(){
		try{ 
			Statement rank = conn.createStatement();
			rank.executeUpdate("SET @rank = 0");
			
        		Statement stmt = conn.createStatement();  
        		ResultSet result = stmt.executeQuery("SELECT @rank := @rank + 1 AS Rank, userName, numberWins, numberLosses FROM authentication.allStats ORDER BY totalScore DESC LIMIT 10");
            
            		Object[] columns = {"Rank", "Username", "Wins", "Losses"};

            		Object[][] data = new Object[100][10];
            
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
	
	
	
	/**
	 * 
	 * Adds the JTable to the JPanel.
	 * A JScrollPane is used in the JPanel since that is required to set the display in the right way.
	 * 
	 * @param none
	 * 
	 * @return void
	 * 
	 * 
	 * 
	 */
	
	
	
	private void initLayout(){
		//setLayout(null);
		add(new JScrollPane(table));
	}

}
