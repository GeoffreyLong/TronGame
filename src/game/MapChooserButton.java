package game;

import gameplay.Tile;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * @author Geoffrey Long
 *
 * Provides a graphical way to represent the maps.  
 * This class provides a button that is painted with one of the 
 * map choices.
 */
public class MapChooserButton extends JButton{
	Tile[][] tiles;
	private int xSize;
	private int ySize;
	private int displaySize = 2;
	
	/**
	 * This constructor will set the size of the MapButton as a function 
	 * of the parameter displaySize and the x and y attributes of the 
	 * parameter map.
	 * @param map
	 * @param displaySize
	 */
	MapChooserButton(Map map, int displaySize){
		this.displaySize = displaySize;
		tiles = map.getMap();
		xSize = map.getXSize();
		ySize = map.getYSize();
		setBounds(0,0,xSize*displaySize+20,ySize*displaySize+20);
		setVisible(true);
	}
	
	/**
	 * This will paint the map in the style of the 
	 * map which was provided as a parameter in the constructor
	 * @param Graphics g
	 */
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
