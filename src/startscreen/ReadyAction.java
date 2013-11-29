package startscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import start.FrameDriver;

public class ReadyAction extends AbstractAction{
	private PlayerPanel pane;
	
	public ReadyAction(PlayerPanel pane){
		this.pane = pane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!FrameDriver.getLoginVisibility() && !FrameDriver.getCreateVisibility()){
	    	if (pane.playerStatus.getText().equals("NOT READY")){
	    		pane.playerStatus.setText("READY");
	    		pane.playerStatus.setForeground(Color.GREEN);
	    	}
	    	else {
	    		pane.playerStatus.setText("NOT READY");
	    		pane.playerStatus.setForeground(Color.RED);
	    	}
		}
	}
}
