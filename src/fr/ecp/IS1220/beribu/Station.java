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
	
	/**
	 * This method is used to count the number of Bicycles available in the
	 * station. To be available, a bicycle has to be parked in a parking slot 
	 * that is not offline or out of service.
	 * @return 	The number of bicycles available in the station
	 */
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
	
	/**
	 * This method is used to count the number of Bicycles available in the
	 * station of certain type defined by bicycleType. To be available, a 
	 * bicycle has to be parked in a parking slot that is not offline or out
	 * of service.
	 * @param bicycleType	A string for the type of bicycle
	 * @return				The number of bicycle available of the defined type
	 */
	public int numberOfBicycles(String bicycleType) {
		int number = 0;
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& this.parkingSlots.get(i).isOffline() == false) {
				if (bicycleType.equalsIgnoreCase(this.parkingSlots.get(i).getBicycle().getType()))
					number++;
			}
		}
		return number;
	}
	
	/**
	 * This method is used to count the number of free parking slots. A free 
	 * parking slot is counted only if it has not bicycle attached to and if
	 * it is online.
	 * @return
	 */
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
	
	/**
	 * This method is user to get a free parking slot. The parking slot has
	 * to be online and has no bicycle attached to. This method return the first 
	 * available parking slot and ignore the next ones. This function throw a 
	 * RuntimeException if there is no parking slot available.
	 * @return						A free ParkingSlot
	 * @throws RuntimeException		If no ParkingSlot are available
	 */
	public ParkingSlot getFreeParkingSlot() throws RuntimeException {
		for(int i = 0; i <= this.parkingSlots.size() - 1; i++) {
			if (!this.parkingSlots.get(i).isBicycle() && this.parkingSlots.get(i).isOffline() == false) {
				return this.parkingSlots.get(i);
			}
		}
		throw new RuntimeException("No parking slot available in this station");
	}
	
	/**
	 * This method is used to detect if the station is full or not. A station
	 * is considered as full if there is no parking slot available. That means
	 * all parking slots has a bicycle attached to or are offline.
	 * @return		Boolean, true if the station has at least one free parking slot,
	 * false if not.
	 */
	public boolean isFull() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (!parkingSlots.get(i).isBicycle()
			&& parkingSlots.get(i).isOffline() == false) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method allows to a user to detach a bicycle from the station. In this
	 * case it is the first available bicycle found. The bicycle type is not
	 * considered.
	 * @return		A Bicycle
	 * @throws 		If no available bicycle found
	 */
	public Bicycle getBicycle() throws RuntimeException {
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
		
	/**
	 * This method allows to a user to detach a bicycle of certain type. The first
	 * bicycle found that match the bicycle type is detached.
	 * @param bicycleType			A string that gives the type of the bicycle wanted.
	 * @return						Return a bicycle with the good type
	 * @throws RuntimeException		Throw an RuntimeException if no bicycle is found.
	 */
	public Bicycle getBicycle(String bicycleType) throws RuntimeException {
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
	
	/**
	 * This method is used to check if there is a bicycle available
	 * @return		True is there is a bicycle available and false otherwise
	 */
	public boolean isBicycle() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& this.parkingSlots.get(i).isOffline() == false) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method is used to check if there is a bicycle of a certain type is available
	 * @return		True is there is a bicycle available and false otherwise
	 */
	public boolean isBicycle(String bicycleType) {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle()
			&& this.parkingSlots.get(i).isOffline() == false) {
				if (bicycleType.equalsIgnoreCase(this.parkingSlots.get(i).getBicycle().getType())) {
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
	
	/**
	 * This method is used to set Offline the station and all parking
	 * slots.
	 * @param isOffline
	 */
	public void setOffline(Boolean isOffline) {
		this.isOffline = isOffline;
		// Set all parking slots offline
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			this.parkingSlots.get(i).setOffline(isOffline);
		}
	}

	public ArrayList<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}
	
	/**
	 * This method add ParkingSlot to the station. <br> <br>
	 * <b>This method should be called by a ParkingSlot object only</b>
	 * @param parkingSlot
	 */
	public void addParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlots.add(parkingSlot);
	}
	
	public ArrayList<Travel> getTargetOf() {
		return targetOf;
	}

	public void setTargetOf(ArrayList<Travel> targetOf) {
		this.targetOf = targetOf;
	}
 
	/**
	 * This method is used to update the status of the station and notify
	 * the Travels that are liked to. It also add all states into the history
	 * attribute.
	 */
	public void updateStatus() {
		for (int i = 0; i < this.targetOf.size(); i++) {
			if (this.isFull()) {
			this.targetOf.get(i).update();
			}
		}
		this.history.add(new State());
	}
	
	/**
	 * A nested class State to provide states feature for stations.
	 * A State is a dated photography of all parking slots status of 
	 * the station. The status of parking slot is the combination of
	 * two booleans representing is the parking isOffline and if it
	 * has a Bicycle attached to. <br>
	 * States are stored in a station attribute.
	 */
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

