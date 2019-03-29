package fr.ecp.IS1220.myVelib.client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import fr.ecp.IS1220.myVelib.core.*;

public class UserGUI extends JFrame {
	private User user;
	int alertCount;
	
	private JPanel home = new JPanel();
	private JPanel alertPan = new JPanel();
	private JPanel fbPan = new JPanel();
	private JPanel planRidePan = new JPanel();
	private JPanel statPan = new JPanel();
	private JPanel subPan = new JPanel();
	
	private JButton backBtn1 = new JButton("back");
	private JButton backBtn2 = new JButton("back");
	private JButton backBtn3 = new JButton("back");
	private JButton backBtn4 = new JButton("back");
	private JButton backBtn5 = new JButton("back");
	private JButton alertBtn = new JButton("ALERTS: "+alertCount);
	private JButton fbBtn = new JButton("SEND FEEDBACK");
	private JButton planRideBtn = new JButton("PLAN A RIDE");
	private JButton statBtn = new JButton("MY STATISTICS");
	private JButton subBtn = new JButton("MY SUBSCRIPTION");
	private JButton discardBtn = new JButton("DISCARD");
	private JButton startBtn = new JButton("START");
	private JButton newRideBtn = new JButton("NEW RIDE");
	
	
			
	public UserGUI(User user){
		super("MyVelibApp v.1.0 - welcome"+user.getName()+"!");
		this.user = user;
		this.alertCount = 0;
		
		home.setBackground(Color.CYAN);        
		home.setLayout(new BorderLayout(3,3));
		alertBtn.addActionListener(new alertBtnListener());
		if (this.alertCount ==0)
			alertBtn.setEnabled(false);
		home.add(alertBtn, BorderLayout.NORTH);
		fbBtn.addActionListener(new fbBtnListener());
		home.add(fbBtn, BorderLayout.SOUTH);
		planRideBtn.addActionListener(new planRideBtnListener());
		home.add(planRideBtn, BorderLayout.CENTER);
		statBtn.addActionListener(new statBtnListener());
		home.add(statBtn, BorderLayout.EAST);
		subBtn.addActionListener(new subBtnListener());
		home.add(subBtn, BorderLayout.WEST);
		
		backBtn1.addActionListener(new backBtnListener());
		backBtn2.addActionListener(new backBtnListener());
		backBtn3.addActionListener(new backBtnListener());
		backBtn4.addActionListener(new backBtnListener());
		backBtn5.addActionListener(new backBtnListener());
		planRidePan.add(backBtn1);
		statPan.add(backBtn2);
		subPan.add(backBtn3);
		alertPan.add(backBtn4);
		fbPan.add(backBtn5);
		
		planRidePan.add(newRideBtn);
		planRidePan.add(discardBtn);
		planRidePan.add(startBtn);
		
		
		
		
//		JMenuBar menuBar = new JMenuBar();
//		
//		JMenu menu1 = new JMenu("Menu1");
//		JMenuItem menu1Item1 = new JMenuItem("Menu1Item1");
//		JMenuItem menu1Item2 = new JMenuItem("Menu1Item2");
//		menu1.add(menu1Item1);
//		menu1.add(menu1Item2);
//		menuBar.add(menu1);
//
//		JMenu menu2 = new JMenu("Menu2");
//		menuBar.add(menu2);
//
//		this.setJMenuBar(menuBar);
		
		this.setContentPane(home);
		this.setSize(500, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class alertBtnListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) { 
	    	UserGUI.this.setContentPane(alertPan);
	    	UserGUI.this.setVisible(true);
	    }
	  }
	
	class fbBtnListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) { 
	    	UserGUI.this.setContentPane(fbPan);
	    	UserGUI.this.setVisible(true);
	    }
	  }
	
	class planRideBtnListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) { 
	    	UserGUI.this.setContentPane(planRidePan);
	    	UserGUI.this.setVisible(true);
	    	
	    }
	  }
	
	class statBtnListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) { 
	    	UserGUI.this.setContentPane(statPan);
	    	UserGUI.this.setVisible(true);
	    }
	  }
	
	class subBtnListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) { 
	    	UserGUI.this.setContentPane(subPan);
	    	UserGUI.this.setVisible(true);
	    }
	  }
	class backBtnListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) { 
	    	UserGUI.this.setContentPane(home);
	    	UserGUI.this.setVisible(true);
	    }
	  }

	public static void main(final String[] args) throws Exception{
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);SD.setTime(0, 0, 0);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		network.createSubscribers(10,"vlibre");
		User user = network.user(0);
		SwingUtilities.invokeLater (new Runnable (){public void run () {new UserGUI(user);}});
	}

}
	

