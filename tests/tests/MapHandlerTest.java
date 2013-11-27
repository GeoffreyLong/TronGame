/** 
 * @author Ashley Simpson
 * @version 2013/11/23
 * MapHandler Testing Class
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Map;
import game.MapHandler;
import gameplay.Tile;

public class MapHandlerTest {
	private Map testMap;
	private final int testXSize = 40;
	private final int testYSize = 45;
	private int p1XStart = 0;
	private int p1YStart = 0;
	private int p2XStart = 75;
	private int p2YStart = 50;
	private final int setXStartAdjustment = 1;
	private final int setYStartAdjustment = 2;
	private final int mapSetAdjustment = 3;
	
	@Test
	public void testSetMapOne() {
		MapHandler mapHandler = new MapHandler();
		mapHandler.setMapOne();
		testMap = mapHandler.getMap();
		Tile[][] tiles = testMap.getMap();
		
		assertEquals(0, testMap.getMapNumber());
		assertEquals(p1XStart+setXStartAdjustment, testMap.getPOneXStart());
		assertEquals(testMap.getYSize()-p1YStart-setYStartAdjustment, testMap.getPOneYStart());
		assertEquals(p2XStart+setXStartAdjustment, testMap.getPTwoXStart());
		assertEquals(testMap.getYSize()-p2YStart-setYStartAdjustment, testMap.getPTwoYStart());
		
		// check that map is clean
		for (int i=1; i<75; i++){
			for (int j=1; j<50; j++){
				assertEquals(Tile.EMPTY, tiles[i][testMap.getYSize()-j-1]);
			}
		}
	}
	
	@Test
	public void testSetMapTwo() {
		MapHandler mapHandler = new MapHandler();
		mapHandler.setMapTwo();
		testMap = mapHandler.getMap();
		Tile[][] tiles = testMap.getMap();
		
		// test by making sure obstacles have been created
		// check first obstacle, 15, 25, 20, 30
		for (int i=16; i<26; i++){
			for (int j=21; j<31; j++){
				assertEquals(Tile.WALL, tiles[i][testMap.getYSize()-j-1]);
			}
		}
		// check second obstacle, 50, 60, 20, 30
		for (int i=51; i<61; i++){
			for (int j=21; j<31; j++){
				assertEquals(Tile.WALL, tiles[i][testMap.getYSize()-j-1]);
			}
		}
	}
	
	@Test
	public void testSetMapThree() {
		MapHandler mapHandler = new MapHandler();
		mapHandler.setMapThree();
		testMap = mapHandler.getMap();
		Tile[][] tiles = testMap.getMap();
		
		// test by making sure obstacles have been created
		// check first obstacle, 15, 25, 20, 30
		for (int i=6; i<26; i++){
			for (int j=6; j<26; j++){
				assertEquals(Tile.WALL, tiles[i][testMap.getYSize()-j-1]);
			}
		}
		// check second obstacle, 50, 60, 20, 30
		for (int i=31; i<46; i++){
			for (int j=21; j<31; j++){
				assertEquals(Tile.WALL, tiles[i][testMap.getYSize()-j-1]);
			}
		}
		// check second obstacle, 50, 60, 20, 30
		for (int i=51; i<71; i++){
			for (int j=26; j<46; j++){
				assertEquals(Tile.WALL, tiles[i][testMap.getYSize()-j-1]);
			}
		}
	}
	
	@Test
	public void testSettingMap() {
		Map testMap = new Map(testXSize, testYSize);
		testMap.setBorder();
		MapHandler.setMap(testMap);
		Map checkMap = MapHandler.map;
		Tile[][] tiles = checkMap.getMap();
		
		// check that the map is the correct size 
		for (int i=1; i<testXSize; i++){
			for (int j=1; j<testYSize; j++){
				assertEquals(Tile.EMPTY, tiles[i][checkMap.getYSize()-j-1]);
			}
		}
	}
}