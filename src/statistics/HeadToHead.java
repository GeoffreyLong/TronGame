package statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 * 
 * 
 * @author Rishabh Tandon
 * @version V1.0
 * 
 * This class creates the panel for all statistics for head to head.
 * The class first connects to the database and then a JTable. The JTable is then populated with the data from the MySQL table. The column data is stored 
 * in Object[] and row data is stored in Object[][]. A loop is then run to populate the JTable with the data in the array.
 * 
 * 
 */

public class HeadToHead extends JPanel{
	
	/*
	 * Initializes everything.
	 * 
	 */
	
	private Connection conn;
	private String username1;
	private String username2;
	
	JScrollPane pane = new JScrollPane();
	JTable table;
	
	

	/*
	 * 
	 * Constructor for the class.
	 * Initializes the components and the layout for Panel
	 * 
	 * @param conn Connection to the database
	 * @param username1 Username for the first player
	 * @param username2 Username for the second player
	 * 
	 * 
	 */
	
	
	public HeadToHead(Connection conn, String username1, String username2 ){
		this.conn = conn;
		this.username1 = username1;
		this.username2 = username2;
		initComponents();
		initLayout();	
	}
	
	
	/*
	 * 
	 * This method initializes the components for the JPanel.
	 * The JTable is created and is populated with data. The column names are stored in an Object[] and the data of the rows is stored in an
	 * a 2 D array Object[][] by running 2 indented for loops. This data is used in the JTable. The rank is calculated as follows:
	 * 
	 * totalGames = wins + losses
	 * Rank = totalGames * (wins/losses)
	 * 
	 * @param none
	 * 
	 * @return void 
	 * 
	 * 
	 */
	
	
	private void initComponents(){
		try{  
            Statement stmt = conn.createStatement();  
            ResultSet result = stmt.executeQuery("SELECT userName, opponent, numberGames, numberWins, numberLosses FROM authentication.playerHistory WHERE userName = '" + username1 + "' AND opponent = '" + username2 + "'");
            ResultSetMetaData md = result.getMetaData();
             
            Object[] columns = {"Player 1", "Player2", "Games", "Player 1 wins", "Player 2 wins"};
            
            Object[][] data = new Object[100][100];
            
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
	
	
	/*
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
