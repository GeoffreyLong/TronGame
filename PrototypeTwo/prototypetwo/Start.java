package prototypetwo;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Start extends JFrame{
	
	public static JFrame frame;
	public static LoginGUI player1;
	public static LoginGUI player2;
	public static int i;
	
	public static LoginGUI[] players = {player1, player2};
	
	private JButton startButton;
	
	public Start(){
		
//		GridBagConstraints c = new GridBagConstraints();
//		
////		JFrame frame = new JFrame("Prototpye II"); 
////		frame.setLayout( new GridLayout(1,2));
//		
		frame = new JFrame("Login"); // creates the JFrame(a window with decorations)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops the program when window is closed
        frame.setLayout(new GridLayout(1,1));
        frame.setSize(800, 600);
        
//        JPanel content = new JPanel(new GridLayout(0, 2));
//        
//        JPanel framePanel = new JPanel(new GridLayout(1,2));
//        JPanel buttonPanel = new JPanel(new GridLayout(1,1));
		
		players[0] = new LoginGUI("PLAYER 1");		
		
		players[1] = new LoginGUI("PLAYER 2");
		
		frame.add(players[0]);
		frame.add(players[1]);
		
//		content.add(player1);
//		content.add(player2);
//		
//		
//		frame.add(content);
		
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
		
		
	}
	
	

}
