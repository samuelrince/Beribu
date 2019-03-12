package fr.ecp.IS1220.beribu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents a station.
 * @author Valentin
 *
 */
public class Station implements Comparable<Station>{
	private static long uniqId;
	private ArrayList<State> history = new ArrayList<State>();
	private long id;
	private Localization localization;
	private String name;
	private Boolean isOffline;
	private Boolean isPlus;
	private ArrayList<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	private ArrayList<Travel> targetOf = new ArrayList<Travel>();
	private Date createdAt;
	
	public Station(Localization localization, Boolean isPlus) {
		super();
		SystemDate SD = SystemDate.getInstance();
		this.localization = localization;
		this.isPlus = isPlus;
		this.id = uniqId++;
		this.createdAt = new Date();
	}
	
	public Station(Localization localization, Boolean isPlus, String name) {
		super();
		this.localization = localization;
		this.isPlus = isPlus;
		this.name = name;
		this.id = uniqId++;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public  ArrayList<State> getHistory() {
		return this.history;
	}
	
	/**
	 * This method returns the number of available bicycles in the station.
	 * Bicycles parked on an offline parking slot are not taken into account.
	 * @return the number of available bicycles
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
	 * This method returns the number of available bicycles of a given type in the station.
	 * Bicycles parked on an offline parking slot are not taken into account.
	 * @return the number of available bicycles of given type
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
	 * This method returns the number of parking slots available in the station,
	 * ie. online slots holding no bicycle.
	 * @return the number of free slots
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
	 * This method returns a free parking slot (online and holding no bicycle) 
	 * of the station if such a parking slot exists, otherwise it throws a RuntimeException.
	 * @return a free parking slot
	 * @throws RuntimeException
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
	 * This method returns true if there is no free parking slot available in the station.
	 * If there is at least one free parking slot (online and holding no bicycle),
	 * it returns false.
	 * @return a boolean
	 */
	public boolean isFull() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (!parkingSlots.get(i).isBicycle()
			&& !parkingSlots.get(i).isOffline()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method returns an available bicycle of the station (the first available in the order
	 * of parking slots) if it exists AND empties the corresponding parking slot. 
	 * If no such bicycle exists, throws a RuntimeException.
	 * @return a Bicycle
	 * @throws RuntimeException
	 */
	public Bicycle getBicycle() throws RuntimeException {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& this.parkingSlots.get(i).isOffline() == false) {
				Bicycle bicycle = this.parkingSlots.get(i).getBicycle();
				this.parkingSlots.get(i).setBicycle(null);
				return bicycle;
			}
		}
		throw new RuntimeException("Sorry, no bicycle is available.");
	}
		
  /**
   * This method returns an available bicycle of given type of the station (the first available in the order
	 * of parking slots) if it exists AND empties the corresponding parking slot. 
	 * If no such bicycle exists, throws a RuntimeException.
	 * @return a Bicycle
	 * @throws RuntimeException
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
		throw new RuntimeException("Sorry, no bicycle of the desired type is available.");
	}
	
	/**
	 * This method returns true if there is at least one available bicycle in the station.
	 * Returns false otherwise.
	 * @return a boolean
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
	 * This method returns true if there is at least one available bicycle of 
	 * given type in the station. Returns false otherwise.
	 * @return a boolean
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
	
	/**
	 * This method returns one available type of bicycle in the station.
	 * For example, if the station contains electrical and mechanical bicycles,
	 * this method can return either "electrical" or "mechanical" (depending
	 * on the order of the bicycles appearance in the parking slots).
	 * If no bicycle is available, throws a RuntimeException.
	 * @return a type of bicycle as a String
	 */
	public String getOneBicycleType() throws RuntimeException {
			for (int i = 0; i < Bicycle.getTypeDict().size(); i++) {
			if (this.isBicycle(Bicycle.getTypeDict().get(i))) {
				return Bicycle.getTypeDict().get(i);
			}
		}
		throw new RuntimeException("No bicycle available in this station.");
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
		return this.localization;
	}

	public String getName() {
		return this.name;
	}
	
	public Boolean isOffline() {
		return this.isOffline;
	}

