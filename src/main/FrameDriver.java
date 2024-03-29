package main;

import game.EndPanel;
import game.GameSetup;
import game.MapChooserPanel;
import game.SetupPanel;
import gameplay.ExplosionPanel;
import gameplay.GamePanel;

import java.awt.Component;
import java.awt.Window;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import menuscreen.PlayerOnePanel;
import menuscreen.PlayerTwoPanel;
import menuscreen.PlayerStatusListener;
import menuscreen.WelcomePanel;
import statistics.AllStatsPanel;
import statistics.HeadToHeadPanel;
import statistics.PlayerHistoryPanel;
import statistics.TopTenPanel;
import user.CreateUserPanel;
import user.LoginPanel;
import user.NullPlayer;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * This class provides a means by which to manage the behavior 
 * of the frame.  Responsible for updating the state of the frame.
 */
public class FrameDriver {
	private static Frame frame;
	
	private static WelcomePanel welcome;
	private static PlayerOnePanel paneOne;
	private static PlayerTwoPanel paneTwo;
	private static CreateUserPanel createOne;
	private static CreateUserPanel createTwo;
	private static LoginPanel loginOne;
	private static LoginPanel loginTwo;
	
	public static JFrame statsFrame;
	public static JFrame historyFrame;
	public static JFrame headToHead;
	public static JFrame topTen;
	
	private static GameSetup setup;
	private static SetupPanel setupPanel;
	private static MapChooserPanel mapChooser;
	private static EndPanel endPanel;
	
	/**
	 * Instantiate the Frame
	 * @param frame  The Frame of the game
	 */
	public FrameDriver(Frame theFrame){
		frame = theFrame;
	}
	
