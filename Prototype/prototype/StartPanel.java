/**@author Rishabh Tandon
*/

package prototype;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StartPanel extends JPanel implements ActionListener{
	
	private JLabel title;
	private JLabel description;
	private JButton start;
	private JLabel rules;

	public StartPanel(){
		makeComponents();
		makeLayout();
	}

	public Dimension getPreferredSize() {
		return new Dimension(Frame.getXSize(),Frame.getYSize());
	}

	private void makeComponents(){
		title = new JLabel("Prototype Demonstration I");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setBounds(470, 10, 600, 45);

		start = new JButton("Start Game");
		start.setBounds(600, 166, 200, 60);
		start.addActionListener(this);
		
		String rulesOfGame = "<html>\n" +
                "Rules of the game are as follows:\n" + 
                "<ul>\n" +
                "<li>Player one is <font color=red>red</font> and Player two is <font color=blue>blue</font>\n" +
                "<li>Player one controllers the racer by W(up), A(left), S(down) and D(right)\n" +
                "<li>Player two controllers the racer by using the arrow keys\n" +
                "<li>The racers follow the same direction, i.e. the one in which the key is pressed until another key is pressed\n" +
                "<li>The players must choose and initial heading before the game starts\n" +
                "</ul>\n";

		description = new JLabel("© Copyright of Group 7, ECSE 321, McGill University");
		description.setFont(new Font("Times", Font.BOLD, 10));
		description.setBounds(100, 600, 510, 45);
	}

	private void makeLayout(){
		setLayout(null);
		add(title);
		add(description);
		add(start);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start Game")){
			GameMaster master = new GameMaster();
			master.gameInit();
			master.gameStart();
		}
	}
}

