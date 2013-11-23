package startscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import user.CreateUser;
import user.LoginGUI;
import start.Frame;
import user.Logout;
import user.Player;

public class PlayerPanel extends JPanel implements ActionListener{
	private Player player;
	public JButton logout;
	public JButton login;
	public JButton createAccount;
	public JLabel playerStatus;
	public InputMap inputMap;
	JLabel playerLabel;
	
	public PlayerPanel(Player player){
		this.player = player;
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		setVisible(true);
		
		playerLabel = new JLabel();
		playerLabel.setBounds(50,30,300,60);
		playerLabel.setFont(new Font("Times", Font.BOLD, 37));
		
		JButton playerStats = new JButton("Show Statistics");
		playerStats.addActionListener(this);
		playerStats.setBounds(140, 225, 300, 70);
		
		JLabel playerNameLabel = new JLabel("Logged in as : " + player.getUserName());
		playerNameLabel.setBounds(140, 325, 300, 30);
		
		if (player.getUserName().equals("anonymous")){
			
		}
		else{

		}
		
		login = new JButton("Login");
		login.setBounds(20, 125, 300, 70);
		login.addActionListener(this);
		login.setVisible(false);
		add(login);
		
		logout = new JButton("Logout");
		logout.setBounds(140, 125, 300, 70);
		logout.addActionListener(this);
		logout.setVisible(false);
		add(logout);
		
		createAccount = new JButton("Create an Account");
		createAccount.setBounds(340,125,300,70);
		createAccount.addActionListener(this);
		add(createAccount);
		
		playerStatus = new JLabel("NOT READY");
		playerStatus.setBounds(140, 425, 300, 30);
		
		inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		
		add(playerLabel);
		add(playerStats);
		add(playerNameLabel);
		add(playerStatus);
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
			LoginGUI login = new LoginGUI(player);
			Frame.removePanel(this);
			if (player.getPlayerNumber() == 1){
				login.setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
			}
			else{
				login.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
			}
			Frame.addPanel(login);
		}
		if (e.getActionCommand().equals("Logout")){

		}
		if (e.getActionCommand().equals("Show Statistics")){
			
		}
		if(e.getActionCommand().equals("Create an Account")){
			CreateUser create = new CreateUser(player);
			Frame.removePanel(this);
			if (player.getPlayerNumber() == 1){
				create.setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
			}
			else{
				create.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
			}
			Frame.addPanel(create);
		}
	}
}
