package startscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import start.Connect;
import start.Frame;
import start.FrameDriver;
import user.LoginGUI;
import user.Player;

public class PlayerPanel extends JPanel implements ActionListener{
	private Player player;
	public JButton logout;
	public JButton login;
	public JButton createAccount;
	public JButton playerHistory;
	public JLabel playerStatus;
	public InputMap inputMap;
	public JLabel playerLabel;
	public JButton playerStats;
	public JButton head2headscore;
	
	private Connection conn;
	
	public PlayerPanel(Player player){
		this.player = player;
		setBackground(Color.DARK_GRAY);
		initComponents();
		initLayout();
		this.conn = Connect.connect();
	}
	private void initComponents(){		
		playerLabel = new JLabel();
		playerLabel.setFont(new Font("Times", Font.BOLD, 37));
		
		logout = new JButton("Logout");
		logout.setForeground(Color.BLACK);
		logout.setBackground(new Color(0x33cc99));
		logout.addActionListener(this);
		logout.setVisible(false);
		
		login = new JButton("Login");
		login.setForeground(Color.BLACK);
		login.setBackground(new Color(0x33cc99));
		login.addActionListener(this);
		login.setVisible(false);
		
		head2headscore = new JButton("Head to Head Score");
		head2headscore.setForeground(Color.BLACK);
		head2headscore.setBackground(new Color(0x33cc99));
		head2headscore.addActionListener(this);
		head2headscore.setVisible(false);
		
		createAccount = new JButton("Create an Account");
		createAccount.setForeground(Color.black);
		createAccount.setBackground(new Color(0x33cc99));
		createAccount.addActionListener(this);
		
		playerStats = new JButton("Show Statistics");
		playerStats.setBackground(new Color(0x33cc99));
		playerStats.addActionListener(this);
		
		playerHistory = new JButton("Show Player History");
		playerHistory.setBounds(140, 225, 300, 70);
		playerHistory.addActionListener(this);
		
		playerStatus = new JLabel("NOT READY");
		playerStatus.setForeground(Color.RED);
		playerStatus.setBackground(Color.DARK_GRAY);
		
		inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
	}
	
	private void initLayout(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new MigLayout("", "[grow][grow][grow]", "[shrink][50px][50px][50px][50px][shrink]"));
		
		add(playerLabel, "cell 0 0 3 1,alignx left");
		add(logout, "cell 1 1,alignx right,growx,growy");
		add(login, "cell 1 2,alignx right,growx,growy");
		add(createAccount, "cell 1 3,alignx right,growx,growy");
		add(playerStats, "cell 1 4,alignx right,growx,growy");
		add(playerStatus, "cell 1 6 2 1,alignx right,aligny baseline");
		add(head2headscore, "cell 1 5, alignx left, growx, growy");

		if(player.getPlayerNumber() == 2){
			
			add(playerLabel, "cell 0 0 3 1,alignx right");
			add(logout, "cell 1 1,alignx left,growx,growy");
			add(login, "cell 1 2,alignx left,growx,growy");
			add(createAccount, "cell 1 3,alignx left,growx,growy");
			add(playerStats, "cell 1 4,alignx left,growx, growy");
			add(playerStatus, "cell 0 6 2 1,alignx left,aligny baseline");
			add(head2headscore, "cell 1 5, alignx left, growx, growy");
		}
		setVisible(true);
	}

	public void setLocation(int x){
		setBounds(x, Frame.getYSize()/6, Frame.getXSize()/2, 5*Frame.getYSize()/6);
	}
	
	public int getPlayerNumber(){
		return this.player.getPlayerNumber();
	}
	
	public void setLabel(String label){
		playerLabel.setText(label);
	}
	
	public void setLogin(boolean isVisible){
		login.setVisible(isVisible);
	}
	
	public void setCreate(boolean isVisible){
		createAccount.setVisible(isVisible);
	}
	
	public void setLogout(boolean isVisible){
		logout.setVisible(true);
		head2headscore.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")){
			FrameDriver.login(player);
		}
		
		if (e.getActionCommand().equals("Logout")){
			FrameDriver.logout(player);
		}
		
		if (e.getActionCommand().equals("Show Statistics")){
		       FrameDriver.Statistics();
		}
		
		if(e.getActionCommand().equals("Create an Account")){
			FrameDriver.createAccount(player);
		}
		
		if(e.getActionCommand().equals("Show Player History")){
			if(player.getPlayerNumber() == 1){
				FrameDriver.playerHistory(LoginGUI.player1);
			}
			
			else{
				FrameDriver.playerHistory(LoginGUI.player2);
			}
		}
		
	}
}
