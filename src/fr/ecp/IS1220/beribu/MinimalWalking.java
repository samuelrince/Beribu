package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class MinimalWalking implements PathStrategy {

	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.set(0, source.getClosestAvailableStation());
		startEnd.set(1, destination.getClosestStationWithBicycle());
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.set(0, source.getClosestAvailableStation());
		startEnd.set(1, destination.getClosestStationWithBicycle(bicycleType));
		return startEnd;
		
	}

}
