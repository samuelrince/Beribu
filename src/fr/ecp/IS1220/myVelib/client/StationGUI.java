package fr.ecp.IS1220.myVelib.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import fr.ecp.IS1220.myVelib.core.*;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchUserExistException;

public class StationGUI extends JFrame {
	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -679348530541363020L;
	public Station station;
	public MyVelibNetwork network;
	public User user = null;
	
	// Login
	public JPanel loginPanel = new JPanel();
	public JTextField nameTF = new JTextField();
	public JPasswordField passTF = new JPasswordField();
	
	// New Ride
	public JPanel stationNewRidePanel = new JPanel();
	public JComboBox<Bicycle> bicycleComboBox = new JComboBox<Bicycle>();
	
	// Create an account
	public JPanel accountPanel = new JPanel();
	public JTextField nameAccTF = new JTextField();
	public JPasswordField passAccTF = new JPasswordField();
	public JPasswordField passAccConfTF = new JPasswordField();
	public JComboBox<String> cardTypesComboBox = new JComboBox<String>(CardFactory.types);
	
	//Drop Bicycle
	public JPanel dropPanel = new JPanel();
	
	//Buttons
	public JButton cancelBTN = new JButton("Cancel");
	public JButton cancelRideBTN = new JButton("Cancel");
	
	
	public StationGUI(MyVelibNetwork network, Station station){
		super(station.toString()+" // "+MyVelibNetwork.getInstance().getName());
		this.station = station;
		this.network = network;
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(400, 400);
	    this.setLocationRelativeTo(null);

	    /*
	     * Define the LoginPanel
	     */
	    JPanel b1 = new JPanel();
	    //Welcome Label
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
				StationGUI.this.user = null;
				StationGUI.this.nameTF.setText("");
				StationGUI.this.passTF.setText("");
				StationGUI.this.setContentPane(StationGUI.this.accountPanel);
				StationGUI.this.setVisible(true);
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
	    cancelRideBTN.addActionListener(new CancelListener());
	    JButton startBTN = new JButton("Start Ride");
	    startBTN.addActionListener(new StartRideListener());
	    c4.add(cancelRideBTN);
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
	    
	    
	    /*
	     * Define Drop Panel 
	     */
	    JPanel f1 = new JPanel();
	    //Drop label
	    f1.setLayout(new BoxLayout(f1, BoxLayout.LINE_AXIS));
	    JLabel dropLabel = new JLabel("Drop your bicycle");
	    f1.add(dropLabel);
	    
	    JPanel f2 = new JPanel();
	    // Buttons
	    f2.setLayout(new BoxLayout(f2, BoxLayout.LINE_AXIS));
	    cancelBTN.addActionListener(new CancelListener());
	    JButton dropBTN = new JButton("Drop Bike");
	    dropBTN.addActionListener(new DropBikeListener());
	    f2.add(cancelBTN);
	    f2.add(dropBTN);
	    
	    //Drop panel
	    dropPanel.setLayout(new BoxLayout(dropPanel, BoxLayout.PAGE_AXIS));
	    dropPanel.add(f1);
	    dropPanel.add(f2);
	    dropPanel.setMaximumSize(new Dimension(200, 200));
	    
	    
	    this.setResizable(false);
	    this.setContentPane(loginPanel);
	    this.setVisible(true);
	}
	
	public class MyJDialog extends JDialog {
		 
	    private static final long serialVersionUID = 1L;
	 
	    public MyJDialog(JFrame parent, String title, String message) {
	        super(parent, title);
	        this.setLocationRelativeTo(null);
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
			String name = StationGUI.this.nameTF.getText();
			String passHash = "";
			try {
				passHash = PasswordHash.hashPassword(StationGUI.this.passTF.getText());
			} catch (NoSuchAlgorithmException e1) {}
			try {
				user = StationGUI.this.network.userAuth(name, passHash);
			} catch(NoSuchUserExistException u) {
				System.err.println(u.getMessage());
				MyJDialog dialog = new MyJDialog(new JFrame(), "", "Wrong combination of username and password");
		        dialog.setSize(300, 150);
			}
			if (user != null) {
				StationGUI.this.user = user;
				if (user.isOnRide()) {
					StationGUI.this.setContentPane(StationGUI.this.dropPanel);
				} else {
					StationGUI.this.setContentPane(StationGUI.this.stationNewRidePanel);
				}
				StationGUI.this.setVisible(true);
				System.out.println("User logged in");
			}
		}
	}
	
