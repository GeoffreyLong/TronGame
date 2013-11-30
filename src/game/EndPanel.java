/**
 * @author: Rishabh Tandon 
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


public class EndPanel extends JPanel implements ActionListener{
    
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
