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

/**
 * @author Geoffrey Long
 * 
 * Provides a way to map key presses to actions and a way to 
 * listen to the ReadyActions for a signal to start the game.
 */
public class ReadyActionListener implements ActionListener{
	private static Timer timer;
	private PlayerPanel paneOne;
	private PlayerPanel paneTwo;

	
	/**
	 * Add the key bindings and start the timer.
	 * 
	 * 
	 * @param paneOne  A PlayerOnePanel
	 * @param paneTwo  A PlayerTwoPanel
	 * @param startRightAway
	 */
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
		amOne.put("w", new ReadyAction(paneOne));

		InputMap imTwo = paneTwo.inputMap;
		ActionMap amTwo = paneTwo.getActionMap();
		
		imTwo.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		amTwo.put("up", new ReadyAction(paneTwo));
	}
	private void setTimer(){
		timer = new Timer(1000, this);
		timer.start();
	}
	
	@Override
	/**
	 * If the timer times out this will check to see if the game is ready
	 */
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
	
	/**
	 * Checks to see the status of the status labels to see if the game 
	 * is ready to be started
	 * @return isReady  True if both player status labels are equal and "ready", false otherwise
	 */
	public boolean isReady(){
    	boolean isReady = false;
    	if (paneOne.playerStatus.getText().equals(paneTwo.playerStatus.getText()) && paneOne.playerStatus.getText().equals("READY")){
    		isReady = true;
    	}
    	return isReady;
	}
}
