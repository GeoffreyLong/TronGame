package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import user.Player;

public class SetupPanel extends JPanel implements ActionListener{
	Player playerOne;
	Player playerTwo;
	private int xCenter;
	private int yCenter;
	private int xSize;
	private int ySize;
	private GameSetup gameSetup;
	
	public SetupPanel(Player playerOne, Player playerTwo, GameSetup gameSetup){
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.gameSetup = gameSetup;
		
		xCenter = start.Frame.getXCenter();
		yCenter = start.Frame.getYCenter();
		xSize = start.Frame.getXSize();
		ySize = start.Frame.getYSize();
		
		setLayout(null);
		setBounds(0,0,xSize,ySize);
		setVisible(true);
		
		JLabel pageTitle = new JLabel(playerOne.getUserName() + " VS " + playerTwo.getUserName());
		pageTitle.setBounds(50, 50, 400, 100);
		
		JButton changeMap = new JButton("Change the Map");
		changeMap.setBounds(100, 200, 400, 100);
		
		JButton minusButton = new JButton("-");
		minusButton.setBounds(225, 375, 50, 50);
		
		JLabel changeDifficulty = new JLabel("<html> <div style='text-align:center'> Change the Difficulty <br> Current difficulty is <br>"+ gameSetup.getGameDifficulty() +"</div></html>");
		changeDifficulty.setBounds(300, 350, 200, 100);
		
		JButton plusButton = new JButton("+");
		plusButton.setBounds(500, 375, 50, 50);
		
		JButton changePOneColor = new JButton("<html> <div style='text-align:center; color:#000000'> Change the Color of PlayerOne's LightCycle <br> Current color is this color </div><html>");
		changePOneColor.setBackground(gameSetup.getPlayerColor(1));
		changePOneColor.setBounds(100, 500, 300, 100);
		
		JButton changePTwoColor = new JButton("<html> <div style='text-align:center; color:#000000'> Change the Color of PlayerTwo's LightCycle <br> Current color is this color </div><html>");
		changePTwoColor.setBackground(gameSetup.getPlayerColor(2));
		changePTwoColor.setBounds(500, 500, 300, 100);
		
		JButton startGame = new JButton("START THE GAME");
		startGame.setBounds(100, 610, 700, 50);
		
		add(pageTitle);
		add(changeMap);
		add(minusButton);
		add(changeDifficulty);
		add(plusButton);
		add(changePOneColor);
		add(changePTwoColor);
		add(startGame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
