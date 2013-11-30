/**
 * 
 * This class is the Map object.  
 * It allows the game to easily set obstacles and bondaries of the map and retreive that data later. The map itself is set 
 * as a double array of Tile objects. 
 * 
 * 
 * @author Rishabh Tandon
 * @version v1.0
 * 
 */

package game;

import gameplay.Tile;

public class Map {
	 
	/*
	 * 
	 * Everything initialized.
	 * 
	 */
	
	private Tile[][] tiles;
    private int xSize;
    private int ySize;
	private int pOneXStart;
	private int pTwoXStart;
	private int pOneYStart;
	private int pTwoYStart;
	private int mapNumber = -1;
	private final int mapSetAdjustment = 3;
	private final int setXStartAdjustment = 1;
	private final int setYStartAdjustment = 2;
	
	/**
	 * 
	 * Constructor for the class object.
	 * 
	 * @param x
	 * @param y
	 */
	
	public Map(int x, int y){
		this.xSize = x+mapSetAdjustment; // if error change to + 3 (Ashley)
		this.ySize = y+mapSetAdjustment; // if error change to + 3 (Ashley)
		tiles = new Tile[xSize][ySize];
	}
	
	/**
	 * 
	 * Returns the tiles.
	 * 
	 * @return Tile[][]
	 */
	
	public Tile[][] getMap(){
		return tiles;
	}
	
	/**
	 * 
	 * Returns the x Size
	 * 
	 * @return xSize
	 */
	
	public int getXSize(){
		return this.xSize;
	}
	
	/**
	 * 
	 * Sets the x
	 * 
	 * @param x
	 * 
	 * @return
	 * 
	 */
	
	public void setXSize(int x){
		this.xSize = x+mapSetAdjustment;   // if error change to + 2 (Ashley)
	}
	
	/**
	 * 
	 * Gets the y size
	 * 
	 * @return ySize
	 */
	
	public int getYSize(){
		return this.ySize;
	}
	
	
	/**
	 * 
	 * Ses the size of y
	 * 
	 * @param y
	 * 
	 * @return
	 * 
	 * 
	 */
	
	public void setYSize(int y){
		this.ySize = y+mapSetAdjustment;   // if error change to + 2 (Ashley)
	}
	
	/**
	 * 
	 * Gets the player one start x coordinate
	 * 
	 * @return x 
	 * 
	 */
	
	public int getPOneXStart(){
		return pOneXStart;
	}
	
	/**
	 * 
	 * Sets the player one start x coordinate
	 * 
	 * @param x
	 * 
	 * @return
	 * 
	 * 
	 */
	public void setPOneXStart(int x){
		this.pOneXStart = x + setXStartAdjustment;
	}
	
	/**
	 * 
	 * Gets the player two start x coordinate
	 * 
	 * @return x
	 * 
	 * 
	 */
	public int getPTwoXStart(){
		return pTwoXStart;
	}
	
	/**
	 * 
	 * Sets the player two start x coordinate
	 * 
	 * 
	 * @param x
	 * 
	 * @return
	 * 
	 */
	
	public void setPTwoXStart(int x){
		this.pTwoXStart = x + setXStartAdjustment;
	}
	
	
	/**
	 * 
	 * Gets the player one start y coordinate
	 * 
	 * 
	 * @return y
	 * 
	 * 
	 */
	
	
	public int getPOneYStart(){
		return pOneYStart;
	}
	
	
	/**
	 * 
	 * Sets the player one start y coordinate
	 * 
	 * @param y
	 * 
	 * @return
	 * 
	 */
	
	public void setPOneYStart(int y){
		this.pOneYStart = ySize-y-setYStartAdjustment;
	}
	
	
	/**
	 * 
	 * Gets the player two start y coordinate
	 * 
	 * 
	 * @return
	 * 
	 */
	
	public int getPTwoYStart(){
		return pTwoYStart;
	}
	
	/**
	 * 
	 * Sets the player two start y coordinate
	 * 
	 * @param y
	 * 
	 */
	
	public void setPTwoYStart(int y){
		this.pTwoYStart = ySize-y-setYStartAdjustment;
	}
	
	/**
	 * 
	 * Sets the map number depending on the choice
	 * 
	 * @param i
	 * 
	 * @return
	 * 
	 */
	
	public void setMapNumber(int i){
		mapNumber = i;
	}
	
	
	/**
	 * 
	 * Gets the map number
	 * 
	 * @return mapNumber
	 * 
	 */
	
	public int getMapNumber(){
		return mapNumber;
	}
	
	
	/**
	 * 
	 * Adds the rectangular obstacle to the map
	 * 
	 * @param xLo
	 * @param xHi
	 * @param yLo
	 * @param yHi
	 * 
	 * @return
	 * 
	 * 
	 */
	
	public void addRectObstacle(int xLo, int xHi, int yLo, int yHi){
		for (int i=xLo+1; i<xHi+1; i++){
			for (int j=yLo+1; j<yHi+1; j++){
				tiles[i][ySize-j-1] = Tile.WALL;
			}
		}
	}
	
	
	/**
	 * 
	 * Sets the border of the map
	 * 
	 * @return
	 * 
	 */
	
	public void setBorder(){
		for (int x=0; x<xSize; x++){
			for (int y=0; y<ySize; y++){
                if (x==0 || y==0 || x>=xSize-1 || y>=ySize-1){
                    tiles[x][y] = Tile.WALL;
                }
                else{
                    tiles[x][y] = Tile.EMPTY;
                }
			}
		}
	}
}