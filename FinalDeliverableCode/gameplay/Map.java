/**
 * @author Rishabh Tandon
 */

package gameplay;

public class Map {
  
        private Tile[][] tiles;
        private int numberOfMaps = 1;
        private int xSize;
        private int ySize;
    	private final int pOneXStart = 0;
    	private final int pTwoXStart = 0;
    	private final int pOneYStart = 0;
    	private final int pTwoYStart = 0;
  
        public Tile[][] getMap(){
                return tiles;
        }
        
        public void setDefaultMap(){
                MapOne mapOne = new MapOne();
                mapOne.setMap();
                tiles = mapOne.getMap();
                xSize = mapOne.getXSize();
                ySize = mapOne.getYSize();
        }
        
        public int getXSize(){
                return this.xSize;
        }
        
        public int getYSize(){
                return this.ySize;
        }
        public int getNumberOfMaps(){
        	return numberOfMaps;
        }
        public Tile[][] getMap(int i){
        	Map map;
        	switch(i){
        	case 0:
        		setDefaultMap();
        		break;
        	}
        	return tiles;
        }
        public void setMap(int i){
        	switch(i){
        	case 0:
        		setDefaultMap();
        		break;
        	}
        }
        
    	public int getPOneXStart(){
    		return pOneXStart;
    	}
    	public int getPTwoXStart(){
    		return pTwoXStart;
    	}
    	public int getPOneYStart(){
    		return pOneYStart;
    	}
    	public int getPTwoYStart(){
    		return pTwoYStart;
    	}
        
        private class MapOne{
        	private Tile[][] tiles;
        	
        	//Add one to the sizes for the border
        	private final int X_SIZE = 76;
        	private final int Y_SIZE = 51;
        	private final int pOneXStart = 1;
        	private final int pTwoXStart = X_SIZE-1;
        	private final int pOneYStart = Y_SIZE-1;
        	private final int pTwoYStart = 1;
        	
        	public MapOne(){
        		setMap();
        	}
        	private void setMap(){
        		tiles = new Tile[X_SIZE][Y_SIZE];
        		for (int x=0; x<X_SIZE; x++){
        			for (int y=0; y<Y_SIZE; y++){
                        if (x==0 || y==0 || y>=Y_SIZE-1 || x>=X_SIZE-1){
                            tiles[x][y] = Tile.WALL;
                        }
                        else{
                            tiles[x][y] = Tile.EMPTY;
                        }
        			}
        		}
        	}
        	public Tile[][] getMap(){
        		return tiles;
        	}
        	public int getXSize(){
        		return this.X_SIZE;
        	}
        	public int getYSize(){
        		return this.Y_SIZE;
        	}
        	public int getPOneXStart(){
        		return pOneXStart;
        	}
        	public int getPTwoXStart(){
        		return pTwoXStart;
        	}
        	public int getPOneYStart(){
        		return pOneYStart;
        	}
        	public int getPTwoYStart(){
        		return pTwoYStart;
        	}
        }
}
