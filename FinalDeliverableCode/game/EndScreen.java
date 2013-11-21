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
	
    public EndScreen(int pOneWins, int pTwoWins, int gamesPlayed){
		this.pOneWins = pOneWins;
		this.pTwoWins = pTwoWins;
		this.gamesPlayed = gamesPlayed;
    	makeComponents();
    	makeLayout();
    }
    
    public void makeComponents(){
    	title = new JLabel("Thank you for playing Tron");
    	title.setFont(new Font("Times", Font.BOLD, 37));
    	title.setBounds(430, 30, 700, 44);
    	
    	winner = new JLabel();
    	if (pOneWins > pTwoWins){
    		winner.setText("Congradulations Player One");
    	}
    	else{
    		winner.setText("Congradulations Player Two");
    	}
    	winner.setBounds(300, 100, 300, 40);
    	
    	endStatus = new JLabel("<html>"
    			+ "Games Played : "
    			+ gamesPlayed + "<br>"
    			+ "Player One Wins : "
    			+ pOneWins + "<br>"
    			+ "Player Two Wins : "
    			+ pTwoWins);
    	endStatus.setBounds(360, 160, 200, 200);
    	
    	replay = new JButton("Replay?");
    	replay.setBounds(560, 166, 200, 60);
    	replay.addActionListener(this);
    	
    }
    
    public void makeLayout(){
    	setLayout(null);
    	add(title);
    	add(replay);
    	add(endStatus);
    	add(winner);
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

}
