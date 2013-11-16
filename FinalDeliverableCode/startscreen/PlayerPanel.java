package startscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.Frame;
import user.Player;

public class PlayerPanel extends JPanel implements ActionListener{
	private Player player;
	public JButton playerLog;
	
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
		
		JButton playerStats = new JButton("Show Statistics");
		playerStats.setBounds(140, 225, 300, 70);
		
		JLabel playerNameLabel = new JLabel("Logged in as : " + player.getUserName());
		playerNameLabel.setBounds(140, 325, 300, 30);
		
		if (player.getUserName().equals("anonymous")){
			playerLog = new JButton("Login");
			playerLog.setBounds(140, 125, 300, 70);
			add(playerLog);
		}
		else{
			playerLog = new JButton("Logout");
			playerLog.setBounds(140, 125, 300, 70);
			add(playerLog);
		}
		
		add(playerLabel);
		add(playerStats);
		add(playerNameLabel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
