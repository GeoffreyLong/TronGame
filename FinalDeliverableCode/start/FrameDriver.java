package start;

import java.awt.Component;

import javax.swing.JComponent;

import game.EndScreen;
import game.GameSetup;
import game.MapChooser;
import game.SetupPanel;
import startscreen.PlayerOnePanel;
import startscreen.PlayerPanel;
import startscreen.PlayerTwoPanel;
import startscreen.ReadyActionListener;
import startscreen.WelcomePanel;
import user.CreateUser;
import user.LoginGUI;
import user.Player;

public class FrameDriver {
	static Frame frame;
	static WelcomePanel welcome;
	static PlayerPanel paneOne;
	static PlayerPanel paneTwo;
	static GameSetup setup;
	static SetupPanel setupPanel;
	static CreateUser createOne;
	static CreateUser createTwo;
	static LoginGUI loginOne;
	static LoginGUI loginTwo;
	static MapChooser choose;
	static EndScreen endScreen;
	
	public FrameDriver(Frame frame){
		this.frame = frame;
	}
	public void init(){
		welcome = new WelcomePanel();
		welcome.setVisible(false);
		paneOne = new PlayerOnePanel(Main.playerOne);
		paneOne.setVisible(false);
		paneTwo = new PlayerTwoPanel(Main.playerTwo);
		paneTwo.setVisible(false);
		ReadyActionListener listen = new ReadyActionListener(paneOne, paneTwo);
		
		setup = new GameSetup(Main.playerOne, Main.playerTwo);
		setupPanel = new SetupPanel(Main.playerOne, Main.playerTwo, setup);
		setupPanel.setVisible(false);
		
		choose = new MapChooser(setupPanel);
		choose.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		choose.setVisible(false);
		
		createOne = new CreateUser(Main.playerOne);
		createOne.setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		createOne.setVisible(false);

		createTwo = new CreateUser(Main.playerTwo);
		createTwo.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		createTwo.setVisible(false);
		
		loginOne = new LoginGUI(Main.playerOne);
		loginOne.setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		loginOne.setVisible(false);

		loginTwo = new LoginGUI(Main.playerTwo);
		loginTwo.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		loginTwo.setVisible(false);
		
		endScreen = new EndScreen();
		endScreen.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		endScreen.setVisible(false);
		
		frame.addPanel(paneOne);
		frame.addPanel(paneTwo);
		frame.addPanel(welcome);
		frame.addPanel(setupPanel);
		frame.addPanel(createOne);
		frame.addPanel(createTwo);
		frame.addPanel(loginOne);
		frame.addPanel(loginTwo);
		frame.addPanel(choose);
		frame.addPanel(endScreen);
	}
	public static void mainMenu(){
		hideAll();
		paneOne.setVisible(true);
		paneTwo.setVisible(true);
		welcome.setVisible(true);
	}
	public static void startGameSetup(){
		hideAll();
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
	public static void endGame(int pOneWins, int pTwoWins, int gamesPlayed){
		hideAll();
		endScreen.makeComponents();
		endScreen.makeLayout();
		endScreen.setPOneWins(pOneWins);
		endScreen.setPTwoWins(pTwoWins);
		endScreen.setGamesPlayed(gamesPlayed);
		endScreen.setVisible(true);
	}
	public static void mapChooser(){
		choose.setVisible(true);
		setupPanel.setVisible(false);
	}
	public static void hideAll(){
		//TODO may want to check to make sure that i is a jpanel
		
		for (Component i : Frame.frame.getContentPane().getComponents()){
			i.setVisible(false);
		}
	}
}
