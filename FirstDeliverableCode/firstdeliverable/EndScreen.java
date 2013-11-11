/**
 * @author: Rishabh Tandon 
 *
 */


package firstdeliverable;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;


public class EndScreen extends JPanel implements ActionListener{
    
    private JLabel title;
    private JButton replay;
	
    public EndScreen(){
    	makeComponents();
    	makeLayout();
    }
    
    public void makeComponents(){
    	title = new JLabel("Thank you for playing Tron");
    	title.setFont(new Font("Times", Font.BOLD, 37));
    	title.setBounds(300, 30, 500, 40);
    	
    	replay = new JButton("Replay");
    	replay.setBounds(440, 166, 200, 60);
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
		
    }

}
