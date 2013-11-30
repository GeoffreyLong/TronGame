package user;

/**
 * @author Geoffrey Long
 * 
 * Provides an object for storing user information such as username and player number
 */
public class Player {
	private int playerNumber;
	private String userName;
	
	/**
	 * Constructor to start the player
	 * @param playerNumber  The number of the player, either 1 or 2
	 */
	public Player(int playerNumber){
		this.userName = "anonymous";
		this.playerNumber = playerNumber;
	}
	
	/**
	 * Set the username of the player
	 * @param userName  The player's username
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}	
	
	/**
	 * Return the player's username
	 * @return userName  The player's username
	 */
	public final String getUserName(){
		return this.userName;
	}
	
	/**
	 * Return the number of the player, either 1 or 2
	 * @return playerNumber  The number of the player
	 */
	public int getPlayerNumber(){
		return this.playerNumber;
	}
}
