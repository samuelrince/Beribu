package fr.ecp.IS1220.myVelib.client;

import javax.swing.*;
import fr.ecp.IS1220.myVelib.core.*;

public class StationGUI extends JFrame {
	private Station station;
	
	public StationGUI(Station station){
		super(station.toString()+" // "+MyVelibNetwork.getInstance().getName());
		this.station = station;
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
		this.setSize(400, 150);
		this.setVisible(true);
	}

	public static void main(final String[] args){
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);SD.setTime(0, 0, 0);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		network.createStations(new Localization(48.86101631231847,2.33583927154541), 5., 10, 4, 10, 70., new double[] {70,30});
		Station station = network.station(0);
		SwingUtilities.invokeLater (new Runnable (){public void run () {new StationGUI(station);}});
	}
}
