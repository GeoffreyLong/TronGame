/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * MapHandler Testing Class
 */

package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import game.MapHandler;
import game.Map;

public class MapHandlerTest {
	private Map testMap;
	private final int standardXSize = 75;
	private final int standardYSize = 50;
	private int p1XStart = 0;
	private int p1YStart = 0;
	private int p2XStart = 75;
	private int p2YStart = 50;
	private final int mapSetAdjustment = 3;
	private final int setXStartAdjustment = 1;
	private final int setYStartAdjustment = 2;
	
	@Test
	public void testSetMapZero() {
		MapHandler mapHandler = new MapHandler();
		mapHandler.setMapOne();
		testMap = mapHandler.getMap();
		
		assertEquals(0, testMap.getMapNumber());
		assertEquals(p1XStart+setXStartAdjustment, testMap.getPOneXStart());
		assertEquals(testMap.getYSize()-p1YStart-setYStartAdjustment, testMap.getPOneYStart());
		assertEquals(p2XStart+setXStartAdjustment, testMap.getPTwoXStart());
		assertEquals(testMap.getYSize()-p2YStart-setYStartAdjustment, testMap.getPTwoYStart());
	}
	
	@Test
	public void testSetMapOne() {
		
	}
	
	@Test
	public void testSetMapTwo() {
		
	}
}