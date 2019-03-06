package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class AvoidPlus implements PathStrategy {

	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.set(0, source.getClosestStationWithBicycle());
		startEnd.set(1, destination.getClosestAvailableStation(false));
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.set(0, source.getClosestStationWithBicycle(bicycleType));
		startEnd.set(1, destination.getClosestAvailableStation(false));
		return startEnd;
		
	}

}

