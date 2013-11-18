package prototypetwo;

import javax.swing.*;

public class Main2 extends JFrame{
	
	public static void main(String[] args){
		Frame frame = new Frame();
		WelcomeScreen panelOne = new WelcomeScreen();
		panelOne.setBounds(0,0,Frame.getXSize()/2, Frame.getYSize());
		WelcomeScreen panelTwo = new WelcomeScreen();
		panelTwo.setBounds(Frame.getXSize()/2,0,Frame.getXSize()/2, Frame.getYSize());
		Frame.addPanel(panelOne);
		Frame.addPanel(panelTwo);
	}

}