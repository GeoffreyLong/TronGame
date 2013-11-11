/**
 * @author Geoffrey Long
 * 
 * The purpose of this class is to provide the frame that will be used 
 * throughout the game.  All panels will be added to and removed from 
 * this frame.
 */
package start;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame {
	static JFrame frame = new JFrame();
	private static int xSize;
	private static int ySize;
	
	/**
	 * This is the constructor for the frame.  
	 * It sets the bounds of the frame so that it takes up the entire screen.  
	 * The xSize and ySize will hold the sizes for the x and the y,  
	 * as these are expected to vary from screen to screen.  
	 * Therefore, the panels can retrieve these sizes to construct a dynamic layout.
	 */
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
	
	/**
	 * Getter for the xSize
	 * @return xSize  The size of the frame in the horizontal position
	 */
	public static int getXSize(){
		return xSize;
	}
	/**
	 * Getter for the ySize
	 * @return xSize  The size of the frame in the horizontal position
	 */
	public static int getYSize(){
		return ySize;
	}
	
	/**
	 * This method will allow classes to add panels to the frame
	 * without creating a new instance (and therefore a new frame) of the frame
	 * @param panel  The panel that is to be added
	 */
	public static void addPanel(JPanel panel){
		frame.add(panel);
        frame.validate();
        frame.repaint();
	}
}
