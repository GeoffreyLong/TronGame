/**
 * @author: Rishabh Tandon 
 *
 */


package game;
import java.awt.Dimension;
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
    
    public EndPanel(int playerOneWins, int playerTwoWins, int gamesPlayed){
    	this.pOneWins = playerOneWins;
    	this.pTwoWins = playerTwoWins;
    	this.gamesPlayed = gamesPlayed;
    	initComponents();
    	initLayout();
    }
    
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
    
    public Dimension getPreferredSize() {
    	return new Dimension(main.Frame.getXSize(),main.Frame.getYSize());
    }
	
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
				FrameDriver.HeadToHead(LoginPanel.player1, LoginPanel.player2);
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
   			FrameDriver.TopTen();
    	}	
    }
}
