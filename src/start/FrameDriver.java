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

import net.miginfocom.swing.MigLayout;
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
	static MapChooser choose;
	static EndScreen endScreen;
	public static PlayerOnePanel pane1;
	public static PlayerTwoPanel pane2;
	
	
	public FrameDriver(Frame frame){
		this.frame = frame;
	}
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
		
		choose = new MapChooser(setupPanel);
		choose.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		choose.setVisible(false);
		
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
		frame.addPanel(choose);
	}
	public static void mainMenu(){
		hideAll();
		new ReadyActionListener(paneOne, paneTwo, true);
		paneOne.setVisible(true);
		paneTwo.setVisible(true);
		welcome.setVisible(true);
	}
	public static void startGameSetup(){
		hideAll();
		setupPanel.setPlayerOne(Main.playerOne);
		setupPanel.setPlayerTwo(Main.playerTwo);
		setupPanel.setVisible(true);
	}
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
	
	public static void endTheGame(int pOneWins, int pTwoWins, int gamesPlayed){
		hideAll();
		setup.resetMap();
		endScreen = new EndScreen(pOneWins, pTwoWins, gamesPlayed);
		endScreen.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		frame.addPanel(endScreen);
		endScreen.setVisible(true);
	}
	
	public static void setPaneOne(Player player){
		loginOne.setVisible(false);
		createOne.setVisible(false);
		
		paneOne.setUser(player);
		paneOne.setVisible(true);
	}
	
	public static void setPaneTwo(Player player){
		loginTwo.setVisible(false);
		createTwo.setVisible(false);
		
		paneTwo.setUser(player);
		paneTwo.setVisible(true);
	}
	
	public static void Statistics(){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View All Statistics");
		JScrollPane scrollPane = new JScrollPane(new AllStatsPanel(conn));  
		newFrame.getContentPane().add(scrollPane);  
		newFrame.setSize(500, 480);  
		newFrame.setVisible(true); 
	}
	
	public static void playerHistory(String username){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View Player History");
		JScrollPane scrollPane = new JScrollPane(new PlayerHistoryPanel(conn, username));  
		newFrame.getContentPane().add(scrollPane);  
		newFrame.setSize(500, 480);  
		newFrame.setVisible(true);
	}
	
	public static void HeadToHead(String username1, String username2){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View Head To Head Score");
		JScrollPane scrollPane = new JScrollPane(new HeadToHead(conn, username1, username2));  
		newFrame.getContentPane().add(scrollPane);  
		newFrame.setSize(500, 480);  
		newFrame.setVisible(true);
	}
	
	public static void TopTen(){
		Connection conn = Connect.connect();
		
		JFrame newFrame = new JFrame("View Top Ten");
		JScrollPane scrollPane = new JScrollPane(new TopTenPanel(conn));  
        	newFrame.getContentPane().add(scrollPane);  
        	newFrame.setSize(500, 480);  
        	newFrame.setVisible(true);
	}
	
	
	public static void mapChooser(){
		choose.setVisible(true);
		setupPanel.setVisible(false);
	}
	public static void hideAll(){
		//TODO may want to check to make sure that i is a jpanel
		
		for (Component i : frame.getContentPane().getComponents()){
			i.setVisible(false);
		}
	}
	public static void startGame(MapPanel mapPanel){
	    FrameDriver.hideAll();
	    frame.addPanel(mapPanel);
	}
	public static void explosion(ExplosionPanel exp, int xOffset, int yOffset){
		exp.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		frame.addPanel(exp);
	}
	public static void removePanel(JPanel panel){
			frame.remove(panel);
			frame.repaint();
	}
	public static boolean getLoginVisibility(){
		if (loginOne.isVisible() || loginTwo.isVisible()){
			return true;
		}
		return false;
	}
	public static boolean getCreateVisibility(){
		if (createOne.isVisible() || createTwo.isVisible()){
			return true;
		}
		return false;
	}
}
