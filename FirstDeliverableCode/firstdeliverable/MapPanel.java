package firstdeliverable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		
		Cycle cycleOne = new Cycle(200, 400, 1, 1, true);
		Cycle cycleTwo = new Cycle(400, 400, 0, 2, true);
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
							g.fillRect(i, j, 5, 5);
						}
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
				int explosionSize;
				if (explosionCount>80){
					explosionSize = 30;
				}
				else{
					explosionSize = explosionCount;
				}
				for (int i = 0; i<explosionSize; i++){
					int iOffset = Math.abs(i-explosionSize/2);
					for (int j=0; j<explosionSize; j++){
						int jOffset = Math.abs(j-explosionSize/2);
						int color = (int) (20*Math.random());
						if (!((iOffset+jOffset)>explosionSize/5)){
							if (explosionSize < 30){
								if (color<15){
									if (iOffset > explosionSize/6 || jOffset > explosionSize/6){
										g.setColor(Color.BLACK);
									}
									else{
										g.setColor(Color.RED);
									}
								}
								if (color>=15){
									if (iOffset > explosionSize/6 || jOffset > explosionSize/6){
										g.setColor(Color.GRAY);
									}
									else{
										g.setColor(Color.ORANGE);
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
								g.setColor(smoke);
							}
							if (!isAliveOne){
								g.fillOval(xPosOne-((int)(explosionSize/2))+(int)(j*Math.random())+(int)(i*Math.random()), 
										yPosOne-((int)(explosionSize/2))+(int)(j*Math.random())+(int)(i*Math.random()), 
										(int)(i*Math.random())+(int)(j/2*Math.random()), 
										(int)(i/1.5*Math.random())+(int)(j*Math.random()));
							}
							if (!isAliveTwo){
								g.fillOval(xPosTwo-((int)(explosionSize/2))+(int)(j*Math.random())+(int)(i*Math.random()), 
										yPosTwo-((int)(explosionSize/2))+(int)(j*Math.random())+(int)(i*Math.random()), 
										(int)(i*Math.random())+(int)(j/2*Math.random()), 
										(int)(i/1.5*Math.random())+(int)(j*Math.random()));
							}
						}
					}
				}
			}
			else{
				explosionTimer.stop();
			}
		}
	}
	
	private void explosion(){
		explosionCount = 0;
		explosionTimer = new Timer(33, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				explosionCount++;
				if(!isAliveOne){
					paintImmediately(xPosOne-((int)(explosionCount/2)), yPosOne-((int)(explosionCount/2)), (int)(explosionCount*1.5), (int)(explosionCount*1.5));
				}
				if(!isAliveTwo){
					paintImmediately(xPosTwo-((int)(explosionCount/2)), yPosTwo-((int)(explosionCount/2)), (int)(explosionCount*1.5), (int)(explosionCount*1.5));
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
