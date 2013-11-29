package statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class PlayerHistoryPanel extends JPanel{
	
	private Connection conn;
	private String username;
	
	JScrollPane pane = new JScrollPane();
	JTable table;
	
	public PlayerHistoryPanel(Connection conn, String username ){
		this.conn = conn;
		this.username = username;
		initComponents();
		initLayout();	
	}
	
	private void initComponents(){
		try{  
            Statement stmt = conn.createStatement();  
            ResultSet result = stmt.executeQuery("SELECT opponent, numberGames, numberWins, numberLosses FROM authentication.playerHistory WHERE userName = '" + username + "'");
            ResultSetMetaData md = result.getMetaData();
             
            Object[] columns = {"Player 1", "Player2", "Games", "Player 1 Wins", "Player 2 Wins"};
            
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
	
	private void initLayout(){
		//setLayout(null);
		add(new JScrollPane(table));
	}

}