	class StartRideListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			User u = StationGUI.this.user;
			Bicycle bike = (Bicycle) StationGUI.this.bicycleComboBox.getSelectedItem();
			u.newRide(StationGUI.this.station, bike);
			StationGUI.this.user = null;
			StationGUI.this.nameTF.setText("");
			StationGUI.this.passTF.setText("");
			try {
				StationGUI.this.station.getParkingSlotAttachedTo(bike).detachBicycle();
			} catch (NoSuchPaddingException e1) {
				e1.printStackTrace();
				return;
			} catch (RuntimeException e1) {
				e1.printStackTrace();
				return;
			}
			StationGUI.this.bicycleComboBox.removeItem(bike);
			StationGUI.this.setContentPane(StationGUI.this.loginPanel);
			StationGUI.this.setVisible(true);
			System.out.println("User log out");
			MyJDialog dialog = new MyJDialog(new JFrame(), "", "You can take your bike and go!");
	        dialog.setSize(300, 150);
		}
	}
	
	class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			StationGUI.this.user = null;
			StationGUI.this.nameTF.setText("");
			StationGUI.this.passTF.setText("");
			StationGUI.this.nameAccTF.setText("");
			StationGUI.this.passAccTF.setText("");
			StationGUI.this.passAccConfTF.setText("");
			StationGUI.this.setContentPane(StationGUI.this.loginPanel);
			StationGUI.this.setVisible(true);
		}
	}
	
	class CreateAccountListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = StationGUI.this.nameAccTF.getText();
			String pass = StationGUI.this.passAccTF.getText();
			String passConf = StationGUI.this.passAccConfTF.getText();
			String cardType = (String) StationGUI.this.cardTypesComboBox.getSelectedItem();
			MyVelibNetwork network = StationGUI.this.network;
			if (!name.isEmpty() && pass.equals(passConf) && !cardType.isEmpty()) {
				try {
					network.newSubscriber(name, pass, cardType);
				} catch (Exception e1) {
					System.err.println("Failed to create new user");
					MyJDialog dialog = new MyJDialog(new JFrame(), "", "An error occured please try again");
			        dialog.setSize(300, 150);
			        return;
				}
			} else {
				MyJDialog dialog = new MyJDialog(new JFrame(), "", "Please fill all fields to create your account");
		        dialog.setSize(300, 150);
		        return;
			}
			MyJDialog dialog = new MyJDialog(new JFrame(), "", "Your account is successfully created");
	        dialog.setSize(300, 150);
			StationGUI.this.nameAccTF.setText("");
			StationGUI.this.passAccTF.setText("");
			StationGUI.this.passAccConfTF.setText("");
			StationGUI.this.setContentPane(StationGUI.this.loginPanel);
			StationGUI.this.setVisible(true);
		}
	}
	
	class DropBikeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (StationGUI.this.user == null || !StationGUI.this.user.isOnRide())
				return;
			if (StationGUI.this.station.isFull()) {
				MyJDialog dialog = new MyJDialog(new JFrame(), "", "This station is full, please find another one");
		        dialog.setSize(300, 150);
		        return;
			}
			Bicycle bike = user.getCurrentRide().getBicycle();
			try {
				user.endCurrentRide(StationGUI.this.station);
			} catch (Exception e1) {e1.printStackTrace(); return;}
			MyJDialog dialog = new MyJDialog(new JFrame(), "", "You have succesfully dropped your bike.");
	        dialog.setSize(300, 150);
			StationGUI.this.user = null;
			StationGUI.this.nameTF.setText("");
			StationGUI.this.passTF.setText("");
			StationGUI.this.bicycleComboBox.addItem(bike);
			StationGUI.this.setContentPane(StationGUI.this.loginPanel);
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
