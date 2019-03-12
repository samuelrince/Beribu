package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class FastestPath implements PathStrategy {
	ArrayList<Station> listOfStations = MyVelibNetwork.getInstance().getStationDatabase();
	String bicycleType;
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination) {
		// TODO Auto-generated method stub
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.add(destination.getClosestStationWithBicycle());

		ArrayList<String> typeDict = Bicycle.getTypeDict();
		double shortestTime = Double.POSITIVE_INFINITY;
		int stationIndex = -1;
		for (int i = 0; i < listOfStations.size(); i++) {
			Station currentStation = listOfStations.get(i);
			if (currentStation.isBicycle()) {
				String fastestBicycleType = null;
				double fastestSpeed = 0;
				for (int j = 0; j < typeDict.size(); j++) {
					if (currentStation.isBicycle(typeDict.get(j)) &&
							Bicycle.getSpeed(typeDict.get(j)) > fastestSpeed) {
						fastestBicycleType = typeDict.get(j);
						this.bicycleType = fastestBicycleType;
					}
				}
						
				double travelTime = 4/3.6*source.distanceTo(currentStation.
						getLocalization()) + Bicycle.getSpeed(fastestBicycleType)/3.6
						*currentStation.getLocalization().distanceTo(
						startEnd.get(0).getLocalization());

				if (travelTime < shortestTime) {
					shortestTime = travelTime;
					stationIndex = i;				
				}
		}
			else {
				continue;
			}
		}
		startEnd.add(0, listOfStations.get(stationIndex));
		return startEnd;
	}
	
	@Override
	public ArrayList<Station> findPath(Localization source, Localization destination,
			String bicycleType) {
		// TODO Auto-generated method stub
		this.bicycleType = bicycleType;
		ArrayList<Station> startEnd = new ArrayList<Station>(2);
		startEnd.add(destination.getClosestStationWithBicycle());

		double bicycleSpeed = Bicycle.getSpeed(bicycleType);
		double shortestTime = Double.POSITIVE_INFINITY;
		int stationIndex = -1;
		for (int i = 0; i < listOfStations.size(); i++) {
			Station currentStation = listOfStations.get(i);
			if (currentStation.isBicycle(bicycleType)) {
				double travelTime = 4/3.6*source.distanceTo(currentStation.
						getLocalization()) + bicycleSpeed/3.6
						*currentStation.getLocalization().distanceTo(
						startEnd.get(0).getLocalization());

				if (travelTime < shortestTime) {
					shortestTime = travelTime;
					stationIndex = i;				
				}
		}
			else {
				continue;
			}
		}
		startEnd.add(0, listOfStations.get(stationIndex));
		return startEnd;
	}

	public String getBicycleType() {
		return this.bicycleType;
	}
}
