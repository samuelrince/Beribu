package fr.ecp.IS1220.beribu;


import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a user.
 * @author Valentin
 *
 */
public class User {
	private static long uniqId;
	private Date creationDate;
	private long id;
	private String name;
	private Localization localization;
	private Duration timeCreditBalance = new Duration();
	private Card card = new Standard(this);
	private ArrayList<Ride> listOfRides = new ArrayList<Ride>();
	private Travel plannedRide;
	
	/**
	 * Constructor of User class.
	 * @param name	Name and First name of the user
	 */
	public User(String name) {
		super();
		this.name = name;
		this.id = uniqId++;
		this.creationDate = new Date();
		System.out.println("New user "+this+".");
	}
	
	public User() {
		super();
		this.id = uniqId++;
		this.name = "Bob"+this.id;
		this.creationDate = new Date();
		System.out.println("New user "+this+".");
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
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
	 * @param minutes
	 */
	public void addTimeCreditBalance(int minutes) {
		this.timeCreditBalance.add(minutes);
	}
	
	/**
	 * This method adds time to the user time credit balance.
	 * The duration to add is expressed in minutes and in seconds.
	 * @param minutes
	 * @param seconds
	 */
	public void addTimeCreditBalance(int minutes, int seconds) {
		this.timeCreditBalance.add(minutes, seconds);
	}
	
	/**
	 * This method adds time to the user credit balance.
	 * The duration to add is expressed in hours, in minutes and in seconds.
	 * @param hours
	 * @param minutes
	 * @param seconds
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
	public void subscribe(Card card) throws IllegalArgumentException {
		if (card.getUser() == this) {
			this.card = card;
			System.out.println(this+" has a new subscription of type "+card.getType()+ ".");			
		} else {
			throw new IllegalArgumentException("A user cannot subscribe to a card that belongs to another user");
		}
	}
	public void subscribe(String cardType) {
		CardFactory cardFactory = new CardFactory();
		this.subscribe(cardFactory.newCard(cardType, this));
	}
	
	public ArrayList<Ride> getListOfRides() {
		return this.listOfRides;
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
			try {
				Bicycle bicycle = station.getBicycle();
				Ride ride = new Ride(this,bicycle,station);
				this.listOfRides.add(ride);
				if (this.plannedRide != null) {
					if (this.plannedRide.isOngoing()){
						this.plannedRide.setBicycleType(bicycle.getType());
						this.plannedRide.setSuggestedStartStation(station);
					}
				}
				station.incRentCount();
				System.out.println(this+" has started"
						+ " a new ride from "+station+".");
			}
			catch(RuntimeException exception) {
				System.err.println("No bicycle available. Please change to another station.");
			}
		} 
		else {
			throw new RuntimeException("User " + this.getName() + " has not finished their current ride.");
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
	 * @throws RuntimeException		Throws RuntimeException when a user tries to start
	 * a new ride without finishing his current ride.
	 */
	public void newRide(Station station, String bicycleType) throws RuntimeException {
		if (!this.isOnRide()) {
			try {
				Bicycle bicycle = station.getBicycle(bicycleType);
				Ride ride = new Ride(this,bicycle,station);
				this.listOfRides.add(ride);
				if (this.plannedRide != null) {
					if (this.plannedRide.isOngoing()){
						this.plannedRide.setBicycleType(bicycle.getType());
						this.plannedRide.setSuggestedStartStation(station);
					}
				}
				station.incRentCount();
				System.out.println(this+" has started"
						+ " a new ride from "+station+".");
			}
			catch(RuntimeException exception) {
				System.err.println("Please try another bicycle type "
						+ "or change to another station.");
			}
		} 
		else {
			throw new RuntimeException("User " + this.getName() + " has not finished his last ride.");
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
	
	public void endCurrentRide(Station station) {
		if (station.isFull())
			throw new IllegalArgumentException("This station is full.");
		else {
			if (this.getCurrentRide() != null) {
				this.getCurrentRide().end(station.getFreeParkingSlot());
				station.incReturnCount();
				System.out.println(this+" has ended"
						+ " their ride in "+station+".");
			}
		}
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
			this.plannedRide.suggestedStartStation.removeTargetOf(this.plannedRide);
			this.plannedRide.suggestedEndStation.removeTargetOf(this.plannedRide);
			this.plannedRide = null;
			System.out.println(this+"has discarded their planned ride.");
		}
		else {
			System.out.println(this+"has no planned ride to discard.");
		}
	}

	public void notifyUser(String message) {
		System.out.println("For "+this+" : "+message);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" (id."+this.id+")";
	}
	
	public static void main(String[] args) {
		User user1 = new User("Robert Downey Jr.");
		System.out.println(user1);
	}
	
}
