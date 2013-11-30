/**
 * @author Aziz Hanna
 *
 */

package styleelements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class StyledButton extends JButton {
	
	public StyledButton(String buttonName){
		new JButton(buttonName);
		setForeground(Color.black);
		setBackground(new Color(250, 215, 180));
		setFont(new Font("Apple Color Emoji", Font.BOLD, 20));
		setText(buttonName);
	}
}
