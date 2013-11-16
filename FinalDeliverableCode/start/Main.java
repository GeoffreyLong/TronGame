/**
 * @author Geoffrey Long
 * 
 * This class serves to start the program.  The primary function is to 
 * instantiate the frame.  This is surrounded by a try-catch to ensure 
 * that no fatal errors arise if the frame cannot be initialized.
 */
package start;

import game.GameSetup;
import game.SetupPanel;
import startscreen.ReadyActionListener;
import startscreen.PlayerPanel;
import startscreen.WelcomePanel;
import user.Player;

/**
 * The main method will start the program by instantiating the GUI 
 * via the frame.
 * @param args
 * @throws java.lang.Exception if the GUI cannot be instantiated
 */
public class Main {
	public static Frame frame;
	public static Player playerOne;
	public static Player playerTwo;
	
	public static void main (String[] args){
		frame = new Frame();
		playerOne = new Player(1);
		playerTwo = new Player(2);
		start();
	}
	
	public static void start(){
		WelcomePanel welcome = new WelcomePanel();
		PlayerPanel paneOne = new PlayerPanel(playerOne);
		PlayerPanel paneTwo = new PlayerPanel(playerTwo);
		ReadyActionListener listen = new ReadyActionListener(paneOne, paneTwo);
		frame.addPanel(paneOne);
		frame.addPanel(paneTwo);
		frame.addPanel(welcome);
	}
	public static void startGameSetup(){
		Frame.removeAll();
		GameSetup setup = new GameSetup(playerOne, playerTwo);
		SetupPanel setupPanel = new SetupPanel(playerOne, playerTwo, setup);
		Frame.addPanel(setupPanel);
	}
}
