package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

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
			Localization destination);
	
	/**
	 * Returns a couple of stations, one for bike rental and one for bike return,
	 * which lets the user go from one localization to another according to the
	 * given path strategy. This method should be used if the user wants to specify
	 * a type of bike for the ride.
	 * @param source the start point of the user
	 * @param destination the destination of the user
	 * @return two stations, one for bike rental and one for bike return
	 */
	public ArrayList<Station> findPath(Localization source, 
			Localization destination, String bicycleType);
	
	/**
	 * Returns the type of bicycle associated to the computed path. <br>
	 * For example, if the user is indifferent to the type of bicycle, a type of
	 * bicycle is still going to be associated to the computed path.
	 */
	public String getBicycleType();
}
