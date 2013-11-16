package startscreen;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

public class ReadyAction extends AbstractAction{
	private JLabel playerStatus;
	
	public ReadyAction(JLabel playerStatus){
		this.playerStatus = playerStatus;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
