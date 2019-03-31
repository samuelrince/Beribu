package fr.ecp.IS1220.myVelib.core;

import java.util.ArrayList;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.ToStringConversion;

import fr.ecp.IS1220.myVelib.core.exception.NoSuchStationExistException;

/**
 * This class represents a localization.
 * @author Valentin
 *
 */
public class Localization {
	private double latitude;
	private double longitude;
	final static double rayonTerre = 6371;

	/**
	 * Constructor of class Localization.
	 * @param latitude in ° (positive for N, negative for S)
	 * @param longitude in ° (positive for E, negative for O)
	 */
	public Localization(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		String horiz;
		String vert;
		if (latitude>0)
			vert = "N";
		else
			vert = "S";
		if (longitude>0)
			horiz = "E";
		else
			horiz = "O";
		return "(" + latitude + "°"+vert+", " + longitude + "°"+horiz+")";
	}

	/**
	 * 
	 * @return latitude of the localization (in °)
	 */
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 
	 * @return longitude of the localization (in °)
	 */
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
	 * Returns the distance between two localizations.
	 * @param loc	the Localization to compare with
	 * @return 		the distance between the two localizations
	 */
	public double distanceTo(Localization loc) {
		double deltaLat = (loc.getLatitude() - this.latitude)*Math.PI/180;
		double deltaLong = (loc.getLongitude() - this.longitude)*Math.PI/180;
		return Math.sqrt(deltaLat*deltaLat + Math.pow(Math.cos(this.latitude),2)
				*deltaLong * deltaLong)*rayonTerre;
	}
	
	/**
	 * This method browses the list of stations on the network and picks the 
	 * one which is the closest to this Localization
	 * and contains at least one available parking slot.
	 * @return 		the closest Station with an available parking slot
	 */
	public Station getClosestAvailableStation() throws NoSuchStationExistException {
		ArrayList<Station> listOfStations = MyVelibNetwork.getInstance().getStationDatabase();
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
			throw new NoSuchStationExistException("Sorry, no available station was found.");
		}
		return listOfStations.get(stationIndex);
	}
	
	/**
	 * This method browses the public list of stations on the network and picks the 
	 * one of specified type which is the closest to this Localization
	 * and contains at least one available parking slot.
	 * @param isPlus	true if the Station should be Plus, false otherwise
	 * @return 		the closest Station of given type with an available parking slot
	 */
	public Station getClosestAvailableStation(boolean isPlus) throws NoSuchStationExistException {
		ArrayList<Station> listOfStations = MyVelibNetwork.getInstance().getStationDatabase();
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
			throw new NoSuchStationExistException("Sorry, no available station was found.");
		}
		return listOfStations.get(stationIndex);
	}
	
	public ArrayList<Station> getStationsInRadius(double radius) 
			throws NoSuchStationExistException {
		ArrayList<Station> listOfStations = MyVelibNetwork.getInstance().getStationDatabase();
		ArrayList<Station> stationsInRadius = new ArrayList<Station>();
		for (int i = 0; i < listOfStations.size(); i++) {
			if (this.distanceTo(listOfStations.get(i).getLocalization())
					<= radius) {
				stationsInRadius.add(listOfStations.get(i));			
			}
		}
		if (stationsInRadius.size() == 0)
			throw new NoSuchStationExistException("Sorry, no station in a radius of "+radius
					+"km around this localization "+this+".");
		return stationsInRadius;
	}
	
	/**
	 * This method browses the public list of stations on the network and picks the one 
	 * which is the closest to this Localization and contains at 
	 * least one available bicycle.
	 * @return 		the closest Station with an available Bicycle
	 */
	public Station getClosestStationWithBicycle() 
			throws RuntimeException {
		ArrayList<Station> listOfStations = MyVelibNetwork.getInstance().getStationDatabase();
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
	 * This method browses the public list of stations on the network and picks the one 
	 * which is the closest to this Localization and contains at 
	 * least one available bicycle of specified type.
	 * @param bicycleType 	the type of bicycle desired, 
	 * null if indifferent to the type
	 * @return 		the closest Station with an available Bicycle of given type
	 */
	public Station getClosestStationWithBicycle(String bicycleType) 
			throws RuntimeException {
		ArrayList<Station> listOfStations = MyVelibNetwork.getInstance().getStationDatabase();
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
	
	
	/**
	 * Acts exactly as randomLocInCircle.generate(this,radius).
	 * @param radius the radius of the circular area
	 */
	public Localization generateLocInRadius(double radius) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException("The radius should be > 0.");
		}
		double r = Math.sqrt(Math.random())*radius;
		double angle = Math.random()*2*Math.PI;
		return new Localization(this.latitude + Math.cos(angle)*r/rayonTerre,
				this.longitude + Math.sin(angle)*r/(Math.cos(this.latitude)*rayonTerre));
	}
	
	/**
	 * Returns a localization which is the barycenter of the localizations given in argument.
	 * @param locs the localizations to compute the barycenter of
	 * @return the barycenter of these localizations
	 */
	public static Localization barycenter(ArrayList<Localization> locs) {
		if (locs.size() < 2)
			throw new IllegalArgumentException("Need at least 2 localizations to compute"
					+ " their barycenter.");
		double latMean = 0;
		double longMean = 0;
		for (int i = 0; i < locs.size(); i++) {
			latMean += locs.get(i).getLatitude();
			longMean += locs.get(i).getLongitude();
		}
		latMean /= locs.size();
		longMean /= locs.size();
		return new Localization(latMean,longMean);
	}
}
