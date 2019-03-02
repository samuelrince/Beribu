import java.util.ArrayList;
import java.util.Date;

public class User {
	private static long uniqId;
	private long id;
	private String name;
	private Localization localization;
	private double timeCreditBalance = 0;
	private Card card = null;
	private Ride[] listOfRides;
	
	public User(String name) {
		super();
		this.name = name;
		this.id = uniqId++;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Localization getLocalization() {
		return this.localization;
	}

	public void setLocalisation(double[] localisation) {
		this.localisation = localisation;
	}

	public double getTimeCreditBalance() {
		return this.timeCreditBalance;
	}

	public void setTimeCreditBalance(double timeCreditBalance) {
		this.timeCreditBalance = timeCreditBalance;
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
