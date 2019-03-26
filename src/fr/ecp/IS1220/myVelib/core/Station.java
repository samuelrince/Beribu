package fr.ecp.IS1220.myVelib.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import fr.ecp.IS1220.myVelib.core.exception.NoBicycleAvailableException;
import fr.ecp.IS1220.myVelib.core.exception.NoParkingSlotAvailableException;

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
	private Boolean isOffline = false;
	private Boolean isPlus;
	private ArrayList<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	private ArrayList<Travel> targetOf = new ArrayList<Travel>();
	private Date createdAt;
	private int rentCount = 0;
	private int returnCount = 0;
	private boolean initializing = true;
	
	public Station(Localization localization, Boolean isPlus) {
		super();
		SystemDate SD = SystemDate.getInstance();
		this.localization = localization;
		this.isPlus = isPlus;
		this.id = uniqId++;
		this.name = "Station"+id;
		this.createdAt = new Date();
		System.out.println("New station "+this+".");
	}
	
	/**
	 * Constructor of class Station. The station created is empty.
	 * @param localization the localization of the station
	 * @param isPlus type of station (true for Plus, false for Standard)
	 * @param name name of the station
	 */
	public Station(Localization localization, Boolean isPlus, String name) {
		super();
		this.localization = localization;
		this.isPlus = isPlus;
		this.name = name;
		this.createdAt = new Date();
		this.id = uniqId++;
		System.out.println("New station "+this+".");
	}
	
	/**
	 * Constructor of class Station. The station created contains a number 
	 * of empty slots.
	 * @param localization the localization of the station
	 * @param isPlus type of station (true for Plus, false for Standard)
	 * @param name name of the station
	 * @param numberOfSlots number of parking slots
	 */
	public Station(Localization localization, Boolean isPlus, String name, int numberOfSlots) {
		super();
		this.localization = localization;
		this.isPlus = isPlus;
		this.name = name;
		this.createdAt = new Date();
		this.id = uniqId++;
		System.out.println("New station "+this+".");
		this.createParkingSlots(numberOfSlots);
	}
	
	/**
	 * Constructor of class Station. The station created contains a number 
	 * of empty slots.
	 * @param localization the localization of the station
	 * @param isPlus type of station (true for Plus, false for Standard)
	 * @param numberOfSlots number of parking slots
	 */
	public Station(Localization localization, Boolean isPlus, int numberOfSlots) {
		super();
		this.localization = localization;
		this.isPlus = isPlus;
		this.id = uniqId++;
		this.name = "Station"+id;
		this.createdAt = new Date();
		System.out.println("New station "+this+".");
		this.createParkingSlots(numberOfSlots);
	}
	
	/**
	 * Returns the date of creation of the station.
	 * @return the date of creation of the station
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Returns the full history of the station, ie. the list of states of the
	 * station since its creation.
	 * @return the history of the station
	 */
	public  ArrayList<State> getHistory() {
		return this.history;
	}
	
	/**
	 * Returns the number of available bicycles in the station.
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
	 * Returns the number of available bicycles of a given type in the station.
	 * Bicycles parked on an offline parking slot are not taken into account.
	 * @param bicycleType A string corresponding to the type of bicycle
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
	 * Returns the number of parking slots available in the station,
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
	 * Returns a free parking slot (online and holding no bicycle) 
	 * of the station if such a parking slot exists, otherwise throws a RuntimeException.
	 * @return a free parking slot
	 * @throws RuntimeException	when there is no available parking slot
	 */
	public ParkingSlot getFreeParkingSlot() throws NoParkingSlotAvailableException {
		for(int i = 0; i <= this.parkingSlots.size() - 1; i++) {
			if (!this.parkingSlots.get(i).isBicycle() && this.parkingSlots.get(i).isOffline() == false) {
				return this.parkingSlots.get(i);
			}
		}
		throw new NoParkingSlotAvailableException("No parking slot available in "+this+".");
	}
	
	public ParkingSlot getParkingSlot(int index) 
			throws IllegalArgumentException, RuntimeException {
		if (index < 0)
			throw new IllegalArgumentException("The parking slot index should be positive.");
		if (index > this.parkingSlots.size()-1)
			throw new RuntimeException("The station "+this.name+" contains only "
				+this.parkingSlots.size()+" parking slots.");	
		return this.parkingSlots.get(index);
	}
	
	/**
	 * Returns true if there is no free parking slot available in the station.
	 * If there is at least one free parking slot (online and holding no bicycle),
	 * returns false.
	 * @return true if the station is full, false otherwise
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
	 * Returns an available bicycle of the station (the first available in the order
	 * of parking slots) if it exists AND empties the corresponding parking slot. 
	 * If no such bicycle exists, throws a RuntimeException.
	 * @return an available bicycle
	 * @throws RuntimeException	When no bicycle is available
	 */
	public Bicycle getBicycle() throws NoBicycleAvailableException {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& !this.parkingSlots.get(i).isOffline()) {
				Bicycle bicycle = this.parkingSlots.get(i).getBicycle();
				this.parkingSlots.get(i).detachBicycle();
				return bicycle;
			}
		}
		throw new NoBicycleAvailableException("No bicycle is available in "+this+".");
	}
		
	/**
	 * Returns an available bicycle of given type of the station (the first available in the order
	 * of parking slots) if it exists AND empties the corresponding parking slot. 
	 * If no such bicycle exists, throws a RuntimeException.
	 * @param bicycleType A string corresponding on the bicycle type
	 * @return an available bicycle of given type
	 * @throws RuntimeException	When no bicycle is available
	 */
	public Bicycle getBicycle(String bicycleType) throws NoBicycleAvailableException {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& !this.parkingSlots.get(i).isOffline()) {
				if (bicycleType.equalsIgnoreCase(this.parkingSlots.get(i).getBicycle().getType())) {
					Bicycle bicycle = this.parkingSlots.get(i).getBicycle();
					this.parkingSlots.get(i).detachBicycle();
					this.updateStatus();
					return bicycle;
				}
			}
		}
		throw new NoBicycleAvailableException("No bicycle of type "+bicycleType+" is available in "+this+".");
	}
	
	/**
	 * Returns true if there is at least one available bicycle in the station.
	 * Returns false otherwise.
	 * @return true if there is a bicycle, false otherwise
	 */
	public boolean isBicycle() {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle() 
			&& !this.parkingSlots.get(i).isOffline()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if there is at least one available bicycle of 
	 * given type in the station. Returns false otherwise.
	 * @param bicycleType a String that correspond to the type of bicycle wanted
	 * @return true if there is a bicycle of given type, false otherwise
	 */
	public boolean isBicycle(String bicycleType) {
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			if (this.parkingSlots.get(i).isBicycle()
			&& !this.parkingSlots.get(i).isOffline()) {
				if (bicycleType.equalsIgnoreCase(this.parkingSlots.get(i).getBicycle().getType())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns one available type of bicycle in the station.
	 * For example, if the station contains electrical and mechanical bicycles,
	 * this method can return either "electrical" or "mechanical" (depending
	 * on the order of the bicycles appearance in the parking slots).
	 * If no bicycle is available, throws a RuntimeException.
	 * @return a type of bicycle as a String
	 * @throws RuntimeException	occurs when no bicycle is available
	 */
	public String getOneBicycleType() {
		if (!this.isBicycle())
			throw new NoBicycleAvailableException("No bicycle is available in "+this+".");
		for (int i = 0; i < Bicycle.getTypeDict().size(); i++) {
			if (this.isBicycle(Bicycle.getTypeDict().get(i))) {
				return Bicycle.getTypeDict().get(i);
			}
		}
		return null;
	}
	
	/**
	 * This method populates a station with the list of bicycles passed in argument.
	 * Only the available parking slots are populated. The bicycles are attributed to
	 * each available parking slot by order of appearance, starting from the beginning 
	 * of the list, until all bicycles have been attributed or until there are no
	 * more available parking slots.
	 * @param bicycleList a population of bicycles
	 */
	public void populate(ArrayList<Bicycle> bicycleList) {
		ArrayList<Bicycle> bList = (ArrayList<Bicycle>) bicycleList.clone();
		for (int i = 0; i < this.parkingSlots.size(); i++) {
			if (bList.size() == 0)
				break;
			try {
				this.parkingSlots.get(i).attachBicycle(bList.get(0));
			} 
			catch (Exception e) {}
			bList.remove(0);
		}
		if (bList.size() == 0)
			System.out.println(bicycleList.size()+" bicycles have been attached to "
					+ this+".");
		else {
			System.out.println(bicycleList.size()-bList.size()+" have been attached. "
					+ bList.size()+ " input bicycles have been ignored.");
		}
		this.updateStatus();
	}

	/**
	 * 
	 * @return id of the station
	 */
	public long getId() {
		return this.id;
	}
	/**
	 * 
	 * @return true if the station is Plus, false otherwise
	 */
	public boolean isPlus() {
		return this.isPlus;
	}
	
	public void setPlus(Boolean isPlus) {
		this.isPlus = isPlus;
	}
	
	public int getRentCount() {
		return rentCount;
	}

	public void incRentCount() {
		this.rentCount++;
	}

	public int getReturnCount() {
		return returnCount;
	}

	public void incReturnCount() {
		this.returnCount++;
	}

	/**
	 * 
	 * @return localization of the station
	 */
	public Localization getLocalization() {
		return this.localization;
	}

	/**
	 * 
	 * @return name of the station
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return true if the station is offline, false otherwise
	 */
	public Boolean isOffline() {
		return this.isOffline;
	}

	/**
	 * A call to this method sets the station and all its parking slots 
	 * in the state specified in argument.
	 * @param isOffline  true to set the station offline, false to set it online
	 */
	public void setOffline(Boolean isOffline) {
		this.isOffline = isOffline;
		// Set all parking slots off/online
		for (int i = 0; i <= this.parkingSlots.size()-1; i++) {
			this.parkingSlots.get(i).setOffline(isOffline);
		}
		if (isOffline)
			System.out.println(this+" is now offline.");
		else
			System.out.println(this+" is now online.");
	}

	/**
	 * 
	 * @return the list of parking slots of the station
	 */
	public ArrayList<ParkingSlot> getParkingSlots() {
		return this.parkingSlots;
	}

	/**
	 * Adds a given parking slot to the station. This method is automatically called
	 * when creating a parking slot associated to the station.
	 * @param parkingSlot	the parking slot you to add
	 * @throws IllegalArgumentException		when the parkingSlot is already in the station
	 * or linked to another station
	 */
	public void addParkingSlot(ParkingSlot parkingSlot) throws IllegalArgumentException {
		if (parkingSlot.getStation().getId() != this.id) {
			throw new IllegalArgumentException("Can't link with a "
					+ "parking slot associated to another station.");
		}
		if (this.getParkingSlots().contains(parkingSlot)) {
			throw new IllegalArgumentException("This parking slot"
					+ "is already linked with the station.");
		}
		this.parkingSlots.add(parkingSlot);
		if (!this.initializing)
			this.updateStatus();
	}
	
	/**
	 * Adds a number of new parking slots to the station.
	 * @param quantity number of new parking slots
	 * @throws IllegalArgumentException	when the quantity is not positive
	 */
	public void createParkingSlots(int quantity) throws IllegalArgumentException {
		if (quantity < 0) {
			throw new IllegalArgumentException("Must enter a positive"
					+ " quantity.");
		}
		for (int i = 0; i < quantity; i++) {
			new ParkingSlot(this);
		}
		this.initializing = false;
	}
	
	/**
	 * 
	 * @return the list of planned rides heading to the station
	 */
	public ArrayList<Travel> getTargetOf() {
		return this.targetOf;
	}

	
	/**
	 * This method is called when a planned ride Travel suggests this station either
	 * as a start station or end station to a user. The Travel then becomes an 
	 * observer of this station and is notified if needed.
	 * @param travel	The Travel object
	 */
	public void addTargetOf(Travel travel) {
		this.targetOf.add(travel); 
	}
	
	/**
	 * Removes a planned ride Travel which was previously observing the station.
	 * @param travel an planned ride observer of the station
	 */
	public void removeTargetOf(Travel travel) {
		this.targetOf.remove(travel); 
	}
 
	/**
	 * This method is automatically called when any operation (rental, return, offline, online, parking slot creation)
	 * is performed in the station, including any of its parking slots. <br>
	 * It does two things :
	 * <br>- If the station becomes full, or empty of some bicycle type, as a 
	 * consequence of this operation, notifies all the planned rides Travel 
	 * currently observing the station (to rent a bicycle or return one) 
	 * which could be affected by this change
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
								&& this.targetOf.get(i).getBicycleType().
								equalsIgnoreCase(Bicycle.getTypeDict().get(j))) {
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
		 * Constructor of class State. Records the time and date at the instant
		 * of its creation, as well as the status of all the parking slots of the station :
		 * their status (online/offline) and their occupation (free/occupied)
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
		
		/**
		 * 
		 * @return the time stamp associated to the state
		 */
		public Date getTimeStamp() {
			return timeStamp;
		}
		
		/**
		 * 
		 * @return the status of all the parking slots of the station at the 
		 * moment of the time stamp
		 */
		public ArrayList<ArrayList<Boolean>> getParkingSlotStatus() {
			return parkingSlotStatus;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String res = timeStamp + " -> / ";
			for (int i = 0; i < parkingSlotStatus.size(); i++) {
				res += "slot "+i+" : ";
				if (parkingSlotStatus.get(i).get(0))
					res += "offline, ";
				else
					res += "online, ";
				if (parkingSlotStatus.get(i).get(1))
					res += "occupied";
				else
					res += "free";
				res += " / ";
			}
			return res;
		}
		
	}
	
	/**
	 * Returns a representation of the full history of the station, ie. the 
	 * list of states of the station since its creation.
	 * @return a representation of the full history of the station
	 */
	public String historyTrace() {
		String res = "----------------------"+"\n"+"History of "+this.toString()+ "\n";
		for (int i = 0; i < this.history.size(); i++)
			res += "\n"+this.history.get(i);
		return res+"\n"+"----------------------";
	}
	
	/**
	 * 
	 * @return the most recent state of the station
	 */
	public State getCurrentState() {
		return this.history.get(this.history.size()-1);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String status;
		if (this.isOffline)
			status = "offline";
		else
			status = "online";
		return this.name+" (id."+this.id+"), "+status+"";
	}
}	

