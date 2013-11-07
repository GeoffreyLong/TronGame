/**
 * @author Rishabh Tandon
 */
 
package firstdeliverable;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame{
	
	private static int xSize;
        private static int ySize;
        static JFrame frame;
	
	public Frame(){
		frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();  
                xSize = ((int) tk.getScreenSize().getWidth());  
                ySize = ((int) tk.getScreenSize().getHeight());
	}
	
	public static int getXSize(){
		
		return xSize;
	}
	
	public static int getYSize(){
		
		return ySize;
	}
	
	public static void addPanel(JPanel panel){
		frame.add(panel);
                frame.pack();
                frame.validate();
		
	}
}
