package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.Frame;

/**
 * @author Geoffrey Long
 *
 * Provides a graphical interface from which the user can select 
 * the map they want to play on.
 */
public class MapChooserPanel extends JPanel{
	private MapHandler mapHandle;
	private SetupPanel setup;
	private int xButtonOffset = 10;
	private int yButtonOffset = 20;
	
	/**
	 * Sets the initial conditions of the panel
	 * @param setup
	 */
	public MapChooserPanel(SetupPanel setup){
		this.setup = setup;
		this.mapHandle = setup.gameSetup.getMapHandler();
		initLayout();
	}
	
	/**
	 * This method will add all the buttons to the map.  These 
	 * buttons will be of the type MapButton and will be added and 
	 * placed dynamically according to the number of maps and the 
	 * size of the maps.  Contains a nested ActionListener which 
	 * will set the map in maphandler to the clicked map and will also 
	 * reset the SetupPanel as the dominant panel. 
	 */
	private void initButtons(){
		for (Map map : mapHandle.maps){
			int displaySize = 4;
			final Map buttonMap = map;
			MapChooserButton chooseMap = new MapChooserButton(buttonMap, displaySize);
			chooseMap.setBounds(xButtonOffset, yButtonOffset, map.getXSize()*displaySize+20, map.getYSize()*displaySize+20);
			xButtonOffset += map.getXSize()*displaySize + 40;
			chooseMap.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					MapHandler.map = buttonMap;
					setVisible(false);
					setup.setVisible(true);
				}
			});
			add(chooseMap);
		}
	}
	
	private void initLayout(){
		setBounds(0,0,main.Frame.getXSize(), main.Frame.getYSize());
		setVisible(true);
		setLayout(null);
		setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		initButtons();
	}
	
	@Override
	/**
	 * Override the paintComponents
	 * @param g  Graphics
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
