package firstdeliverable;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MapPanel extends JPanel implements KeyListener {
	public MapPanel(){
		addKeyListener(this);
	}
	
	@Override
	public void paintComponents(Graphics g){
		super.paintComponents(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub	
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
