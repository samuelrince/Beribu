package fr.ecp.IS1220.myVelib.core.user;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import fr.ecp.IS1220.myVelib.core.bicycle.Bicycle;
import fr.ecp.IS1220.myVelib.core.card.Card;
import fr.ecp.IS1220.myVelib.core.card.CardFactory;
import fr.ecp.IS1220.myVelib.core.card.Standard;
import fr.ecp.IS1220.myVelib.core.exception.NoNewRideException;
import fr.ecp.IS1220.myVelib.core.exception.SuchStationIsFullException;
import fr.ecp.IS1220.myVelib.core.exception.SuchStationIsOfflineException;
import fr.ecp.IS1220.myVelib.core.ride.PathStrategy;
import fr.ecp.IS1220.myVelib.core.ride.Ride;
import fr.ecp.IS1220.myVelib.core.ride.Travel;
import fr.ecp.IS1220.myVelib.core.station.ParkingSlot;
import fr.ecp.IS1220.myVelib.core.station.Station;
import fr.ecp.IS1220.myVelib.core.system.Duration;
import fr.ecp.IS1220.myVelib.core.system.Localization;

/**
 * This class represents a user.
 * @author Valentin
 *
 */
public class User implements java.io.Serializable {
	private static long uniqId;
	private Date creationDate;
	private long id;
	private String name;
	private String passwordHash;
	private Localization localization;
	private Duration timeCreditBalance = new Duration();
	private Card card = new Standard(this);
	private ArrayList<Ride> listOfRides = new ArrayList<Ride>();
	private Travel plannedRide;
	private transient MsgBox msgBox = new MsgBox(this);
	
	/**
	 * Constructor of User class.
	 * @param name	Name and First name of the user
	 */
	public User(String name) {
		super();
		this.id = uniqId++;
		this.name = name;
		try {
			this.passwordHash = hashPassword("password");
		} catch(NoSuchAlgorithmException e) {}
		this.creationDate = new Date();
		System.out.println("New user "+this+".");
	}
	
	public User(String name, Localization loc) {
		super();
		this.name = name;
		this.id = uniqId++;
		this.creationDate = new Date();
		this.localization = loc;
		System.out.println("New user "+this+".");
	}
	
	public User() {
		super();
		this.id = uniqId++;
		this.name = "Bob"+this.id;
		try {
			this.passwordHash = hashPassword("password");
		} catch(NoSuchAlgorithmException e) {}
		this.creationDate = new Date();
		System.out.println("New user "+this+".");
	}
	
	public User(String name, String password) {
		super();
		this.id = uniqId++;
		this.name = name;
		try {
			this.passwordHash = hashPassword(password);
		} catch(NoSuchAlgorithmException e) {}
		this.creationDate = new Date();
		System.out.println("New user "+this+".");
	}

	public User(String name, String password, Localization loc) {
		super();
		this.id = uniqId++;
		this.name = name;
		try {
			this.passwordHash = hashPassword(password);
		} catch(NoSuchAlgorithmException e) {}
		this.creationDate = new Date();
		this.localization = loc;
		System.out.println("New user "+this+".");
	}
	
