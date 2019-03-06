package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class Localization {
	private double latitude;
	private double longitude;

	public Localization(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "GPS (" + latitude + "°, " + longitude + "°)";
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localization other = (Localization) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	/**
	 * This method returns the distance between two Localization.
	 * @param loc	the Localization target to compare with
	 * @return 		the distance between the two Localization
	 */
	public double distanceTo(Localization loc) {
		double rayonTerre = 6371000;
		double deltaLat = (loc.getLatitude() - this.latitude)*Math.PI/180;
		double deltaLong = (loc.getLongitude() - this.longitude)*Math.PI/180;
		return Math.sqrt(deltaLat*deltaLat + Math.pow(Math.cos(this.latitude),2)
				*deltaLong * deltaLong)*rayonTerre;
	}
	
	/**
	 * This method browses the public list of stations and picks the 
	 * one which is the closest to the Localization instance 
	 * and contains at least one free parking slot.
	 * @return 		the closest available Station
	 */
	public Station getClosestAvailableStation() throws RuntimeException {
		ArrayList<Station> listOfStations = Station.allStations();
		double shortestDistance = Double.POSITIVE_INFINITY;
		int stationIndex = -1;
		for (int i = 0; i < listOfStations.size(); i++) {
			if (this.distanceTo(listOfStations.get(i).getLocalization())
					< shortestDistance && !listOfStations.get(i).isFull()) {
				shortestDistance = this.distanceTo(listOfStations.get(i)
						.getLocalization());
				stationIndex = i;				
			}
		}
		if (stationIndex == -1) {
			throw new RuntimeException("Sorry, no available station was found.");
		}
		return listOfStations.get(stationIndex);
	}
	
	/**
	 * This method browses the public list of stations and picks the 
	 * one of specified type which is the closest to the Localization instance 
	 * and contains at least one free parking slot.
	 * @param isPlus	true if the Station should be Plus, false otherwise
	 * @return 		the closest available Station
	 */
	public Station getClosestAvailableStation(boolean isPlus) throws RuntimeException {
		ArrayList<Station> listOfStations = Station.allStations();
		double shortestDistance = Double.POSITIVE_INFINITY;
		int stationIndex = -1;
		for (int i = 0; i < listOfStations.size(); i++) {
			if (this.distanceTo(listOfStations.get(i).getLocalization())
					< shortestDistance && !listOfStations.get(i).isFull()
					&&listOfStations.get(i).isPlus() == isPlus) {
				shortestDistance = this.distanceTo(listOfStations.get(i)
						.getLocalization());
				stationIndex = i;				
			}
		}
		if (stationIndex == -1) {
			throw new RuntimeException("Sorry, no available station was found.");
		}
		return listOfStations.get(stationIndex);
	}
	
	public ArrayList<Station> getStationsInRadius(double radius) 
			throws RuntimeException {
		ArrayList<Station> listOfStations = Station.allStations();
		ArrayList<Station> stationsInRadius = new ArrayList<Station>();
		for (int i = 0; i < listOfStations.size(); i++) {
			if (this.distanceTo(listOfStations.get(i).getLocalization())
					<= radius) {
				stationsInRadius.add(listOfStations.get(i));			
			}
		}
		return stationsInRadius;
	}
	
	/**
	 * This method browses the public list of stations and picks the one 
	 * which is the closest to the Localization instance and contains at 
	 * least one bicycle.
	 * @return 		the closest Station with an available Bicycle
	 */
	public Station getClosestStationWithBicycle() 
			throws RuntimeException {
		ArrayList<Station> listOfStations = Station.allStations();
		double shortestDistance = Double.POSITIVE_INFINITY;
		int stationIndex = -1;
		for (int i = 0; i < listOfStations.size(); i++) {
			if (this.distanceTo(listOfStations.get(i).getLocalization())
					< shortestDistance && 
					listOfStations.get(i).isBicycle()) {
				shortestDistance = this.distanceTo(listOfStations.get(i)
						.getLocalization());
				stationIndex = i;				
			}
		}
		if (stationIndex == -1) {
			throw new RuntimeException("Sorry, no station with an available"
					+ " bicycle was found.");
		}
		return listOfStations.get(stationIndex);
	}
	
	/**
	 * This method browses the public list of stations and picks the one 
	 * which is the closest to the Localization instance and contains at 
	 * least one bicycle of specified type.
	 * @param bicycleType 	the type of bicycle desired, 
	 * null if indifferent to the type
	 * @return 		the closest Station with an available Bicycle
	 */
	public Station getClosestStationWithBicycle(String bicycleType) 
			throws RuntimeException {
		ArrayList<Station> listOfStations = Station.allStations();
		double shortestDistance = Double.POSITIVE_INFINITY;
		int stationIndex = -1;
		for (int i = 0; i < listOfStations.size(); i++) {
			if (this.distanceTo(listOfStations.get(i).getLocalization())
					< shortestDistance && 
					listOfStations.get(i).isBicycle(bicycleType)) {
				shortestDistance = this.distanceTo(listOfStations.get(i)
						.getLocalization());
				stationIndex = i;				
			}
		}
		if (stationIndex == -1) {
			throw new RuntimeException("Sorry, no station with an available"
					+ " bicycle of type "+ bicycleType +" was found.");
		}
		return listOfStations.get(stationIndex);
	}
	
	public static void main(String[] args) {
		Localization loc1 = new Localization(0,-160);
		Localization loc2 = new Localization(0,160);
		System.out.println(loc1.distanceTo(loc2)/1000+" km");
	}
}
