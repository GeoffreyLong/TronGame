package main;

import javax.swing.UIManager;

import user.NullPlayer;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * This class provides the start of the program, it serves to initialize 
 * the Frame with a MainMenu as well as the player objects.
 */
public class Main {
	public static Player playerOne;
	public static Player playerTwo;
	private static Frame frame;
	
	/**
	 * The main method will start the program by instantiating the GUI 
	 * and the players
	 * @param args
	 */
	public static void main (String[] args){
		//These Player objects are actualy public static, 
		//they are updated throughout the game
		playerOne = new NullPlayer(1);
		playerTwo = new NullPlayer(2);
		
		frame = new Frame();
		initializeLookAndFeel();
		start();
	}
	
	/**
	 * This method will initialize the look and feel of the GUI
	 */
	private static void initializeLookAndFeel(){
		/*
		 * The look and feel will regulate how the individual styling looks.  
		 * This tries two UI managers, GTK and Nimbus.  If neither of these 
		 * are able to be used then the default UI manager is used.  This 
		 * isn't really considered a problem as it is only for styling.
		 */
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
	
	/**
	 * Start the main menu
	 */
	private static void start(){
		new FrameDriver(frame);
		
		//This will initialize most of the panels of FrameDriver
		FrameDriver.init();
		FrameDriver.mainMenu();
	}
}
