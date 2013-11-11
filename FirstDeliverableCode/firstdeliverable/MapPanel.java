package firstdeliverable;

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

public class MapPanel extends JPanel implements KeyListener {
	private int[][] map;
	private int xSize;
	private int ySize;
	PlayerControl cont;
	Cycle[] cycles;
	private boolean gameStart = true;
	private int xPosOne;
	private int yPosOne;
	private int xPosTwo;
	private int yPosTwo;
	private boolean isAliveOne = true;
	private boolean isAliveTwo = true;
	private Timer explosionTimer;
	private int explosionCount;
	
	public MapPanel(Map map){
		this.map = map.getMap();
		this.xSize = map.getXSize();
		this.ySize = map.getYSize();
		
		int xOffset = (Frame.getXSize() - xSize) / 2;
		int yOffset = (Frame.getYSize() - ySize) / 2;
		
		
		Cycle cycleOne = new Cycle(200, 400, 1, 1, true);
		Cycle cycleTwo = new Cycle(400, 400, 0, 2, true);
		cycles = new Cycle[]{cycleOne, cycleTwo};
		
		xPosOne = cycles[0].getXPos();
		yPosOne = cycles[0].getYPos();
		xPosTwo = cycles[1].getXPos();
		yPosTwo = cycles[1].getYPos();
		
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
			switch (cycle.getCurHeading()){
				case 0:
					cycle.setXPos(cycle.getXPos()-5);
					break;
				case 1:
					cycle.setXPos(cycle.getXPos()+5);
					break;
				case 2:
					cycle.setYPos(cycle.getYPos()+5);
					break;
				case 3:
					cycle.setYPos(cycle.getYPos()-5);
					break;
				default:
					break;
			}
			if (map[cycle.getXPos()][cycle.getYPos()]!=0){
				GameMaster.gameEnd();
				if (cycle.getPlayerNum() == 1){
					isAliveOne = false;
				}
				else{
					isAliveTwo = false;
				}
				explosion();
			}
			else{
				if (cycle.getPlayerNum() == 1){
					xPosOne = cycle.getXPos();
					yPosOne = cycle.getYPos();
				}
				else{
					xPosTwo = cycle.getXPos();
					yPosTwo = cycle.getYPos();
				}
				map[xPosOne][yPosOne]=1;
				map[xPosTwo][yPosTwo]=1;
				paintImmediately(xPosOne,yPosOne,5,5);
				paintImmediately(xPosTwo,yPosTwo,5,5);
			}
		}
	}
	@Override
	public void paintComponent(Graphics g){
		if (isAliveOne && isAliveTwo){
			if (gameStart){
				for (int i=0; i<xSize; i++){
					for (int j=0; j<ySize; j++){
						if (map[i][j]==1){
							g.setColor(Color.BLACK);
						}
						else{
							g.setColor(Color.WHITE);
						}
						g.fillRect(i, j, 1, 1);
					}
				}
				gameStart = false;
			}
			else{
				g.setColor(Color.RED);
				g.fillRect(xPosOne, yPosOne, 5, 5);
				g.setColor(Color.BLUE);
				g.fillRect(xPosTwo, yPosTwo, 5, 5);
			}
		}
		else{
			if (explosionCount<80){
				for (Color color : getExplosionColors()){
					g.setColor(color);
					if (!isAliveOne){
						g.fillOval(xPosOne-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
								yPosOne-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
								(int)(explosionCount*Math.random()), 
								(int)(explosionCount*Math.random()));
					}
					if (!isAliveTwo){
						g.fillOval(xPosTwo-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
								yPosTwo-((int)(explosionCount/2))+(int)(explosionCount*Math.random()), 
								(int)(explosionCount*Math.random()), 
								(int)(explosionCount*Math.random()));
					}
				}
			}
			else{
				explosionTimer.stop();
				Frame.endGame();
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
		explosionCount = 0;
		explosionTimer = new Timer(33, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				explosionCount++;
				if(!isAliveOne){
					repaint();
				}
				if(!isAliveTwo){
					repaint();
				}
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
