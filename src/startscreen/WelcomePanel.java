package startscreen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.Frame;
import start.Main;

public class WelcomePanel extends JPanel {
	private JLabel welcome;
	
	public WelcomePanel(){
		initComponents();
		initLayout();
	}	
	private void initComponents(){
		welcome = new JLabel("Welcome to Tron");
		welcome.setBounds(start.Frame.getXCenter()-100, 100, 200, 30);
	}
	private void initLayout(){
		setBounds(-1,0,Frame.getXSize(),Frame.getYSize()/4);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(welcome);
		
		setVisible(true);
	}
}
