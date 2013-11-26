package startscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import statistics.*;

import javax.swing.*;

import user.CreateUser;
import user.LoginGUI;
import start.Frame;
import start.FrameDriver;
import start.Main;
import user.Logout;
import user.Player;
import start.Connect;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;

public class PlayerPanel extends JPanel implements ActionListener{
	private Player player;
	public JButton logout;
	public JButton login;
	public JButton createAccount;
	public JLabel playerStatus;
	public InputMap inputMap;
	JLabel playerLabel;
	JButton playerStats;
	
	private Connection conn;
	
	public PlayerPanel(Player player){
		this.player = player;
		setBackground(Color.DARK_GRAY);
		initComponents();
		initLayout();
		this.conn = Connect.connect();
	}
	private void initComponents(){		
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("Times", Font.BOLD, 37));
		
		playerStats = new JButton("Show Statistics");
		playerStats.setBackground(new Color(0, 255, 255));
		playerStats.addActionListener(this);

		login = new JButton("Login");
		login.setForeground(Color.BLACK);
		login.setBackground(Color.ORANGE);
		login.addActionListener(this);
		login.setVisible(false);
		
		logout = new JButton("Logout");
		logout.setForeground(Color.BLACK);
		logout.setBackground(Color.ORANGE);
		logout.addActionListener(this);
		logout.setVisible(false);
		
		createAccount = new JButton("Create an Account");
		createAccount.setForeground(new Color(153, 0, 0));
		createAccount.setBackground(new Color(0, 102, 102));
		createAccount.addActionListener(this);
		
		playerStatus = new JLabel("NOT READY");
		playerStatus.setForeground(Color.WHITE);
		playerStatus.setBackground(Color.DARK_GRAY);
		
		inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
	}
	
	private void initLayout(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new MigLayout("", "[]", "[][][][]"));
		
		if(player.getPlayerNumber() == 1){
			add(playerLabel, "cell 0 0");
			add(playerStats, "cell 0 3");
			add(playerStatus, "cell 0 4");
			add(login, "cell 0 1");
			add(logout, "cell 0 1");
			add(createAccount, "cell 0 2");
		}
		if(player.getPlayerNumber() == 2){
			
			add(playerLabel, "cell 0 0, alignx right, growy");
			add(playerStats, "cell 0 3,alignx right, growy");
			add(playerStatus, "cell 0 4,alignx right, growy");
			add(login, "cell 0 1,alignx right, growy");
			add(logout, "cell 0 1,alignx right,growy");
			add(createAccount, "cell 0 2,alignx right,growy");
		}
		
		
		setVisible(true);
	}

	public void setLocation(int x){
		setBounds(x, Frame.getYSize()/6, Frame.getXSize()/2, 5*Frame.getYSize()/6);
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
		logout.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")){
			FrameDriver.login(player);
		}
		
		if (e.getActionCommand().equals("Logout")){

		}
		
		if (e.getActionCommand().equals("Show Statistics")){
			
		       FrameDriver.Statistics(); 
			
		}
		
		if(e.getActionCommand().equals("Create an Account")){
			FrameDriver.createAccount(player);
		}
		
	}
}
