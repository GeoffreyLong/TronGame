package prototype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import prototype.Cycle.Heading;

public class MapPanel extends JPanel implements KeyListener {
	private int[][] map;
	private int xSize;
	private int ySize;
	PlayerControl cont;
	Cycle[] cycles;
	private boolean gameStart = true;
	private Timer explosionTimer;
	private int explosionCount;
	private int xOffset;
	private int yOffset;
	
	public MapPanel(Map map){
		this.map = map.getMap();
		this.xSize = map.getXSize();
		this.ySize = map.getYSize();
		
		xOffset = (Frame.getXSize() - xSize) / 2;
		yOffset = (Frame.getYSize() - ySize) / 2;
		
		Cycle cycleOne = new Cycle(100, 400, Heading.RIGHT, 1, true, Color.RED);
		Cycle cycleTwo = new Cycle(400, 400, Heading.LEFT, 2, true, Color.BLUE);
		cycles = new Cycle[]{cycleOne, cycleTwo};
		cont = new PlayerControl(cycleOne, cycleTwo);
		
		addKeyListener(this);
		setVisible(true);
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	public Dimension getPreferredSize() {
        return new Dimension(Frame.getXSize(),Frame.getYSize());
	}
	
	public void updateMap(){
		this.requestFocusInWindow();
		for (Cycle cycle : cycles){
			cycle.travel(5);
			if (map[cycle.getXPos()][cycle.getYPos()]!=0){
				GameMaster.gameEnd();
				cycle.isAlive = false;
				explosion();
			}
			else{
				map[cycle.getXPos()][cycle.getYPos()]=1;
			}
			repaint();
		}
	}
	@Override
	public void paintComponent(Graphics g){
		if (gameStart){
			for (int i=0; i<xSize; i++){
				for (int j=0; j<ySize; j++){
					if (map[i][j]==1){
						g.setColor(Color.BLACK);
					}
					else{
						g.setColor(Color.WHITE);
					}
					g.fillRect(i+xOffset, j+yOffset, 1, 1);
				}
			}
			gameStart = false;
		}
		else{
			for (Cycle cycle : cycles){
				if (cycle.isAlive){
					g.setColor(cycle.getColor());
					g.fillRect(cycle.getXPos()+xOffset, cycle.getYPos()+yOffset, 5, 5);
				}
				else{
					if (explosionCount<80){
						for (Color color : getExplosionColors()){
							g.setColor(color);
							g.fillOval(xOffset+cycle.getXPos()-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
									yOffset+cycle.getYPos()-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
									(int)(explosionCount*Math.random()), 
									(int)(explosionCount*Math.random()));
						}
					}
					else{
						explosionTimer.stop();
						Frame.endGame();
					}
				}
			}
		}
	}
	private List<Color> getExplosionColors(){
		LinkedList<Color> colors = new LinkedList<Color>();
		for (int i = 0; i<explosionCount; i++){
			int iOffset = Math.abs(i-explosionCount/2);
			for (int j=0; j<explosionCount; j++){
				int jOffset = Math.abs(j-explosionCount/2);
				int color = (int) (20*Math.random());
				if (!((iOffset+jOffset)>explosionCount/5)){
					if (explosionCount < 30){
						if (color<15){
							if (iOffset > explosionCount/6 || jOffset > explosionCount/6){
								colors.add(Color.BLACK);
							}
							else{
								colors.add(Color.RED);
							}
						}
						if (color>=15){
							if (iOffset > explosionCount/6 || jOffset > explosionCount/6){
								colors.add(Color.GRAY);
							}
							else{
								colors.add(Color.ORANGE);
							}
						}
					}
					else{
						int hexDiffs = 62-explosionCount;
						if(hexDiffs<=0){
							hexDiffs = 0;
						}
						else{
							hexDiffs /= 2;
						}
						hexDiffs = (int) (Math.random()*hexDiffs);
						int hex = 0;
						for (int k=0; k<6; k++){
							hex += (int) (hexDiffs*Math.pow(16, k));
						}
						hex = ~hex;
						Color smoke = new Color(hex);
						colors.add(smoke);
					}
				}
			}
		}
		return colors;
	}
	private void explosion(){
		Color transparent = new Color(0,0,0,0);
		cycles[0].setColor(transparent);
		cycles[1].setColor(transparent);
		explosionCount = 0;
		explosionTimer = new Timer(33, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				explosionCount++;
				repaint();
			}
		});
		explosionTimer.start();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		cont.setHeading(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}
