/**
 * @author Rishabh Tandon
 */

package gameplay;

public class Map {
  
        private Tile[][] tiles;
        private int DEFAULT_MAP_SIZE = 500;
        private int xSize;
        private int ySize;
        private int numberOfMaps = 1;
  
        public Tile[][] getMap(){
                return tiles;
        }
        
        private void setBorder(int height, int width){
          
                 for (int i = 0; i<height; i++){
                        for (int j = 0; j<width; j++){
                                if (i<=4|| j<=4 || j>=width-5 || i>=height-5){
                                        tiles[i][j] = Tile.WALL;
                                }
                                else{
                                        tiles[i][j] = Tile.EMPTY;
                                }
                        }
                  }
          
          
        }
        
        public void setDefaultMap(){
                tiles = new Tile[DEFAULT_MAP_SIZE][DEFAULT_MAP_SIZE];
                setBorder(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE);
                this.xSize = DEFAULT_MAP_SIZE;
                this.ySize = DEFAULT_MAP_SIZE;
          
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
        public int getDefaultMapSize() {
        	return this.DEFAULT_MAP_SIZE;  			
        }
        
        private class MapTwo{
        	private Tile[][] tiles;
        	private final int X_SIZE = 75;
        	private final int Y_SIZE = 50;
        	public MapTwo(){
        		setMap();
        	}
        	private void setMap(){
        		tiles = new Tile[X_SIZE][Y_SIZE];
        		
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
        }
}
