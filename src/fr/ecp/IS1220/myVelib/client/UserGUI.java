package fr.ecp.IS1220.myVelib.client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import fr.ecp.IS1220.myVelib.core.*;

public class UserGUI extends JFrame {
	private User user;
	
	public UserGUI(User user){
		super("MyVelibApp - welcome "+user.getName());
		this.user = user;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add the menu.
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Menu1");
		JMenuItem menu1Item1 = new JMenuItem("Menu1Item1");
		JMenuItem menu1Item2 = new JMenuItem("Menu1Item2");
		menu1.add(menu1Item1);
		menu1.add(menu1Item2);
		menuBar.add(menu1);

		JMenu menu2 = new JMenu("Menu2");
		menuBar.add(menu2);

		this.setJMenuBar(menuBar);

		//Add the label.
		JLabel label = new JLabel("Illusion is the first of all pleasures.");
		this.getContentPane().add(label);
		//	        frame.setContentPane(label);

		//Display the window.
		//	        frame.pack();
		this.setSize(800, 500);
		this.setVisible(true);
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

