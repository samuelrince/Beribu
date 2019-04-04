package fr.ecp.IS1220.myVelib.core.ride;

import java.util.ArrayList;

import fr.ecp.IS1220.myVelib.core.station.Station;
import fr.ecp.IS1220.myVelib.core.system.Localization;

/**
 * A path strategy for finding the path between two localizations which 
 * is a compromise between the walk distance and the conservation of a uniform
 * distribution of bicycles among the stations of the network.
 * @author Valentin
 *
 */
public class PreserveDistribution implements PathStrategy {
	String bicycleType;
	
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
		startEnd.add(startStation);
		this.bicycleType = startStation.getOneBicycleType();
		
		Station endStation = destination.getClosestAvailableStation();
		ArrayList<Station> closeStations1 = destination.getStationsInRadius(
				1.05*destination.distanceTo(endStation.getLocalization()));
		for (int i = 0; i < closeStations1.size(); i++) {
			if (closeStations1.get(i).numberOfFreeSlots() > 
			endStation.numberOfFreeSlots())
				endStation = closeStations1.get(i);
		}
		startEnd.add(endStation);
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		this.bicycleType = bicycleType;
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		Station startStation = source.getClosestStationWithBicycle(bicycleType);
		ArrayList<Station> closeStations = source.getStationsInRadius(
				1.05*source.distanceTo(startStation.getLocalization()));
		for (int i = 0; i < closeStations.size(); i++) {
			if (closeStations.get(i).numberOfBicycles(bicycleType) > 
			startStation.numberOfBicycles(bicycleType))
				startStation = closeStations.get(i);
		}
		startEnd.add(startStation);
		
		Station endStation = destination.getClosestAvailableStation();
		ArrayList<Station> closeStations1 = destination.getStationsInRadius(
				1.05*destination.distanceTo(endStation.getLocalization()));
		for (int i = 0; i < closeStations1.size(); i++) {
			if (closeStations1.get(i).numberOfFreeSlots() > 
			endStation.numberOfFreeSlots())
				endStation = closeStations1.get(i);
		}
		startEnd.add(endStation);
		return startEnd;
	}
	
	public String getBicycleType() {
		return this.bicycleType;
	}
}