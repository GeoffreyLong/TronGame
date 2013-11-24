package game;

import java.util.LinkedList;

import gameplay.Tile;

/**
 * @author Geoffrey Long
 *
 * Provides the instantiation of each of the Map objects and the 
 * programmatic interface from which the map will be set and gotten.
 */
public class MapHandler {
	public static Map map;
	Map[] maps = new Map[3];
    public MapHandler(){
    	setMapThree();
    	setMapTwo();
    	setMapOne();
    	setMapDefaults();
    }
    public Map getMap(){
    	return map;
    }
    
    /**
     * Will set the mapNumber as well as any obstacles.  Calls 
     * setMapDefaults.
     */
    public void setMapOne(){
    	setMapDefaults();
    	map.setMapNumber(0);
    	maps[0] = map;
    }
    
    /**
     * Will set the mapNumber as well as any obstacles.  Calls 
     * setMapDefaults.
     */
    public void setMapTwo(){
    	setMapDefaults();
    	map.setMapNumber(1);
    	map.addRectObstacle(15,25,20,30);
    	map.addRectObstacle(50, 60, 20, 30);
    	maps[1] = map;
    }
    
    /**
     * Will set the mapNumber as well as any obstacles.  Calls 
     * setMapDefaults.
     */
    public void setMapThree(){
    	setMapDefaults();
    	map.setMapNumber(2);
    	map.addRectObstacle(5, 25, 5, 25);
    	map.addRectObstacle(30, 45, 20, 30);
    	map.addRectObstacle(50, 70, 25, 45);
    	maps[2] = map;
    }
    
    /**
     * Set the map defaults, these include <ul>
     * <li> Player one start position <li>
     * <li> Player two start position <li>
     * <li> Map size <li>
     * <li> Map border <li></ul>
     * For the provided maps these were all the same
     * so for maps one, two, and three.  Includes a call to setBorder 
     * method within the Map object which will provide a standard rectangular 
     * border as per the map size.
     */
    public void setMapDefaults(){
    	map = new Map(75,50);
    	map.setBorder();
    	map.setPOneXStart(0);
    	map.setPOneYStart(0);
    	map.setPTwoXStart(75);
    	map.setPTwoYStart(50);
    }
    public static void setMap(Map aMap){
    	map = aMap;
    }
}
