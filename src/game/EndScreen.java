/**
 * @author: Rishabh Tandon 
 *
 */


package game;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.FrameDriver;
import start.Main;
import styleelements.StyledButton;
import user.LoginGUI;
import user.Player;


public class EndScreen extends JPanel implements ActionListener{
    
    private JLabel title;
    private JButton replay;
    private int gamesPlayed;
    private int pOneWins;
    private int pTwoWins;
    private JLabel winner;
    private JLabel endStatus;
    private JButton headToHead;
    private JButton mainMenu;
    
    public EndScreen(){
    	initComponents();
    	initLayout();
    }
    
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
    	
    	headToHead = new StyledButton("Player Matchup Score");
    	headToHead.setBounds(760, 266, 200, 60);
    	headToHead.addActionListener(this);
    	
    	mainMenu = new StyledButton("Main Menu");
    	mainMenu.setBounds(760, 366, 200, 60);
    	mainMenu.addActionListener(this);    	
    }
    
    public void initLayout(){
    	setLayout(null);
    	add(title);
    	add(replay);
    	add(endStatus);
    	add(winner);
    	add(headToHead);
    	add(mainMenu);
    }
    
    public Dimension getPreferredSize() {
    	return new Dimension(start.Frame.getXSize(),start.Frame.getYSize());
    }
	
    @Override
    public void actionPerformed(ActionEvent e) {
	    // TODO Auto-generated method stub
		if(e.getSource() == replay){
			System.out.println("sdf");
		    FrameDriver.startGameSetup();
		}
    	
    	if(e.getSource() == headToHead){
    		FrameDriver.HeadToHead(LoginGUI.player1, LoginGUI.player2);
    	}
    }
    public void setPOneWins(int i){
    	pOneWins = i;
    }
    public void setPTwoWins(int i){
    	pTwoWins = i;
    }
    public void setGamesPlayed(int i){
    	gamesPlayed = i;
    }
    public void updatePanel(){
    	if (pOneWins > pTwoWins){
    		winner.setText("Congratulations " + Main.playerOne.getUserName() + "!!!");
    	}
    	else{
    		winner.setText("Congratulations " + Main.playerTwo.getUserName() + "!!!");
    	}
    	
    	endStatus.setText("<html>"
    			+ "Number of... <br>"
    			+ "Games Played : "
    			+ gamesPlayed + "<br>"
    			+ "Player One Wins : "
    			+ pOneWins + "<br>"
    			+ "Player Two Wins : "
    			+ pTwoWins);
    }
}
