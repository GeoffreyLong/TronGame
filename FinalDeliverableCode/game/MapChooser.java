package game;

import gameplay.Map;

import javax.swing.JPanel;

public class MapChooser extends JPanel{
	private Map map;
	public MapChooser(Map map){
		this.map = map;
		setBounds(0,0,start.Frame.getXSize(), start.Frame.getYSize());
		setVisible(true);
		setLayout(null);
	}
}
