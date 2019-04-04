package fr.ecp.IS1220.myVelib.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.ecp.IS1220.myVelib.core.*;
import fr.ecp.IS1220.myVelib.core.station.Station;
import fr.ecp.IS1220.myVelib.core.statistic.SortStationByLeastOccupied;
import fr.ecp.IS1220.myVelib.core.statistic.SortStationByMostUsed;
import fr.ecp.IS1220.myVelib.core.statistic.StationBalance;
import fr.ecp.IS1220.myVelib.core.statistic.UserBalance;
import fr.ecp.IS1220.myVelib.core.system.Localization;
import fr.ecp.IS1220.myVelib.core.system.MyVelibNetwork;
import fr.ecp.IS1220.myVelib.core.system.NetworkBackup;
import fr.ecp.IS1220.myVelib.core.system.SystemDate;

/**
 * This class is used to demonstrate some usages of the program.
 */
public class Scenario1 {
	
	public static void main(String[] args) throws RuntimeException, Exception {
		//Creation of an instance of SystemDate.
		SystemDate SD = SystemDate.getInstance();
		//Initialization of the date and time of SystemDate.
		SD.setDay(2019, 02, 17);SD.setTime(0, 0, 0);
		
		//Creation of a MyVelib network named "Paris".
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		
		//Creation of 10 Stations (of which 4 Plus stations), each dotted with 10 parking slots.
		//These stations are created in a circular area of radius 5 km centered on the pyramide du Louvre in Paris.
		//70% of the totality of the parking slots are populated with bicycles.
		//70% of these bicycles are "mechanical", the other 30% are "electrical".
		network.createStations(new Localization(48.86101631231847,2.33583927154541), 5., 10, 4, 10, 70., new double[] {70,30});
		//Creation of a new standard station named "Odeon" next to the Odeon Metro station.
		//This station is dotted with 12 parking slots, and populated with 1 "mechanical" bike and 1 "electrical" bike.
		network.createPopStation(new Localization(48.85223492920533,2.339315414428711), false, 12,new int[]{1,1},"Odeon");
		//Creation of a new Plus station named "Vaneau" next to the Vaneau Metro station.
		//This station is dotted with 8 parking slots, and populated with 6 "mechanical" bikes.
		network.createPopStation(new Localization(48.84890268296977,2.321033477783203), true, 8,new int[]{6,0},"Vaneau");
		
		//Creation of 11 user with no subscription.
		network.createSubscribers(11, "standard");
		//Creation of 7 user with a "Vlibre" subscription.
		network.createSubscribers(7, "Vlibre");
		//Creation of 4 user with a "Vmax" subscription.
		network.createSubscribers(4, "Vmax");
		
		//Displaying the station database and their current state.
		System.out.println(network.stationDatabaseState());
		//Displaying the user database.
		System.out.println(network.userDatabaseRepresentation());
		
		//Change of the time.
		SD.setTime(9, 22, 37);
		//User1 starts a new ride in Station2. They do not care about bicycle type.
		network.user(1).newRide(network.station(2));
		//
		SD.setTime(9, 28, 42);
		//User20 starts a new ride in Station1 with an electrical bike.
		network.user(20).newRide(network.station(1),"electrical");
		//
		SD.setTime(9, 31, 13);
		//User15 plans a new ride from a point next to Odeon to a point next to Vaneau.
		//He wishes to ride with an electrical bike.
		network.user(15).planRide(new Localization(48.85223492920534,2.339315414428711),
				new Localization(48.84890268296978,2.321033477783203),"electrical");
		//
		SD.setTime(9, 31, 49);
		//User4 starts a new ride in Station10 Odeon with an electrical bike.
		//Doing so, he takes the last electrical bike of this station so the planned ride of User15 has to be updated.
		network.user(4).newRide(network.station(10),"electrical");
		//
		SD.setTime(9, 33, 4);
		//User15 decides to discard their planned ride to plan an other one.
		network.user(15).discardPlannedRide();
		//This time, User15 doesn't specify any bicycle type for their planned ride.
		network.user(15).planRide(new Localization(48.85223492920534,2.339315414428711),
				new Localization(48.84890268296978,2.321033477783203));
		//User15 indicates that they're going for the ride they had planned.
		network.user(15).getPlannedRide().start();
		//
		SD.setTime(9, 35, 38);
		//User15 decides to follow the indications of their planned ride and starts a new ride from Station10 Odeon. They do not care about bicycle type.
		network.user(15).newRide(network.station(10));
		//
		SD.setTime(9, 46, 34);
		//User15 ends their ride in Station11 Vaneau.
		network.user(15).endCurrentRide(network.station(11));
		//
		SD.setTime(9, 48, 13);
		//User20 ends their ride in Station10 Odeon.
		network.user(20).endCurrentRide(network.station(10));
		//
		SD.setTime(10, 37, 59);
		//User1 ends their ride in Station10 Odeon.
		network.user(1).endCurrentRide(network.station(10));
		//
		SD.setTime(10, 56, 03);
		//User4 ends their ride in Station11 Vaneau.
		network.user(4).endCurrentRide(network.station(11));
			
		//Displaying the history of a station
		System.out.println(network.station(10).historyTrace());
		//Displaying the statistics of a few stations
		StationBalance.display(network.station(10));
		StationBalance.display(network.station(11));
		//Displaying the statistics of a few users
		UserBalance.display(network.user(1));
		UserBalance.display(network.user(15));
		UserBalance.display(network.user(20));
		
		//Sorting of station database
		//Basic
		ArrayList<Station> stations = (ArrayList<Station>) network.getStationDatabase().clone();
		System.out.println(" == Stations initial order == ");
		System.out.println(stations.toString());
		System.out.println();
		//By ID
		Collections.sort(stations);
		System.out.println(" == Stations sorted by ID == ");
		System.out.println(stations.toString());
		System.out.println();		
		//By most used
		Comparator<Station> c = new SortStationByMostUsed();
		Collections.sort(stations, c);
		System.out.println(" == Stations sorted by most used == ");
		System.out.println(stations);
		System.out.println();		
		//By least occupied
		c = new SortStationByLeastOccupied();
		Collections.sort(stations, c);
		System.out.println(" == Stations sorted by least occupied == ");
		System.out.println(stations);
		System.out.println();
	}
}