	public User(Localization loc) {
		super();
		this.id = uniqId++;
		this.name = "Bob"+this.id;
		this.creationDate = new Date();
		this.localization = loc;
		System.out.println("New user "+this+".");
	}
	
	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}
	
	public void setPasswordHash(String password) {
		try {
			this.passwordHash = hashPassword(password);
		} catch(NoSuchAlgorithmException e) {}
	}
	
	public Date getCreationDate() {
		return this.creationDate;
	}

	public Localization getLocalization() {
		return this.localization;
	}

	public void setLocalisation(Localization localization) {
		this.localization = localization;
	}

	public Duration getTimeCreditBalance() {
		return this.timeCreditBalance;
	}
	
	/**
	 * This method adds time to the user credit balance.
	 * The duration to add is expressed in minutes.
	 * @param minutes correspond to minutes
	 */
	public void addTimeCreditBalance(int minutes) {
		this.timeCreditBalance.add(minutes);
	}
	
	/**
	 * This method adds time to the user time credit balance.
	 * The duration to add is expressed in minutes and in seconds.
	 * @param minutes correspond to minutes
	 * @param seconds correspond to seconds
	 */
	public void addTimeCreditBalance(int minutes, int seconds) {
		this.timeCreditBalance.add(minutes, seconds);
	}
	
	/**
	 * This method adds time to the user credit balance.
	 * The duration to add is expressed in hours, in minutes and in seconds.
	 * @param hours	correspond to hours
	 * @param minutes correspond to minutes
	 * @param seconds correspond to seconds
	 */
	public void addTimeCreditBalance(int hours, int minutes, int seconds) {
		this.timeCreditBalance.add(hours, minutes, seconds);
	}

	public Card getCard() {
		return this.card;
	}

	/**
	 * This method is called when a user subscribes to a subscription program.
	 * The type of subscription is handle by the type of card the user subscribed to.
	 * The Card object calls this method when being created at the user's name.
	 * @param card		Should be a card
	 * @throws RuntimeException		When a user tries to subscribe to a wrong card (a card
	 * that belongs to another user)
	 */
	public void subscribe(Card card) {
		if (card.getUser() == this) {
			this.card = card;
			System.out.println(this+" has a new subscription of type "+card.getType()+ ".");			
		} else {
			throw new IllegalArgumentException("A user cannot subscribe to a card that belongs to another user.");
		}
	}
	
	public void subscribe(String cardType) {
		CardFactory cardFactory = new CardFactory();
		if (!this.getCard().getType().equalsIgnoreCase(cardType))
			this.subscribe(cardFactory.newCard(cardType, this));
	}
	
	public ArrayList<Ride> getListOfRides() {
		return this.listOfRides;
	}
	
	public String getHistory() {
		String res = "History of "+this.getName();
		if (this.listOfRides.size() == 0)
			res += "\n"+"\n"+"-empty-";
		else {
			for (int i =0 ; i < this.listOfRides.size(); i++) 
				res += "\n"+"\n"+this.listOfRides.get(i);
		}
		return res;
	}


	/**
	 * This private method returns true if the user is currently on a ride, false otherwise.
	 * @return boolean 
	 */
	public boolean isOnRide() {

		if (!this.listOfRides.isEmpty()) {
			Ride lastRide = this.listOfRides.get(this.listOfRides.size() - 1);
			if (lastRide.getEndStation() == null || lastRide.getEndTime() == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	/**
	 * This method is used to generate a new Ride object stored in listOfRides attribute.
	 * <br><br>
	 * <b>Use case:</b> <br>
	 * The user approaches a station and decides to take a bike for a ride. The user has
	 * no preference on the type of bicycle. So the station "decides" for him to choose a bicycle 
	 * and take it off from the right parking slot, or returns an error if no bike is available.
	 * 
	 * @param station	Should be a Station object
	 * @throws RuntimeException		Throws RuntimeException when a user tries to start
	 * a new ride without finishing his current ride.
	 */
	public void newRide(Station station) throws RuntimeException {
		if (!this.isOnRide()) {
			if (station.isOffline()) {
				throw new SuchStationIsOfflineException("The station is offline");
			}
			Bicycle bicycle = station.getBicycle();
			Ride ride = new Ride(this,bicycle,station);
			this.listOfRides.add(ride);
			System.out.println(this+" has started"
					+ " a new ride from "+station+".");
			if (this.plannedRide != null) {
				if (this.plannedRide.isOngoing()){
					this.plannedRide.setBicycleType(bicycle.getType());
					this.plannedRide.setStartStation(station);
				}
			}
			station.incRentCount();
			station.updateStatus();
		} 
		else {
			throw new NoNewRideException("User " + this.getName() + " has not finished their current ride.");
		}
	}
	
	/**
	 * This method is used to generate a new Ride object stored in listOfRides attribute.
	 * <br><br>
	 * <b>Use case:</b> <br>
	 * The user approaches a station and decides to take a bike for a ride. The user has
	 * preference on the type bicycle. So the station delivers the right bicycle if possible
	 * and return an error otherwise. 
	 * 
	 * @param station	Should be a Station object
	 * @param bicycleType	A String corresponding to the type of bicycle wanted
	 * @throws RuntimeException		Throws RuntimeException when a user tries to start
	 * a new ride without finishing his current ride.
	 */
	public void newRide(Station station, String bicycleType) throws RuntimeException {
		if (!this.isOnRide()) {
			Bicycle bicycle;
			if (bicycleType == null)
				bicycle = station.getBicycle();
			else
				bicycle = station.getBicycle(bicycleType);
			Ride ride = new Ride(this,bicycle,station);
			this.listOfRides.add(ride);
			System.out.println(this+" has started"
					+ " a new ride from "+station+".");
			if (this.plannedRide != null) {
				if (this.plannedRide.isOngoing()){
					this.plannedRide.setBicycleType(bicycle.getType());
					this.plannedRide.setStartStation(station);
				}
			}
				station.incRentCount();
				station.updateStatus();
		} 
		else {
			throw new NoNewRideException("User " + this.getName() + " has not finished his last ride.");
		}
	}
	
	public void newRide(Station station, Bicycle bike) throws RuntimeException {
		if (!this.isOnRide()) {
			boolean bikeAttachedToStation = false;
			for (ParkingSlot ps: station.getParkingSlots()) {
				if (bike.equals(ps.getBicycle()))
					bikeAttachedToStation = true;
			}
			if (!bikeAttachedToStation)
				throw new NoNewRideException("Wrong bike selection");
			Ride ride = new Ride(this,bike,station);
			this.listOfRides.add(ride);
			System.out.println(this+" has started"
					+ " a new ride from "+station+".");
			if (this.plannedRide != null) {
				if (this.plannedRide.isOngoing()){
					this.plannedRide.setBicycleType(bike.getType());
					this.plannedRide.setStartStation(station);
				}
			}
				station.incRentCount();
				station.updateStatus();
		} 
		else {
			throw new NoNewRideException("User " + this.getName() + " has not finished his last ride.");
		}
	}

	/**
	 * This method returns the current (not finished) ride of a user if it exists.
	 * @return Ride		Ride object or <b>null</b> if not current ride
	 */
	public Ride getCurrentRide() {
		if (this.isOnRide()) {
			return this.listOfRides.get(this.listOfRides.size() - 1);
		} else {
			return null;
		}
	}
	
	/**
	 * This method should be used when a user wants to end their current Ride in a
	 * given station.
	 * @param station	the return station
	 * @throws Exception	occurs when there is no parking slot available
	 */
	public void endCurrentRide(Station station) throws Exception {
		if (station.isFull())
			throw new SuchStationIsFullException("The station is full.");
		else {
			if (this.getCurrentRide() != null) {
				this.getCurrentRide().end(station.getFreeParkingSlot());
				//station.incReturnCount(); TEMPORARY
				System.out.println(this+" has ended"
						+ " their ride in "+station+".");
				station.updateStatus();
				this.notifyUser("Thank you for using MyVelib network!\n You will"
						+ " find a sum up of your last ride in 'My Statistics'.");
			}
		}
	}
	
	public Ride getLastRide() {
		if (this.listOfRides.size() > 0)
			return this.listOfRides.get(this.listOfRides.size() - 1);
		return null;
	}
	
	/**
	 * This method should be used when a user want to plan a future ride.
	 * A new object Travel associated to the user is created.
	 * The user then has the possibility to find automatically a start station
	 * and an end station, and to calculate the duration and cost of this ride.
	 * By default, the start station and end station are chosen so as to minimize
	 * the walking time. The type of bicycle is indifferent.
	 * @param source	the initial position of the user
	 * @param destination	the destination of the user
	 */
	public void planRide(Localization source, Localization destination) {
		this.plannedRide = new Travel(this,source,destination);
	}
	/**
	 * This method should be used when a user want to plan a future ride.
	 * A new object Travel associated to the user is created.
	 * The user then has the possibility to find automatically a start station
	 * and an end station according to the strategy specified by the user,
	 * and to calculate the duration and cost of this ride. 
	 * The type of bicycle is indifferent.
	 * @param source	the initial position of the user
	 * @param destination	the destination of the user
	 * @param pathStrategy	the PathStrategy employed to chose the start and end stations
	 */
	public void planRide(Localization source, Localization destination,
			PathStrategy pathStrategy) {
		this.plannedRide = new Travel(this,source,destination,pathStrategy);
	}
	/**
	 * This method should be used when a user want to plan a future ride.
	 * A new object Travel associated to the user is created.
	 * The user then has the possibility to find automatically a start station
	 * and an end station according to the strategy specified by the user,
	 * and to calculate the duration and cost of this ride.
	 * The user should specify the desired type of bicycle.
	 * @param source	the initial position of the user
	 * @param destination	the destination of the user
	 * @param bicycleType	the desired type of bicycle
	 * @param pathStrategy	the PathStrategy employed to chose the start and end stations
	 */
	public void planRide(Localization source, Localization destination, 
			String bicycleType, PathStrategy pathStrategy) {
		this.plannedRide = new Travel(this,source,destination,pathStrategy,bicycleType);
	}
	/**
	 * This method should be used when a user want to plan a future ride.
	 * A new object Travel associated to the user is created.
	 * The user then has the possibility to find automatically a start station
	 * and an end station, and to calculate the duration and cost of this ride.
	 * By default, the start station and end station are chosen so as to minimize
	 * the walking time. The user should specify the desired type of bicycle.
	 * @param source	the initial position of the user
	 * @param destination	the destination of the user
	 * @param bicycleType	the desired type of bicycle
	 */
	public void planRide(Localization source, Localization destination, 
			String bicycleType) {
		this.plannedRide = new Travel(this,source,destination,bicycleType);
	}
	
	public Travel getPlannedRide() {
		return this.plannedRide;
	}
	
	public void discardPlannedRide() {
		if (this.plannedRide != null) {
			if (this.plannedRide.getSuggestedStartStation() != null)
				this.plannedRide.getSuggestedStartStation().removeTargetOf(this.plannedRide);
			if (this.plannedRide.getSuggestedEndStation() != null)
				this.plannedRide.getSuggestedEndStation().removeTargetOf(this.plannedRide);
			if (this.plannedRide.getObserver() != null)
				this.plannedRide.removeObserver();
			this.plannedRide = null;
			System.out.println(this+"has discarded their planned ride.");
		}
		else {
			System.out.println(this+"has no planned ride to discard.");
		}
	}

	public MsgBox getMsgBox() {
		return this.msgBox;
	}
	
	public void notifyUser(String message) {
		this.msgBox.add(message);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" (id."+this.id+")";
	}
	
	private String hashPassword(String password) throws NoSuchAlgorithmException{
		return PasswordHash.hashPassword(password);
	}
	
	public static void resetUniqID() {uniqId=0;}
}
