/** 
 * @author Ashley Simpson
 * @version 2013/11/14
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
	private Heading testHeading = Heading.LEFT;
	private boolean testAliveCase = true;
	private Color testColor;
	private int testSetXPos = 2;
	private int testSetYPos = 2;
	private Heading testSetRight = Heading.RIGHT;
	private Heading testSetLeft = Heading.LEFT;
	private Heading testSetUp = Heading.UP;
	private Heading testSetDown = Heading.DOWN;
	private int testSize = 5;
	
	@Test
	public void testCycleGetters() {
		 Cycle testCycle = new Cycle(testXPos, testYPos, testHeading, testAliveCase, testColor.BLACK);
		 assertEquals(testCycle.getXPos(), testXPos);
		 assertEquals(testCycle.getYPos(), testYPos);
		 assertEquals(testCycle.getCurHeading(), testHeading);
		 assertEquals(testCycle.getColor(), testColor.BLACK);
	}
	
	@Test
	public void testCycleSetters() {
		 Cycle testCycle = new Cycle(testXPos, testYPos, testHeading, testAliveCase, testColor);
		 testCycle.setXPos(testSetXPos);
		 assertEquals(testCycle.getXPos(), testSetXPos);
		 testCycle.setYPos(testSetYPos);
		 assertEquals(testCycle.getYPos(), testSetYPos);
		 testCycle.setCurHeading(testSetRight);
		 assertEquals(testCycle.getCurHeading(), testSetRight);
	}
	
	@Test
	public void testTravel() {
		Cycle testCycle = new Cycle(testXPos, testYPos, testHeading, testAliveCase, testColor);
		
		// test movement left
		testXPos = testXPos - testSize;
		testCycle.setCurHeading(testSetLeft);
		testCycle.travel(testSize);
		assertEquals(testCycle.getXPos(), testXPos);
		
		// test movement right
		testXPos = testXPos + testSize;
		testCycle.setCurHeading(testSetRight);
		testCycle.travel(testSize);
		assertEquals(testCycle.getXPos(), testXPos);
		
		// test movement up
		testYPos = testYPos - testSize;
		testCycle.setCurHeading(testSetUp);
		testCycle.travel(testSize);
		assertEquals(testCycle.getYPos(), testYPos);
		
		// test movement down
		testYPos = testYPos + testSize;
		testCycle.setCurHeading(testSetDown);
		testCycle.travel(testSize);
		assertEquals(testCycle.getYPos(), testYPos);
	}
}
