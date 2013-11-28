package statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class HeadToHead extends JPanel{
	
	private Connection conn;
	private String username1;
	private String username2;
	
	JScrollPane pane = new JScrollPane();
	JTable table;
	
	public HeadToHead(Connection conn, String username1, String username2 ){
		this.conn = conn;
		this.username1 = username1;
		this.username2 = username2;
		initComponents();
		initLayout();	
	}
	
	private void initComponents(){
		try{  
            Statement stmt = conn.createStatement();  
            ResultSet result = stmt.executeQuery("SELECT userName, opponent, numberGames, numberWins, numberLosses FROM authentication.playerHistory WHERE userName = '" + username1 + "' AND opponent = '" + username2 + "'");
            ResultSetMetaData md = result.getMetaData();
             
            Object[] columns = {"Player 1", "Player2", "Games", "Wins", "Losses"};
            
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
	
	private void initLayout(){
		//setLayout(null);
		add(new JScrollPane(table));
	}

}
