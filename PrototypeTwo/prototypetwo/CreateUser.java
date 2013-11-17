package prototypetwo;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class CreateUser extends JPanel implements ActionListener{
  
	private JLabel title;
	private JLabel username;
	private JTextField usernameField;
	
	
	
	public CreateUser(){
		makeComponents();
		makeLayout();		
	}
	
	private void makeComponents(){
		title = new JLabel("Create your account");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setBounds(100, 10, 510, 45);
		
		username = new JLabel("Username : ");
		username.setFont(new Font("Times", Font.BOLD, 18));
		username.setBounds(20, 170, 140, 25);
		
		usernameField = new JTextField(40);
		usernameField.setBounds(200, 170, 170, 25);
		
		
		
		
	}
	
	private void makeLayout(){
		setLayout(null);
		add(title);
		add(username);
		add(usernameField);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}

}
