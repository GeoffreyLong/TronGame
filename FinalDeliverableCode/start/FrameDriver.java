package start;

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
	
	public FrameDriver(Frame frame){
		this.frame = frame;
	}
	public void init(){
		welcome = new WelcomePanel();
		paneOne = new PlayerOnePanel(Main.playerOne);
		paneTwo = new PlayerTwoPanel(Main.playerTwo);
		ReadyActionListener listen = new ReadyActionListener(paneOne, paneTwo);
		
		frame.addPanel(paneOne);
		frame.addPanel(paneTwo);
		frame.addPanel(welcome);
	}
	public static void startGameSetup(){
		frame.removeAll();
		GameSetup setup = new GameSetup(Main.playerOne, Main.playerTwo);
		SetupPanel setupPanel = new SetupPanel(Main.playerOne, Main.playerTwo, setup);
		frame.addPanel(setupPanel);
	}
	public static void createAccount(Player player){
		CreateUser create = new CreateUser(player);
		
		if (player.getPlayerNumber() == 1){
			paneOne.setVisible(false);
			create.setBounds(-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		}
		else{
			paneTwo.setVisible(false);
			create.setBounds(Frame.getXSize()/2-1,Frame.getYSize()/4,Frame.getXSize()/2,3*Frame.getYSize()/4);
		}
		frame.addPanel(create);
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
}
