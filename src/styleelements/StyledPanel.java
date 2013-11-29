package styleelements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class StyledPanel extends JPanel {
	
	public StyledPanel(){
		setBorder(BorderFactory.createLineBorder(new Color(0xD4FFFF)));
		setLayout(new MigLayout("","[]","[]"));
		setBackground(Color.black);
	}
}
