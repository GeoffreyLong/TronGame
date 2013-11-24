package startscreen;

import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import startscreen.ReadyTimerListener;
import startscreen.ReadyAction;

public class ReadyActionListener {
	public static Timer timer;

	public ReadyActionListener(PlayerPanel paneOne, PlayerPanel paneTwo){
		InputMap imOne = paneOne.inputMap;
		ActionMap amOne = paneOne.getActionMap();
		
		imOne.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "w");
		amOne.put("w", new ReadyAction(paneOne.playerStatus));

		InputMap imTwo = paneTwo.inputMap;
		ActionMap amTwo = paneTwo.getActionMap();
		
		imTwo.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		amTwo.put("up", new ReadyAction(paneTwo.playerStatus));
		
		timer = new Timer(1000, new ReadyTimerListener(paneOne.playerStatus, paneTwo.playerStatus));
		timer.start();
	}
}
