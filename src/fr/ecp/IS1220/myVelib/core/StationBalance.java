package fr.ecp.IS1220.myVelib.core;

import java.util.ArrayList;

import fr.ecp.IS1220.myVelib.core.exception.NoSuchStationExistException;

/**
 * This class provides a number of methods for computing station-related statistics.
 * @author Valentin
 *
 */
public class StationBalance {

	/**
	 * This method returns the total number of bike rentals performed in a
	 * given station since its creation.
	 * @param station station to analyze
	 * @return the total number of bike rentals
	 */
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
	
	/**
	 * This method returns the total number of bike returns performed in a
	 * given station since its creation.
	 * @param station station to analyze
	 * @return the total number of bike returns
	 */
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
	 * @throws RuntimeException	when there is no data
	 */
	public static double occupationRate(Station station, Date start, Date end) 
			throws NoSuchStationExistException {
		int minIndex = 0;
		int maxIndex;
		while (start.isAfter(station.getHistory().get(minIndex).getTimeStamp())){
			minIndex++;
			if (minIndex == station.getHistory().size()) {
				break;
			}
		}
		if (minIndex > 0)
			minIndex--;
		maxIndex = minIndex;
		if (!end.isAfter(station.getHistory().get(maxIndex).getTimeStamp()))
			throw new NoSuchStationExistException(station+" did not exist yet during that time window.");
		while (end.isAfter(station.getHistory().get(maxIndex).getTimeStamp())){
			maxIndex++;
			if (maxIndex == station.getHistory().size())				
				break;
				
		}
		maxIndex--;
		
		int occupationTime = 0;
		int totalTime = 0;
		
		if (minIndex == maxIndex) {
			ArrayList<ArrayList<Boolean>> parkingSlotStatus = station.getHistory().
					get(minIndex).getParkingSlotStatus();
			Date timeStamp = station.getHistory().get(minIndex).getTimeStamp();
			if (start.isAfter(timeStamp))
				timeStamp = start;
			Date nextTimeStamp = end;
			int numberOfOccupied = 0;
			for (int j = 0; j < parkingSlotStatus.size(); j++) {
				if (parkingSlotStatus.get(j).get(0) == true 
						|| parkingSlotStatus.get(j).get(1) == true) {
					numberOfOccupied++;
				}	
			}
			int elapsedTime = new Duration(timeStamp,nextTimeStamp).getDuration();
			occupationTime += numberOfOccupied*elapsedTime;
			totalTime += parkingSlotStatus.size()*elapsedTime;
			
			return (double)occupationTime/(double)totalTime;
		}
		
		ArrayList<ArrayList<Boolean>> parkingSlotStatus = station.getHistory().
				get(minIndex).getParkingSlotStatus();
		Date timeStamp = station.getHistory().get(minIndex).getTimeStamp();
		if (start.isAfter(timeStamp))
			timeStamp = start;
		Date nextTimeStamp = station.getHistory().get(minIndex+1).getTimeStamp();
		int numberOfOccupied = 0;
		for (int j = 0; j < parkingSlotStatus.size(); j++) {
			if (parkingSlotStatus.get(j).get(0) == true 
					|| parkingSlotStatus.get(j).get(1) == true) {
				numberOfOccupied++;
			}	
		}
		int elapsedTime = new Duration(timeStamp, nextTimeStamp).getDuration();
		occupationTime += numberOfOccupied*elapsedTime;
		totalTime += parkingSlotStatus.size()*elapsedTime;
		
		for (int i = minIndex+1; i < maxIndex; i++) {
			parkingSlotStatus = station.getHistory().get(i).getParkingSlotStatus();
			timeStamp = station.getHistory().get(i).getTimeStamp();
			nextTimeStamp = station.getHistory().get(i+1).getTimeStamp();
			numberOfOccupied = 0;
			for (int j = 0; j < parkingSlotStatus.size(); j++) {
				if (parkingSlotStatus.get(j).get(0) == true 
						|| parkingSlotStatus.get(j).get(1) == true) {
					numberOfOccupied++;
				}	
			}
			elapsedTime = new Duration(timeStamp,nextTimeStamp).getDuration();
			occupationTime += numberOfOccupied*elapsedTime;
			totalTime += parkingSlotStatus.size()*elapsedTime;
		}
		
		parkingSlotStatus = station.getHistory().
				get(maxIndex).getParkingSlotStatus();
		timeStamp = station.getHistory().get(maxIndex).getTimeStamp();
		nextTimeStamp = end;
		Date currentDate = new Date();
		if (!currentDate.isAfter(end)) {
			nextTimeStamp = currentDate;
			System.out.println("The time window for computing occupation rate"
					+ " was truncated because it ended after the current date.");
		}			
		numberOfOccupied = 0;
		for (int j = 0; j < parkingSlotStatus.size(); j++) {
			if (parkingSlotStatus.get(j).get(0) == true 
					|| parkingSlotStatus.get(j).get(1) == true) {
				numberOfOccupied++;
			}	
		}
		elapsedTime = new Duration(timeStamp,nextTimeStamp).getDuration();
		occupationTime += numberOfOccupied*elapsedTime;
		totalTime += parkingSlotStatus.size()*elapsedTime;
				
		return (double)occupationTime/(double)totalTime;
	}
	
