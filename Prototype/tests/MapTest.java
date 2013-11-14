/** 
 * @author Ashley Simpson
 * @version 2013/11/10
 * Map Testing Class
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import prototype.Map;

public class MapTest {
	
	@Test
	public void testDefaultMapSize() {
		Map map = new Map();
		int mapSize = map.getDefaultMapSize();
		map.setDefaultMap();
		assertEquals(mapSize, map.getDefaultMapSize()); 
	}
	
	@Test	
	public void testMapGetters() {
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
	}
}