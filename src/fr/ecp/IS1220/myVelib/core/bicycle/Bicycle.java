package fr.ecp.IS1220.myVelib.core.bicycle;

import java.util.ArrayList;
import java.util.Arrays;

import fr.ecp.IS1220.myVelib.core.exception.BadBicycleTypeException;

/**
 * This abstract class represents a bicycle.
 * @author Valentin
 *
 */
public abstract class Bicycle implements java.io.Serializable {
	private long id;
	private static long uniqId;
	private boolean attached;
	private static final ArrayList<String> typeDict = new ArrayList<String>(
			Arrays.asList("MECHANICAL","ELECTRICAL"));
	
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
	 * @throws IllegalArgumentException occurs when a wrong bicycleType is entered
	 */
	public static double getSpeed(String bicycleType) throws BadBicycleTypeException {
		if (bicycleType.equalsIgnoreCase("MECHANICAL")) {
			return 15/3.6;
		}
		if (bicycleType.equalsIgnoreCase("ELECTRICAL")) {
			return 20/3.6;
		}
		else {
			throw new BadBicycleTypeException("Not a valid bicycle type.");
		}
	}
	
	/**
	 * 
	 * @return the dictionary of bicycle types
	 */
	public static ArrayList<String> getTypeDict(){
		return typeDict;
	}
	
	@Override
	public String toString() {
		return this.getType() + " #" + this.getId(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Bicycle other = (Bicycle) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public static void resetUniqID() {uniqId=0;}
}
