package startscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import statistics.*;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

import user.CreateUser;
import user.LoginGUI;
import start.Frame;
import start.FrameDriver;
import user.Logout;
import user.Player;
import start.Connect;

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
		initComponents();
		initLayout();
		this.player = player;
		this.conn = Connect.connect();
	}
	private void initComponents(){		
		playerLabel = new JLabel();
		playerLabel.setBounds(50,30,300,60);
		playerLabel.setFont(new Font("Times", Font.BOLD, 37));
		
		playerStats = new JButton("Show Statistics");
		playerStats.setBounds(140, 225, 300, 70);
		playerStats.addActionListener(this);

		login = new JButton("Login");
		login.setBounds(20, 125, 300, 70);
		login.addActionListener(this);
		login.setVisible(false);
		
		logout = new JButton("Logout");
		logout.setBounds(140, 125, 300, 70);
		logout.addActionListener(this);
		logout.setVisible(false);
		
		createAccount = new JButton("Create an Account");
		createAccount.setBounds(340,125,300,70);
		createAccount.addActionListener(this);
		
		playerStatus = new JLabel("NOT READY");
		playerStatus.setBounds(140, 425, 300, 30);
		
		inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
	}
	private void initLayout(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		add(playerLabel);
		add(playerStats);
		add(playerStatus);
		add(login);
		add(logout);
		add(createAccount);
		
		setVisible(true);
	}
	public void setLocation(int x){
		setBounds(x, Frame.getYSize()/4, Frame.getXSize()/2, 3*Frame.getYSize()/4);
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
