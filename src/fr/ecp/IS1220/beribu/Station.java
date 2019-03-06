package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class Station {
	private static long uniqId;
	private static ArrayList<Station> allStations = new ArrayList<Station>();
	private long id;
	private Localization localization;
	private Boolean isOffline;
	private Boolean isPlus;
	private ArrayList<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	private ArrayList<Travel> targetOf = new ArrayList<Travel>();
	
	public Station(Localization localization, Boolean isPlus) {
		super();
		this.localization = localization;
		this.isPlus = isPlus;
		this.id = uniqId++;
		allStations.add(this);
	}
	
	public static ArrayList<Station> allStations(){
		return allStations;
	}
	
	public boolean isFull() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (parkingSlots.get(i).getBicycle() == null 
			&& parkingSlots.get(i).isOffline() == false) {
				return true;
			}
		}
		return false;
	}
	
	public Bicycle getBicycle() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).getBicycle() != null 
			&& this.parkingSlots.get(i).isOffline() == false) {
				Bicycle bicycle = this.parkingSlots.get(i).getBicycle();
				this.parkingSlots.get(i).setBicycle(null);
				return bicycle;
			}
		}
		throw new RuntimeException("Sorry, no bicycle is available.");
	}
		
	public Bicycle getBicycle(String bicycleType) {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).getBicycle() != null 
			&& this.parkingSlots.get(i).isOffline() == false) {
				if (this.parkingSlots.get(i).getBicycle().getType() == bicycleType) {
					Bicycle bicycle = this.parkingSlots.get(i).getBicycle();
					this.parkingSlots.get(i).setBicycle(null);
					return bicycle;
				}
			}
		}
		throw new RuntimeException("Sorry, no bicycle of the wanted type is available");
	}
	
	public boolean isBicycle() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).getBicycle() != null 
			&& this.parkingSlots.get(i).isOffline() == false) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBicycle(String bicycleType) {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).getBicycle() != null 
			&& this.parkingSlots.get(i).isOffline() == false) {
				if (this.parkingSlots.get(i).getBicycle().getType() == bicycleType) {
					return true;
				}
			}
		}
		return false;
	}
	
	public long getId() {
		return this.id;
	}
	
	public boolean isPlus() {
		return this.isPlus;
	}

	public void setPlus(Boolean isPlus) {
		this.isPlus = isPlus;
	}
	
	public Localization getLocalization() {
		return localization;
	}

	public Boolean isOffline() {
		return isOffline;
	}

	public void setOffline(Boolean isOffline) {
		this.isOffline = isOffline;
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			this.parkingSlots.get(i).setOffline(isOffline);
		}
	}

	public ArrayList<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}

	public void addParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlots.add(parkingSlot);
	}
 
	public void updateStatus() {
		for (int i = 0; i < this.targetOf.size(); i++) {
			if (this.isFull()) {
			this.targetOf.get(i).update();
			}
		}
	}
	
	public static void main(String[] args) {
		Station station1 = new Station(new Localization(0,0),false);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		new ParkingSlot(station1);
		ElectricalBike eBike1 = new ElectricalBike();
		MechanicalBike mBike1 = new MechanicalBike();
		ElectricalBike eBike2 = new ElectricalBike();
		station1.getParkingSlots().get(0).setBicycle(eBike1);
		station1.getParkingSlots().get(1).setBicycle(mBike1);
		station1.getParkingSlots().get(2).setBicycle(eBike2);
		station1.getParkingSlots().get(2).setOffline(true);
		System.out.println(station1.getParkingSlots());
	}
	
}	

