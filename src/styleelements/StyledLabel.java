/**
 * @author Aziz Hanna
 *
 */

package styleelements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StyledLabel extends JLabel {

	public StyledLabel(String labelName, int fontSize){
		setForeground(new Color(240, 255, 255));
		setBackground(Color.BLACK);
		setFont(new Font("Apple Color Emoji", Font.BOLD, fontSize));
		setText(labelName);
	}
}
