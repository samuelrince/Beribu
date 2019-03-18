package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public abstract class Bicycle {
	private long id;
	private static long uniqId;
	private boolean attached;
	
	public Bicycle() {
		this.id = uniqId++;
	}

	public long getId() {
		return id;
	}
		
	public boolean getAttached() {
		return this.attached;
	}
	
	public void setAttached(boolean attached) {
		this.attached = attached;
	}
	
	public abstract String getType();
	
	public abstract double getSpeed();
	
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
	
	public static ArrayList<String> getTypeDict(){
		ArrayList<String> typeDict = new ArrayList<String>();
		typeDict.add("MECHANICAL");
		typeDict.add("ELECTRICAL");	
		return typeDict;
	}
}
