package start;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Geoffrey Long
 * 
 * The purpose of this class is to provide the frame that will be used 
 * throughout the game.  All panels will be added to and removed from 
 * this frame.
 */
public class Frame extends JFrame {
	private static int xSize;
	private static int ySize;
	
	/**
	 * This is the constructor for the frame.  
	 * It sets the important behaviour for the Frame.
	 */
	public Frame(){
		setTitle("Light Cycles");
		Toolkit tk = Toolkit.getDefaultToolkit();  
		xSize =  ((int) tk.getScreenSize().getWidth());
		ySize =  ((int) tk.getScreenSize().getHeight());  
		setBounds(0,0,xSize,ySize);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
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
	 * without creating a new instance of the frame
	 * @param panel  The panel that is to be added
	 */
	public void addPanel(JPanel panel){
		add(panel);
        validate();
        repaint();
	}
}
