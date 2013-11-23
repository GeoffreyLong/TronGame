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
		
		createOne = new CreateUser(Main.playerOne);
		createOne.setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		createOne.setVisible(false);

		createTwo = new CreateUser(Main.playerTwo);
		createTwo.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		createTwo.setVisible(false);
		
		frame.addPanel(paneOne);
		frame.addPanel(paneTwo);
		frame.addPanel(welcome);
		frame.addPanel(setupPanel);
		frame.addPanel(createOne);
		frame.addPanel(createTwo);
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
		LoginGUI login = new LoginGUI(player);

		if (player.getPlayerNumber() == 1){
			paneOne.setVisible(false);
			login.setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		}
		else{
			paneTwo.setVisible(false);
			login.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		}
		frame.addPanel(login);
	}
	public static void endGame(int pOneWins, int pTwoWins, int gamesPlayed){
		EndScreen endScreen = new EndScreen(pOneWins, pTwoWins, gamesPlayed);
		endScreen.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		frame.removeAll();
		frame.addPanel(endScreen);
	}
	public static void mapChooser(SetupPanel setup){
		MapChooser choose = new MapChooser(setup);
		choose.setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		choose.setVisible(true);
		frame.addPanel(choose);
		setup.setVisible(false);
	}
	public static void hideAll(){
		//may want to check to make sure that i is a jpanel
		for (Component i : Frame.frame.getContentPane().getComponents()){
			i.setVisible(false);
		}
	}
}
