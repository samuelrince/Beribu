
public class Ride {
	private static long uniqId;
	private long id;
	private User user;
	private Bicycle bicycle;
	private Station startStation;
	private Date startTime;
	private Station endStation;
	private Date endTime;
	private Duration duration;
	private boolean current;
	private double price;
	
	public Ride(User user, Bicycle bicycle, Station startStation) {
		Date startTime = new Date();
		this.user = user;
		this.bicyle = bicycle;
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
			this.duration = new Duration(this.startTime,this.endTime);
			this.pay();
		}
		else {
			throw new Exception("Cette place est déjà prise ou hors-service.");
		}
	}
	
	public void pay() {
		this.price = this.user.getCard().cost(this.duration, this.bicycle.getType());
		this.user.getCard().updateTimeCreditBalance(this.duration, 
				this.bicycle.getType(),	this.endStation.getIsPlus());
	}
}
