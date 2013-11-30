package user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.DatabaseCalls;
import main.FrameDriver;
import main.Main;
import net.miginfocom.swing.MigLayout;
import styleelements.StyledButton;
import styleelements.StyledLabel;
import styleelements.StyledPanel;



/**
 * 
 * @author Rishabh Tandon
 * @version v1.0
 * 
 * This class basically handles the JPanel using in the main GUI for login.
 * First a JLabels are created and then a JTextField and a JPasswordField is used to take in username and password of the player as 
 * input respectively. A JButton for submit is then created and an Action Listener is registered for it. When the submit button is pressed, 
 * an object 'call' of type DatabaseCalls is created which is then used to call the method login to check if the username-password
 * combination is correct. If it is correct then the player can go ahead, if not then a pop up is shown to warn the user about the wrong 
 * combination.
 * 
 */



public class LoginPanel extends StyledPanel implements ActionListener{
	
	
	/**
	 * Everything initialized here
	 * 
	 */
	
	
	private JLabel title;
	private JLabel username;
	private JTextField usernameField;
	private JLabel password;
	private JPasswordField passwordField;
	private StyledButton login;	
	private Player player;
	private StyledButton back;
	public static String player1 = "";
	public static String player2 = "";
	
	
	/**
	 * 
	 * Constructor for the class.
	 * Initializes the components and the layout used in the JPanel.
	 * 
	 * @param player Player who wants to login
	 * 
	 */
	
	
	public LoginPanel(Player player){
		setBackground(Color.BLACK);
		this.player = player;
		setBorder(BorderFactory.createLineBorder(Color.black));
		initComponents();
		initLayout();
	}
	
	
	public void setPlayer(Player player){
		this.player = player;
	}
	/**
	 * 
	 * This method initializes the components of the JPanel used in the main GUI.
	 * The bounds, text and font of the components are set and the ActionListener for the login button is registerd.
	 * 
	 * 
	 * @param none
	 * @return void
	 * 
	 * 
	 */
	
	
	
	private void initComponents(){

		title = new StyledLabel("Login to your account", 18);
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setForeground(new Color(142,229,238));
		
		back = new StyledButton("Back");
		back.addActionListener(this);
		
		login = new StyledButton("Login");
		login.addActionListener(this);
		
		username = new StyledLabel("Username : ", 18);
		username.setFont(new Font("Times", Font.BOLD, 18));
		username.setForeground(new Color(142,229,238));

		usernameField = new JTextField(40);

		password = new StyledLabel("Password : ", 18);
		password.setFont(new Font("Times", Font.BOLD, 18));
		password.setForeground(new Color(142,229,238));

		passwordField = new JPasswordField(40);

	}
	
	

	/**
	 * 
	 * This method initializes the layout of the JPanel used in the main GUI.
	 * The layout is set as MigLayout and the components are added to the JPanel in this method.
	 * 
	 * @param none
	 * @return void
	 * 
	 * 
	 */
	 
	
	
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
	
	
	/**
	 * 
	 * This is the ActionListener for the class. 
	 * When the login button is pressed a DatabaseCalls object 'call' is created which is then used to call method login to check 
	 * if the username-password combination exists. If the combination is correct the player object and username is updated for the respective Panel,
	 * Player1 for Panel 1 and Player2 for Panel 2.
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login){
			
			if(this.player.getPlayerNumber() == 1){
				player1 = usernameField.getText();;
			}
			
			else{
				player2 = usernameField.getText();
			}
			
			if((this.player.getPlayerNumber() == 1 && !player1.equals(player2)) || (this.player.getPlayerNumber() == 2 && !player2.equals(player1))){
				DatabaseCalls call = new DatabaseCalls();
				
				boolean pass = call.login(usernameField.getText(), passwordField.getText());
				
				
				if(pass){
					
					if(this.player.getPlayerNumber() == 1){
						player = new Player(1);
						player.setUserName(usernameField.getText());
						Main.playerOne = player;
						FrameDriver.setPaneOne(player);
					}
					
					else{
						player = new Player(2);
						player.setUserName(usernameField.getText());
						Main.playerTwo = player;
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
			
			else{
				System.out.println(player.getUserName());
				if (player.getClass().getName().equals("user.NullPlayer")){
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "Please enter a username !");
				}
				else{
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "Player already logged in !");
				}
			}
		}
		
		if(e.getSource() == back){
			FrameDriver.mainMenu();
		}
		
	}


}
