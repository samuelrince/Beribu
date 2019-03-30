package fr.ecp.IS1220.myVelib.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;
import fr.ecp.IS1220.myVelib.core.*;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchUserExistException;

public class StationGUI extends JFrame {
	public static Station station;
	public static MyVelibNetwork network;
	
	// Login
	public static JTextField nameTF = new JTextField();
	public static JTextField passTF = new JTextField();
	
	public StationGUI(MyVelibNetwork network, Station station){
		super(station.toString()+" // "+MyVelibNetwork.getInstance().getName());
		StationGUI.station = station;
		StationGUI.network = network;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(400, 400);
	    this.setLocationRelativeTo(null);

	    /*
	     * Define the LoginPanel
	     */
	    JPanel b1 = new JPanel();
	    //Welcome Lable
	    b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
	    JLabel welcomeLabel = new JLabel("Welcome to MyVelib");
	    b1.add(welcomeLabel);

	    JPanel b2 = new JPanel();
	    //Username
	    b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
	    StationGUI.nameTF.setMaximumSize(new Dimension(150, 30));
	    b2.add(new JLabel("Username:"));
	    b2.add(StationGUI.nameTF);

	    JPanel b3 = new JPanel();
	    //Password
	    b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
	    StationGUI.passTF.setMaximumSize(new Dimension(150, 30));
	    b3.add(new JLabel("Password:"));
	    b3.add(StationGUI.passTF);
	    
	    JPanel b4 = new JPanel();
	    //Buttons
	    b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
	    JButton loginBTN = new JButton("Login");
	    loginBTN.addActionListener(new LoginButtonListener());
	    JButton createBTN = new JButton("Create account");
	    createBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(nameTF.getText());
			}
		});
	    b4.add(createBTN);
	    b4.add(loginBTN);

	    JPanel loginPanel = new JPanel();
	    //Add to LoginPanel
	    loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
	    loginPanel.add(b1);
	    loginPanel.add(b2);
	    loginPanel.add(b3);
	    loginPanel.add(b4);
	    loginPanel.setMaximumSize(new Dimension(200, 200));
	    
	    /*
	     * Define Station Panel : New Ride
	     */
	    
	    
	    this.setResizable(false);
	    this.getContentPane().add(loginPanel);
	    this.setVisible(true);
	}
	
	public static void userLoggedIn(User user) {
		
	}
	
	public class MyJDialog extends JDialog {
		 
	    private static final long serialVersionUID = 1L;
	 
	    public MyJDialog(JFrame parent, String title, String message) {
	        super(parent, title);
	        this.setLocationRelativeTo(StationGUI.nameTF);
	        // Create a message
	        JPanel messagePane = new JPanel();
	        messagePane.add(new JLabel(message));
	        // get content pane, which is usually the
	        // Container of all the dialog's components.
	        getContentPane().add(messagePane);
	 
	        // Create a button
	        JPanel buttonPane = new JPanel();
	        JButton button = new JButton("Close");
	        buttonPane.add(button);
	        // set action listener on the button
	        button.addActionListener(new ActionListener() {
	        	//close and dispose of the window.
	            public void actionPerformed(ActionEvent e) {
	                setVisible(false);
	                dispose();
	            }
	        });
	        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        pack();
	        setVisible(true);
	    }
	}
	
	class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			User user = null;
			String name = StationGUI.nameTF.getText();
			String passHash = "";
			try {
				passHash = PasswordHash.hashPassword(StationGUI.passTF.getText());
			} catch (NoSuchAlgorithmException e1) {}
			try {
				user = StationGUI.network.userAuth(name, passHash);
			} catch(NoSuchUserExistException u) {
				System.err.println(u.getMessage());
				MyJDialog dialog = new MyJDialog(new JFrame(), "", "Wrong combination of username and password");
		        // set the size of the window
		        dialog.setSize(300, 150);
			}
			if (user != null) {
				StationGUI.userLoggedIn(user);
			}
		}
	}

	public static void main(final String[] args){
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);SD.setTime(0, 0, 0);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		network.createStations(new Localization(48.86101631231847,2.33583927154541), 5., 10, 4, 10, 70., new double[] {70,30});
		Station station = network.station(0);
		try {
			network.newSubscriber("Jean", "test", "Standard");
			network.newSubscriber("John", "Vlibre");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater (new Runnable (){public void run () {new StationGUI(network, station);}});
	}
}
