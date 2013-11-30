package menuscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Frame;
import main.FrameDriver;
import net.miginfocom.swing.MigLayout;
import styleelements.StyledButton;
import styleelements.StyledLabel;
import user.LoginPanel;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * Provides the main menu layout for the players
 */
public class PlayerPanel extends JPanel implements ActionListener{
	public Player player;
	
	public JLabel playerStatus;
	public InputMap inputMap;
	private JLabel playerLabel;
	public JButton playerStats;
	private JButton head2head;
	
	private JLabel instructionsTwo;
	private JLabel instructionsOne;
	
	private JPanel panel;
	
	public JButton logout;
	public JButton login;
	public JButton createAccount;
	public JButton playerHistory;
	
	/**
	 * Constructor takes in a Player object and initializes the components 
	 * of the panel.
	 * 
	 * @param player  The player object
	 */
	public PlayerPanel(Player player){
		this.player = player;
		initComponents();
		initLayout();
	}
	
	/**
	 * Standard Panel method to initialize all the components
	 */
	private void initComponents(){		
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("Times", Font.BOLD, 37));
		playerLabel.setForeground(new Color(142,229,238));
		
		instructionsOne = new StyledLabel("Press 'W' button when you are ready to go...", 16);
		instructionsOne.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));


		instructionsTwo = new StyledLabel("Press '\u2191' button when you are ready to go...", 16);
		instructionsTwo.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));

		
		panel = new JPanel();
		panel.setBackground(Color.black);

		login = new StyledButton("Login");
		login.addActionListener(this);
		login.setVisible(false);

		logout = new StyledButton("Logout");
		logout.addActionListener(this);
		logout.setVisible(false);

		head2head = new StyledButton("Head to Head Score");
		head2head.addActionListener(this);
		head2head.setVisible(true);
		
		createAccount = new StyledButton("Create an Account");
		createAccount.addActionListener(this);
		
		playerStats = new StyledButton("Show Top Ten");
		playerStats.addActionListener(this);
		
		playerHistory = new StyledButton("Show Player History");
		playerHistory.setBounds(140, 225, 300, 70);
		playerHistory.addActionListener(this);
		
		playerStatus = new JLabel("NOT READY");
		playerStatus.setFont(new Font("Apple Color Emoji", Font.BOLD, 89));
		playerStatus.setForeground(Color.RED);
		playerStatus.setBackground(Color.DARK_GRAY);
		
		inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
	}
	
	/**
	 * Standard Panel method to initialize the layout
	 */
	private void initLayout(){
		setBackground(Color.black);
		setBorder(BorderFactory.createLineBorder(new Color(0xD4FFFF)));
		setLayout(new MigLayout("", "[grow][grow][grow]", "[50px][50px][50px][50px][50px]"));
		
		
		if(player.getPlayerNumber() == 1){
			add(playerLabel, "cell 0 0 3 1,alignx left");
			add(instructionsOne, "cell 0 1 2 1, alignx left");
			add(panel, "cell 1 2,grow");			
			panel.setLayout(null);
			panel.add(login, "center");
			login.setBounds(0, 0, 390, 50);
			panel.add(logout, "center");
			logout.setBounds(0, 0, 390, 50);
			add(createAccount, "cell 1 3,alignx right,grow");
			add(playerStats, "cell 1 4,alignx right,grow");
			add(playerStatus, "cell 0 7 3 1,alignx center,aligny baseline");
			add(playerHistory, "cell 1 5,alignx right,grow");
			add(head2head, "cell 1 6,alignx right,grow");
		}
		

		if(player.getPlayerNumber() == 2){
			
			add(playerLabel, "cell 0 0 3 1,alignx right");
			add(instructionsTwo, "cell 0 1 3 1, alignx right");
			add(panel, "cell 1 2,grow");
			panel.setLayout(null);
			panel.add(login, "center");
			login.setBounds(0, 0, 376, 50);
			panel.add(logout, "center");
			logout.setBounds(0, 0, 376, 50);
			add(createAccount, "cell 1 3,alignx left,growx,growy");
			add(playerStats, "cell 1 4,alignx left,growx, growy");
			add(playerStatus, "cell 0 7 3 1,alignx center,aligny baseline, shrinky");
			add(playerHistory, "cell 1 5, alignx left, growx, growy");
			add(head2head, "cell 1 6, alignx left, growx, growy");
		}
		setVisible(true);
	}

	/**
	 * Set the x location of the panel
	 * @param x  The x coordinate of the start of the panel
	 */
	public void setLocation(int x){
		//Only need to set the X location as both 
		//PlayerOnePanel and PlayerTwoPanel are the same size and y coordinate
		setBounds(x, Frame.getYSize()/6, Frame.getXSize()/2, 5*Frame.getYSize()/6);
	}
	
	/**
	 * Return the player number of the player
	 * @return the player number of the player
	 */
	public int getPlayerNumber(){
		return this.player.getPlayerNumber();
	}
	
	/**
	 * Set the label of the panel.
	 * @param label  The title label of the panel
	 */
	public void setLabel(String label){
		playerLabel.setText(label);
	}
	
	/**
	 * Set the visibility of the login button
	 * @param isVisible  True for is visible, false for invisible
	 */
	public void setLogin(boolean isVisible){
		login.setVisible(isVisible);
	}
	
	/**
	 * Set the visibility of the create user button
	 * @param isVisible  True for is visible, false for invisible
	 */
	public void setCreate(boolean isVisible){
		createAccount.setVisible(isVisible);
	}
	
	/**
	 * Set the visibility of the logout button
	 * @param isVisible  True for is visible, false for invisible
	 */
	public void setLogout(boolean isVisible){
		logout.setVisible(isVisible);
		head2head.setVisible(isVisible);
	}
	
	@Override
	/**
	 * Respond to the actions caught by the ActionListener
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")){
			FrameDriver.login(player);
		}
		
		if (e.getActionCommand().equals("Logout")){
			FrameDriver.logout(player);
		}
		
		if (e.getActionCommand().equals("Show Top Ten")){
			
			boolean open = false;
			Window[] allWindows = Window.getWindows();
		    
			for(int i = 0; i < allWindows.length; i++){
				if(allWindows[i].equals(FrameDriver.topTen)){
					allWindows[i].dispose();
					FrameDriver.TopTen();
					open = true;
				}
			}
			
			if(!open){
				FrameDriver.TopTen();
			}
			
			
		}
		
		if(e.getActionCommand().equals("Create an Account")){
			FrameDriver.createAccount(player);
		}
		
		if(e.getActionCommand().equals("Head to Head Score")){
			if(!LoginPanel.player1.equals("") && !LoginPanel.player2.equals("")){
				
				boolean open = false;
				Window[] allWindows = Window.getWindows();
			    
				for(int i = 0; i < allWindows.length; i++){
					if(allWindows[i].equals(FrameDriver.headToHead)){
						allWindows[i].dispose();
						FrameDriver.HeadToHead(LoginPanel.player1, LoginPanel.player2);
						open = true;
					}
				}
				
				if(!open){
					FrameDriver.HeadToHead(LoginPanel.player1, LoginPanel.player2);
				}
			
			}
				
			
			else{
				JFrame frame = new JFrame("Error");
				JOptionPane.showMessageDialog(frame, "Both players must be logged in to view head to head score");
			}
		}
		
		if(e.getActionCommand().equals("Show Player History")){
			if(player.getPlayerNumber() == 1){
				if(!LoginPanel.player1.equals("")){
					
					boolean open = false;
					Window[] allWindows = Window.getWindows();
					
					for(int i = 0; i < allWindows.length; i++){
						if(allWindows[i].equals(FrameDriver.historyFrame)){
							allWindows[i].dispose();
							FrameDriver.playerHistory(LoginPanel.player1);
							open = true;
						}
					}
					
					if(!open){
						FrameDriver.playerHistory(LoginPanel.player1);
					}
				}
				
				else{
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "You must be logged in to view history");
				}
			}
			
			else{
				if(!LoginPanel.player2.equals("")){
					
					boolean open = false;
					Window[] allWindows = Window.getWindows();
					
					for(int i = 0; i < allWindows.length; i++){
						if(allWindows[i].equals(FrameDriver.historyFrame)){
							allWindows[i].dispose();
							FrameDriver.playerHistory(LoginPanel.player2);
							open = true;
						}
					}
					
					if(!open){
						FrameDriver.playerHistory(LoginPanel.player2);
					}
				}
				
				else{
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "You must be logged in to view player history");
				}
			}
		}
		
	}
}
