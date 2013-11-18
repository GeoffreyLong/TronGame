/**
 * @author Rishabh Tandon
 */

package gameplay;

public class Map {
	private Tile[][] tiles;
    private int xSize;
    private int ySize;
	private int pOneXStart;
	private int pTwoXStart;
	private int pOneYStart;
	private int pTwoYStart;
	
	
	public Map(int x, int y){
		this.xSize = x;
		this.ySize = y;
		tiles = new Tile[x+1][y+1];
	}
	public Tile[][] getMap(){
		return tiles;
	}
	public int getXSize(){
		return this.xSize;
	}
	public void setXSize(int x){
		this.xSize = x;
	}
	public int getYSize(){
		return this.ySize;
	}
	public void setYSize(int y){
		this.ySize = y;
	}
	public int getPOneXStart(){
		return pOneXStart;
	}
	public void setPOneXStart(int x){
		this.pOneXStart = x;
	}
	public int getPTwoXStart(){
		return pTwoXStart;
	}
	public void setPTwoXStart(int x){
		this.pTwoXStart = x;
	}
	public int getPOneYStart(){
		return pOneYStart;
	}
	public void setPOneYStart(int y){
		this.pOneYStart = y;
	}
	public int getPTwoYStart(){
		return pTwoYStart;
	}
	public void setPTwoYStart(int y){
		this.pTwoYStart = y;
	}
	public void setRectObstacle(int xLo, int xHi, int yLo, int yHi){
		for (int i=xLo; i<xHi; i++){
			for (int j=yLo; j<yHi; j++){
				tiles[i][j] = Tile.WALL;
			}
		}
	}
	public void setBorder(){
		for (int x=0; x<xSize; x++){
			for (int y=0; y<ySize; y++){
                if (x==0 || y==0 || y>=xSize || x>=ySize){
                    tiles[x][y] = Tile.WALL;
                }
                else{
                    tiles[x][y] = Tile.EMPTY;
                }
			}
		}
	}
}