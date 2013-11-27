/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * PlayerControl Testing Class
 */

package tests;

import static org.junit.Assert.*;
import gameplay.Cycle;
import gameplay.Cycle.Heading;
import gameplay.PlayerControl;

import java.awt.Color;

import org.junit.Test;

public class PlayerControlTest {
	private int testXPos = 1;
	private int testYPos = 1;
	private Heading testHeading = Heading.UP;
	private Color testColor;
	private boolean testAlive = true;
	
	@Test
	public void testSetHeading() {
		// set two default cycles for testing
		Cycle cycleOne = new Cycle(testXPos, testYPos, testHeading, testAlive, testColor);
		Cycle cycleTwo = new Cycle(testXPos, testYPos, testHeading, testAlive, testColor);
		
		PlayerControl testControl = new PlayerControl(cycleOne, cycleTwo);
		
		// test moving cycle one
		// test moving left
		testControl.setHeading(65);
		assertEquals(testControl.getCycleOne().getCurHeading(), Heading.LEFT);
		// test moving right
		testControl.setHeading(68);
		assertEquals(testControl.getCycleOne().getCurHeading(), Heading.RIGHT);
		// test moving up
		testControl.setHeading(87);
		assertEquals(testControl.getCycleOne().getCurHeading(), Heading.UP);
		// test moving down
		testControl.setHeading(83);
		assertEquals(testControl.getCycleOne().getCurHeading(), Heading.DOWN);
		
		// test moving cycle two
		// test moving left
		testControl.setHeading(37);
		assertEquals(testControl.getCycleTwo().getCurHeading(), Heading.LEFT);
		// test moving right
		testControl.setHeading(39);
		assertEquals(testControl.getCycleTwo().getCurHeading(), Heading.RIGHT);
		// test moving up
		testControl.setHeading(38);
		assertEquals(testControl.getCycleTwo().getCurHeading(), Heading.UP);
		// test moving down
		testControl.setHeading(40);
		assertEquals(testControl.getCycleTwo().getCurHeading(), Heading.DOWN);	
		
	}
	
}