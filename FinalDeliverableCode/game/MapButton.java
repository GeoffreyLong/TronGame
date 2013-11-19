package game;

import java.awt.Color;
import java.awt.Graphics;

import gameplay.Map;
import gameplay.Tile;

import javax.swing.JButton;

public class MapButton extends JButton{
	Tile[][] tiles;
	private int xSize;
	private int ySize;
	private int displaySize = 2;
	
	MapButton(Map map, int displaySize){
		this.displaySize = displaySize;
		tiles = map.getMap();
		xSize = map.getXSize();
		ySize = map.getYSize();
		setBounds(0,0,xSize*displaySize+20,ySize*displaySize+20);
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (int i=0; i<xSize; i++){
			for (int j=0; j<ySize; j++){
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
				g.fillRect(i*displaySize+10, j*displaySize+10, displaySize, displaySize);
			}
		}
	}
}
