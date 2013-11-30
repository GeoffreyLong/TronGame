/**
 * 
 * This class is an extension of Jpanel.  It will output all the data regarding the game statistics including player wins, 
 * who won, and the number of games played.  It will allow the users to replay the game (which sends them to the SetupPanel 
 * already populated with the previous game settings), return to the Main Menu, view the latest top ten or see the Head to Head history.
 * 
 * 
 * @author Rishabh Tandon 
 * @version v1.0
 *
 */


package game;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.FrameDriver;
import main.Main;
import styleelements.StyledButton;
import user.LoginPanel;
import user.Player;


public class EndPanel extends JPanel implements ActionListener{
	
	/*
	 * 
	 * Everything initialized
	 * 
	 */
    
    private JLabel title;
    private JButton replay;
    private int gamesPlayed;
    private int pOneWins;
    private int pTwoWins;
    private JLabel winner;
    private JLabel endStatus;
    private JButton headToHead;
    private JButton topTen;
    private JButton mainMenu;
    
    /**
     * 
     * Constructor for the class,
     * This initializes the components and the layout of the JPanel and sets the player 1 wins and player 2 wins
     * 
     * 
     * @param playerOneWins
     * @param playerTwoWins
     * @param gamesPlayed
     * 
     * @return 
     */
    
    public EndPanel(int playerOneWins, int playerTwoWins, int gamesPlayed){
    	this.pOneWins = playerOneWins;
    	this.pTwoWins = playerTwoWins;
    	this.gamesPlayed = gamesPlayed;
    	initComponents();
    	initLayout();
    }
    
    /**
     * This method initializes the components of the JPanel.
     * All components have already been initialized, and this method initializes them, sets their bounds and their font
     * 
     * @return 
     * 
     */
    
    public void initComponents(){
    	title = new JLabel("Thank you for playing Tron");
    	title.setFont(new Font("Times", Font.BOLD, 37));
    	title.setBounds(430, 30, 700, 44);
    	
    	winner = new JLabel();
    	winner.setBounds(360, 200, 300, 40);
    	
    	endStatus = new JLabel();
    	endStatus.setBounds(360, 250, 200, 200);
    	
    	replay = new StyledButton("Replay?");
    	replay.setBounds(760, 166, 200, 60);
    	replay.addActionListener(this);
    	
    	headToHead = new StyledButton("Matchup Stats");
    	headToHead.setBounds(760, 266, 200, 60);
    	headToHead.addActionListener(this);
    	
    	topTen = new StyledButton("Show Top Ten");
    	topTen.setBounds(760, 366, 200, 60);
    	topTen.addActionListener(this);
    	
    	mainMenu = new StyledButton("Main Menu");
    	mainMenu.setBounds(760, 366, 200, 60);
    	mainMenu.addActionListener(this); 
    	
    	addWinLogic();
    }
    
    
    /**
     * 
     * This method adds the win logic to the class.
     * 
     * @return
     * 
     * 
     */
    
    public void addWinLogic(){
    	if (pOneWins > pTwoWins){
    		winner.setBounds(360, 200, 300, 40);
    		winner.setText("Congratulations " + Main.playerOne.getUserName() + "!!!");
    	}
    	
    	else{
    		winner.setBounds(360, 200, 300, 40);
    		winner.setText("Congratulations " + Main.playerTwo.getUserName() + "!!!");
    	}
    	
    	endStatus.setOpaque(true);
    	winner.setOpaque(true);
    	endStatus = new JLabel();
    	endStatus.setText("<html>"
    			+ "Number of... <br>"
    			+ "Games Played : "
    			+ gamesPlayed + "<br>"
    			+ "Player One Wins : "
    			+ pOneWins + "<br>"
    			+ "Player Two Wins : "
    			+ pTwoWins);
    	endStatus.setBounds(360, 250, 200, 200);
    	add(endStatus);
    	add(winner);
    }
    
    
    /**
     * 
     * This method initializes the layout of the JPanel.
     * All the components have been initialized and set, all this method does is add them to the layout and the layout is set as null
     * 
     * 
     * @return
     * 
     */
    public void initLayout(){
    	setLayout(null);
    	add(title);
    	add(replay);
    	add(endStatus);
    	add(winner);
    	add(headToHead);
    	add(mainMenu);
    	add(topTen);
    }
    
    /**
     * This method gets the preferred size
     * 
     * @return Dimension
     * 
     */
    
    public Dimension getPreferredSize() {
    	return new Dimension(main.Frame.getXSize(),main.Frame.getYSize());
    }
    
    /*
     * The ActionListener for the button clicks.
     *  ---> The replay button, replays the game
     *  ---> The main menu button navigates back to the main menu
     *  ---> The top ten button shows the latest top ten
     *  ---> The match up stats buttons shows the latest head to head scores
     *  
     * This also takes care that only one window of each type can be opened at a time. A for loop is run to get a list of all open windows
     *  
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
	    // TODO Auto-generated method stub
		if(e.getActionCommand().equals("Replay?")){
			FrameDriver.startGameSetup();
		    winner.setText("");
		    endStatus.setText("");
		}
    	
    	if(e.getActionCommand().equals("Matchup Stats")){
    		if(!LoginPanel.player1.equals("") && !LoginPanel.player2.equals("")){
				
    			FrameDriver.HeadToHead(LoginPanel.player1, LoginPanel.player2);boolean open = false;
				Window[] allWindows = Window.getWindows();
			    
				for(int i = 0; i < allWindows.length; i++){
					if(allWindows[i].equals(FrameDriver.headToHead)){
						allWindows[i].dispose();
						FrameDriver.HeadToHead(LoginPanel.player1, LoginPanel.player2);
						open = true;
					}
				}
				
				if(!open){
					FrameDriver.HeadToHead(LoginPanel.player1, LoginPanel.player2);
				}
				
				winner.setText("");
			    endStatus.setText("");
    		}
			
			else{
				JFrame frame = new JFrame("Error");
				JOptionPane.showMessageDialog(frame, "Both players must be logged in to view head to head score");
			}
    	}
	
    	if(e.getActionCommand().equals("Main Menu")){
    		winner.setText("");
 		    endStatus.setText("");
    		FrameDriver.mainMenu();
    	}
    	
    	if(e.getActionCommand().equals("Show Top Ten")){
   		 	winner.setText("");
   		 	endStatus.setText("");

   		 	
   		 boolean open = false;
			Window[] allWindows = Window.getWindows();
		    
			for(int i = 0; i < allWindows.length; i++){
				if(allWindows[i].equals(FrameDriver.topTen)){
					allWindows[i].dispose();
					FrameDriver.TopTen();
					open = true;
				}
			}
			
			if(!open){
				FrameDriver.TopTen();
			}
			
    	}	
    }
}
