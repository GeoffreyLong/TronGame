package prototypetwo;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener{
	
	private JLabel title;
	
	private JButton createAccount;
	private JButton login;
	private JButton startGame;
	
	public WelcomeScreen(){
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
		
		startGame = new JButton("Start Game");
		startGame.setBounds(100, 350, 300, 80);
		startGame.addActionListener(this);
		
	}
	
	private void makeLayout(){
		setLayout(null);
		add(title);
		add(createAccount);
		add(login);
		add(startGame);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == createAccount){
			Main2.frame.getContentPane().removeAll();
			Main2.frame.getContentPane().add(new CreateUser());
			Main2.frame.setVisible(true);
		}
		
		if(e.getSource() == login){
//			Main2.frame.getContentPane().removeAll();
//			Main2.frame.getContentPane().add(new LoginGUI());
//			Main2.frame.setVisible(true);
			
			Start startScreen = new Start();
			
		}
		
		if(e.getSource() == startGame){
			if(Start.player1.isLoggedIn() && Start.player2.isLoggedIn()){
				// START GAME CODE HERE 
				
			}
			
		}
		
		
	}

}
