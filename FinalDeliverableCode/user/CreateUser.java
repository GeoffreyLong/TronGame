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
		usernameField.setBounds(240, 170, 170, 25);
		

		password = new JLabel("Password : ");
		password.setFont(new Font("Times", Font.BOLD, 18));
		password.setBounds(20, 200, 140, 25);
		
		passwordField = new JPasswordField(40);
		passwordField.setBounds(240, 200, 170, 25);

		rePassword = new JLabel("Re-enter Password : ");
		rePassword.setFont(new Font("Times", Font.BOLD, 18));
		rePassword.setBounds(20, 230, 210, 25);
		
		rePasswordField = new JPasswordField(40);
		rePasswordField.setBounds(240, 230, 170, 25);
		
		createAccount = new JButton("Create Account");
		createAccount.setBounds(240, 260, 140, 30);
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
	
	private boolean checkPassword(String password){
		boolean pass = false;
		
		boolean length = password.length() >= 8;
		boolean lowercase = false;
		boolean uppercase = false;
		boolean digit = false;
		boolean character = false;
		
		char[] check = password.toCharArray();
		
		for(int i = 0; i < check.length; i++){
			if(check[i] >= 'a' && check[i] <= 'z'){
				lowercase = true;
			}
			
			else if(check[i] >= 'A' && check[i] <= 'Z'){
				uppercase = true;
			}
			
			else if(check[i] >= '0' && check[i] <= '9'){
				digit = true;
			}
			
			else if((check[i] >= 33 && check[i] <= 47) || (check[i] >= 58 && check[i] <= 64) || (check[i] >= 91 && check[i] <= 96) || (check[i] >= 123 && check[i] <= 126)){
				character = true;
			}
		}
		
		pass = length && lowercase && uppercase && digit && character;
		
		return pass;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == createAccount){
			if(passwordField.getText().equals(rePasswordField.getText())){
				Connection conn = Connect.connect();
				
				boolean pass = false;
				
				DatabaseCalls call = new DatabaseCalls(conn);
				
				
				if(checkPassword(passwordField.getText())){
					pass = call.createUser(usernameField.getText(), passwordField.getText());
				}
				
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
