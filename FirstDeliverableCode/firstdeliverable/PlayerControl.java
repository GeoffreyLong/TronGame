package firstdeliverable;

import firstdeliverable.Cycle.Heading;

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
			cycleOne.setCurHeading(Heading.LEFT);
			break;
		case 68:
			cycleOne.setCurHeading(Heading.RIGHT);
			break;
		case 87:
			cycleOne.setCurHeading(Heading.UP);
			break;
		case 83:
			cycleOne.setCurHeading(Heading.DOWN);
			break;
		case 37:
			cycleTwo.setCurHeading(Heading.LEFT);
			break;
		case 39:
			cycleTwo.setCurHeading(Heading.RIGHT);
			break;
		case 38:
			cycleTwo.setCurHeading(Heading.UP);
			break;
		case 40:
			cycleTwo.setCurHeading(Heading.DOWN);
			break;
		}
	}
}
