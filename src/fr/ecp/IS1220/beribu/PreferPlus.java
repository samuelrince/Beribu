package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class PreferPlus implements PathStrategy {

	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.set(0, source.getClosestStationWithBicycle());
		startEnd.set(1, destination.getClosestAvailableStation(true));
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.set(0, source.getClosestStationWithBicycle(bicycleType));
		Station closestStation = destination.getClosestAvailableStation();
		Station closestPlusStation = destination.getClosestAvailableStation(true);
		if (destination.distanceTo(closestPlusStation.getLocalization()) <= 
				1.1*destination.distanceTo(closestStation.getLocalization())){				
			startEnd.set(1, closestPlusStation);
		}
		else {
			startEnd.set(1, closestStation);
		}
		return startEnd;
		
	}

}

