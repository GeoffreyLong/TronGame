package startscreen;

import start.Frame;
import user.Player;

public class PlayerTwoPanel extends PlayerPanel{
	Player player;
	public PlayerTwoPanel(Player player) {
		super(player);
		this.player = player;
		setLoadout();
	}
	public void setLoadout(){
		setLocation(Frame.getXSize()/2-1);
		if(player.getClass().getName().equals("user.NullPlayer")){
			setNull();
		}
		else{
			setUser();
		}
	}
	public void setNull(){
		setLabel("PLAYER TWO");
		login.setVisible(true);
		createAccount.setVisible(true);
		logout.setVisible(false);
	}
	public void setUser(){
		setLabel(player.getUserName());
		login.setVisible(false);
		playerStats.setVisible(false);
		playerHistory.setVisible(true);
		createAccount.setVisible(false);
		logout.setVisible(true);
	}
}
