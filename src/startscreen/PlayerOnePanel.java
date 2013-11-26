package startscreen;

import user.Player;
import javax.swing.JLabel;

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
			setNull();
		}
		else{
			setUser();
		}
	}
	public void setNull(){
		setLabel("PLAYER ONE");
		login.setVisible(true);
		createAccount.setVisible(true);
		logout.setVisible(false);
	}
	public void setUser(){
		setLabel(player.getUserName());
		login.setVisible(false);
		createAccount.setVisible(false);
		logout.setVisible(true);
	}
}
