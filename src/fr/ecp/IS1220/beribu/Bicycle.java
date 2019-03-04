package fr.ecp.IS1220.beribu;


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
	
	public static void main(String[] args) {
		ElectricalBike eBike1 = new ElectricalBike();
		MechanicalBike mBike1 = new MechanicalBike();
		ElectricalBike eBike2 = new ElectricalBike();
		System.out.println(eBike1.getType()+" "+eBike1.getId());
		System.out.println(mBike1.getType()+" "+mBike1.getId());
		System.out.println(eBike2.getType()+" "+eBike2.getId());
	}

	
}
