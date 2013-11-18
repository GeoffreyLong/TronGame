package prototypetwo;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class WelcomeScreen extends JPanel implements ActionListener{
	
	private JLabel title;

	private JButton createAccount;
	private JButton login;
	
	
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
		
		
		
	}
	
	private void makeLayout(){
		setLayout(null);
		add(title);
		add(title);
		add(createAccount);
		add(login);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == createAccount){
			Main2.frame.getContentPane().removeAll();
			Main2.frame.getContentPane().add(new CreateUser());
			Main2.frame.setVisible(true);
		}
		
		else if(e.getSource() == login){
			Main2.frame.getContentPane().removeAll();
			Main2.frame.getContentPane().add(new LoginGUI());
			Main2.frame.setVisible(true);
		}
		
	}



}
