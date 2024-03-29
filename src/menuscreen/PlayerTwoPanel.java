package menuscreen;

import java.awt.Color;

import main.Frame;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * Provides a safe way to differentiate between the two player panels
 */
public class PlayerTwoPanel extends PlayerPanel{
	public Player player;
	
	/**
	 * Store the Player object in a class variable
	 * 
	 * @param player  The player corresponding to Player Two
	 */
	public PlayerTwoPanel(Player player) {
		super(player);
		this.player = player;
		
		setLoadout();
	}
	
	/**
	 * Instantiate the initial attributes of the player panel such as 
	 * the location of the panel and the title label.
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
	 * Sets the panel setup for a null player (not logged in)
	 */
	private void setNull(){
		//Reset the playerStatus label
		this.playerStatus.setText("NOT READY");
		this.playerStatus.setForeground(Color.RED);
		
		setLabel("PLAYER TWO");
		login.setVisible(true);
		playerStats.setVisible(true);
		createAccount.setVisible(true);
		logout.setVisible(false);
	}
	
	/**
	 * Sets the panel of a non-null player (logged in)
	 */
	private void setUser(){
		setLabel(player.getUserName());
		login.setVisible(false);
		createAccount.setVisible(false);
		playerStats.setVisible(true);
		playerHistory.setVisible(true);
		logout.setVisible(true);
	}
	
	/**
	 * Receive a new player object and set the panel according the the object.
	 * @param player  The new player corresponding to player One
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
