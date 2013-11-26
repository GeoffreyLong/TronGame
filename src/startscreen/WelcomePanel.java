package startscreen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import start.Frame;
import start.Main;
import java.awt.Font;

public class WelcomePanel extends JPanel {
	private JLabel welcome;
	
	public WelcomePanel(){
		setBackground(Color.DARK_GRAY);
		initComponents();
		initLayout();
	}	
	private void initComponents(){
		welcome = new JLabel("Welcome to Tron!!!!");
		welcome.setFont(new Font("Lucida Console", Font.BOLD, 64));
		welcome.setForeground(Color.ORANGE);
		welcome.setBackground(Color.DARK_GRAY);
		welcome.setBounds(start.Frame.getXCenter()-100, 100, 200, 30);
	}
	private void initLayout(){
		setBounds(-1,0,Frame.getXSize(),Frame.getYSize()/6);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(welcome);
		
		setVisible(true);
	}
}
