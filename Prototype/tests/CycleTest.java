/** 
 * @author Ashley Simpson
 * @version 2013/11/10
 * Cycle Testing Class
 */

package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import prototype.Cycle;
import prototype.Cycle.Heading;

public class CycleTest {
	private int testXPos = 1;
	private int testYPos = 1;
	private Heading testHeading;
	private boolean testAliveCase = true;
	private Color testColor;
	private int testSetXPos = 2;
	private int testSetYPos = 2;
	private int testSetHeading = 2;
	
	@Test
	public void testCycleGetters() {
		 Cycle testCycle = new Cycle(testXPos, testYPos, testHeading, testAliveCase, testColor);
		 assertEquals(testCycle.getXPos(), testXPos);
		 assertEquals(testCycle.getYPos(), testYPos);
		 assertEquals(testCycle.getCurHeading(), testHeading);
		 //assertEquals(testCycle.getPlayerNum(), testPlayerNum);
	}
	
	@Test
	public void testCycleSetters() {
		 Cycle testCycle = new Cycle(testXPos, testYPos, testHeading, testAliveCase, testColor);
		 testCycle.setXPos(testSetXPos);
		 assertEquals(testCycle.getXPos(), testSetXPos);
		 testCycle.setYPos(testSetYPos);
		 assertEquals(testCycle.getYPos(), testSetYPos);
		 //testCycle.setCurHeading(testSetHeading);
		 assertEquals(testCycle.getCurHeading(), testSetHeading);
		 }
}