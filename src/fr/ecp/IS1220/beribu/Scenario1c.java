package fr.ecp.IS1220.beribu;

public class Scenario1c {

	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		network.createPopStation(new Localization(0,0), false,
				10, new int[] {5,2});
		System.out.println(network.getStationDatabase());
		network.createStations(new Localization(0,0), 5., 3, 1, 10, 70., new double[] {70,30});
		network.createSubscribers(3, "standard");
		network.createSubscribers(2, "Vlibre");
		network.createSubscribers(1, "Vmax");
		System.out.println(network.stationDatabaseState());
		System.out.println(network.userDatabaseRepresentation());
		SD.setTime(20, 22, 37);
		network.user(1).newRide(network.station(1));
		network.user(2).newRide(network.station(1));
		network.user(1).endCurrentRide(network.station(1));
		network.user(2).endCurrentRide(network.station(1));
		System.out.println(network.station(1).historyTrace());
		StationBalance.display(network.station(1));
		UserBalance.display(network.user(1));
	}
}
