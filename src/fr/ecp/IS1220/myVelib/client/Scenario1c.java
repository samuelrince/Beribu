package fr.ecp.IS1220.myVelib.client;

import fr.ecp.IS1220.myVelib.core.Localization;
import fr.ecp.IS1220.myVelib.core.MyVelibNetwork;
import fr.ecp.IS1220.myVelib.core.SystemDate;

public class Scenario1c {

	public static void main(String[] args) throws RuntimeException, Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
//		network.createStations(new Localization(0,0), 5., 2, 1, 10, 70., new double[] {70,30});
		network.createPopStation(new Localization(0,0), false, 10,new int[]{0,1});
		network.createPopStation(new Localization(0,0.1), true, 10,new int[]{2,0});
		network.createPopStation(new Localization(1,1), true, 10,new int[]{0,0});
		network.createSubscribers(3, "standard");
		network.createSubscribers(2, "Vlibre");
		network.createSubscribers(1, "Vmax");
		System.out.println(network.stationDatabaseState());
		System.out.println(network.userDatabaseRepresentation());
		SD.setTime(20, 22, 37);
		network.user(1).newRide(network.station(1));
		network.user(2).newRide(network.station(1));
		network.user(3).planRide(new Localization(0,0),
				new Localization(1,1),"electrical");
		network.user(4).newRide(network.station(0),"electrical");
		System.out.println(network.stationDatabaseState());
		network.user(2).endCurrentRide(network.station(2));
		network.user(4).endCurrentRide(network.station(2));
	}
}
