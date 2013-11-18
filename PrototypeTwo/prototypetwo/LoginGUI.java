package prototypetwo;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginGUI extends JPanel implements ActionListener{
	
	private JLabel title;
	private JLabel username;
	private JTextField usernameField;
	private JLabel password;
	private JPasswordField passwordField;
	private JButton login;	
	private Players player;
	
	public LoginGUI(Players player){
		this.player = player;
		makeComponents();
		makeLayout();
	}
	
	private void makeComponents(){

		title = new JLabel("Login to your account");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setBounds(90, 10, 510, 45);
		
		username = new JLabel("Username : ");
		username.setFont(new Font("Times", Font.BOLD, 18));
		username.setBounds(20, 170, 140, 25);
		
		usernameField = new JTextField(40);
		usernameField.setBounds(130, 170, 170, 25);
		
		password = new JLabel("Password : ");
		password.setFont(new Font("Times", Font.BOLD, 18));
		password.setBounds(20, 200, 140, 25);
		
		passwordField = new JPasswordField(40);
		passwordField.setBounds(130, 200, 170, 25);
		
		login = new JButton("Login");
		login.setBounds(130, 230, 100, 30);
		login.addActionListener(this);
		
	}
	
	private void makeLayout(){
		setLayout(null);
		add(title);
		add(username);
		add(usernameField);
		add(password);
		add(passwordField);
		add(login);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == login){
			Connection conn = Connect.connect();
			
			DatabaseCalls call = new DatabaseCalls(conn);
			
			String password = passwordField.getPassword().toString();
			boolean pass = call.login(usernameField.getText(), passwordField.getText());
			
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println(pass);
			
			if(pass){
				LoggedIn login = new LoggedIn(usernameField.getText(), player);
				Frame.removePanel(this);
				if (player == Players.ONE){
					login.setBounds(0,0,Frame.getXSize()/2, Frame.getYSize());
				}
				else{
					login.setBounds(Frame.getXSize()/2,0,Frame.getXSize()/2, Frame.getYSize());
				}
				Frame.addPanel(login);
			}
			
			else{
				
			}
		}
		
	}


}
