package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public abstract class Bicycle {
	private long id;
	private static long uniqId;
	
	public Bicycle() {
		this.id = uniqId++;
	}

	public long getId() {
		return id;
	}
		
	public abstract String getType();
	
	public abstract double getSpeed();
	
	public static double getSpeed(String bicycleType) throws IllegalArgumentException {
		if (bicycleType == "Mechanical") {
			return 15/3.6;
		}
		if (bicycleType == "Electrical") {
			return 20/3.6;
		}
		else {
			throw new IllegalArgumentException("Not a valid bicycle type.");
		}
	}
	
	public static ArrayList<String> getTypeDict(){
		ArrayList<String> typeDict = new ArrayList<String>();
		typeDict.add("Mechanical");
		typeDict.add("Electrical");	
		return typeDict;
	}
	
	public static void main(String[] args) {
		ElectricalBike eBike1 = new ElectricalBike();
		MechanicalBike mBike1 = new MechanicalBike();
		ElectricalBike eBike2 = new ElectricalBike();
		System.out.println(eBike1.getType()+" "+eBike1.getId());
		System.out.println(mBike1.getType()+" "+mBike1.getId());
		System.out.println(eBike2.getType()+" "+eBike2.getId());
	}
	
}
