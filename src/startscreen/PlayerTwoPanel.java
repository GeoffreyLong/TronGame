package startscreen;

import start.Frame;
import user.Player;

public class PlayerTwoPanel extends PlayerPanel{
	private Player player;
	public PlayerTwoPanel(Player player) {
		super(player);
		this.player = player;
		setLoadout();
	}
	public void setLoadout(){
		setLocation(Frame.getXSize()/2-1);
		if(player.getClass().getName().equals("user.NullPlayer")){
			setNull(player);
		}
		else{
			setUser();
		}
	}
	public void setNull(Player player){
		this.player = player;
		setLabel("PLAYER TWO");
		login.setVisible(true);
		playerStats.setVisible(true);
		createAccount.setVisible(true);
		logout.setVisible(false);
	}
	public void setUser(){
		setLabel(player.getUserName());
		login.setVisible(false);
		createAccount.setVisible(false);
		playerStats.setVisible(true);
		playerHistory.setVisible(true);
		logout.setVisible(true);
	}
	public void setUser(Player player){
		this.player = player;
		setLabel(player.getUserName());
		login.setVisible(false);
		createAccount.setVisible(false);
		playerStats.setVisible(true);
		playerHistory.setVisible(true);
		logout.setVisible(true);
	}
}
