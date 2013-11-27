package user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import start.Connect;
import start.DatabaseCalls;
import start.FrameDriver;
import user.LoginGUI;

public class LoginGUI extends JPanel implements ActionListener{
	
	private JLabel title;
	private JLabel username;
	private JTextField usernameField;
	private JLabel password;
	private JPasswordField passwordField;
	private JButton login;	
	private Player player;
	private JButton back;
	public static String player1;
	public static String player2;
	
	public LoginGUI(Player player){
		this.player = player;
		setBorder(BorderFactory.createLineBorder(Color.black));
		initComponents();
		initLayout();
	}
	
	private void initComponents(){

		title = new JLabel("Login to your account");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setBounds(90, 10, 510, 45);
		
		username = new JLabel("Username : ");
		username.setFont(new Font("Times", Font.BOLD, 18));
		username.setBounds(20, 170, 140, 25);
		
		usernameField = new JTextField(40);
		usernameField.setBounds(150, 170, 170, 25);
		
		password = new JLabel("Password : ");
		password.setFont(new Font("Times", Font.BOLD, 18));
		password.setBounds(20, 200, 140, 25);
		
		passwordField = new JPasswordField(40);
		passwordField.setBounds(150, 200, 170, 25);
		
		login = new JButton("Login");
		login.setBounds(150, 230, 100, 30);
		login.addActionListener(this);
		
		back = new JButton("Back");
		back.setBounds(50, 430, 100, 30);
		back.addActionListener(this);
		
	}
	
	private void initLayout(){
		setLayout(null);
		add(title);
		add(username);
		add(usernameField);
		add(password);
		add(passwordField);
		add(login);
		add(back);
		
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
				
				if(this.player.getPlayerNumber() == 1){
					player.setUserName(usernameField.getText());
					player1 = usernameField.getText();;
					FrameDriver.setPaneOne(player);
				}
				
				else{
					player.setUserName(usernameField.getText());
					player2 = usernameField.getText();
					FrameDriver.setPaneTwo(player);
				}
				
				usernameField.setText("");
				passwordField.setText("");
			}
			
			else{
				JFrame frame = new JFrame("Error");
				JOptionPane.showMessageDialog(frame, "Check your username/password");
				usernameField.setText("");
				passwordField.setText("");
			}
		}
		
		if(e.getSource() == back){
			FrameDriver.mainMenu();
		}
		
	}


}
