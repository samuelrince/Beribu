
public class User {
	private static int uniqId = 00001;
	private int id;
	private String name;
	private double[] localisation;
	private double timeCreditBalance;
	private Card card;
	private Ride[] listOfRides;
	
	public User(String name) {
		super();
		this.name = name;
		this.id = uniqId;
		uniqId += 1;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double[] getLocalisation() {
		return localisation;
	}

	public void setLocalisation(double[] localisation) {
		this.localisation = localisation;
	}

	public double getTimeCreditBalance() {
		return timeCreditBalance;
	}

	public void setTimeCreditBalance(double timeCreditBalance) {
		this.timeCreditBalance = timeCreditBalance;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Ride[] getListOfRides() {
		return listOfRides;
	}

	public void setListOfRides(Ride[] listOfRides) {
		this.listOfRides = listOfRides;
	}
	
	public void newRide() {
	}
	 
}
