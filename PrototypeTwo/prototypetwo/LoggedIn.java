package prototypetwo;

import java.awt.Font;

import javax.swing.*;

public class LoggedIn extends JPanel{
	
	private JLabel username;
	
	public LoggedIn(String username){
		makeComponents(username);
		makeLayout();
		
	}
	
	private void makeComponents(String username){
		this.username = new JLabel("Welcome " + username + "!");
		this.username.setFont(new Font("Times", Font.BOLD, 30));
		this.username.setBounds(140, 200, 400, 50);
		
	}
	
	private void makeLayout(){
		setLayout(null);
		add(username);
	}

}