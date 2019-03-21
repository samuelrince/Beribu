package fr.ecp.IS1220.beribu;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * This class provides a number of methods for computing station-related statistics.
 * @author Valentin
 *
 */
public class StationBalance implements Statistics {

	/**
	 * This method returns the total number of bike rentals performed in a
	 * given station since its creation.
	 * @param station station to analyze
	 * @return the total number of bike rentals
	 */
//	public static int totalRentCount(Station station) {
//		int totalRentCount = 0;
//		for (int i = 1; i < station.getHistory().size(); i++) {
//			ArrayList<ArrayList<Boolean>> previousStatus = 
//					station.getHistory().get(i-1).getParkingSlotStatus();
//			ArrayList<ArrayList<Boolean>> parkingSlotStatus = 
//					station.getHistory().get(i).getParkingSlotStatus();
//			for (int j = 0; j < previousStatus.size(); j++) {
//				if (previousStatus.get(j).get(1) == true
//						&& parkingSlotStatus.get(j).get(1) == false) {
//					totalRentCount++;
//				}						
//			}
//		}
//		return totalRentCount;
//	}
	
	/**
	 * This method returns the total number of bike returns performed in a
	 * given station since its creation.
	 * @param station station to analyze
	 * @return the total number of bike returns
	 */
//	public static int totalReturnCount(Station station) {
//		int totalReturnCount = 0;
//		for (int i = 1; i < station.getHistory().size(); i++) {
//			ArrayList<ArrayList<Boolean>> previousStatus = 
//					station.getHistory().get(i-1).getParkingSlotStatus();
//			ArrayList<ArrayList<Boolean>> parkingSlotStatus = 
//					station.getHistory().get(i).getParkingSlotStatus();
//			for (int j = 0; j < previousStatus.size(); j++) {
//				if (previousStatus.get(j).get(1) == false
//						&& parkingSlotStatus.get(j).get(1) == true) {
//					totalReturnCount++;
//				}						
//			}
//		}
//		return totalReturnCount;
//	}
	
	/**
	 * This method returns the rate of occupation of a given station during a
	 * given time window, ie. the sum on each parking slot of the time during which
	 * this parking slot has been occupied or offline divided by the total available 
	 * time. <br> This available time is defined as the length
	 * of the time window, except for the parking slots which have been created
	 * during the time window ; it is then defined as the total time of existence of
	 * the parking slot until the end of the time window.
	 * @param station the station to analyze
	 * @param start start of the time window
	 * @param end end of the time window
	 * @return the occupation rate of the station during this time window
	 * @throws RuntimeException
	 */
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
	
	public static void display(Station station) {
		System.out.println("----------------------"+"\n"+"Statistics of station "+station+"\n"+"\n"
	+"Date of creation :"+station.getCreatedAt()+"\n"+"Number of slots : "+station.getParkingSlots().size()
	+"\n"+"Total number of rentals : "+station.getRentCount()+"\n"+"Total number of returns : "
	+station.getReturnCount()+"\n"+"Occupation rate : "+StationBalance.occupationRate(
			station, new Date(1970,1,1,0,0,0), new Date())*100+"%"+"\n"+"----------------------");
	}
}
