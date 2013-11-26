package startscreen;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.JPanel;

import start.Frame;
import start.Main;
import java.awt.Font;

public class WelcomePanel extends JPanel {
	private JLabel welcome;
	private JLabel instructions;
	
	public WelcomePanel(){
		setBackground(Color.DARK_GRAY);
		initComponents();
		initLayout();
	}	
	private void initComponents(){
		welcome = new JLabel("Welcome to Tron!!!!");
		welcome.setFont(new Font("Apple Color Emoji", Font.BOLD, 64));
		welcome.setForeground(new Color(153, 51, 51));
		welcome.setBackground(Color.DARK_GRAY);
		
		instructions = new JLabel("Press \u2191 button when you are ready to go...");
		instructions.setFont(new Font("Batang", Font.BOLD | Font.ITALIC, 27));
		instructions.setForeground(new Color(255, 255, 153));
		instructions.setToolTipText("\u2191");
	}
	private void initLayout(){
		setBounds(-1,0,Frame.getXSize(),Frame.getYSize()/6);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(welcome, "cell 0 0, alignx center, growy");
		add(instructions, "cell 1 0,alignx center,growy");
		
		setVisible(true);
	}
}
