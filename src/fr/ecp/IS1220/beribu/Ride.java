package fr.ecp.IS1220.beribu;


public class Ride {
	private static long uniqId;
	private long id;
	private User user;
	private Bicycle bicycle;
	private Station startStation;
	private Date startTime;
	private Station endStation = null;
	private Date endTime = null;
	private Duration duration;
	private boolean current;
	private double price;
	private int timeCreditOperation = 0;
	
	public Ride(User user, Bicycle bicycle, Station startStation) {
		Date startTime = new Date();
		this.user = user;
		this.bicycle = bicycle;
		this.startStation = startStation;
		this.startTime = startTime;
		this.current = true;
		this.id = uniqId++;
	}

	public void end(ParkingSlot parkingSlot) throws Exception {
		if (!parkingSlot.isBicycle()) {
			parkingSlot.setBicycle(this.bicycle);
			Date endTime = new Date();
			this.endTime = endTime;
			this.endStation = parkingSlot.getStation();
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
		this.user.addTimeCreditBalance(0, this.user.getCard().timeCreditOperation(
				this.duration, this.bicycle.getType(),this.endStation.isPlus()));
	}

	public long getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public Bicycle getBicycle() {
		return bicycle;
	}

	public Station getStartStation() {
		return startStation;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Station getEndStation() {
		return endStation;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Duration getDuration() {
		return duration;
	}

	public boolean isCurrent() {
		return current;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Ride [" + id + "], of " + user.getName() + " on bike N°" + bicycle.getId() + " start at station (" + startStation.getId() + ") " + startTime.toString();
	}
	
}
