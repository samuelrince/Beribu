package fr.ecp.IS1220.beribu;

import java.util.ArrayList;
import java.util.Map.Entry;

public class StationBalance implements Statistics {

	public static int totalRentCount(Station station) {
		int totalRentCount = 0;
		for (int i = 1; i < station.getHistory().size(); i++) {
			ArrayList<ArrayList<Boolean>> previousStatus = 
					station.getHistory().get(i-1).getParkingSlotStatus();
			ArrayList<ArrayList<Boolean>> parkingSlotStatus = 
					station.getHistory().get(i).getParkingSlotStatus();
			for (int j = 0; j < previousStatus.size(); j++) {
				if (previousStatus.get(j).get(1) == true
						&& parkingSlotStatus.get(j).get(1) == false) {
					totalRentCount++;
				}						
			}
		}
		return totalRentCount;
	}
	
	public static int totalReturnCount(Station station) {
		int totalReturnCount = 0;
		for (int i = 1; i < station.getHistory().size(); i++) {
			ArrayList<ArrayList<Boolean>> previousStatus = 
					station.getHistory().get(i-1).getParkingSlotStatus();
			ArrayList<ArrayList<Boolean>> parkingSlotStatus = 
					station.getHistory().get(i).getParkingSlotStatus();
			for (int j = 0; j < previousStatus.size(); j++) {
				if (previousStatus.get(j).get(1) == false
						&& parkingSlotStatus.get(j).get(1) == true) {
					totalReturnCount++;
				}						
			}
		}
		return totalReturnCount;
	}
	
	public static double occupationRate(Station station, Date start, Date end) 
			throws RuntimeException {
		int minIndex = 0;
		int maxIndex;
		while (start.isAfter(station.getHistory().get(minIndex).getTimeStamp())){
			minIndex++;
			if (minIndex == station.getHistory().size())
				throw new RuntimeException("No data for this time window yet.");
		}
		maxIndex = minIndex + 1;
		while (end.isAfter(station.getHistory().get(maxIndex).getTimeStamp())){
			maxIndex++;
			if (maxIndex == station.getHistory().size())
				break;
		}
		int occupationTime = 0;
		int totalTime = 0;
		
		for (int i = 0; i < station.getHistory().size()-1; i++) {
			ArrayList<ArrayList<Boolean>> parkingSlotStatus = 
					station.getHistory().get(i).getParkingSlotStatus();
			Date nextTimeStamp = station.getHistory().get(i+1).getTimeStamp();
			int numberOfOccupied = 0;
			for (int j = 0; j < parkingSlotStatus.size(); j++) {
				if (parkingSlotStatus.get(j).get(0) == true 
						|| parkingSlotStatus.get(j).get(1) == true) {
					numberOfOccupied++;
				}	
			}
			int elapsedTime = new Duration(station.getHistory().get(i).
					getTimeStamp(),nextTimeStamp).getDuration();
			occupationTime += numberOfOccupied*elapsedTime;
			totalTime += parkingSlotStatus.size()*elapsedTime;
		}
		ArrayList<ArrayList<Boolean>> parkingSlotStatus = station.getHistory().
				get(station. getHistory().size()-1).getParkingSlotStatus();
		int numberOfOccupied = 0;
		for (int j = 0; j < parkingSlotStatus.size(); j++) {
			if (parkingSlotStatus.get(j).get(0) == true 
					|| parkingSlotStatus.get(j).get(1) == true) {
				numberOfOccupied++;
			}	
		}
		int elapsedTime = new Duration(station.getHistory().get(station.
				getHistory().size()-1).getTimeStamp(),end).getDuration();
		occupationTime += numberOfOccupied*elapsedTime;
		totalTime += parkingSlotStatus.size()*elapsedTime;

		return (double)occupationTime/(double)totalTime;
	}
	
}
