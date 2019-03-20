package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

/**
 * This abstract class represents a bicycle.
 * @author Valentin
 *
 */
public abstract class Bicycle {
	private long id;
	private static long uniqId;
	private boolean attached;
	
	public Bicycle() {
		this.id = uniqId++;
	}
	/**
	 * 
	 * @return id of the bicycle
	 */
	public long getId() {
		return id;
	}
		
	/**
	 * 
	 * @return true if the bicycle is attached to a parking slot, false otherwise
	 */
	public boolean getAttached() {
		return this.attached;
	}
	
	public void setAttached(boolean attached) {
		this.attached = attached;
	}
	
	/**
	 * 
	 * @return type of the bicycle
	 */
	public abstract String getType();
	
	/**
	 * 
	 * @return speed of the bicycle, corresponding to its type
	 */
	public abstract double getSpeed();
	
	/**
	 * Returns the speed of a given type of bicycle.
	 * @param bicycleType type of bicycle
	 * @return the speed of this type of bicycle
	 * @throws IllegalArgumentException
	 */
	public static double getSpeed(String bicycleType) throws IllegalArgumentException {
		if (bicycleType.equalsIgnoreCase("MECHANICAL")) {
			return 15/3.6;
		}
		if (bicycleType.equalsIgnoreCase("ELECTRICAL")) {
			return 20/3.6;
		}
		else {
			throw new IllegalArgumentException("Not a valid bicycle type.");
		}
	}
	
	/**
	 * 
	 * @return the dictionary of bicycle types
	 */
	public static ArrayList<String> getTypeDict(){
		ArrayList<String> typeDict = new ArrayList<String>();
		typeDict.add("MECHANICAL");
		typeDict.add("ELECTRICAL");	
		return typeDict;
	}
}
