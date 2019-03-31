package fr.ecp.IS1220.myVelib.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.*;
import fr.ecp.IS1220.myVelib.core.*;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchUserExistException;

public class StationGUI extends JFrame {
	public static Station station;
	public static MyVelibNetwork network;
	public static User user = null;
	
	// Login
	public static JPanel loginPanel = new JPanel();
	public static JTextField nameTF = new JTextField();
	public static JPasswordField passTF = new JPasswordField();
	
	// New Ride
	public static JPanel stationNewRidePanel = new JPanel();
	public static JComboBox<Bicycle> bicycleComboBox = new JComboBox<Bicycle>();
	
	// Create an account
	public static JPanel accountPanel = new JPanel();
	public static JTextField nameAccTF = new JTextField();
	public static JTextField passAccTF = new JTextField();
	public static JTextField passAccConfTF = new JTextField();
	public static JComboBox<String> cardTypesComboBox = new JComboBox<String>(CardFactory.types);
	
	//Buttons
	public static JButton cancelBTN = new JButton("Cancel");
	
	
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
	    nameTF.setMaximumSize(new Dimension(150, 30));
	    b2.add(new JLabel("Username:"));
	    b2.add(nameTF);

	    JPanel b3 = new JPanel();
	    //Password
	    b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
	    passTF.setMaximumSize(new Dimension(150, 30));
	    b3.add(new JLabel("Password:"));
	    b3.add(passTF);
	    
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
	    JPanel c1 = new JPanel();
	    //Station Label
	    c1.setLayout(new BoxLayout(c1, BoxLayout.LINE_AXIS));
	    JLabel stationLabel = new JLabel(station.getName());
	    c1.add(stationLabel);
	    
	    JPanel c2 = new JPanel();
	    //Choose Bike Label
	    c2.setLayout(new BoxLayout(c2, BoxLayout.LINE_AXIS));
	    JLabel chooseLabel = new JLabel("Choose a bike:");
	    c2.add(chooseLabel);
	    
	    JPanel c3 = new JPanel();
	    // Bike selector
	    c3.setLayout(new BoxLayout(c3, BoxLayout.LINE_AXIS));
	    for (ParkingSlot ps: station.getParkingSlots()) {
	    	if (ps.hasBicycle() && !ps.isOffline()) {
	    		bicycleComboBox.addItem(ps.getBicycle());
	    	}
	    }
	    bicycleComboBox.setMaximumSize(new Dimension(200, 30));
	    c3.add(bicycleComboBox);
	    
	    JPanel c4 = new JPanel();
	    //Buttons
	    c4.setLayout(new BoxLayout(c4, BoxLayout.LINE_AXIS));
	    cancelBTN.addActionListener(new CancelListener());
	    JButton startBTN = new JButton("Start Ride");
	    startBTN.addActionListener(new StartRideListener());
	    c4.add(cancelBTN);
	    c4.add(startBTN);
	    
	    //Add to StationNewRidePanel
	    stationNewRidePanel.setLayout(new BoxLayout(stationNewRidePanel, BoxLayout.PAGE_AXIS));
	    stationNewRidePanel.add(c1);
	    stationNewRidePanel.add(c2);
	    stationNewRidePanel.add(c3);
	    stationNewRidePanel.add(c4);
	    stationNewRidePanel.setMinimumSize(new Dimension(200, 200));
	    
	    
	    /*
	     * Define create an account panel
	     */
	    JPanel d1 = new JPanel();
	    //Create acc label
	    d1.setLayout(new BoxLayout(d1, BoxLayout.LINE_AXIS));
	    JLabel createAccLabel = new JLabel("Create a MyVelib account");
	    d1.add(createAccLabel);
	    
	    JPanel d2 = new JPanel();
	    //Username
	    d2.setLayout(new BoxLayout(d2, BoxLayout.LINE_AXIS));
	    nameAccTF.setMaximumSize(new Dimension(150, 30));
	    d2.add(new JLabel("Username:"));
	    d2.add(nameAccTF);
	    
	    JPanel d3 = new JPanel();
	    //Password
	    d3.setLayout(new BoxLayout(d3, BoxLayout.LINE_AXIS));
	    passAccTF.setMaximumSize(new Dimension(150, 30));
	    d3.add(new JLabel("Password:"));
	    d3.add(passAccTF);
	    
	    JPanel d4 = new JPanel();
	    //Password Confirmation
	    d4.setLayout(new BoxLayout(d4, BoxLayout.LINE_AXIS));
	    passAccConfTF.setMaximumSize(new Dimension(150, 30));
	    d4.add(new JLabel("Password:"));
	    d4.add(passAccConfTF);
	    
	    JPanel d5 = new JPanel();
	    //Card Selector
	    d5.setLayout(new BoxLayout(d5, BoxLayout.LINE_AXIS)); 
	    cardTypesComboBox.setMaximumSize(new Dimension(200, 30));
	    d5.add(cardTypesComboBox);
	    
	    JPanel d6 = new JPanel();
	    //Buttons
	    d6.setLayout(new BoxLayout(d6, BoxLayout.LINE_AXIS));
	    cancelBTN.addActionListener(new CancelListener());
	    JButton createAccBTN = new JButton("Create account");
	    createAccBTN.addActionListener(new CreateAccountListener());
	    d6.add(cancelBTN);
	    d6.add(createAccBTN);
	    
	    
	    // Account Panel
	    accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.PAGE_AXIS));
	    accountPanel.add(d1);
	    accountPanel.add(d2);
	    accountPanel.add(d3);
	    accountPanel.add(d4);
	    accountPanel.add(d5);
	    accountPanel.add(d6);
	    accountPanel.setMaximumSize(new Dimension(200, 200));
	    
	    
	    this.setResizable(false);
	    this.setContentPane(accountPanel);
	    this.setVisible(true);
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
		        dialog.setSize(300, 150);
			}
			if (user != null) {
				StationGUI.user = user;
				StationGUI.this.setContentPane(StationGUI.stationNewRidePanel);
				StationGUI.this.setVisible(true);
				System.out.println("User logged in");
			}
		}
	}
	
	class StartRideListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			User u = StationGUI.user;
			Bicycle bike = (Bicycle) StationGUI.bicycleComboBox.getSelectedItem();
			u.newRide(StationGUI.station, bike);
			StationGUI.user = null;
			StationGUI.nameTF.setText("");
			StationGUI.passTF.setText("");
			StationGUI.this.setContentPane(StationGUI.loginPanel);
			StationGUI.this.setVisible(true);
			System.out.println("User log out");
			MyJDialog dialog = new MyJDialog(new JFrame(), "", "You can take your bike and go!");
	        dialog.setSize(300, 150);
		}
	}
	
	class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			StationGUI.user = null;
			StationGUI.nameTF.setText("");
			StationGUI.passTF.setText("");
			StationGUI.nameAccTF.setText("");
			StationGUI.passAccTF.setText("");
			StationGUI.passAccConfTF.setText("");
			StationGUI.this.setContentPane(StationGUI.loginPanel);
			StationGUI.this.setVisible(true);
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
