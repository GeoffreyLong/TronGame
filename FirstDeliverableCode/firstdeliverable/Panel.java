@author Rishabh Tandon
 */

package firstdeliverable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel{
	
	private JLabel title;
	private JLabel description;
	
	public Panel(){
		makeComponents();
                makeLayout();
	}
	
	 public Dimension getPreferredSize() {
                return new Dimension(Frame.getXSize(),Frame.getYSize());
        }
	
	private void makeComponents(){
		title = new JLabel("Prototype Demonstration I");
                title.setFont(new Font("Times", Font.BOLD, 37));
                title.setBounds(310, 10, 510, 45);
                
                description = new JLabel("© Copyright of Group 7, ECSE 321, McGill University");
                description.setFont(new Font("Times", Font.BOLD, 10));
                description.setBounds(100, 500, 510, 45);
		
	}
	
	private void makeLayout(){
		setLayout(null);
                add(title);
                add(description);
	}

}
