package startscreen;

import start.Frame;
import user.Player;

public class PlayerTwoPanel extends PlayerPanel{

	public PlayerTwoPanel(Player player) {
		super(player);
		setLocation(Frame.getXSize()/2-1);
		setLabel("PLAYER TWO");
	}

}
