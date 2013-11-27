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
import styleelements.StyledButton;
import user.LoginGUI;
import net.miginfocom.swing.MigLayout;

public class LoginGUI extends JPanel implements ActionListener{
	
	private JLabel title;
	private JLabel username;
	private JTextField usernameField;
	private JLabel password;
	private JPasswordField passwordField;
	private StyledButton login;	
	private Player player;
	private StyledButton back;
	public static String player1;
	public static String player2;
	
	public LoginGUI(Player player){
		setBackground(Color.BLACK);
		this.player = player;
		setBorder(BorderFactory.createLineBorder(Color.black));
		initComponents();
		initLayout();
	}
	
	private void initComponents(){

		title = new JLabel("Login to your account");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setForeground(new Color(142,229,238));
		
		back = new StyledButton("Back");
		back.addActionListener(this);
		
		login = new StyledButton("Login");
		login.addActionListener(this);
		
		username = new JLabel("Username : ");
		username.setFont(new Font("Times", Font.BOLD, 18));
		username.setForeground(new Color(142,229,238));

		usernameField = new JTextField(40);

		password = new JLabel("Password : ");
		password.setFont(new Font("Times", Font.BOLD, 18));
		password.setForeground(new Color(142,229,238));

		passwordField = new JPasswordField(40);

	}
	
	private void initLayout(){
		setLayout(new MigLayout("", "[130px][450px]", "[45px][25px][25px][30px][30px][][][]"));
		add(title, "cell 0 0 2 3,alignx center,aligny top");
		add(username, "cell 0 3,alignx left,growy");
		add(usernameField, "cell 1 3,grow");
		add(password, "cell 0 4,alignx left,growy");
		add(passwordField, "cell 1 4,grow");
		add(login, "cell 1 5,alignx left,growy");
		add(back, "cell 0 7,alignx right,growy");
		
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
