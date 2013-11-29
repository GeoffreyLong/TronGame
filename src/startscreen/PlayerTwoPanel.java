package startscreen;

import main.Frame;
import user.NullPlayer;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * Provides a safer way to differentiate between the two player panels
 */
public class PlayerTwoPanel extends PlayerPanel{
	public Player player;
	
	/**
	 * 
	 * @param player  The player corresponding to Player Two
	 */
	public PlayerTwoPanel(Player player) {
		super(player);
		this.player = player;
		
		setLoadout();
	}
	
	/**
	 * Instantiate the initial load of the player panel
	 */
	public void setLoadout(){
		setLocation(Frame.getXSize()/2-1);
		setLabel("PLAYER TWO");
		if(player.getClass().getName().equals("user.NullPlayer")){
			setNull();
		}
		else{
			setUser();
		}
	}
	
	/**
	 * Load the panel for a Null Player.
	 */
	public void setNull(){
		setLabel("PLAYER TWO");
		login.setVisible(true);
		playerStats.setVisible(true);
		createAccount.setVisible(true);
		logout.setVisible(false);
	}
	
	/**
	 * Load the panel of a non-null player
	 */
	public void setUser(){
		setLabel(player.getUserName());
		login.setVisible(false);
		createAccount.setVisible(false);
		playerStats.setVisible(true);
		playerHistory.setVisible(true);
		logout.setVisible(true);
	}
	
	/**
	 * Receive the new player object and set the panel according the the object.
	 * @param player  The new player corresponding to player Two
	 */
	public void setUser(Player player){
		this.player = player;
		if (player.getClass().getName().equals("user.NullPlayer")){
			setNull();
		}
		else{
			setUser();
		}
	}
}
