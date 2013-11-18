
package prototypetwo;

public class Map {
  
        private int[][] map;
        private int DEFAULT_MAP_SIZE = 500;
        private int xSize;
        private int ySize;
  
        public int[][] getMap(){
                return map;
        }
        
        private void setBorder(int height, int width){
          
                 for (int i = 0; i<height; i++){
                        for (int j = 0; j<width; j++){
                                if (i<=4|| j<=4 || j>=width-5 || i>=height-5){
                                        map[i][j] = 1;
                                }
                                else{
                                        map[i][j] = 0;
                                }
                        }
                  }
          
          
        }
        
        public void setDefaultMap(){
                map = new int[DEFAULT_MAP_SIZE][DEFAULT_MAP_SIZE];
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
        
        public int getDefaultMapSize() {
        	return this.DEFAULT_MAP_SIZE;  			
        }
  
  
}
