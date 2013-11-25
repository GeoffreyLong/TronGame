package gameplay;

import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import start.Frame;

public class ExplosionPanel extends JPanel{
	Cycle[] cycles;
	private int increment;
	private int explosionCount;
	private List<Color> explosionColors;
	private int buffer;
	
	public ExplosionPanel(Cycle[] cycles, int increment, int buffer){
		this.cycles = cycles;
		this.increment = increment;
		this.buffer = buffer;
		
		setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		setVisible(true);
		setLayout(null);
	}
	public void updatePanel(int explosionCount, List<Color> explosionColors){
		this.explosionCount = explosionCount;
		this.explosionColors = explosionColors;
		repaint();
	}
	@Override
	public void paintComponent(Graphics g){
		for (Cycle cycle : cycles){
			if (!cycle.isAlive){
				for (Color color : explosionColors){
					g.setColor(color);
					g.fillOval(cycle.getXPos()*increment+buffer -((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
							cycle.getYPos()*increment+buffer -((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
							(int)(explosionCount*Math.random()), 
							(int)(explosionCount*Math.random()));
				}
			}
		}
	}
}
