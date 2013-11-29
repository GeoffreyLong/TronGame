package startscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import start.FrameDriver;
import start.Main;
import user.Player;

public class ReadyAction extends AbstractAction{
	private PlayerPanel pane;
	private Player player;
	
	public ReadyAction(PlayerPanel pane){
		this.pane = pane;
	}
	
	@Override
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
