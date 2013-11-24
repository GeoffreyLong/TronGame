/**
 * @author Rishabh Tandon
 */

package game;

import gameplay.Tile;

public class Map {
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
	
	
	public Map(int x, int y){
		this.xSize = x+mapSetAdjustment; // if error change to + 3 (Ashley)
		this.ySize = y+mapSetAdjustment; // if error change to + 3 (Ashley)
		tiles = new Tile[xSize][ySize];
	}
	public Tile[][] getMap(){
		return tiles;
	}
	public int getXSize(){
		return this.xSize;
	}
	public void setXSize(int x){
		this.xSize = x+mapSetAdjustment;   // if error change to + 2 (Ashley)
	}
	public int getYSize(){
		return this.ySize;
	}
	public void setYSize(int y){
		this.ySize = y+mapSetAdjustment;   // if error change to + 2 (Ashley)
	}
	public int getPOneXStart(){
		return pOneXStart;
	}
	public void setPOneXStart(int x){
		this.pOneXStart = x + setXStartAdjustment;
	}
	public int getPTwoXStart(){
		return pTwoXStart;
	}
	public void setPTwoXStart(int x){
		this.pTwoXStart = x + setXStartAdjustment;
	}
	public int getPOneYStart(){
		return pOneYStart;
	}
	public void setPOneYStart(int y){
		this.pOneYStart = ySize-y-setYStartAdjustment;
	}
	public int getPTwoYStart(){
		return pTwoYStart;
	}
	public void setPTwoYStart(int y){
		this.pTwoYStart = ySize-y-setYStartAdjustment;
	}
	public void setMapNumber(int i){
		mapNumber = i;
	}
	public int getMapNumber(){
		return mapNumber;
	}
	public void addRectObstacle(int xLo, int xHi, int yLo, int yHi){
		for (int i=xLo+1; i<xHi+1; i++){
			for (int j=yLo+1; j<yHi+1; j++){
				tiles[i][ySize-j-1] = Tile.WALL;
			}
		}
	}
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