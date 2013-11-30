/**
 * 
 * 
 * This class is responsible for storing the position and heading of the light cycle.
 * Stores the current cycle heading, position, number, and alive status. It also contains a method that will increment 
 * the cycles position according to the cycle heading.
 * 
 * 
 * @author Rishabh Tandon
 * @version v1.0
 * 
 */


package gameplay;

import java.awt.Color;

public class Cycle {
	
	/*
	 * 
	 * Initializes everything.
	 * 
	 */
  
    private int xPos;
    private int yPos;
    private Heading curHeading;
    public boolean isAlive;
    private Color color;
    
    
    /**
     *
     * Constructor for the class
     * 
     * 
     * @param xPos
     * @param yPos
     * @param curHeading
     * @param isAlive
     * @param color
     * 
     */
  
    public Cycle(int xPos, int yPos, Heading curHeading, boolean isAlive, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.curHeading = curHeading;
        this.isAlive = isAlive;
        this.color = color;
    }
    
    
    /**
     * 
     * 
     * Enum for the directions
     *
     */
        
    public enum Heading{
        LEFT,RIGHT,UP,DOWN;
    }
    
    
    /**
     * 
     * Sets the position of x
     * 
     * @param i
     * 
     */
        
    public void setXPos(int i){
        xPos = i;
    }
       
    
    /**
     * 
     * Gets the x position 
     * 
     * @return
     * 
     */
    
    public int getXPos(){
        return xPos;
    }
      
    
    /**
     * 
     * Sets the y position 
     * 
     * @param i
     * 
     */
    public void setYPos(int i){
        yPos = i;
    }
    
    
    /**
     * 
     * Gets the y position 
     * 
     * @return
     */
        
    public int getYPos(){
        return yPos;
    }
    
    
    /**
     * 
     * Sets the current heading of the light cycle
     * 
     * @param heading Heading of the player
     * 
     */
        
    public void setCurHeading(Heading heading){
        curHeading = heading;
    }
       
    
    /**
     * 
     * Gets the current heading of the light cycle
     * 
     * 
     * @return Heading
     */
    
    public Heading getCurHeading(){
        return curHeading;
    }
    
    
    /**
     * 
     * Sets the color of the light cycle
     * 
     * @param color
     * 
     * @return
     * 
     */
        
    public void setColor(Color color){
        this.color = color;
    }
    
    
    /**
     * 
     * Gets the color of the light cycle
     * 
     * @return Color
     * 
     */
    
    public Color getColor(){
        return color;
    }
    
    
    /**
     * 
     * Travel position 
     * 
     * 
     */
    
    public void travel(){
        switch (getCurHeading()){
            case LEFT:
                xPos--;
                break;
            case RIGHT:
                xPos++;
                break;
            case UP:
                yPos--;
                break;
            case DOWN:
                yPos++;
                break;
            default:
                break;
        }
    }
}