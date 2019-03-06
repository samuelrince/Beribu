package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class PreserveDistribution implements PathStrategy {

	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		Station startStation = source.getClosestStationWithBicycle();
		ArrayList<Station> closeStations = source.getStationsInRadius(
				1.05*source.distanceTo(startStation.getLocalization()));
		for (int i = 0; i < closeStations.size(); i++) {
			if (closeStations.get(i).numberOfBicycles() > 
			startStation.numberOfBicycles())
				startStation = closeStations.get(i);
		}
		startEnd.set(0, startStation);
		
		Station endStation = destination.getClosestAvailableStation();
		ArrayList<Station> closeStations1 = destination.getStationsInRadius(
				1.05*destination.distanceTo(endStation.getLocalization()));
		for (int i = 0; i < closeStations1.size(); i++) {
			if (closeStations1.get(i).numberOfFreeSlots() > 
			endStation.numberOfFreeSlots())
				endStation = closeStations1.get(i);
		}
		startEnd.set(1, endStation);
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		Station startStation = source.getClosestStationWithBicycle(bicycleType);
		ArrayList<Station> closeStations = source.getStationsInRadius(
				1.05*source.distanceTo(startStation.getLocalization()));
		for (int i = 0; i < closeStations.size(); i++) {
			if (closeStations.get(i).numberOfBicycles(bicycleType) > 
			startStation.numberOfBicycles(bicycleType))
				startStation = closeStations.get(i);
		}
		startEnd.set(0, startStation);
		
		Station endStation = destination.getClosestAvailableStation();
		ArrayList<Station> closeStations1 = destination.getStationsInRadius(
				1.05*destination.distanceTo(endStation.getLocalization()));
		for (int i = 0; i < closeStations1.size(); i++) {
			if (closeStations1.get(i).numberOfFreeSlots() > 
			endStation.numberOfFreeSlots())
				endStation = closeStations1.get(i);
		}
		startEnd.set(1, endStation);
		return startEnd;
	}
	
}