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
	
	public void attachBicycle(Bicycle bicycle) throws Exception {
		if (this.isBicycle()) {
			throw new RuntimeException("This parking slot already holds a bicycle.");
		}
		if (this.isOffline) {
			throw new RuntimeException("This parking slot is offline.");
		}
		if (bicycle == null) {
			throw new IllegalArgumentException("The argument can't be null.");
		}
		if (bicycle.getAttached() == true) {
			throw new IllegalArgumentException("This bicycle is already linked to"
					+ " a parking slot.");
		}
		this.bicycle = bicycle;
		bicycle.setAttached(true);
		this.station.updateStatus();
	}
	
	public void detachBicycle() throws RuntimeException{
		if (!this.isBicycle()) {
			throw new RuntimeException("This parking slot holds on bicycle.");
		}
		if (this.isOffline) {
			throw new RuntimeException("This parking slot is offline.");
		}
		this.bicycle.setAttached(false);
		this.bicycle = null;
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
