package startscreen;

import user.Player;

public class PlayerOnePanel extends PlayerPanel{
	Player player;
	public PlayerOnePanel(Player player) {
		super(player);
		this.player = player;
		
		setLoadout();
	}
	public void setLoadout(){
		setLocation(-1);
		setLabel("PLAYER ONE");
		if(player.getClass().getName().equals("user.NullPlayer")){
			setNull(player);
		}
		else{
			setUser();
		}
	}
	public void setNull(Player player){
		this.player = player;
		setLabel("PLAYER ONE");
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
