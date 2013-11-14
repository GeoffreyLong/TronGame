/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * MapPanel Testing Class
 */

package tests;

import static org.junit.Assert.*;
import java.awt.Color;
import java.awt.Frame;

import org.junit.Test;

import prototype.MapPanel;
import prototype.Map;
import java.awt.Dimension;

public class MapPanelTest {
	
	@Test
	public void testMapPanelGetters() {
		// configure a map to test MapPanel
		Map testMap = new Map();
		testMap.setDefaultMap();
		
		// TODO update!
		MapPanel testMapPanel = new MapPanel(testMap);	
	}
	
}