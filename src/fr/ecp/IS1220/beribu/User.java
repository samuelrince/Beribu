package fr.ecp.IS1220.beribu;


import java.util.ArrayList;
import java.util.Date;

public class User {
	private static long uniqId;
	private static ArrayList<User> userDataBase = new ArrayList<User>();
	private Date creationDate;
	private long id;
	private String name;
	private Localization localization;
	private Duration timeCreditBalance = new Duration();
	private Card card = new Standard(this);
	private ArrayList<Ride> listOfRides = new ArrayList<Ride>();
	
	/**
	 * Constructor of User class.
	 * @param name	Should be a String
	 */
	public User(String name) {
		super();
		this.name = name;
		this.id = uniqId++;
		this.creationDate = new Date();
		userDataBase.add(this);
	}

	public static ArrayList<User> allUsers() {
		return userDataBase;
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
	 * This method adds time to the user credit balance.
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
	 * @param minutes
	 * @param seconds
	 */
	public void addTimeCreditBalance(int hours, int minutes, int seconds) {
		this.timeCreditBalance.add(hours, minutes, seconds);
	}
	
	public void setTimeCreditBalance(int duration) {
		this.timeCreditBalance.setDuration(duration);
	}

	public Card getCard() {
		return this.card;
	}

	/**
	 * This method is used when a user subscribe to subscription program.
	 * The type of subscription is handle by the type of card the user subscribed to.
	 * The card object call the user.subscribe method during the subscription process.
	 * @param card		Should be a card
	 * @throws RuntimeException		When a user tries to subscribe to a wrong card (a card)
	 * that belongs to another user
	 */
	public void subscribe(Card card) throws RuntimeException {
		if (card.getUser() == this) {
			this.card = card;
		} else {
			throw new RuntimeException("A user cannot subscribe to a card that belongs to another user");
		}
	}

	public ArrayList<Ride> getListOfRides() {
		return this.listOfRides;
	}
	

	/**
	 * This private method asserts true if the user is currently on ride, false otherwise.
	 * @return boolean 
	 */
	private boolean isOnRide() {

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
	 * no preference on the type bicycle. So the station "decides" for him to choose a bicycle 
	 * and take it off from the right parking slot.
	 * 
	 * @param station	Should be a Station object
	 * @throws RuntimeException		Throws RuntimeException when a user tries to start
	 * a new ride without finishing his last ride.
	 */
	public void newRide(Station station) throws RuntimeException {
		if (!this.isOnRide()) {
			try {
				Bicycle bicycle = station.getBicycle();
				this.listOfRides.add(new Ride(this,bicycle,station));
			}
			catch(RuntimeException exception) {
				System.err.println("Please change to another station.");
			}
		} else {
			throw new RuntimeException("User " + this.getName() + " has not finished his last ride.");
		}
	}
	
	/**
	 * This method is used to generate a new Ride object stored in listOfRides attribute.
	 * <br><br>
	 * <b>Use case:</b> <br>
	 * The user approaches a station and decides to take a bike for a ride. The user has
	 * preference on the type bicycle. So the station deliver the right bicycle if possible
	 * and return an error otherwise. 
	 * 
	 * @param station	Should be a Station object
	 * @throws RuntimeException		Throws RuntimeException when a user tries to start
	 * a new ride without finishing his last ride.
	 */
	public void newRide(Station station, String bicycleType) throws RuntimeException {
		if (!this.isOnRide()) {
			try {
				Bicycle bicycle = station.getBicycle(bicycleType);
				this.listOfRides.add(new Ride(this,bicycle,station));
			}
			catch(RuntimeException exception) {
				System.err.println("Please try another bicycle type "
						+ "or change to another station.");
			}
		} else {
			throw new RuntimeException("User " + this.getName() + " has not finished his last ride.");
		}
	}
	
	/**
	 * This method returns the current (not finished) ride of a user.
	 * @return Ride		Should be a Ride object or return <b>null<b> otherwise
	 */
	public Ride getCurrentRide() {
		if (this.isOnRide()) {
			return this.listOfRides.get(this.listOfRides.size() - 1);
		} else {
			return null;
		}
	}
	
	public void planRide(Localization source, Localization destination) {
		Travel travel = new Travel(this,source,destination);
	}
	public void planRide(Localization source, Localization destination,
			PathStrategy pathStrategy) {
		Travel travel = new Travel(this,source,destination,pathStrategy);
	}
	public void planRide(Localization source, Localization destination, 
			String bicycleType, PathStrategy pathStrategy) {
		Travel travel = new Travel(this,source,destination,pathStrategy,bicycleType);
	}
	public void planRide(Localization source, Localization destination, 
			String bicycleType) {
		Travel travel = new Travel(this,source,destination,bicycleType);
	}

	public void notifyUser(String message) {
		System.out.println(message);
	}
	
	public static void main(String[] args) {
		User user1 = new User("Robert Downey Jr.");
		System.out.println(user1);
	}
	
}
