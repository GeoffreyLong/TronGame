package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameplay.Map;
import gameplay.Tile;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MapChooser extends JPanel{
	private Map map;
	private MapHandler mapHandle;
	public MapChooser(MapHandler mapHandle){
		setBounds(0,0,start.Frame.getXSize(), start.Frame.getYSize());
		this.mapHandle = mapHandle;
		setVisible(true);
		setLayout(null);
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
