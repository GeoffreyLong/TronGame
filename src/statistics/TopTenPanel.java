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

	}
	
	private void initLayout(){
		
	}

}
