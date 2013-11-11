/**
 * @author Rishabh Tandon
 */



package firstdeliverable;

public class Cycle {
  
        private int xPos;
        private int yPos;
        private Heading curHeading;
        private int playerNum;
        public boolean isAlive;
  
        public Cycle(int xPos, int yPos, Heading curHeading, int playerNum, boolean isAlive){
                this.xPos = xPos;
                this.yPos = yPos;
                this.curHeading = curHeading;
                this.playerNum = playerNum;
                this.isAlive = isAlive;
        }
        
        public enum Heading{
        	LEFT,RIGHT,UP,DOWN;
        }
        
        public void setXPos(int i){
                xPos = i;
        }
        
        public int getXPos(){
                return xPos;
        }
        
        public void setYPos(int i){
                yPos = i;
        }
        
        public int getYPos(){
                return yPos;
        }
        
        public void setCurHeading(Heading heading){
                curHeading = heading;
        }
        
        public Heading getCurHeading(){
                return curHeading;
        }
        
        public int getPlayerNum(){
                return playerNum;
        }
        public void travel(int size){
			switch (getCurHeading()){
				case LEFT:
					xPos-=size;
					break;
				case RIGHT:
					xPos+=size;
					break;
				case UP:
					yPos-=size;
					break;
				case DOWN:
					yPos+=size;
					break;
				default:
					break;
			}
        }
}
