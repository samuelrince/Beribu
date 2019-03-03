
public class ParkingSlot {
	private static long uniqId;
	private long id;
	private Bicycle bicycle;
	private boolean isOffline = false;
	private Station station;
	
	public ParkingSlot(Station station) {
		super();
		this.station = station;
		this.id = uniqId++;
		station.addParkingSlot(this);
	}
	
	public long getId() {
		return this.id;
	}
	
	public Bicycle getBicycle() {
		return this.bicycle;
	}
	
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
		this.station.updateStatus();
	}
	
	public Station getStation() {
		return station;
	}

	public void setOffline(boolean isOffline) {
		this.isOffline = isOffline;
		this.station.updateStatus();
	}

	public boolean isOffline() {
		return this.isOffline;
		
	}
	
}
