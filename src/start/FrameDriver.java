package start;

import game.EndScreen;
import game.GameSetup;
import game.MapChooser;
import game.SetupPanel;
import gameplay.ExplosionPanel;
import gameplay.MapPanel;

import java.awt.Component;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import startscreen.PlayerOnePanel;
import startscreen.PlayerTwoPanel;
import startscreen.ReadyActionListener;
import startscreen.WelcomePanel;
import statistics.AllStatsPanel;
import statistics.HeadToHead;
import statistics.PlayerHistoryPanel;
import statistics.TopTenPanel;
import user.CreateUser;
import user.LoginGUI;
import user.NullPlayer;
import user.Player;

/**
 * @author Geoffrey Long
 * 
 * This class provides a means by which to manage the behavior 
 * of the frame.  Responsible for updating the state of the frame.
 */
public class FrameDriver {
	static Frame frame;
	static WelcomePanel welcome;
	static PlayerOnePanel paneOne;
	static PlayerTwoPanel paneTwo;
	static GameSetup setup;
	static SetupPanel setupPanel;
	static CreateUser createOne;
	static CreateUser createTwo;
	static LoginGUI loginOne;
	static LoginGUI loginTwo;
	static MapChooser mapChooser;
	static EndScreen endScreen;
	public static PlayerOnePanel pane1;
	public static PlayerTwoPanel pane2;
	
	/**
	 * Instantiate the Frame
	 * @param frame  The Frame of the game
	 */
	public FrameDriver(Frame theFrame){
		frame = theFrame;
	}
	
	/**
	 * Initialize the majority of the panels that will be used throughout 
	 * the game.
	 */
	public static void init(){
		
		welcome = new WelcomePanel();
		welcome.setVisible(false);
		
		paneOne = new PlayerOnePanel(Main.playerOne);
		paneOne.setVisible(false);
		
		paneTwo = new PlayerTwoPanel(Main.playerTwo);
		paneTwo.setVisible(false);
		
		setup = new GameSetup(Main.playerOne, Main.playerTwo);
		setupPanel = new SetupPanel(Main.playerOne, Main.playerTwo, setup);
		setupPanel.setVisible(false);
		
		mapChooser = new MapChooser(setupPanel);
		mapChooser.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		mapChooser.setVisible(false);
		
		createOne = new CreateUser(Main.playerOne);
		createOne.setBounds(-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		createOne.setVisible(false);

		createTwo = new CreateUser(Main.playerTwo);
		createTwo.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		createTwo.setVisible(false);
		
		loginOne = new LoginGUI(Main.playerOne);
		loginOne.setBounds(-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		loginOne.setVisible(false);

		loginTwo = new LoginGUI(Main.playerTwo);
		loginTwo.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/6,Frame.getXSize()/2,5*Frame.getYSize()/6);
		loginTwo.setVisible(false);
		
		frame.addPanel(welcome);
		frame.addPanel(paneOne);
		frame.addPanel(paneTwo);
		frame.addPanel(setupPanel);
		frame.addPanel(createOne);
		frame.addPanel(createTwo);
		frame.addPanel(loginOne);
		frame.addPanel(loginTwo);
		frame.addPanel(mapChooser);
	}
	
	/**
	 * Hide all components on the frame
	 */
	public static void hideAll(){
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
		new ReadyActionListener(paneOne, paneTwo, true);
		paneOne.setVisible(true);
		paneTwo.setVisible(true);
		welcome.setVisible(true);
	}
	
	/**
	 * Hide the player start panel and open the CreateUser panel  
	 * corresponding to the player who invoked it.
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
	 * @param player
	 */
	public static void logout(Player player){
		if(player.getPlayerNumber() == 1){
			Main.playerOne = new NullPlayer(1);
			LoginGUI.player1 = "";
			paneOne.setNull();
		}
		
		else{
			Main.playerTwo = new NullPlayer(2);
			LoginGUI.player2 = "";
			paneTwo.setNull();
		}
	}
	
	/**
	 * Set the player one panel with this player after login
	 * @param player
	 */
	public static void setPaneOne(Player player){
		loginOne.setVisible(false);
		createOne.setVisible(false);
		
		paneOne.setUser(player);
		paneOne.setVisible(true);
	}
	
	/**
	 * Set the player two panel with this player after login
	 * @param player
	 */
	public static void setPaneTwo(Player player){
		loginTwo.setVisible(false);
		createTwo.setVisible(false);
		
		paneTwo.setUser(player);
		paneTwo.setVisible(true);
	}
	
	/**
	 * Check to see if either player is currently logging in
	 * @return
	 */
	public static boolean getLoginVisibility(){
		if (loginOne.isVisible() || loginTwo.isVisible()){
			return true;
		}
		return false;
	}
	
	/**
	 * Check to see if either player is currently creating a user
	 * 	 * @return
	 */
	public static boolean getCreateVisibility(){
		if (createOne.isVisible() || createTwo.isVisible()){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	public static void Statistics(){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View All Statistics");
		JScrollPane scrollPane = new JScrollPane(new AllStatsPanel(conn));  
		newFrame.getContentPane().add(scrollPane);  
		newFrame.setSize(500, 480);  
		newFrame.setVisible(true); 
	}
	
	/**
	 * 
	 * @param username
	 */
	public static void playerHistory(String username){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View Player History");
		JScrollPane scrollPane = new JScrollPane(new PlayerHistoryPanel(conn, username));  
		newFrame.getContentPane().add(scrollPane);  
		newFrame.setSize(500, 480);  
		newFrame.setVisible(true);
	}
	
	/**
	 * 
	 * @param username1
	 * @param username2
	 */
	public static void HeadToHead(String username1, String username2){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View Head To Head Score");
		JScrollPane scrollPane = new JScrollPane(new HeadToHead(conn, username1, username2));  
		newFrame.getContentPane().add(scrollPane);  
		newFrame.setSize(500, 480);  
		newFrame.setVisible(true);
	}
	
	/**
	 * 
	 */
	public static void TopTen(){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View Top Ten");
		JScrollPane scrollPane = new JScrollPane(new TopTenPanel(conn));  
        	newFrame.getContentPane().add(scrollPane);  
        	newFrame.setSize(500, 480);  
        	newFrame.setVisible(true);
	}
	
	/**
	 * Hide everything but the game setup panel
	 */
	public static void startGameSetup(){
		hideAll();
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
	 * Hide everything and launch the MapPanel
	 * @param mapPanel  The GUI for the actual Light Cycle gameplay
	 */
	public static void startGame(MapPanel mapPanel){
	    FrameDriver.hideAll();
	    frame.addPanel(mapPanel);
	}
	
	/**
	 * Adds the explosion panel on player cycle crash
	 * @param exp  Explosion panel to be added
	 */
	public static void explosion(ExplosionPanel exp){
		exp.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		frame.addPanel(exp);
	}
	
	/**
	 * Ends the game by setting the EndScreen
	 * 
	 * @param pOneWins  Number of player one's wins during that round
	 * @param pTwoWins  Number of player two's wins during that round
	 * @param gamesPlayed  Number of games played during that round
	 */
	public static void endTheGame(int pOneWins, int pTwoWins, int gamesPlayed){
		hideAll();
		setup.resetMap();
		endScreen = new EndScreen(pOneWins, pTwoWins, gamesPlayed);
		endScreen.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		frame.addPanel(endScreen);
		endScreen.setVisible(true);
	}
}
