/**
 * @author Aziz Hanna
 * This class allows for faster, simplified implementation of styling for JPanel. Background, border, and layout will
 * be the same for all of our Panels. Thus, they are implemented here.
 */

package styleelements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class StyledPanel extends JPanel {
	/**
	 * Sets the layout, background color, and borders for the JPanel.
	 */
	public StyledPanel(){
		setBorder(BorderFactory.createLineBorder(new Color(0xD4FFFF)));
		setLayout(new MigLayout("","[]","[]"));
		setBackground(Color.black);
	}
}
