/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * Frame Testing Class
 */

package tests;

import static org.junit.Assert.*;

import java.awt.Toolkit;

import org.junit.Test;

import start.Frame;

public class FrameTest {
	private Frame frame;
	private int testXSize;
	private int testYSize;
	private int testXCenter;
	private int testYCenter;
	
	public FrameTest() {
		frame = new Frame();
		Toolkit tk = Toolkit.getDefaultToolkit();  
        testXSize = ((int) tk.getScreenSize().getWidth());  
        testYSize = ((int) tk.getScreenSize().getHeight());
        testXCenter = testXSize/2;
        testYCenter = testYSize/2;
	}
	
	//simply test the getters of frame
	@Test
	public void testGetters() {
		assertEquals(testXSize, Frame.getXSize());
		assertEquals(testYSize, Frame.getYSize());
		assertEquals(testYCenter, Frame.getXCenter());
		assertEquals(testXCenter, Frame.getYCenter());
	}	
}