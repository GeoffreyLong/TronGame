package firstdeliverable;

public class PlayerControl {
	Cycle cycleOne;
	Cycle cycleTwo;
	
	PlayerControl(Cycle cycleOne, Cycle cycleTwo){
		this.cycleOne = cycleOne;
		this.cycleTwo = cycleTwo;
	}
	public void setHeading(int i){
		switch(i){
		case 65:
			cycleOne.setCurHeading(0);
			break;
		case 68:
			cycleOne.setCurHeading(1);
			break;
		case 87:
			cycleOne.setCurHeading(3);
			break;
		case 83:
			cycleOne.setCurHeading(2);
			break;
		case 37:
			cycleTwo.setCurHeading(0);
			break;
		case 39:
			cycleTwo.setCurHeading(1);
			break;
		case 38:
			cycleTwo.setCurHeading(3);
			break;
		case 40:
			cycleTwo.setCurHeading(2);
			break;
		}
	}
}
