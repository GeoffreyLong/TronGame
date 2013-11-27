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
		setBackground(new Color(204, 204, 255));
		setFont(new Font("Lucida Grande", Font.BOLD, 13));
		setText(buttonName);
	}
}
