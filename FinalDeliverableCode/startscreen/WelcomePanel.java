package startscreen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.Main;
import firstdeliverable.Frame;

public class WelcomePanel extends JPanel {
	public WelcomePanel(){
		setBounds(0,0,Main.frame.getXSize(),Main.frame.getYSize()/4);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
	}	
}
