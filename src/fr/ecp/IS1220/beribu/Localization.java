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
	 * closest one to the Localization instance containing at least 
	 * one free parking slot.
	 * @return 		the closest available station
	 */
	public Station getClosestStation() throws RuntimeException {
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
	
	public static void main(String[] args) {
		Localization loc1 = new Localization(0,-180);
		Localization loc2 = new Localization(0,180);
		System.out.println(loc1.distanceTo(loc2)/1000+" km");
	}
}
