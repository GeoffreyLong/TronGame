/** 
 * @author Ashley Simpson
 * @version 2013/11/23
 * Setup Panel Testing Class
 */

package tests;

import game.GameSetup;
import game.SetupPanel;
import user.Player;

public class SetupPanelTest {
	private int playerNumOne = 1;
	private int playerNumTwo = 2;
	private SetupPanel.Colors testColour;
	private SetupPanel.Colors colorRed = SetupPanel.Colors.RED;
	private SetupPanel.Colors colorBlue = SetupPanel.Colors.BLUE;
	private SetupPanel.Colors colorYellow = SetupPanel.Colors.YELLOW;
	private SetupPanel.Colors colorMagenta = SetupPanel.Colors.MAGENTA;
	private SetupPanel.Colors colorGreen = SetupPanel.Colors.GREEN;
	private SetupPanel.Colors colorBlack = SetupPanel.Colors.BLACK;
	
	@Test
	public void testChangeColors() {
		// create necessary objects
		Player testPlayerOne = new Player(playerNumOne);
		Player testPlayerTwo = new Player(playerNumTwo);
		GameSetup testGameSetup = new GameSetup(testPlayerOne, testPlayerTwo);
		SetupPanel testSetup = new SetupPanel(testPlayerOne, testPlayerTwo, testGameSetup);
		
		// test changing color for default colours
		testColour = testSetup.colorChange(colorRed);
		assertEquals(colorYellow, testColour);
		testColour = testSetup.colorChange(colorBlue);
		assertEquals(colorYellow, testColour);
		testColour = testSetup.colorChange(colorYellow);
		assertEquals(colorMagenta, testColour);
		testColour = testSetup.colorChange(colorMagenta);
		assertEquals(colorGreen, testColour);
		testColour = testSetup.colorChange(colorGreen);
		assertEquals(colorBlack, testColour);
		testColour = testSetup.colorChange(colorBlack);
		assertEquals(colorYellow, testColour);
	}
}