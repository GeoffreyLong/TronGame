package startscreen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.Frame;
import start.Main;

public class WelcomePanel extends JPanel {
	public WelcomePanel(){
		setBounds(-1,0,Frame.getXSize(),Frame.getYSize()/4);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel welcome = new JLabel("Welcome to Tron");
		welcome.setBounds(start.Frame.getXCenter()-100, 100, 200, 30);
		add(welcome);
		
		setVisible(true);
	}	
}
