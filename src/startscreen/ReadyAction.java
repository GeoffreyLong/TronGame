package startscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import start.FrameDriver;

public class ReadyAction extends AbstractAction{
	private JLabel playerStatus;
	
	public ReadyAction(JLabel playerStatus){
		this.playerStatus = playerStatus;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!FrameDriver.getLoginVisibility() && !FrameDriver.getCreateVisibility()){
	    	if (playerStatus.getText().equals("NOT READY")){
	    		playerStatus.setText("READY");
	    		playerStatus.setForeground(Color.GREEN);
	    	}
	    	else {
	    		playerStatus.setText("NOT READY");
	    		playerStatus.setForeground(Color.RED);
	    	}
		}
	}
}
