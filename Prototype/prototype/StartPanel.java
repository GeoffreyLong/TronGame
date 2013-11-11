/*
 * @author Rishabh Tandon
 *
 * This class is responsible for creating the JPanel which would be used
 * in the first prototype demonstration. It basically gives the rules of the game,
 * and redirects to the main gameplay when the start button is pressed
 */

package prototype;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StartPanel extends JPanel implements ActionListener{
	
	/* The following are the JComponents which would be used in the class implementation */
	
	private JLabel title;
	private JLabel description;
	private JButton start;
	private JLabel rules;
        /* This is the constructor for the class which basically makes the components and the 
         * layout of the JPanel
         */
	public StartPanel(){
		makeComponents();
		makeLayout();
	}

	public Dimension getPreferredSize() {
		return new Dimension(Frame.getXSize(),Frame.getYSize());
	}
	
	/* The method initializes the components for the layout and sets bounds for them to 
	 * place them in the right position
	 */

	private void makeComponents(){
		title = new JLabel("Prototype Demonstration I");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setBounds(470, 10, 600, 45);

		start = new JButton("Start Game");
		start.setBounds(600, 175, 200, 60);
		start.addActionListener(this);
		
		String rulesOfGame = "<html>\n" +
                "Rules of the game are as follows:\n" + 
                "<ul>\n" +
                "<li>Player one is <font color=red>red</font> and Player two is <font color=blue>blue</font>\n" +
                "<li>Player one controllers the racer by W(up), A(left), S(down) and D(right)\n" +
                "<li>Player two controllers the racer by using the arrow keys\n" +
                "<li>The racers follow the same direction, i.e. the one in which the key is pressed until another key is pressed\n" +
                "<li>The players must choose and initial heading before the game starts\n" +
                "<li>The players may not go backwards, doing so results in explosion of the racer\n" +
                "<li>As the player moves there is a wall built with his path in color of the racer\n" +
                "<li>If the players collide with the wall, and/or each other, it results in an explosion\n" +
                "</ul>\n";
                
                rules = new JLabel(rulesOfGame);
		rules.setBounds(100, 100, 600, 500);

		description = new JLabel("© Copyright of Group 7, ECSE 321, McGill University");
		description.setFont(new Font("Times", Font.BOLD, 10));
		description.setBounds(100, 600, 510, 45);
	}
	
	/* The following method initializes the layout to null, which is what we need and then
	 * adds all the JComponents to the the layout
	 */

	private void makeLayout(){
		setLayout(null);
		add(title);
		add(description);
		add(start);
		add(rules);
	}
	
	/* This is the ActionListener which listens to the button press and when it does,
	 * it initializes the main gameplay
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start Game")){
			GameMaster master = new GameMaster();
			master.gameInit();
			master.gameStart();
		}
	}
}

