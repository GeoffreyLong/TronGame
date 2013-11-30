package menuscreen;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Frame;

/**
 * @author Geoffrey Long
 * 
 * Provides a title label for the start screen
 */
public class WelcomePanel extends JPanel {
	private JLabel welcome;
	
	/**
	 * Initialize the panel with the appropriate labels
	 */
	public WelcomePanel(){
		initComponents();
		initLayout();
	}	
	private void initComponents(){
		welcome = new JLabel("Welcome to Tron!!!!");
		welcome.setFont(new Font("Apple Color Emoji", Font.BOLD, 64));
		welcome.setForeground(new Color(142,229,238));

	}
	private void initLayout(){
		setBackground(Color.black);
		setBounds(0,0,Frame.getXSize(),Frame.getYSize()/6);
		setBorder(BorderFactory.createLineBorder(new Color(0xD4FFFF)));
		
		add(welcome, "cell 0 0, alignx center, growy");
		
		setVisible(true);
	}
}
