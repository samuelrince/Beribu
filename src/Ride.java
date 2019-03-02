
public class Ride {
	private static long uniqId;
	private long id;
	private User user;
	private Bicycle bicyle;
	private Station startStation;
	private Date startTime;
	private Station endStation;
	private Date endTime;
	private boolean current;
	private double price;
	
	public Ride(User user, Bicycle bicyle, Station startStation) {
		Date startTime = new Date();
		this.user = user;
		this.bicyle = bicyle;
		this.startStation = startStation;
		this.startTime = startTime;
		this.current = true;
		this.id = uniqId++;
	}

	public void end(ParkingSlot parkingSlot) {
		if (parkingSlot.isAvailable()) {
			Date endTime = new Date();
			Station station;
			this.endTime = endTime;
			this.endStation = station;
			this.current = false;
			this.pay();
		}
		else {
			throw new Exception("Cette place est déjà prise ou hors-service.");
		}
	}
	
	public void pay() {
		double duration;
		//compute duration
		//choice costStrategy
		this.price = 0;
	}
}
