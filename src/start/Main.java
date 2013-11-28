/**
 * @author Geoffrey Long
 * 
 * This class serves to start the program.  The primary function is to 
 * instantiate the frame.  This is surrounded by a try-catch to ensure 
 * that no fatal errors arise if the frame cannot be initialized.
 */
package start;

import javax.swing.UIManager;

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
	private static Frame frame;
	
	public static void main (String[] args){
		playerOne = new NullPlayer(1);
		playerTwo = new NullPlayer(2);
		frame = new Frame();
		initializeLookAndFeel();
		start();
	}
	
	public static void initializeLookAndFeel(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (Exception e1) {
				//Keep regular UI manager
			}
		}
	}
	
	public static void start(){
		new FrameDriver(frame);
		FrameDriver.init();
		FrameDriver.mainMenu();
	}
}
