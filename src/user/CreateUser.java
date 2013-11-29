package user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
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
import net.miginfocom.swing.MigLayout;

public class CreateUser extends JPanel implements ActionListener{
  
	private JLabel title;
	private JLabel username;
	private JTextField usernameField;
	private JLabel password;
	private JPasswordField passwordField;
	private JLabel rePassword;
	private JPasswordField rePasswordField;
	private StyledButton createAccount;
	private Player player;
	private StyledButton back;
	
	
	public CreateUser(Player player){
		this.player = player;
		initComponents();
		initLayout();		
	}
	
	private void initComponents(){
		title = new JLabel("Create your account");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setForeground(new Color(142,229,238));
		
		back = new StyledButton("Back");
		back.addActionListener(this);
		
		createAccount = new StyledButton("Create Account");
		createAccount.addActionListener(this);	
				
		username = new JLabel("Username : ");
		username.setFont(new Font("Times", Font.BOLD, 18));
		username.setForeground(new Color(142,229,238));
		usernameField = new JTextField(40);

		
		password = new JLabel("Password : ");
		password.setFont(new Font("Times", Font.BOLD, 18));
		password.setForeground(new Color(142,229,238));

		passwordField = new JPasswordField(40);
		
		rePassword = new JLabel("Verify Password : ");
		rePassword.setFont(new Font("Times", Font.BOLD, 18));
		rePassword.setForeground(new Color(142,229,238));

		rePasswordField = new JPasswordField(40);
	}
	
	private void initLayout(){
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new MigLayout("", "[210px][10px][370px]", "[45px][25px][25px][25px][30px][30px][][]"));
		add(title, "cell 0 0 3 1,alignx center,aligny top");
		add(username, "cell 0 2,alignx left,growy");
		add(usernameField, "cell 2 2,grow");
		add(password, "cell 0 3,alignx left,growy");
		add(passwordField, "cell 2 3,grow");
		add(rePassword, "cell 0 4,grow");
		add(rePasswordField, "cell 2 4,grow");
		add(createAccount, "cell 2 5,alignx left,growy");
		add(back, "cell 0 7,alignx center,growy");
		
	}
	
	public boolean checkPassword(String password){
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
		
		if(e.getSource() == back){
			FrameDriver.mainMenu();
		}
		
		if(e.getSource() == createAccount){
			if(passwordField.getText().equals(rePasswordField.getText())){
				
				boolean pass = false;
				
				DatabaseCalls call = new DatabaseCalls();
				
				
				if(checkPassword(passwordField.getText())){
					pass = call.createUser(usernameField.getText(), passwordField.getText());
				}
				
				else{
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "Your password must be at least 8 charecters long, and contain at least 1 Uppercase, 1 lowercase, 1 number and 1 non-alphanumeric character");
				}
				
				try {
					if(pass){
						
						if(this.player.getPlayerNumber() == 1){
							player.setUserName(usernameField.getText());
							LoginGUI.player1 = usernameField.getText();
							
							FrameDriver.setPaneOne(player);
						}
						
						else{
							player.setUserName(usernameField.getText());
							LoginGUI.player2 = usernameField.getText();
							
							FrameDriver.setPaneTwo(player);
						}
						passwordField.setText("");
						rePasswordField.setText("");
						
					}
					
					else{
						
						JFrame frame = new JFrame("Error");
						JOptionPane.showMessageDialog(frame, "Account Already Exists");
					
						
						usernameField.setText("");
						passwordField.setText("");
						rePasswordField.setText("");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			else{
				if (!passwordField.getText().equals(rePasswordField.getText())) {
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "Please retype!");
				}
				usernameField.setText("");
				passwordField.setText("");
				rePasswordField.setText("");
			}
		} 
	}
}
