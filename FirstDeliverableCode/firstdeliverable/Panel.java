@author Rishabh Tandon
 */

package firstdeliverable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel{
	
	private JLabel title;
	
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
		
	}
	
	private void makeLayout(){
		setLayout(null);
                add(title);
	}

}
