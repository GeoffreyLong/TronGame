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
import startscreen.PlayerOnePanel;
import startscreen.PlayerTwoPanel;
import startscreen.ReadyActionListener;
import startscreen.PlayerPanel;
import startscreen.WelcomePanel;
import user.NullPlayer;
import user.Player;

/**
 * The main method will start the program by instantiating the GUI 
 * via the frame.
 * @param args
 * @throws java.lang.Exception if the GUI cannot be instantiated
 */
public class Main {
	public static Player playerOne;
	public static Player playerTwo;
	
	public static void main (String[] args){
		playerOne = new NullPlayer(1);
		playerTwo = new NullPlayer(2);
		Frame frame = new Frame();
		FrameDriver frameDriver = new FrameDriver(frame);
		frameDriver.init();
		start();
	}
	
	public static void start(){
		
	}
}
