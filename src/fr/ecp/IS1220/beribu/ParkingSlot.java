package fr.ecp.IS1220.beribu;


public class ParkingSlot {
	private static long uniqId;
	private long id;
	private Bicycle bicycle = null;
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
		if (!isOffline && this.station.isOffline()) {
			throw new RuntimeException("This parking slot can't be"
					+ " set online because the station is offline.");
		}
		this.isOffline = isOffline;
		this.station.updateStatus();
	}

	public boolean isOffline() {
		return this.isOffline;
		
	}
	
	/**
	 * This method returns true if a bicycle is attached to the parking
	 * slot, and false otherwise.
	 * @return		boolean 
	 */
	public boolean isBicycle() {
		if (this.getBicycle() != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
