/** 
 * @author Ashley Simpson
 * @version 2013/11/23
 * Setup Panel Testing Class
 */

package tests;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Test;

import game.SetupPanel;
import game.GameSetup;
import user.Player;

public class SetupPanelTest {
	private int playerNumOne = 1;
	private int playerNumTwo = 2;
	private Color testColor,BLACK;
	
	@Test
	public void testChangeColors() {
		// create necessary objects
		Player testPlayerOne = new Player(playerNumOne);
		Player testPlayerTwo = new Player(playerNumTwo);
		GameSetup testGameSetup = new GameSetup(testPlayerOne, testPlayerTwo);
		SetupPanel testSetup = new SetupPanel(testPlayerOne, testPlayerTwo, testGameSetup);
		
	}
}