package fr.ecp.IS1220.myVelib.core.ride;

import java.util.ArrayList;

import fr.ecp.IS1220.myVelib.core.exception.NoSuchStationExistException;
import fr.ecp.IS1220.myVelib.core.station.Station;
import fr.ecp.IS1220.myVelib.core.system.Localization;

/**
 * This interface represents a path strategy.
 * @author Valentin
 *
 */
public interface PathStrategy {

	/**
	 * Returns a couple of stations, one for bike rental and one for bike return,
	 * which lets the user go from one localization to another according to the
	 * given path strategy. This method should be used if the user is indifferent
	 * to the type of bike for the ride.
	 * @param source the start point of the user
	 * @param destination the destination of the user
	 * @return two stations, one for bike rental and one for bike return
	 */
	public ArrayList<Station> findPath(Localization source, 
			Localization destination) throws NoSuchStationExistException;
	
	/**
	 * Returns a couple of stations, one for bike rental and one for bike return,
	 * which lets the user go from one localization to another according to the
	 * given path strategy. This method should be used if the user wants to specify
	 * a type of bike for the ride.
	 * @param source the start point of the user
	 * @param destination the destination of the user
	 * @param bicycleType a String that to correspond to the type of the bicycle
	 * @return two stations, one for bike rental and one for bike return
	 */
	public ArrayList<Station> findPath(Localization source, 
			Localization destination, String bicycleType) throws NoSuchStationExistException;
	
	/**
	 * Returns the type of bicycle associated to the computed path. <br>
	 * For example, if the user is indifferent to the type of bicycle, a type of
	 * bicycle is still going to be associated to the computed path.
	 * @return a String that to correspond to the type of the bicycle
	 */
	public String getBicycleType();
}
