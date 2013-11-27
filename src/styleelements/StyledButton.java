/**
 * 
 */
package styleelements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

/**
 * @author Aziz
 *
 */
public class StyledButton extends JButton {
	
	public StyledButton(String buttonName){
		new JButton(buttonName);
		setForeground(Color.black);
		setBackground(new Color(255, 215, 0));
		setFont(new Font("Lucida Grande", Font.BOLD, 20));
		setText(buttonName);
	}
}
