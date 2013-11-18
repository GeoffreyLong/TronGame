package user;

import javax.swing.*;

import start.Connect;
import start.DatabaseCalls;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateUser extends JPanel implements ActionListener{
  
	private JLabel title;
	private JLabel username;
	private JTextField usernameField;
	private JLabel password;
	private JPasswordField passwordField;
	private JLabel rePassword;
	private JPasswordField rePasswordField;
	private JButton createAccount;
	private Player player;
	
	
	public CreateUser(Player player){
		this.player = player;
		setBorder(BorderFactory.createLineBorder(Color.black));
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
		

		password = new JLabel("Password : ");
		password.setFont(new Font("Times", Font.BOLD, 18));
		password.setBounds(20, 200, 140, 25);
		
		passwordField = new JPasswordField(40);
		passwordField.setBounds(200, 200, 170, 25);

		rePassword = new JLabel("Re-enter Password : ");
		rePassword.setFont(new Font("Times", Font.BOLD, 18));
		rePassword.setBounds(20, 230, 190, 25);
		
		rePasswordField = new JPasswordField(40);
		rePasswordField.setBounds(200, 230, 170, 25);
		
		createAccount = new JButton("Create Account");
		createAccount.setBounds(200, 260, 140, 30);
		createAccount.addActionListener(this);	
		
		
	}
	
	private void makeLayout(){
		setLayout(null);
		add(title);
		add(username);
		add(usernameField);
		add(password);
		add(passwordField);
		add(rePassword);
		add(rePasswordField);
		add(createAccount);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == createAccount){
			if(passwordField.getText().equals(rePasswordField.getText())){
				Connection conn = Connect.connect();
				
				DatabaseCalls call = new DatabaseCalls(conn);
				
				boolean pass = call.createUser(usernameField.getText(), passwordField.getText());
				
				try {
					if(pass){
						conn.close();
					}
					
					else{
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
	}

}