	/**
	 * A call to this method sets all the parking slots of the station in the state
	 * specified by input isOffline.
	 * @param isOffline should be true to set the station offline, false to set it online
	 */
	public void setOffline(Boolean isOffline) {
		this.isOffline = isOffline;
		// Set all parking slots offline
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			this.parkingSlots.get(i).setOffline(isOffline);
		}
	}

	public ArrayList<ParkingSlot> getParkingSlots() {
		return this.parkingSlots;
	}

	/**
	 * Adds a given parking slot to the station. This method is automatically called
	 * when creating a parking slot associated to the station.
	 * @param parkingSlot
	 */
	public void addParkingSlot(ParkingSlot parkingSlot) {
		this.parkingSlots.add(parkingSlot);
		this.updateStatus();
	}
	
	public ArrayList<Travel> getTargetOf() {
		return this.targetOf;
	}

	
	/**
	 * This method is called when a planned ride Travel suggests this station either
	 * as a station or end station to a user.
	 * @param travel
	 */
	public void addTargetOf(Travel travel) {
		this.targetOf.add(travel); 
	}
	
	/**
	 * This method is called when the station is no longer being targeted by a planned
	 * ride Travel either as start station or end station.
	 * @param travel
	 */
	public void removeTargetOf(Travel travel) {
		this.targetOf.remove(travel); 
	}
 
	/**
	 * This is automatically called when any operation (rental, return, offline, online, parking slot creation)
	 * is performed in the station, including any of its parking slots. <br>
	 * It does two things :
	 * <br>- If the station gets empty or full as a consequence of this operation, 
	 * notifies all the users with a planned ride Travel who were supposedly 
	 * heading towards this station (to rent a bicycle or return one).
	 * <br>- Creates a new instance of State and adds it to the station history
	 */
	public void updateStatus() {
		if (!this.isBicycle()) {
			for (int i = 0; i < this.targetOf.size(); i++) {
				if (this.targetOf.get(i).getSuggestedStartStation() == this){
					this.targetOf.get(i).update();
				}
			}
		}
		else {
			for (int j = 0; j < Bicycle.getTypeDict().size(); j++) {
				if (!this.isBicycle(Bicycle.getTypeDict().get(j))) {
					for (int i = 0; i < this.targetOf.size(); i++) {
						if (this.targetOf.get(i).getSuggestedStartStation() == this
								&& this.targetOf.get(i).getBicycleType() == 
								Bicycle.getTypeDict().get(j)){
							this.targetOf.get(i).update();
						}
					}
				}
			}
		}
		
		if (this.isFull()) {
			for (int i = 0; i < this.targetOf.size(); i++) {
				if (this.targetOf.get(i).getSuggestedEndStation() == this){
					this.targetOf.get(i).update();
				}
			}
		}
		this.history.add(new State());
	}
	
	@Override
	public String toString() {
		return "Station No." + this.getId();
	}
	
	@Override
	public int compareTo(Station o) {
		return this.id > o.getId() ? 1 : this.id < o.getId() ? -1 : 0;
		
	}
	
  /**
	 * A nested class of the class Station that depicts the state of the station 
	 * at a given moment in time.
	 * @author Valentin
	 */
	public class State{
		private Date timeStamp;
		private ArrayList<ArrayList<Boolean>> parkingSlotStatus 
		= new ArrayList<ArrayList<Boolean>>();
		
		/**
		 * Constructor of the class State. Records the time and date at the instant
		 * of its creation, as well as the state of all the parking slots of the station :
		 * their status (online/offline) and their occupation (holding/not holding a bicycle)
		 * in the form of booleans.
		 */
		private State() {
			super();
			this.timeStamp = new Date();
			for (int i = 0; i < Station.this.parkingSlots.size(); i++) {
				ParkingSlot parkingSlot = Station.this.parkingSlots.get(i);
				this.parkingSlotStatus.add(new ArrayList<Boolean>(
						Arrays.asList(parkingSlot.isOffline(), parkingSlot.isBicycle())));
			}
		}
		
		public Date getTimeStamp() {
			return timeStamp;
		}
		public ArrayList<ArrayList<Boolean>> getParkingSlotStatus() {
			return parkingSlotStatus;
		}
		
	}

	public static void main(String[] args) {
		Station s1 = new Station(new Localization(2.1, 3.1), false);
		Station s2 = new Station(new Localization(2.1, 3.1), false);
		Station s3 = new Station(new Localization(2.1, 3.1), true);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s3); stations.add(s1); stations.add(s2);
		
		System.out.println(" == Stations initial order == ");
		System.out.println(stations.toString());
		
		
		
		Collections.sort(stations);
		System.out.println(" == Stations sorted by ID == ");
		System.out.println(stations.toString());
		
		Comparator<Station> c = new SortStationByMostUsed();
	}
	
}	

