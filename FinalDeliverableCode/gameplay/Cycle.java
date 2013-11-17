/**
 * @author Rishabh Tandon
 */



package gameplay;

import java.awt.Color;

public class Cycle {
  
    private int xPos;
    private int yPos;
    private Heading curHeading;
    public boolean isAlive;
    private Color color;
  
    public Cycle(int xPos, int yPos, Heading curHeading, boolean isAlive, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.curHeading = curHeading;
        this.isAlive = isAlive;
        this.color = color;
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
        
    public void setColor(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return color;
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