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

import game.GameSetup;
import game.Map;
import gameplay.GameMaster;
import gameplay.MapPanel;

public class Frame {
	static JFrame frame = new JFrame();
	private static int xSize;
	private static int ySize;
	private static int xCenter;
	private static int yCenter;
	
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
		xCenter = xSize/2;
		yCenter = ySize/2;
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
	public void addPanel(JPanel panel){
		frame.add(panel);
        frame.validate();
        frame.repaint();
	}
	public static MapPanel start(GameSetup gameSetup, GameMaster gameMaster){
	    MapPanel mapPanel = new MapPanel(gameSetup, gameMaster);
		
	    frame.getContentPane().removeAll();
	    frame.add(mapPanel);
	    frame.validate();
	    frame.repaint();
		
	    return mapPanel;
	}
	public void removeAll(){
		frame.getContentPane().removeAll();
		frame.validate();
		frame.repaint();
	}
	public static void removePanel(JPanel panel){
		frame.remove(panel);
		frame.repaint();
	}
	public static int getYCenter(){
		return xCenter;
	}
	public static int getXCenter(){
		return yCenter;
	}
}
