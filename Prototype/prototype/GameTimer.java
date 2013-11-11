package prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer implements ActionListener {
	private MapPanel mapPanel;
	
	public GameTimer(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		mapPanel.updateMap();
	}

}