	/**
	 * This method returns the rate of occupation of a given station from the date of its
	 * creation to the present, ie. the sum on each parking slot of the time during which
	 * this parking slot has been occupied or offline divided by its total time of existence.
	 * <br> 
	 * @param station the station to analyze
	 * @return the occupation rate of the station during this time window
	 * @throws RuntimeException	when there is no data
	 */
	public static double occupationRate(Station station) 
			throws NoSuchStationExistException {
		Date start = new Date(1970,0,0,0,0,0);
		Date end = new Date();
		int minIndex = 0;
		int maxIndex;
		while (start.isAfter(station.getHistory().get(minIndex).getTimeStamp())){
			minIndex++;
			if (minIndex == station.getHistory().size()) {
				break;
			}
		}
		if (minIndex > 0)
			minIndex--;
		maxIndex = minIndex;
		if (!end.isAfter(station.getHistory().get(maxIndex).getTimeStamp()))
			throw new NoSuchStationExistException(station+" did not exist yet during that time window.");
		while (end.isAfter(station.getHistory().get(maxIndex).getTimeStamp())){
			maxIndex++;
			if (maxIndex == station.getHistory().size())				
				break;
				
		}
		maxIndex--;
		
		int occupationTime = 0;
		int totalTime = 0;
		
		if (minIndex == maxIndex) {
			ArrayList<ArrayList<Boolean>> parkingSlotStatus = station.getHistory().
					get(minIndex).getParkingSlotStatus();
			Date timeStamp = station.getHistory().get(minIndex).getTimeStamp();
			if (start.isAfter(timeStamp))
				timeStamp = start;
			Date nextTimeStamp = end;
			int numberOfOccupied = 0;
			for (int j = 0; j < parkingSlotStatus.size(); j++) {
				if (parkingSlotStatus.get(j).get(0) == true 
						|| parkingSlotStatus.get(j).get(1) == true) {
					numberOfOccupied++;
				}	
			}
			int elapsedTime = new Duration(timeStamp,nextTimeStamp).getDuration();
			occupationTime += numberOfOccupied*elapsedTime;
			totalTime += parkingSlotStatus.size()*elapsedTime;
			
			return (double)occupationTime/(double)totalTime;
		}
		
		ArrayList<ArrayList<Boolean>> parkingSlotStatus = station.getHistory().
				get(minIndex).getParkingSlotStatus();
		Date timeStamp = station.getHistory().get(minIndex).getTimeStamp();
		if (start.isAfter(timeStamp))
			timeStamp = start;
		Date nextTimeStamp = station.getHistory().get(minIndex+1).getTimeStamp();
		int numberOfOccupied = 0;
		for (int j = 0; j < parkingSlotStatus.size(); j++) {
			if (parkingSlotStatus.get(j).get(0) == true 
					|| parkingSlotStatus.get(j).get(1) == true) {
				numberOfOccupied++;
			}	
		}
		int elapsedTime = new Duration(timeStamp, nextTimeStamp).getDuration();
		occupationTime += numberOfOccupied*elapsedTime;
		totalTime += parkingSlotStatus.size()*elapsedTime;
		
		for (int i = minIndex+1; i < maxIndex; i++) {
			parkingSlotStatus = station.getHistory().get(i).getParkingSlotStatus();
			timeStamp = station.getHistory().get(i).getTimeStamp();
			nextTimeStamp = station.getHistory().get(i+1).getTimeStamp();
			numberOfOccupied = 0;
			for (int j = 0; j < parkingSlotStatus.size(); j++) {
				if (parkingSlotStatus.get(j).get(0) == true 
						|| parkingSlotStatus.get(j).get(1) == true) {
					numberOfOccupied++;
				}	
			}
			elapsedTime = new Duration(timeStamp,nextTimeStamp).getDuration();
			occupationTime += numberOfOccupied*elapsedTime;
			totalTime += parkingSlotStatus.size()*elapsedTime;
		}
		
		parkingSlotStatus = station.getHistory().
				get(maxIndex).getParkingSlotStatus();
		timeStamp = station.getHistory().get(maxIndex).getTimeStamp();
		nextTimeStamp = end;
		Date currentDate = new Date();
		if (!currentDate.isAfter(end)) {
			nextTimeStamp = currentDate;
			System.out.println("The time window for computing occupation rate"
					+ " was truncated because it ended after the current date.");
		}			
		numberOfOccupied = 0;
		for (int j = 0; j < parkingSlotStatus.size(); j++) {
			if (parkingSlotStatus.get(j).get(0) == true 
					|| parkingSlotStatus.get(j).get(1) == true) {
				numberOfOccupied++;
			}	
		}
		elapsedTime = new Duration(timeStamp,nextTimeStamp).getDuration();
		occupationTime += numberOfOccupied*elapsedTime;
		totalTime += parkingSlotStatus.size()*elapsedTime;
				
		return (double)occupationTime/(double)totalTime;
	}
	
