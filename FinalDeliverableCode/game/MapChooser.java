package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameplay.Map;
import gameplay.Tile;

import javax.swing.JButton;
import javax.swing.JPanel;

import prototype.Frame;

public class MapChooser extends JPanel{
	private Map map;
	private MapHandler mapHandle;
	private SetupPanel setup;
	public MapChooser(SetupPanel setup){
		this.setup = setup;
		setBounds(0,0,start.Frame.getXSize(), start.Frame.getYSize());
		this.mapHandle = setup.gameSetup.getMapHandler();
		setVisible(true);
		setLayout(null);
		setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		setButtons();
	}
	private void setButtons(){
		for (Map map : mapHandle.maps){
			final Map buttonMap = map;
			MapButton chooseMap = new MapButton(buttonMap);
			chooseMap.setBounds(10+100*map.getMapNumber(), 20, 120, 120);
			chooseMap.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					MapHandler.map = buttonMap;
					setup.setVisible(true);
				}
			});
		}
	}
		/*
		for (int i = 0; i<map.getNumberOfMaps(); i++){

			chooseMap.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String button = e.getActionCommand();
					int number = Integer.valueOf(button.split(" ")[0]);
					map.setMap(number);
				}
			});
			add(chooseMap);
		}*/
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
