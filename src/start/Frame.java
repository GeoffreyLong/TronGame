package start;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame {
	static JFrame frame = new JFrame();
	private int xSize;
	private int ySize;
	
	public Frame(){
		frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();  
		xSize = ((int) tk.getScreenSize().getWidth());  
		ySize = ((int) tk.getScreenSize().getHeight());  
		frame.setBounds(0,0,xSize,ySize);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
