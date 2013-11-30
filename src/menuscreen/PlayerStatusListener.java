package menuscreen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import main.FrameDriver;

/**
 * @author Geoffrey Long
 * 
 * Provides a way to map key presses to actions, and a way to 
 * listen to the PlayerStatusUpdate for a signal to start the game.
 */
public class PlayerStatusListener implements ActionListener{
	private static Timer timer;
	private PlayerPanel paneOne;
	private PlayerPanel paneTwo;

	
	/**
	 * If startRightAway is true then the key bindings and start the timer, 
	 * else simply add the PlayerPanels as class variables.
	 * 
	 * 
	 * @param paneOne  A PlayerOnePanel
	 * @param paneTwo  A PlayerTwoPanel
	 * @param startRightAway  Allows for easy testing
	 */
	public PlayerStatusListener(PlayerPanel paneOne, PlayerPanel paneTwo, boolean startRightAway){
		this.paneOne = paneOne;
		this.paneTwo = paneTwo;
		
		if (startRightAway) {
			keyBind();
			setTimer();
		}
	}
	
	/**
	 * Start method to facilitate testing
	 */
	public void start(){
		keyBind();
		setTimer();
	}
	
	/**
	 * Use the input and action maps to perform Swing KeyBindings
	 */
	private void keyBind(){
		InputMap imOne = paneOne.inputMap;
		ActionMap amOne = paneOne.getActionMap();
		
		//Bind 'w' to the event in the actionPerfomed method 
		//of paneOne's PlayerStatusUpdate
		imOne.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "w");
		amOne.put("w", new PlayerStatusUpdate(paneOne));

		InputMap imTwo = paneTwo.inputMap;
		ActionMap amTwo = paneTwo.getActionMap();
		
		//Bind the up arrow key to the event in the actionPerfomed method 
		//of paneTwo's PlayerStatusUpdate
		imTwo.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		amTwo.put("up", new PlayerStatusUpdate(paneTwo));
	}
	
	/**
	 * Make this class a timer, trigger actionPerformed on each timeout
	 */
	private void setTimer(){
		timer = new Timer(1000, this);
		timer.start();
	}
	
	@Override
	/**
	 * If the timer times out this will check to see if the game is ready.  
	 * If it is ready, then change revert the playerStatus labels back to 
	 * initial conditions and start the game.
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
    	//Check if the labels match up and if one of the labels is "READY"
    	//Don't need to check both labels because if one is "READY" and they are 
    	//equal then both are "READY"
    	if (paneOne.playerStatus.getText().equals(paneTwo.playerStatus.getText()) && paneOne.playerStatus.getText().equals("READY")){
    		isReady = true;
    	}
    	return isReady;
	}
}
