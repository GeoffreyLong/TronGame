package startscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import start.FrameDriver;

public class ReadyActionListener implements ActionListener{
	private static Timer timer;
	private PlayerPanel paneOne;
	private PlayerPanel paneTwo;

	public ReadyActionListener(PlayerPanel paneOne, PlayerPanel paneTwo, boolean startRightAway){
		this.paneOne = paneOne;
		this.paneTwo = paneTwo;
		
		if (startRightAway) {
			keyBind();
			setTimer();
		}
	}
	
	public void start(){
		keyBind();
		setTimer();
	}
	
	private void keyBind(){
		InputMap imOne = paneOne.inputMap;
		ActionMap amOne = paneOne.getActionMap();
		
		imOne.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "w");
		amOne.put("w", new ReadyAction(paneOne.playerStatus));

		InputMap imTwo = paneTwo.inputMap;
		ActionMap amTwo = paneTwo.getActionMap();
		
		imTwo.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		amTwo.put("up", new ReadyAction(paneTwo.playerStatus));
	}
	private void setTimer(){
		timer = new Timer(1000, this);
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer && isReady()){
			timer.stop();
			paneOne.playerStatus.setText("NOT READY");
			paneOne.playerStatus.setForeground(Color.RED);
			paneTwo.playerStatus.setText("NOT READY");
			paneTwo.playerStatus.setForeground(Color.RED);
			FrameDriver.startGameSetup();
		}
	}
	public boolean isReady(){
    	boolean isReady = false;
    	if (paneOne.playerStatus.getText().equals(paneTwo.playerStatus.getText()) && paneOne.playerStatus.getText().equals("READY")){
    		isReady = true;
    	}
    	return isReady;
	}
}
