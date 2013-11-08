package firstdeliverable;

public class Cycle {
  
  private int xPos;
  private int yPos;
  private int curHeading;
  private int playerNum;
  public boolean isAlive;
  
  public Cycle(int xPos, int yPos, int curHeading, int playerNum, boolean isAlive){
    this.xPos = xPos;
    this.yPos = yPos;
    this.curHeading = curHeading;
    this.playerNum = playerNum;
    this.isAlive = isAlive;
  }

}
