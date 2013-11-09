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
			if (explosionCount<30){
				
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
					paintImmediately(xPosOne, yPosOne, explosionCount, explosionCount);
				}
				if(!isAliveTwo){
					paintImmediately(xPosTwo, yPosTwo, explosionCount, explosionCount);
				}
			}
		});
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
