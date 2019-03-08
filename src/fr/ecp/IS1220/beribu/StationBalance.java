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
				if (previousStatus.get(j).get(0) == true
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
				if (previousStatus.get(j).get(0) == false
						&& parkingSlotStatus.get(j).get(1) == true) {
					totalReturnCount++;
				}						
			}
		}
		return totalReturnCount;
	}
	
}
