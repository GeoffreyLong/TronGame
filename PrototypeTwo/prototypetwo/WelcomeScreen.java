package prototypetwo;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener{
	
	private JLabel title;
	
	
	public WelcomeScreen(){
		makeComponents();
		makeLayout();
	}
	
	private void makeComponents(){
		
		title = new JLabel("Protoype II");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setBounds(170, 10, 510, 45);
		
		
		
	}
	
	private void makeLayout(){
		setLayout(null);
		add(title);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}



}