	public static void display(Station station) {
		System.out.println("----------------------"+"\n"+"Statistics of station "+station+"\n"+"\n"
	+"Date of creation :"+station.getCreatedAt()+"\n"+"GPS coordinates : "+station.getLocalization()
	+"\n"+"Number of slots : "+station.getParkingSlots().size()+"\n"+
	"Total number of rentals : "+station.getRentCount()+"\n"+"Total number of returns : "
	+station.getReturnCount()+"\n"+"Occupation rate : "+StationBalance.occupationRate(
			station, new Date(1970,1,1,0,0,0), new Date())*100+"%"+"\n"+"----------------------");
	}
	
	public static void main(String[] args) throws Exception {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 02, 17);
		SD.setTime(19, 22, 37);
		MyVelibNetwork network = new MyVelibNetwork("Paris");
		System.out.println(MyVelibNetwork.getInstance().getName());
		network.createPopStation(new Localization(0,0), false,
				10, new int[] {5,2});
		System.out.println(network.getStationDatabase());
		network.createStations(new Localization(0,0), 5., 3, 1, 10, 70., new double[] {70,30});
		network.createSubscribers(3, "standard");
		network.createSubscribers(2, "Vlibre");
		network.createSubscribers(1, "Vmax");
		System.out.println(network.stationDatabaseState());
		System.out.println(network.userDatabaseRepresentation());
		SD.setTime(20, 22, 37);
		network.user(1).newRide(network.station(1));
		network.user(2).newRide(network.station(1));
		network.user(1).endCurrentRide(network.station(1));
		network.user(2).endCurrentRide(network.station(1));
		System.out.println(network.station(1).historyTrace());
		StationBalance.display(network.station(1));
		System.out.println("rent:"+StationBalance.totalRentCount(network.station(1)));
		System.out.println("return:"+StationBalance.totalReturnCount(network.station(1)));
	}
}