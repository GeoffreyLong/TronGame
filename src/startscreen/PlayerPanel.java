package startscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import start.Connect;
import start.Frame;
import start.FrameDriver;
import styleelements.StyledButton;
import user.LoginGUI;
import user.Player;

public class PlayerPanel extends JPanel implements ActionListener{
	public Player player;
	public JButton logout;
	public JButton login;
	public JButton createAccount;
	public JButton playerHistory;
	public JLabel playerStatus;
	public InputMap inputMap;
	public JLabel playerLabel;
	public JButton playerStats;
	public JButton head2head;
	
	private Connection conn;
	private JPanel panel;
	
	public PlayerPanel(Player player){
		this.player = player;
		setBackground(Color.black);
		initComponents();
		initLayout();
		this.conn = Connect.connect();
	}
	private void initComponents(){		
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("Times", Font.BOLD, 37));
		playerLabel.setForeground(new Color(142,229,238));
		
		panel = new JPanel();
		panel.setBackground(Color.black);

		login = new StyledButton("Login");
		login.setBounds(0, 0, 399, 50);
		login.addActionListener(this);
		login.setVisible(false);

		logout = new StyledButton("Logout");
		logout.setBounds(0, 0, 399, 50);
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
		playerStatus.setForeground(Color.RED);
		playerStatus.setBackground(Color.DARK_GRAY);
		
		inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
	}
	
	private void initLayout(){
		setBorder(BorderFactory.createLineBorder(new Color(0xD4FFFF)));
		setLayout(new MigLayout("", "[grow][grow][grow]", "[50px][50px][50px][50px][50px]"));
		
		
		if(player.getPlayerNumber() == 1){
			add(playerLabel, "cell 0 0 3 1,alignx left");						
			add(panel, "cell 1 2,grow");			
			panel.setLayout(null);
			panel.add(login, "center");
			panel.add(logout, "center");
			add(createAccount, "cell 1 3,alignx right,grow");
			add(playerStats, "cell 1 4,alignx right,grow");
			add(playerStatus, "cell 0 7 3 1,alignx center,aligny baseline");
			add(playerHistory, "cell 1 5,alignx right,grow");
			add(head2head, "cell 1 6,alignx right,grow");
		}
		

		if(player.getPlayerNumber() == 2){
			
			add(playerLabel, "cell 0 0 3 1,alignx right");
			add(panel, "cell 1 2,grow");
			panel.setLayout(null);
			panel.add(login, "center");
			panel.add(logout, "center");
			add(createAccount, "cell 1 3,alignx left,growx,growy");
			add(playerStats, "cell 1 4,alignx left,growx, growy");
			add(playerStatus, "cell 0 7 3 1,alignx center,aligny baseline, shrinky");
			add(playerHistory, "cell 1 5, alignx left, growx, growy");
			add(head2head, "cell 1 6, alignx left, growx, growy");
		}
		setVisible(true);
	}

	public void setLocation(int x){
		setBounds(x, Frame.getYSize()/6, Frame.getXSize()/2, 5*Frame.getYSize()/6);
	}
	
	public int getPlayerNumber(){
		return this.player.getPlayerNumber();
	}
	
	public void setLabel(String label){
		playerLabel.setText(label);
	}
	
	public void setLogin(boolean isVisible){
		login.setVisible(isVisible);
	}
	
	public void setCreate(boolean isVisible){
		createAccount.setVisible(isVisible);
	}
	
	public void setLogout(boolean isVisible){
		logout.setVisible(isVisible);
		head2head.setVisible(isVisible);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")){
			FrameDriver.login(player);
		}
		
		if (e.getActionCommand().equals("Logout")){
			FrameDriver.logout(player);
		}
		
		if (e.getActionCommand().equals("Show Top Ten")){
		       FrameDriver.TopTen();
		}
		
		if(e.getActionCommand().equals("Create an Account")){
			FrameDriver.createAccount(player);
		}
		
		if(e.getActionCommand().equals("Head to Head Score")){
			if(!LoginGUI.player1.equals("") && !LoginGUI.player2.equals(""))
				FrameDriver.HeadToHead(LoginGUI.player1, LoginGUI.player2);
			
			else{
				JFrame frame = new JFrame("Error");
				JOptionPane.showMessageDialog(frame, "Both players must be logged in to view head to head score");
			}
		}
		
		if(e.getActionCommand().equals("Show Player History")){
			if(player.getPlayerNumber() == 1){
				if(!LoginGUI.player1.equals("")){
					FrameDriver.playerHistory(LoginGUI.player1);
				}
				
				else{
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "You must be logged in to view history");
				}
			}
			
			else{
				if(!LoginGUI.player2.equals(""))
					FrameDriver.playerHistory(LoginGUI.player2);
				
				else{
					JFrame frame = new JFrame("Error");
					JOptionPane.showMessageDialog(frame, "You must be logged in to view player history");
				}
			}
		}
		
	}
}
