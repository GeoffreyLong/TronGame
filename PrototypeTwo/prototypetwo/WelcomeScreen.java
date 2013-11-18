package prototypetwo;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener{
	
	private JLabel title;

	private JButton createAccount;
	private JButton login;
	private JButton start;
	
	
	public WelcomeScreen(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		makeComponents();
		makeLayout();
	}
	
	private void makeComponents(){
		
		title = new JLabel("Protoype II");
		title.setFont(new Font("Times", Font.BOLD, 37));
		title.setBounds(170, 10, 510, 45);

		createAccount = new JButton("Create your account");
		createAccount.setBounds(100, 150, 300, 80);
		createAccount.addActionListener(this);
		
		login = new JButton("Login to your account");
		login.setBounds(100, 250, 300, 80);
		login.addActionListener(this);
		
		start = new JButton("Start the Game");
		start.setBounds(100,350,300,80);
		start.addActionListener(this);
	}
	
	private void makeLayout(){
		setLayout(null);
		add(title);
		add(title);
		add(createAccount);
		add(login);
		add(start);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == createAccount){
			Frame.removeAll();
			Frame.addPanel(new CreateUser());
		}
		
		else if(e.getSource() == login){
			Frame.removeAll();
			Frame.addPanel(new LoginGUI());
		}
		
		else if(e.getActionCommand().equals("Start the Game")){
			GameMaster game = new GameMaster();
			game.gameInit();
			game.gameStart();
		}
	}



}
