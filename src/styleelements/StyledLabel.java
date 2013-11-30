/**
 * @author Aziz Hanna
 *
 *  This class allows for faster, simplified implementation of styling for JLabels.
 */

package styleelements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StyledLabel extends JLabel {
/**
 * Initializes the JLabel, sets default colors and font.
 * 
 * @param labelName
 * @param fontSize
 */
	public StyledLabel(String labelName, int fontSize){
		new JLabel();
		setForeground(new Color(240, 255, 255));
		setBackground(Color.BLACK);
		setFont(new Font("Apple Color Emoji", Font.BOLD, fontSize));
		setText(labelName);
	}
}
