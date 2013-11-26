/**
 * @author: Rishabh Tandon 
 *
 */


package game;
import javax.swing.*;

import java.awt.Dimension;
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
    private JButton headToHead
	
    public EndScreen(){
    	initComponents();
    	initLayout();
    }
    
    public void initComponents(){
    	title = new JLabel("Thank you for playing Tron");
    	title.setFont(new Font("Times", Font.BOLD, 37));
    	title.setBounds(430, 30, 700, 44);
    	
    	winner = new JLabel();
    	winner.setBounds(300, 400, 300, 40);
    	
    	
    	endStatus = new JLabel();
    	endStatus.setBounds(360, 460, 200, 200);
    	
    	replay = new JButton("Replay?");
    	replay.setBounds(560, 166, 200, 60);
    	replay.addActionListener(this);
    	
    	headToHead = new JButton("Player Matchup Score");
    	headToHead.setBounds(560, 266, 200, 60);
    	headToHead.addActionListener(this);
    	
    }
    
    public void initLayout(){
    	setLayout(null);
    	add(title);
    	add(replay);
    	add(endStatus);
    	add(winner);
    	add(headToHead);
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
