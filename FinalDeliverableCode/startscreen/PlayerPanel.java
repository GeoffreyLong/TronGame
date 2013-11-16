package startscreen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.Frame;
import user.Player;

public class PlayerPanel extends JPanel{
	private Player player;
	
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
		
		add(playerLabel);
		add(playerStats);
	}
}
