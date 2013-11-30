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
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.FrameDriver;
import main.Main;
import net.miginfocom.swing.MigLayout;
import styleelements.StyledButton;
import styleelements.StyledLabel;
import styleelements.StyledPanel;
import user.LoginPanel;


public class EndPanel extends StyledPanel implements ActionListener{
	
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
    	title = new StyledLabel("Thank you for playing Tron", 37);
    	
		winner = new StyledLabel("Congratulations", 18);
    	
    	endStatus = new StyledLabel("End of Game Stats", 18);
    	
    	replay = new StyledButton("Replay?");
    	replay.addActionListener(this);
    	
    	headToHead = new StyledButton("Matchup Stats");
    	headToHead.addActionListener(this);
    	
    	topTen = new StyledButton("Show Top Ten");
    	topTen.addActionListener(this);
    	
    	mainMenu = new StyledButton("Main Menu");
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
    		winner.setText("Congratulations " + Main.playerOne.getUserName() + "!!!");
    	}
    	
    	else{
    		winner.setText("Congratulations " + Main.playerTwo.getUserName() + "!!!");
    	}
    	
    	endStatus.setOpaque(true);
    	winner.setOpaque(true);
    	endStatus.setText("<html>"
    			+ "Number of... <br>"
    			+ "Games Played : "
    			+ gamesPlayed + "<br>"
    			+ "Player One Wins : "
    			+ pOneWins + "<br>"
    			+ "Player Two Wins : "
    			+ pTwoWins);
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
    	setLayout(new MigLayout("","[grow][grow]", "[]200px[][][][][]"));
    	add(title, "cell 0 0 2 1,alignx center,growy");
    	add(replay, " cell 1 3, alignx center,grow");
    	add(endStatus, "cell 0 5,alignx left,grow");
    	add(winner, "cell 0 4, alignx left,grow");
    	add(headToHead, "cell 1 4, alignx center,grow");
    	add(mainMenu, "cell 1 5, alignx center,grow");
    	add(topTen, "cell 1 6, alignx center,grow");
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
