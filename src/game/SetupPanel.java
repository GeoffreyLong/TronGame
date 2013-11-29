package game;

import gameplay.GameMaster;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.FrameDriver;
import user.Player;
import styleelements.StyledButton;
import net.miginfocom.swing.MigLayout;

/**
 * @author Geoffrey Long
 * 
 * Provides a graphical interface between the user and the GameSetup.  
 * This allows users to set the game difficulty, the map, and their cycle colors.
 */
public class SetupPanel extends JPanel implements ActionListener{
	Player playerOne;
	Player playerTwo;
	private int xCenter;
	private int yCenter;
	private int xSize;
	private int ySize;
	public GameSetup gameSetup;
	private JLabel changeDifficulty;
	private Colors pOneColor = Colors.RED;
	private Colors pTwoColor = Colors.BLUE;
	private StyledButton changePOneColor;
	private StyledButton changePTwoColor;
	private StyledButton startGame;
	private StyledButton plusButton;
	private JLabel pageTitle;
	private StyledButton changeMap;
	private StyledButton minusButton;
	
	/**
	 * Sets the initial conditions of the MapPanel
	 * 
	 * @param playerOne
	 * @param playerTwo
	 * @param gameSetup
	 */
	public SetupPanel(Player playerOne, Player playerTwo, GameSetup gameSetup){
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.gameSetup = gameSetup;
		
		xCenter = start.Frame.getXCenter();
		yCenter = start.Frame.getYCenter();
		xSize = start.Frame.getXSize();
		ySize = start.Frame.getYSize();
		
		initComponents();
		initLayout();
	}

	private void initComponents(){				
		pageTitle = new JLabel(playerOne.getUserName() + " VS " + playerTwo.getUserName());
		pageTitle.setForeground(new Color(142,229,238));
		pageTitle.setFont(new Font("Apple Color Emoji", Font.BOLD, 20));
		
		startGame = new StyledButton("START THE GAME");
		startGame.addActionListener(this);
		

		changePOneColor = new StyledButton("<html> <div style='text-align:center'> "
				+ "Change the Color of PlayerOne's LightCycle <br> "
				+ "Current color is the button color </div><html>");
		changePOneColor.setOpaque(true);
		changePOneColor.setBackground(gameSetup.getPlayerColor(1));
		changePOneColor.setForeground(Color.BLACK);
		changePOneColor.addActionListener(this);
		
		minusButton = new StyledButton("-");
		minusButton.addActionListener(this);
				
		changeMap = new StyledButton("Change the Map");
		changeMap.addActionListener(this);
		
		changeDifficulty = new JLabel("<html> <div style='text-align:center'> "
				+ "Change the Difficulty <br> Current difficulty is <br>"
				+ gameSetup.getGameDifficulty() +"</div></html>");
		changeDifficulty.setForeground(new Color(142,229,238));
		changeDifficulty.setFont(new Font("Apple Color Emoji", Font.BOLD, 13));

		plusButton = new StyledButton("+");
		plusButton.addActionListener(this);
		
		changePTwoColor = new StyledButton("<html> <div style='text-align:center'> "
				+ "Change the Color of PlayerTwo's LightCycle <br> "
				+ "Current color is the button color </div><html>");
		changePTwoColor.setOpaque(true);
		changePTwoColor.setBackground(gameSetup.getPlayerColor(2));
		changePTwoColor.setForeground(Color.BLACK);
		changePTwoColor.addActionListener(this);
		
	}
	private void initLayout(){
		setBackground(Color.black);
		setBounds(0,0,xSize,ySize);
		setLayout(new MigLayout("", "[]50[]", "[]50[]50[]50[]50[]100[]100[][][]"));
		
		add(pageTitle, "cell 0 0 6 1,alignx center,aligny top");
		add(changeMap, "cell 1 2 5 1,alignx center,aligny top");
		add(minusButton, "cell 1 3,alignx right, grow");
		
		
		add(changeDifficulty, "cell 2 3 3 1,alignx center,shrinkx,growy");
		
		add(plusButton, "cell 5 3,alignx left, grow");
		add(changePOneColor, "cell 2 5,grow");
		
	
		add(changePTwoColor, "cell 4 5,grow");
		add(startGame, "cell 2 7 3 2,grow");
		
		setVisible(true);
	}
	/**
	 * Maps the button presses to the corresponding program action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Change the Map")){
			FrameDriver.mapChooser();
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
				+ "Current color is the button color </div><html>")){
			pOneColor = colorChange(pOneColor);
			changePOneColor.setBackground(pOneColor.getColor());
			gameSetup.setPOneColor(pOneColor.getColor());
			repaint();
		}
		if(e.getActionCommand().equals("<html> <div style='text-align:center'> "
				+ "Change the Color of PlayerTwo's LightCycle <br> "
				+ "Current color is the button color </div><html>")){
			pTwoColor = colorChange(pTwoColor);
			changePTwoColor.setBackground(pTwoColor.getColor());
			gameSetup.setPTwoColor(pTwoColor.getColor());
			repaint();
		}
		if(e.getActionCommand().equals("START THE GAME")){
			GameMaster master = new GameMaster(gameSetup);
			master.gameStart();
		}
	}
	
	/**
	 * Will switch the color of the cycles as a function of the 
	 * current player colors.
	 * 
	 * @param color
	 * @return color
	 */
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
	
	/**
	 * @author Geoffrey Long
	 * 
	 * Provide an easy means by which cycle colors can be compared.
	 */
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

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
		pageTitle.setText(playerOne.getUserName() + " VS " + playerTwo.getUserName());
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
		pageTitle.setText(playerOne.getUserName() + " VS " + playerTwo.getUserName());
	}
}
