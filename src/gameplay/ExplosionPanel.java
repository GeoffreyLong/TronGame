package gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import main.Frame;

/**
 * @author Geoffrey Long
 * 
 * Provides a way to extract the explosion effect from the game mechanics.
 */
public class ExplosionPanel extends JPanel{
	private Cycle[] cycles;
	private int increment;
	private int explosionCount;
	private List<Color> explosionColors;
	private Cycle curCycle;
	
	/**
	 * Create the panel and save parameters as class variables.
	 * 
	 * @param cycles  The cycles that were used in the GamePanel
	 * @param increment  A number of pixels which serves as a sizing reference
	 */
	public ExplosionPanel(Cycle[] cycles, int increment){
		this.cycles = cycles;
		this.increment = increment;
		
		setBounds(0,0,Frame.getXSize(), Frame.getYSize());
		setVisible(true);
		setLayout(null);
	}
	
	/**
	 * A method that gets called according to a timer.  It serves to 
	 * update the state of the explosion.
	 * 
	 * @param explosionCount  The current state of the explosion
	 * @param explosionColors  A list of all the colors to be added to the explosion
	 */
	public void updatePanel(int explosionCount, List<Color> explosionColors){
		this.explosionCount = explosionCount;
		this.explosionColors = explosionColors;
		for (Cycle cycle : cycles){
			if (!cycle.isAlive){
				curCycle = cycle;
				paintImmediately(0,0,Frame.getXSize(),Frame.getYSize());
			}
		}
	}
	@Override
	/**
	 * Paint in a randomized pattern of ovals at the location of the cycle crash.
	 * 
	 * @param g  Graphics
	 */
	public void paintComponent(Graphics g){
		for (Color color : explosionColors){
			g.setColor(color);
			g.fillOval(GamePanel.getXOffset() + curCycle.getXPos()*increment -((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
					GamePanel.getYOffset() + curCycle.getYPos()*increment -((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
					(int)(explosionCount*Math.random()), 
					(int)(explosionCount*Math.random()));
		}
	}
}
