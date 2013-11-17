package prototypetwo;

import javax.swing.*;

public class Main2 extends JFrame{
	
	public static JFrame frame;
	
	public static void main(String[] args){
		frame = new JFrame();
		WelcomeScreen panel = new WelcomeScreen();
		frame.add(panel);
		frame.setBounds(0, 0, 600, 600);
		frame.setVisible(true);
	}

}