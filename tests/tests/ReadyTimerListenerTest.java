/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * ReadyTimerListener Testing Class
 */

package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import startscreen.ReadyTimerListener;
import javax.swing.JLabel;

public class ReadyTimerListenerTest {
	private JLabel playerOneReady;
	private JLabel playerTwoReady;
	private String testReady = "READY";
	private String testUnReady = "UNREADY";
	
	@Test
	public void areBothPlayersReady() {
		playerOneReady = new JLabel();
		playerTwoReady = new JLabel();
		playerOneReady.setText(testReady);
		playerTwoReady.setText(testReady);
		ReadyTimerListener testListener = new ReadyTimerListener(playerOneReady, playerTwoReady);
		
		assertEquals(true, testListener.isReady());
	}
	
	@Test
	public void testPlayerOneReadyOnly() {
		playerOneReady = new JLabel();
		playerTwoReady = new JLabel();
		playerOneReady.setText(testReady);
		playerTwoReady.setText(testUnReady);
		ReadyTimerListener testListener = new ReadyTimerListener(playerOneReady, playerTwoReady);
		
		assertEquals(false, testListener.isReady());
	}
	
	@Test
	public void testPlayerTwoReadyOnly() {
		playerOneReady = new JLabel();
		playerTwoReady = new JLabel();
		playerOneReady.setText(testUnReady);
		playerTwoReady.setText(testReady);
		ReadyTimerListener testListener = new ReadyTimerListener(playerOneReady, playerTwoReady);
		
		assertEquals(false, testListener.isReady());
	}
	
	@Test
	public void testBothPlayersUnReady() {
		playerOneReady = new JLabel();
		playerTwoReady = new JLabel();
		playerOneReady.setText(testUnReady);
		playerTwoReady.setText(testUnReady);
		ReadyTimerListener testListener = new ReadyTimerListener(playerOneReady, playerTwoReady);
		
		assertEquals(false, testListener.isReady());
	}
}