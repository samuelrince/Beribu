package fr.ecp.IS1220.myVelib.core.ride;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ecp.IS1220.myVelib.core.station.Station;
import fr.ecp.IS1220.myVelib.core.system.Localization;

/**
 * A path strategy for finding the path between two localizations which 
 * requires to walk the minimal distance.
 * @author Valentin
 *
 */
public class MinimalWalking implements PathStrategy {
	String bicycleType;
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.add(source.getClosestStationWithBicycle());
		this.bicycleType = startEnd.get(0).getOneBicycleType();
		startEnd.add(destination.getClosestAvailableStation());
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		this.bicycleType = bicycleType;
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.add(source.getClosestStationWithBicycle(bicycleType));
		startEnd.add(destination.getClosestAvailableStation());
		return startEnd;
		
	}

	public String getBicycleType() {
		return this.bicycleType;
	}
	
}
