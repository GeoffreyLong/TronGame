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

public class Frame extends JFrame {
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
		setTitle("Light Cycles");
		Toolkit tk = Toolkit.getDefaultToolkit();  
		xSize =  ((int) tk.getScreenSize().getWidth());
		ySize =  ((int) tk.getScreenSize().getHeight());  
		xCenter = xSize/2;
		yCenter = ySize/2;
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
	 * without creating a new instance (and therefore a new frame) of the frame
	 * @param panel  The panel that is to be added
	 * @param constraint 
	 * @param location The panel's location in the MigLayout
	 */
	public void addPanel(JPanel panel){
		add(panel);
        validate();
        repaint();
	}
	/*public static MapPanel start(GameSetup gameSetup, GameMaster gameMaster){
	    //MapPanel mapPanel = new MapPanel(gameSetup, gameMaster);
		
	    FrameDriver.hideAll();
	    frame.add(mapPanel);
	    frame.validate();
	    frame.repaint();
		
	    return mapPanel;
	}*/
	public void removeAll(){
		getContentPane().removeAll();
		validate();
		repaint();
	}
	public static int getYCenter(){
		return xCenter;
	}
	public static int getXCenter(){
		return yCenter;
	}
}
