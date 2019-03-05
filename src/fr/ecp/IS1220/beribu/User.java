package fr.ecp.IS1220.beribu;


import java.util.ArrayList;
import java.util.Date;

public class User {
	private static long uniqId;
	private long id;
	private String name;
	private Localization localization;
	private Duration timeCreditBalance = new Duration();
	private Card card = new Standard(this);
	private ArrayList<Ride> listOfRides;
	
	public User(String name) {
		super();
		this.name = name;
		this.id = uniqId++;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
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

	public void addTimeCreditBalance(int minutes) {
		this.timeCreditBalance.add(minutes);
	}
	
	public void addTimeCreditBalance(int minutes, int seconds) {
		this.timeCreditBalance.add(minutes, seconds);
	}
	
	public void addTimeCreditBalance(int hours, int minutes, int seconds) {
		this.timeCreditBalance.add(hours, minutes, seconds);
	}
	
	public void setTimeCreditBalance(int duration) {
		this.timeCreditBalance.setDuration(duration);
	}

	public Card getCard() {
		return this.card;
	}

	public void subscribe(Card card) {
		this.card = card;
	}

	public ArrayList<Ride> getListOfRides() {
		return this.listOfRides;
	}
	
	public void newRide(Station station) {
		try {
			Bicycle bicycle = station.getBicycle();
			this.listOfRides.add(new Ride(this,bicycle,station));
		}
		catch(RuntimeException exception) {
			System.err.println("Please change to another station.");
		}
	}
	
	public void newRide(Station station, String bicycleType) {
		try {
			Bicycle bicycle = station.getBicycle(bicycleType);
			this.listOfRides.add(new Ride(this,bicycle,station));
		}
		catch(RuntimeException exception) {
			System.err.println("Please try another bicycle type"
					+ "or change to another station.");
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
