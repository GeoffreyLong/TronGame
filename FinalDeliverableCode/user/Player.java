package user;

public class Player {
	private int playerNumber;
	private String userName;
	
	public Player(int playerNumber){
		this.playerNumber = playerNumber;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}	
	public String getUserName(){
		return this.userName;
	}
	public int getPlayerNumber(){
		return this.playerNumber;
	}
}
