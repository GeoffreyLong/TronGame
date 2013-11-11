/**
 * @author: Rishabh Tandon 
 *
 */


package prototype;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;


public class EndScreen extends JPanel implements ActionListener{
    
    private JLabel title;
    private JButton replay;
    private int winCondition;
    private int playerOneWins;
    private int playerTwoWins;
	
    public EndScreen(int winCondition, int playerOneWins, int playerTwoWins){
    	this.winCondition = winCondition;
    	makeComponents();
    	makeLayout();
    }
    
    public void makeComponents(){
    	title = new JLabel("Thank you for playing Tron");
    	title.setFont(new Font("Times", Font.BOLD, 37));
    	title.setBounds(430, 30, 700, 44);
    	
    	replay = new JButton("Replay?");
    	replay.setBounds(560, 166, 200, 60);
    	replay.addActionListener(this);
    	
    }
    
    public void makeLayout(){
    	setLayout(null);
    	add(title);
    	add(replay);
    	
    }
    
    public Dimension getPreferredSize() {
	return new Dimension(Frame.getXSize(),Frame.getYSize());
    }
	
    @Override
    public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
	
	if(e.getSource() == replay){
	    GameMaster master = new GameMaster();
	    master.gameInit();
            master.gameStart();
	}
    }

}
