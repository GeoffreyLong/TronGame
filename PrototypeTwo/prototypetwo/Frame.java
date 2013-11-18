/**
 * @author Rishabh Tandon
 */
 
package prototypetwo;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame{
	
    private static int xSize;
    private static int ySize;
    static JFrame frame;
    private static int playerOneWins = 0;
    private static int playerTwoWins = 0;
	
	public Frame(){
	    frame = new JFrame();
	    frame.setLayout(null);
  	    Toolkit tk = Toolkit.getDefaultToolkit();  
        xSize = ((int) tk.getScreenSize().getWidth());  
        ySize = ((int) tk.getScreenSize().getHeight());
        frame.setBounds(0,0,xSize,ySize);  
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static int getXSize(){
	    return xSize;
	}
	
	public static int getYSize(){
	    return ySize;
	}
	
	public static void addPanel(JPanel panel){
	    frame.add(panel);
        frame.validate();
	}
	public static MapPanel start(Map map){
	    MapPanel mapPanel = new MapPanel(map);
		mapPanel.setBounds(0,0, getXSize(), getYSize());
	    frame.getContentPane().removeAll();
	    frame.add(mapPanel);
	    frame.validate();
	    frame.repaint();
		
	    return mapPanel;
	}
	public static void endGame(int winCondition){
	    frame.getContentPane().removeAll();
	    switch (winCondition){
	        case 1:
		    playerOneWins++;
		    break;
		case 2:
		    playerTwoWins++;
		    break;
		default:
		    break;
	    }
	    
	    EndScreen end = new EndScreen(winCondition, playerOneWins, playerTwoWins);
	    frame.add(end);
	    frame.validate();
	    frame.repaint();
	}
	public static void removeAll(){
		frame.getContentPane().removeAll();
		frame.validate();
		frame.repaint();
	}
	public static void removePanel(JPanel panel){
		frame.remove(panel);
		frame.repaint();
	}
}
