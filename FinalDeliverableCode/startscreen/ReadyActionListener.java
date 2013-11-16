package startscreen;

import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class ReadyActionListener {

	public ReadyActionListener(PlayerPanel paneOne, PlayerPanel paneTwo){
		InputMap imOne = paneOne.inputMap;
		ActionMap amOne = paneOne.getActionMap();

		InputMap imTwo = paneTwo.inputMap;
		ActionMap amTwo = paneTwo.getActionMap();
	}
}
