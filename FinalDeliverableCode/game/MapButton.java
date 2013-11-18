package game;

import java.awt.Color;
import java.awt.Graphics;

import gameplay.Map;
import gameplay.Tile;

import javax.swing.JButton;

public class MapButton extends JButton{
	Tile[][] tiles;
	Map map;
	MapButton(Map map, int i){
		tiles = map.getMap(i);
		this.map = map;
		setBounds(0,0,110,110);
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (int i=0; i<100; i+=5){
			for (int j=0; j<100; j+=5){
				switch(tiles[i][j]){
					case WALL:
						g.setColor(Color.BLACK);
						break;
					case EMPTY:
						g.setColor(Color.WHITE);
						break;
					default:
						break;
				}
				g.fillRect(i, j, 1, 1);
			}
		}
	}
}
