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

import user.LoginGUI;
import start.Frame;
import user.Login;
import user.Logout;
import user.Player;

public class PlayerPanel extends JPanel implements ActionListener{
	private Player player;
	public JButton playerLog;
	public JLabel playerStatus;
	public InputMap inputMap;
	
	public PlayerPanel(Player player){
		this.player = player;
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		setVisible(true);
		
		JLabel playerLabel;
		if (player.getPlayerNumber()==1){
			setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
			playerLabel = new JLabel("PLAYER ONE");
		}
		else{
			setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
			playerLabel = new JLabel("PLAYER TWO");
		}
		playerLabel.setBounds(50,30,300,60);
		playerLabel.setFont(new Font("Times", Font.BOLD, 37));
		
		JButton playerStats = new JButton("Show Statistics");
		playerStats.addActionListener(this);
		playerStats.setBounds(140, 225, 300, 70);
		
		JLabel playerNameLabel = new JLabel("Logged in as : " + player.getUserName());
		playerNameLabel.setBounds(140, 325, 300, 30);
		
		JButton createUser = new JButton("Create User");
		createUser.setBounds(340,125,300,70);
		createUser.addActionListener(this);
		add(createUser);
		
		if (player.getUserName().equals("anonymous")){
			playerLog = new JButton("Login");
			playerLog.setBounds(20, 125, 300, 70);
			createUser.setVisible(true);
		}
		else{
			playerLog = new JButton("Logout");
			playerLog.setBounds(140, 125, 300, 70);
			createUser.setVisible(false);
		}
		
		playerLog.addActionListener(this);
		add(playerLog);
		
		playerStatus = new JLabel("NOT READY");
		playerStatus.setBounds(140, 425, 300, 30);
		
		inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
		
		add(playerLabel);
		add(playerStats);
		add(playerNameLabel);
		add(playerStatus);
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
	}
}
