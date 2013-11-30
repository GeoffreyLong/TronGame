package menuscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import main.FrameDriver;
import main.Main;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * Provides an AbstractAction listener for the key bindings made in PlayerStatusListener
 */
public class PlayerStatusUpdate extends AbstractAction{
	private PlayerPanel pane;
	private Player player;
	
	/**
	 * Set the class varaible pane to the passed pane
	 * 
	 * @param pane  The PlayerPanel for this specific action
	 */
	public PlayerStatusUpdate(PlayerPanel pane){
		this.pane = pane;
	}
	
	@Override
	/**
	 * AbstractAction listener which updates the player status labels
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Update the player object
		if (pane.getPlayerNumber() == 1){
			player = Main.playerOne;
		}
		else{
			player = Main.playerTwo;
		}
		
		//Check to make sure the player is not currently 
		//loggin in, creating a userName, or null 
		//prior to changing the status.
		if (!FrameDriver.getLoginVisibility() && !FrameDriver.getCreateVisibility()){
	    	if (pane.playerStatus.getText().equals("NOT READY")){
	    		if (!player.getClass().getName().equals("user.NullPlayer")){
	    			pane.playerStatus.setText("READY");
		    		pane.playerStatus.setForeground(Color.GREEN);
	    		}
	    	}
	    	else {
	    		pane.playerStatus.setText("NOT READY");
	    		pane.playerStatus.setForeground(Color.RED);
	    	}
		}
	}
}
