package statistics;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AllStatsPanel extends JPanel{
	
	private Connection conn;
	
	JScrollPane pane = new JScrollPane();
	
	public AllStatsPanel(Connection conn){
		makeComponents();
		makeLayout();	
		this.conn = conn;
	}
	
	private void makeComponents(){
		
	}
	
	private void makeLayout(){
		setLayout(null);
		
	}

}
