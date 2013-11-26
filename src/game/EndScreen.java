/**
 * @author: Rishabh Tandon 
 *
 */


package game;
import javax.swing.*;

import start.FrameDriver;
import user.LoginGUI;

import java.awt.*;
import java.awt.event.*;


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
    	
    	replay = new JButton("Replay?");
    	replay.setBounds(760, 166, 200, 60);
    	replay.addActionListener(this);
    	
    	headToHead = new JButton("Player Matchup Score");
    	headToHead.setBounds(760, 266, 200, 60);
    	headToHead.addActionListener(this);
    	
    	mainMenu = new JButton("Main Menu");
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
		/*
		if(e.getSource() == replay){
		    GameMaster master = new GameMaster();
		    master.gameInit();
	        master.gameStart();
		}*/
    	
    	if(e.getSource() == headToHead){
    		FrameDriver.HeadToHead(LoginGUI.player1.getUserName(), LoginGUI.player2.getUserName());
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
    		winner.setText("Congratulations Player One");
    	}
    	else{
    		winner.setText("Congratulations Player Two");
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
