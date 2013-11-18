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
	private Tile[][] theMap;
	public MapChooser(final Map map){
		this.map = map;
		setBounds(0,0,start.Frame.getXSize(), start.Frame.getYSize());
		setVisible(true);
		setLayout(null);
		
		for (int i = 0; i<map.getNumberOfMaps(); i++){
			MapButton chooseMap = new MapButton(map, i);
			chooseMap.setBounds(10+100*i, 20, 80, 20);
			chooseMap.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String button = e.getActionCommand();
					int number = Integer.valueOf(button.split(" ")[0]);
					map.setMap(number);
				}
			});
			add(chooseMap);
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
