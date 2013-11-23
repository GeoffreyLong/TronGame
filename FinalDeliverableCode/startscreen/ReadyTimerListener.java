package startscreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import start.FrameDriver;
import start.Main;

public class ReadyTimerListener implements ActionListener{
	private JLabel playerOneReady;
	private JLabel playerTwoReady;
	
    ReadyTimerListener(JLabel playerOneReady, JLabel playerTwoReady){
    	this.playerOneReady = playerOneReady;
    	this.playerTwoReady = playerTwoReady;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isReady()){
			FrameDriver.startGameSetup();
			ReadyActionListener.timer.stop();
		}
	}
	
    public boolean isReady(){
    	boolean isReady = false;
    	if (this.playerOneReady.getText()==this.playerTwoReady.getText() && this.playerOneReady.getText().equals("READY")){
    		isReady = true;
    	}
    	return isReady;
    }
}