	/**
	 * Initialize the majority of the panels that will be used throughout 
	 * the game and add them to the Frame.
	 */
	public static void init(){
		//Notice that all of these panels are set to invisible 
		//We do most of the management of the gui by turning on and off 
		//the visibility of the panel, except in certain situations where 
		//we found it necessary to create a new Panel instead.
		
		welcome = new WelcomePanel();
		welcome.setVisible(false);
		
		paneOne = new PlayerOnePanel(Main.playerOne);
		paneOne.setVisible(false);
		
		paneTwo = new PlayerTwoPanel(Main.playerTwo);
		paneTwo.setVisible(false);
		
		setup = new GameSetup(Main.playerOne, Main.playerTwo);
		setupPanel = new SetupPanel(Main.playerOne, Main.playerTwo, setup);
		setupPanel.setVisible(false);
		
		mapChooser = new MapChooserPanel(setupPanel);
		mapChooser.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		mapChooser.setVisible(false);
		
		createOne = new CreateUserPanel(Main.playerOne);
		createOne.setBounds(-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		createOne.setVisible(false);

		createTwo = new CreateUserPanel(Main.playerTwo);
		createTwo.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		createTwo.setVisible(false);
		
		loginOne = new LoginPanel(Main.playerOne);
		loginOne.setBounds(-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		loginOne.setVisible(false);

		loginTwo = new LoginPanel(Main.playerTwo);
		loginTwo.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		loginTwo.setVisible(false);
		
		frame.add(welcome);
		frame.add(paneOne);
		frame.add(paneTwo);
		frame.add(setupPanel);
		frame.add(createOne);
		frame.add(createTwo);
		frame.add(loginOne);
		frame.add(loginTwo);
		frame.add(mapChooser);
		frame.validate();
		frame.repaint();
	}
	
	/**
	 * Hide all components on the frame
	 */
	public static void hideAll(){
		//Get all the Components of the Frame and turn them all invisible
		//Although this is to turn off panel visibility, we are not checking 
		//for component type, because if a non JPanel appeared on the Frame 
		//we would want it to be invisible anyways.
		for (Component i : frame.getContentPane().getComponents()){
			i.setVisible(false);
		}
	}
	
	/**
	 * Remove a panel from the frame
	 * @param panel  Panel to be removed
	 */
	public static void removePanel(JPanel panel){
			frame.remove(panel);
			frame.repaint();
	}
	
	/**
	 * Set all panels of the mainMenu to visible
	 */
	public static void mainMenu(){
		hideAll();
		//Create a new PlayerStatusListener
		new PlayerStatusListener(paneOne, paneTwo, true);
		paneOne.setVisible(true);
		paneTwo.setVisible(true);
		welcome.setVisible(true);
	}
	
	/**
	 * Hide the player start panel and open the CreateUser panel  
	 * corresponding to the player who invoked it.
	 * 
	 * @param player  The Player associated with the pane that create user button was located on
	 */
	public static void createAccount(Player player){
		if (player.getPlayerNumber() == 1){
			paneOne.setVisible(false);
			createOne.setVisible(true);
		}
		else{
			paneTwo.setVisible(false);
			createTwo.setVisible(true);
		}
	}
	
	/**
	 * Hide the player start panel and open the login panel 
	 * corresponding to the player who invoked it.
	 * 
	 * @param player  The Player associated with the pane that the login button was located on
	 */
	public static void login(Player player){
		if (player.getPlayerNumber() == 1){
			loginOne.setVisible(true);
			paneOne.setVisible(false);
		}
		else{
			loginTwo.setVisible(true);
			paneTwo.setVisible(false);
		}
	}
	
	/**
	 * Reset the start panel of the player who invoked this method.  
	 * Reset the player to a NullPlayer.
	 * @param player  The Player associated with the pane that the logout button was located on
	 */
	public static void logout(Player player){
		if(player.getPlayerNumber() == 1){
			Main.playerOne = new NullPlayer(1);
			paneOne.setUser(Main.playerOne);
			loginOne.setPlayer(Main.playerOne);
			LoginPanel.player1 = "";
		}
		else{
			Main.playerTwo = new NullPlayer(2);
			paneOne.setUser(Main.playerTwo);
			loginTwo.setPlayer(Main.playerTwo);
			LoginPanel.player2 = "";
		}
	}
	
	/**
	 * After the CreateUser or Login is run, 
	 * set the PlayerOnePanel player to this player
	 * @param player  The Player associated with the paneOne
	 */
	public static void setPaneOne(Player player){
		loginOne.setVisible(false);
		createOne.setVisible(false);
		
		Main.playerOne = player;
		paneOne.setUser(player);
		paneOne.setVisible(true);
	}
	
	/**
	 * After the CreateUser or Login is run, 
	 * set the PlayerTwoPanel player to this player
	 * @param player  The Player associated with the paneTwo
	 */
	public static void setPaneTwo(Player player){
		loginTwo.setVisible(false);
		createTwo.setVisible(false);
		
		Main.playerTwo = player;
		paneTwo.setUser(player);
		paneTwo.setVisible(true);
	}
	
	/**
	 * Check to see if either player is currently logging in
	 * @return isVisible  True if one or both of the players is logging in, false otherwise
	 */
	public static boolean getLoginVisibility(){
		boolean isVisible = false;
		if (loginOne.isVisible() || loginTwo.isVisible()){
			isVisible = true;
		}
		return isVisible;
	}
	
	/**
	 * Check to see if either player is currently creating a user
	 * @return isVisible  True if one or both of the players is creating a user, false otherwise
	 */
	public static boolean getCreateVisibility(){
		boolean isVisible = false;
		if (createOne.isVisible() || createTwo.isVisible()){
			isVisible = true;
		}
		return isVisible;
	}
	
	
	/**
	 * 
	 */
	public static void Statistics(){
		Connection conn = Connect.connect();
		
		statsFrame = new JFrame("View All Statistics");
		JScrollPane scrollPane = new JScrollPane(new AllStatsPanel(conn));  
		statsFrame.getContentPane().add(scrollPane);  
		statsFrame.setSize(500, 480);  
		statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		statsFrame.setVisible(true); 
	}
	
	
	/**
	 * 
	 * @param username
	 */
	public static void playerHistory(String username){
		Connection conn = Connect.connect();
		
		historyFrame = new JFrame("View Player History");
		JScrollPane scrollPane = new JScrollPane(new PlayerHistoryPanel(conn, username));  
		historyFrame.getContentPane().add(scrollPane);  
		historyFrame.setSize(500, 480);
		historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		historyFrame.setVisible(true);
	}
	
	
	/**
	 * 
	 * @param username1
	 * @param username2
	 */
	public static void HeadToHead(String username1, String username2){
		Connection conn = Connect.connect();
		
		headToHead = new JFrame("View Head To Head Score");
		JScrollPane scrollPane = new JScrollPane(new HeadToHeadPanel(conn, username1, username2));  
		headToHead.getContentPane().add(scrollPane);  
		headToHead.setSize(500, 480); 
		headToHead.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		headToHead.setVisible(true);
	}
	
	
	/**
	 * 
	 */
	public static void TopTen(){
		Connection conn = Connect.connect();
		
		topTen = new JFrame("View Top Ten");
		JScrollPane scrollPane = new JScrollPane(new TopTenPanel(conn));  
        topTen.getContentPane().add(scrollPane);  
        topTen.setSize(500, 480); 
        topTen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        topTen.setVisible(true);
	}
	
	
	/**
	 * Show the SetupPanel and remove all statistic Frames
	 */
	public static void startGameSetup(){
		hideAll();
		
		
		//Get all open windows, if the window is not a main.Frame, then close it
		for(Window window : Window.getWindows()){
			if(!window.getClass().getName().equals("main.Frame")){
				window.dispose();
			}
		}
		
		setupPanel.setPlayerOne(Main.playerOne);
		setupPanel.setPlayerTwo(Main.playerTwo);
		setupPanel.setVisible(true);
	}
	
	/**
	 * Open the MapChooser panel and hide the SetupPanel
	 */
	public static void mapChooser(){
		mapChooser.setVisible(true);
		setupPanel.setVisible(false);
	}
	
	/**
	 * Hide everything and launch the GamePanel
	 * @param GamePanel  The GUI for the actual Light Cycle gameplay
	 */
	public static void startGame(GamePanel gamePanel){
	    hideAll();
	    frame.add(gamePanel);
	    frame.validate();
	    frame.repaint();
	}
	
	/**
	 * Adds the explosion panel on player cycle crash
	 * @param exp  Explosion panel to be added
	 */
	public static void explosion(ExplosionPanel exp){
		exp.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		frame.add(exp);
	    frame.validate();
	    frame.repaint();
	}
	
	/**
	 * Hide all panels, show the EndPanel 
	 * 
	 * @param pOneWins  Number of player one's wins during that round
	 * @param pTwoWins  Number of player two's wins during that round
	 * @param gamesPlayed  Number of games played during that round
	 */
	public static void endTheGame(int pOneWins, int pTwoWins, int gamesPlayed){
		hideAll();
		
		//Reset the map so that the trails disappear 
		setup.resetMap();
		
		//Create and add endPanel
		endPanel = new EndPanel(pOneWins, pTwoWins, gamesPlayed);
		endPanel.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		frame.add(endPanel);
		endPanel.setVisible(true);
	    frame.validate();
	    frame.repaint();
	}
}
