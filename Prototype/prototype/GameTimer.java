/**
 * @author Geoffrey Long
 * 
 * This class will make a call to MapPanel.updateMap().  
 * The method call will be done every 33 ms (30 FPS). 
 * In effect, this class is the game engine.
 */

package prototype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer implements ActionListener {
	private MapPanel mapPanel;
	
	/**
	 * Instantiate the mapPanel as a class variable.  
	 * Performing this action allows mapPanel to be used every time 
	 * the timer times out.
	 * @param mapPanel
	 */
	public GameTimer(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	/**
	 * Calls mapPanel.updateMap() every 33 ms.  
	 * The method update map paints the map with the new player position.  
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		mapPanel.updateMap();
	}

}
