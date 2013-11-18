package game;

import gameplay.Map;
import gameplay.Tile;

import javax.swing.JButton;

public class MapButton extends JButton{
	Tile[][] tiles;
	Map map;
	MapButton(Map map, int i){
		tiles = map.getMap(i);
		this.map = map;
	}
}
