package styleelements;

/**
 * @author Aziz Hanna
 *
 * This class allows for faster, simplified implementation of styling for JButtons.
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class StyledButton extends JButton {
	/**
	 * Initialize the JButton with @param buttonName
	 */
	public StyledButton(String buttonName){
		new JButton(buttonName);
		setForeground(Color.black);
		setBackground(new Color(250, 215, 180));
		setFont(new Font("Apple Color Emoji", Font.BOLD, 20));
		setText(buttonName);
	}
}
