/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * Frame Testing Class
 */

package tests;

import static org.junit.Assert.*;

import java.awt.Toolkit;

import org.junit.Test;

import prototype.Frame;

public class FrameTest {
	private int testXSize = 5;
	private int testYSize = 5;
	
	public FrameTest() {
		Frame frame = new Frame();
		Toolkit tk = Toolkit.getDefaultToolkit();  
        testXSize = ((int) tk.getScreenSize().getWidth());  
        testYSize = ((int) tk.getScreenSize().getHeight());
	}
	
	@Test
	public void testGetters() {
		assertEquals(Frame.getXSize(), testXSize);
		assertEquals(Frame.getYSize(), testYSize);
	}	
}