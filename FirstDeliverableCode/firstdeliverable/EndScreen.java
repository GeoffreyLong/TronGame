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
	
    public EndScreen(){
    	makeComponents();
    	makeLayout();
    }
    
    public void makeComponents(){
    	title = new JLabel("Thank you for playing Tron");
    	title.setFont(new Font("Times", Font.BOLD, 37));
    	title.setBounds(300, 30, 500, 40);
    	
    }
    
    public void makeLayout(){
    	setLayout(null);
    	
    }
    
    public Dimension getPreferredSize() {
		
    }
	
    @Override
    public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
		
    }

}
