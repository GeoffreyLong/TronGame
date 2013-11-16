package startscreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class ReadyTimerListener implements ActionListener{
	private JLabel playerOneReady;
	private JLabel playerTwoReady;
	
    ReadyTimerListener(JLabel playerOneReady, JLabel playerTwoReady){
    	this.playerOneReady = playerOneReady;
    	this.playerTwoReady = playerTwoReady;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
