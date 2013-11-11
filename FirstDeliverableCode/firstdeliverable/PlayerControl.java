package firstdeliverable;

public class PlayerControl {
	Cycle cycleOne;
	Cycle cycleTwo;
	
	PlayerControl(Cycle cycleOne, Cycle cycleTwo){
		this.cycleOne = cycleOne;
		this.cycleTwo = cycleTwo;
	}
	public void setHeading(int i){
		//cycleTwo methods
		//left
		if (i==65){
			cycleOne.setCurHeading(0);
		}
		//right
		if (i==68){
			cycleOne.setCurHeading(1);
		}
		//up
		if (i==87){
			cycleOne.setCurHeading(3);
		}
		//down
		if (i==83){
			cycleOne.setCurHeading(2);
		}
		
		
		//cycleOne methods
		//left
		if (i==37){
			cycleTwo.setCurHeading(0);
		}
		//right
		if (i==39){
			cycleTwo.setCurHeading(1);
		}
		//up
		if (i==38){
			cycleTwo.setCurHeading(3);
		}
		//down
		if (i==40){
			cycleTwo.setCurHeading(2);
		}
	}
}
