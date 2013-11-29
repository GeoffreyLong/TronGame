package startscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import start.FrameDriver;
import start.Main;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * Provides an AbstractAction listener for the key bindings
 */
public class ReadyAction extends AbstractAction{
	private PlayerPanel pane;
	private Player player;
	
	/**
	 * 
	 * @param pane  The PlayerPanel for this specific action
	 */
	public ReadyAction(PlayerPanel pane){
		this.pane = pane;
	}
	
	@Override
	/**
	 * AbstractAction listener which updates the player status labels
	 */
	public void actionPerformed(ActionEvent e) {
		if (pane.getPlayerNumber() == 1){
			player = Main.playerOne;
		}
		else{
			player = Main.playerTwo;
		}
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
