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
            int columnCount = md.getColumnCount();
            
            Vector columns = new Vector(columnCount);

            //store column names
            for(int i=1; i<=columnCount; i++)
            columns.add(md.getColumnName(i));

            Vector data = new Vector();
            Vector row;

            //store row data
            while(result.next())
            {
            row = new Vector(columnCount);
            for(int i=1; i<=columnCount; i++)
            {
            row.add(result.getString(i));
            }
            data.add(row);
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
		add(table);
	}

}
