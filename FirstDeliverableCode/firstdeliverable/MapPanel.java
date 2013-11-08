package firstdeliverable;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

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
			}
			else{
				
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
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
