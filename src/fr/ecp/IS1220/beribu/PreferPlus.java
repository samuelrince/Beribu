package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

/**
 * A path strategy for finding the path between two localizations which 
 * makes the ride end in a Plus station provided it keeps the walk distance close to
 * the minimal.
 * @author Valentin
 *
 */
public class PreferPlus implements PathStrategy {
	String bicycleType;
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.add(source.getClosestStationWithBicycle());
		this.bicycleType = startEnd.get(0).getOneBicycleType();
		Station closestStation = destination.getClosestAvailableStation();
		Station closestPlusStation = destination.getClosestAvailableStation(true);
		if (destination.distanceTo(closestPlusStation.getLocalization()) <= 
				1.1*destination.distanceTo(closestStation.getLocalization())){				
			startEnd.add(closestPlusStation);
		}
		else {
			startEnd.add(closestStation);
		}
		return startEnd;
		
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		this.bicycleType = bicycleType;
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.add(source.getClosestStationWithBicycle(bicycleType));
		Station closestStation = destination.getClosestAvailableStation();
		Station closestPlusStation = destination.getClosestAvailableStation(true);
		if (destination.distanceTo(closestPlusStation.getLocalization()) <= 
				1.1*destination.distanceTo(closestStation.getLocalization())){				
			startEnd.add(closestPlusStation);
		}
		else {
			startEnd.add(closestStation);
		}
		return startEnd;
		
	}

	public String getBicycleType() {
		return this.bicycleType;
	}
	
}

