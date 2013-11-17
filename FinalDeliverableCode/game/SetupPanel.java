package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gameplay.GameMaster;
import user.Player;

public class SetupPanel extends JPanel implements ActionListener{
	Player playerOne;
	Player playerTwo;
	private int xCenter;
	private int yCenter;
	private int xSize;
	private int ySize;
	private GameSetup gameSetup;
	private JLabel changeDifficulty;
	private Colors pOneColor = Colors.RED;
	private Colors pTwoColor = Colors.BLUE;
	private JButton changePOneColor;
	private JButton changePTwoColor;
	
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
		changeMap.addActionListener(this);
		
		JButton minusButton = new JButton("-");
		minusButton.setBounds(225, 375, 50, 50);
		minusButton.addActionListener(this);
		
		changeDifficulty = new JLabel("<html> <div style='text-align:center'> "
				+ "Change the Difficulty <br> Current difficulty is <br>"
				+ gameSetup.getGameDifficulty() +"</div></html>");
		changeDifficulty.setBounds(300, 350, 200, 100);
		
		JButton plusButton = new JButton("+");
		plusButton.setBounds(500, 375, 50, 50);
		plusButton.addActionListener(this);
		
		changePOneColor = new JButton("<html> <div style='text-align:center'> "
				+ "Change the Color of PlayerOne's LightCycle <br> "
				+ "Current color is this color </div><html>");
		changePOneColor.setBackground(gameSetup.getPlayerColor(1));
		changePOneColor.setBounds(100, 500, 300, 100);
		changePOneColor.setForeground(Color.BLACK);
		changePOneColor.addActionListener(this);
		
		changePTwoColor = new JButton("<html> <div style='text-align:center'> "
				+ "Change the Color of PlayerTwo's LightCycle <br> "
				+ "Current color is this color </div><html>");
		changePTwoColor.setBackground(gameSetup.getPlayerColor(2));
		changePTwoColor.setBounds(500, 500, 300, 100);
		changePTwoColor.setForeground(Color.BLACK);
		changePTwoColor.addActionListener(this);
		
		JButton startGame = new JButton("START THE GAME");
		startGame.setBounds(100, 610, 700, 50);
		startGame.addActionListener(this);
		
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
		if(e.getActionCommand().equals("Change the Map")){
			
		}
		if(e.getActionCommand().equals("-")){
			if (gameSetup.getGameDifficulty()>0){
				gameSetup.decrementGameDifficulty();
			}
			changeDifficulty.setText("<html> <div style='text-align:center'> "
					+ "Change the Difficulty <br> Current difficulty is <br>"
					+ gameSetup.getGameDifficulty() +"</div></html>");
		}
		if(e.getActionCommand().equals("+")){
			if (gameSetup.getGameDifficulty()<10){
				gameSetup.incrementGameDifficulty();
			}
			changeDifficulty.setText("<html> <div style='text-align:center'> "
					+ "Change the Difficulty <br> Current difficulty is <br>"
					+ gameSetup.getGameDifficulty() +"</div></html>");
		}
		if(e.getActionCommand().equals("<html> <div style='text-align:center'> "
				+ "Change the Color of PlayerOne's LightCycle <br> "
				+ "Current color is this color </div><html>")){
			pOneColor = colorChange(pOneColor);
			changePOneColor.setBackground(pOneColor.getColor());
			if (pOneColor == Colors.BLACK){
				changePOneColor.setForeground(Color.WHITE);
			}
			else{
				changePOneColor.setForeground(Color.BLACK);
			}
			gameSetup.setPOneColor(pOneColor.getColor());
		}
		if(e.getActionCommand().equals("<html> <div style='text-align:center'> "
				+ "Change the Color of PlayerTwo's LightCycle <br> "
				+ "Current color is this color </div><html>")){
			pTwoColor = colorChange(pTwoColor);
			changePTwoColor.setBackground(pTwoColor.getColor());
			if (pTwoColor == Colors.BLACK){
				changePTwoColor.setForeground(Color.WHITE);
			}
			else{
				changePTwoColor.setForeground(Color.BLACK);
			}
			gameSetup.setPTwoColor(pTwoColor.getColor());
		}
		if(e.getActionCommand().equals("START THE GAME")){
			GameMaster master = new GameMaster(gameSetup);
			master.gameInit();
			master.gameStart();
		}
	}
	public Colors colorChange(Colors color){
		switch(color){
			case RED:
				color = Colors.BLUE;
				break;
			case BLUE:
				color = Colors.YELLOW;
				break;
			case YELLOW:
				color = Colors.MAGENTA;
				break;
			case MAGENTA:
				color = Colors.GREEN;
				break;
			case GREEN:
				color = Colors.BLACK;
				break;
			case BLACK:
				color = Colors.RED;
				break;
			default:
				break;
		}
		if (color == pOneColor || color == pTwoColor){
			return colorChange(color);
		}
		else{
			return color;
		}
	}
	public enum Colors{
		RED(Color.RED), BLUE(Color.BLUE), YELLOW(Color.YELLOW), 
		MAGENTA(Color.MAGENTA), GREEN(Color.GREEN), 
		PINK(Color.PINK), BLACK(Color.BLACK);
		private final Color color;
		Colors(Color color){
			this.color = color;
		}
		public Color getColor(){
			return this.color;
		}
	}
}
