/** 
 * @author Ashley Simpson
 * @version 2013/11/14
 * ReadyTimerListener Testing Class
 */

package tests;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import menuscreen.PlayerPanel;
import menuscreen.ReadyActionListener;

import org.junit.Test;

import user.Player;

public class ReadyTimerListenerTest{
	private int playerOneNumber = 1;
	private int playerTwoNumber = 2;
	private JLabel playerOneReady;
	private JLabel playerTwoReady;
	private String unReady = "NOT READY";
	private String ready = "READY";
	
	@Test
	public void areBothPlayersReady() {
		Player playerOne = new Player(playerOneNumber);
		Player playerTwo = new Player(playerTwoNumber);
		PlayerPanel playerPanelOne = new PlayerPanel(playerOne);
		PlayerPanel playerPanelTwo = new PlayerPanel(playerTwo);
		playerPanelOne.playerStatus.setText(ready);
		playerPanelTwo.playerStatus.setText(ready);
		ReadyActionListener testListener = new ReadyActionListener(playerPanelOne, playerPanelTwo, false);
		
		assertEquals(true, testListener.isReady());
	}
	
	@Test
	public void testPlayerOneReadyOnly() {
		Player playerOne = new Player(playerOneNumber);
		Player playerTwo = new Player(playerTwoNumber);
		PlayerPanel playerPanelOne = new PlayerPanel(playerOne);
		PlayerPanel playerPanelTwo = new PlayerPanel(playerTwo);
		playerPanelOne.playerStatus.setText(ready);
		playerPanelTwo.playerStatus.setText(unReady);
		ReadyActionListener testListener = new ReadyActionListener(playerPanelOne, playerPanelTwo, false);
		
		assertEquals(false, testListener.isReady());
	}
	
	@Test
	public void testPlayerTwoReadyOnly() {
		Player playerOne = new Player(playerOneNumber);
		Player playerTwo = new Player(playerTwoNumber);
		PlayerPanel playerPanelOne = new PlayerPanel(playerOne);
		PlayerPanel playerPanelTwo = new PlayerPanel(playerTwo);
		playerPanelOne.playerStatus.setText(unReady);
		playerPanelTwo.playerStatus.setText(ready);
		ReadyActionListener testListener = new ReadyActionListener(playerPanelOne, playerPanelTwo, false);
		
		assertEquals(false, testListener.isReady());
	}
	
	@Test
	public void testBothPlayersUnReady() {
		Player playerOne = new Player(playerOneNumber);
		Player playerTwo = new Player(playerTwoNumber);
		PlayerPanel playerPanelOne = new PlayerPanel(playerOne);
		PlayerPanel playerPanelTwo = new PlayerPanel(playerTwo);
		playerPanelOne.playerStatus.setText(unReady);
		playerPanelTwo.playerStatus.setText(unReady);
		ReadyActionListener testListener = new ReadyActionListener(playerPanelOne, playerPanelTwo, false);
		
		assertEquals(false, testListener.isReady());
	}
}