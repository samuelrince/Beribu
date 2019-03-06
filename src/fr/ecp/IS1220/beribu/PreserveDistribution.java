package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class PreserveDistribution implements PathStrategy {

	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		Station closestStation = source.getClosestAvailableStation();
		ArrayList<Station> closeStations = source.getStationsInRadius(
				source.distanceTo(closestStation.getLocalization()));
		for (int i = 0; i < closeStations.size(); i++) {
			if (closeStations.get(i).isBicycle() and
		}
		startEnd.set(0, source.getClosestStationWithBicycle());
		startEnd.set(1, destination.getClosestAvailableStation());
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.set(0, source.getClosestStationWithBicycle(bicycleType));
		startEnd.set(1, destination.getClosestAvailableStation());
		return startEnd;
		
	}

}
