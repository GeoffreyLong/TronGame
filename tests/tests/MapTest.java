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
	private int testX = 30; // random set value
	private int testY = 40; // random set value
	private int testChangeX = 10;
	private int testChangeY = 20;
	private int testChangeMap = 3;
	private int testP1XStart = 0;
	private int testP1YStart = 0;
	private int testP2XStart = 45;
	private int testP2YStart = 45;
	private int xBorderSize = 10; // tests for border creation
	private int yBorderSize = 10;
	private int xLowRectangleTest = 2; // parameters for creating a 5 x 5 square
	private int xHighRectangleTest = 7;
	private int yLowRectangleTest = 2;
	private int yHighRectangleTest = 7;
	private final int mapSetAdjustment = 3;
	private final int setXStartAdjustment = 1;
	private final int setYStartAdjustment = 2;
	
	// simple tests for the getters
	@Test
	public void testGenericGetters() {
		
		Map map = new Map(testX, testY);
		Tile[][] testTiles = map.getMap();
		
		assertEquals(testX + mapSetAdjustment, map.getXSize());
		assertEquals(testY + mapSetAdjustment, map.getYSize());
		assertEquals(testX + mapSetAdjustment, testTiles.length);
		assertEquals(testY + mapSetAdjustment, testTiles[0].length);
		assertEquals(-1, map.getMapNumber());
	}
	
	// simple tests for the setters
	@Test	
	public void testGenericSetters() {
		
		Map map = new Map(testX, testY);
		
		// test setting Map number
		map.setMapNumber(testChangeMap);
		assertEquals(testChangeMap, map.getMapNumber());
		// test setting PlayerOne X Start
		map.setPOneXStart(testP1XStart);
		assertEquals(testP1XStart + setXStartAdjustment, map.getPOneXStart());
		// test setting PlayerOne Y Start
		map.setPOneYStart(testP1YStart);
		assertEquals((testY - testP1YStart - setYStartAdjustment) + mapSetAdjustment, map.getPOneYStart());
		// test setting PlayerTwo X Start
		map.setPTwoXStart(testP2XStart);
		assertEquals(testP2XStart + setXStartAdjustment, map.getPTwoXStart());
		// test setting PlayerTwo Y Start
		map.setPTwoYStart(testP2YStart);
		assertEquals((testY - testP2YStart - setYStartAdjustment) + mapSetAdjustment, map.getPTwoYStart());
		// test setting X Size
		map.setXSize(testChangeX);
		assertEquals(testChangeX + mapSetAdjustment, map.getXSize());		
		// test setting Y Size
		map.setYSize(testChangeY);
		assertEquals(testChangeY + mapSetAdjustment, map.getYSize());
	}
	
	// testing that the map border is set correctly
	@Test
	public void testSetMapBorder() {
		// check to see if a border is created properly
		
		Map map = new Map(xBorderSize, yBorderSize);
		map.setBorder();
		Tile[][] tiles = map.getMap();
		
		int x = 0;
		int y = 0;
		
		// check top of map
		while (x < map.getXSize()) {
			assertEquals(Tile.WALL, tiles[x][y]);
			x++;
		}
		x--;
		// check right of map
		while (y < map.getYSize()) {
			assertEquals(Tile.WALL, tiles[x][y]);
			y++;
		}
		y--;
		// check bottom of map
		while (x >= 0) {
			assertEquals(Tile.WALL, tiles[x][y]);
			x--;
		}
		x++;
		// check left of map
		while (y >= 0) {
			assertEquals(Tile.WALL, tiles[x][y]);
			y--;
		}
	}
	
	// test adding a rectangle to the map
	@Test
	public void testAddRectangle() {
		Map map = new Map(xBorderSize, yBorderSize);
		
		// set some pre specified map
		map.setBorder();
		map.addRectObstacle(xLowRectangleTest, xHighRectangleTest, yLowRectangleTest, yHighRectangleTest);
		Tile[][] tiles = map.getMap();
		
		// make sure the reactangle is actually considered a WALL
		for (int i=xLowRectangleTest+1; i<xHighRectangleTest+1; i++){
			for (int j=yLowRectangleTest+1; j<yHighRectangleTest+1; j++){
				assertEquals(Tile.WALL, tiles[i][map.getYSize()-j-1]);
			}
		}
		
	}
}