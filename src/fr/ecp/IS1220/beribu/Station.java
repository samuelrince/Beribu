package fr.ecp.IS1220.beribu;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class Station {
	private static long uniqId;
	private static ArrayList<Station> stationDataBase = new ArrayList<Station>();
	private ArrayList<State> history = new ArrayList<State>();
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
		stationDataBase.add(this);
	}
	
	public static ArrayList<Station> allStations(){
		return stationDataBase;
	}
	
	public  ArrayList<State> getHistory() {
		return this.history;
	}
	
	public int numberOfBicycles() {
		int number = 0;
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle()
			&& this.parkingSlots.get(i).isOffline() == false) {
				number++;
			}
		}
		return number;
	}
	
	public int numberOfBicycles(String bicycleType) {
		int number = 0;
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& this.parkingSlots.get(i).isOffline() == false) {
				if (this.parkingSlots.get(i).getBicycle().getType() == bicycleType)
					number++;
			}
		}
		return number;
	}
	
	public int numberOfFreeSlots() {
		int number = 0;
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (!this.parkingSlots.get(i).isBicycle()
			&& this.parkingSlots.get(i).isOffline() == false) {
				number++;
			}
		}
		return number;
	}
	
	public ParkingSlot getFreeParkingSlot() throws RuntimeException {
		for(int i = 0; i <= this.parkingSlots.size() - 1; i++) {
			if (!this.parkingSlots.get(i).isBicycle() && this.parkingSlots.get(i).isOffline() == false) {
				return this.parkingSlots.get(i);
			}
		}
		throw new RuntimeException("No parking slot available in this station");
	}
	
	public boolean isFull() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (!parkingSlots.get(i).isBicycle()
			&& parkingSlots.get(i).isOffline() == false) {
				return true;
			}
		}
		return false;
	}
	
	public Bicycle getBicycle() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& this.parkingSlots.get(i).isOffline() == false) {
				Bicycle bicycle = this.parkingSlots.get(i).getBicycle();
				this.parkingSlots.get(i).setBicycle(null);
				this.updateStatus();
				return bicycle;
			}
		}
		throw new RuntimeException("Sorry, no bicycle is available.");
	}
		
	public Bicycle getBicycle(String bicycleType) {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& this.parkingSlots.get(i).isOffline() == false) {
				if (bicycleType.equalsIgnoreCase(this.parkingSlots.get(i).getBicycle().getType())) {
					Bicycle bicycle = this.parkingSlots.get(i).getBicycle();
					this.parkingSlots.get(i).setBicycle(null);
					this.updateStatus();
					return bicycle;
				}
			}
		}
		throw new RuntimeException("Sorry, no bicycle of the wanted type is available");
	}
	
	public boolean isBicycle() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& this.parkingSlots.get(i).isOffline() == false) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBicycle(String bicycleType) {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle()
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
	
	public ArrayList<Travel> getTargetOf() {
		return targetOf;
	}

	public void setTargetOf(ArrayList<Travel> targetOf) {
		this.targetOf = targetOf;
	}
 
	public void updateStatus() {
		for (int i = 0; i < this.targetOf.size(); i++) {
			if (this.isFull()) {
			this.targetOf.get(i).update();
			}
		}
		this.history.add(new State());
	}
	
	public class State{
		private Date timeStamp;
		private ArrayList<Map.Entry<Boolean, Boolean>> parkingSlotStatus 
		= new ArrayList<Map.Entry<Boolean, Boolean>>();
		
		private State() {
			super();
			this.timeStamp = new Date();
			for (int i = 0; i < Station.this.parkingSlots.size(); i++) {
				ParkingSlot parkingSlot = Station.this.parkingSlots.get(i);
				this.parkingSlotStatus.add(new AbstractMap.SimpleEntry(
						parkingSlot.isOffline(), parkingSlot.isBicycle()));
			}
		}
		
		public Date getTimeStamp() {
			return timeStamp;
		}
		public ArrayList<Map.Entry<Boolean, Boolean>> getParkingSlotStatus() {
			return parkingSlotStatus;
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
		System.out.println(station1.getBicycle("ELECTrICAL").getType());
	}
	
}	

