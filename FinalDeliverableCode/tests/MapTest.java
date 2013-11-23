/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * Map Testing Class
 */

package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import game.Map;
import gameplay.Tile;

public class MapTest {
	private int testX = 30;
	private int testY = 30;
	private final int mapSetAdjustment = 3;
	
	@Test
	public void testGenericGetters() {
		
		Map map = new Map(testX, testY);
		
		assertEquals(testX + mapSetAdjustment, map.getXSize());
		assertEquals(testX + mapSetAdjustment, map.getYSize());		
	}
	
	@Test	
	public void testMapGetters() {
		/*
		Map map = new Map();
		int mapSize = map.getDefaultMapSize();
		map.setDefaultMap();
		int testX = map.getXSize();
		assertEquals(testX, mapSize);
		int testY = map.getYSize();
		assertEquals(testY, mapSize);
		int[][] testMap = new int[mapSize][mapSize];
		int[][] getMap = map.getMap();
		assertEquals(getMap.length, testMap.length);
		assertEquals(getMap[0].length, testMap[0].length);
		*/
	}
	
}