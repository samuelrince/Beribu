import java.util.ArrayList;
import java.util.Date;

public class User {
	private static long uniqId;
	private long id;
	private String name;
	private Localization localization;
	private Duration timeCreditBalance = new Duration();
	private Card card = null;
	private Ride[] listOfRides;
	
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

	public void setLocalisation(double[] localization) {
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

	public Card getCard() {
		return this.card;
	}

	public void subscribe(Card card) {
		this.card = card;
	}

	public Ride[] getListOfRides() {
		return this.listOfRides;
	}

	public void setListOfRides(Ride[] listOfRides) {
		this.listOfRides = listOfRides;
	}
	
	public void newRide(Station station, String bicyleType) {
		if (station.isAvailable(bicycleType)) {
			Bicycle bicycle;
			Date time;
			Ride ride = new Ride(this,bicycle,station,time);
		}
		else {
			System.out.println("Désolé, le type de vélo souhaité n'est pas disponible.");
		}
	}
	 
	public void newTravel(double[] source, double[] destination, 
			String bicyleType, PathStrategy pathStrategy) {
		
	}
}
