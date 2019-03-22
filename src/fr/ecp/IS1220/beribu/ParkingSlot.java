package fr.ecp.IS1220.beribu;

/**
 * This class represents a parking slot.
 * @author Valentin
 *
 */
public class ParkingSlot {
	private static long uniqId;
	private long id;
	private Bicycle bicycle = null;
	private boolean isOffline = false;
	private Station station;
	
	/**
	 * Constructor of class ParkingSlot.
	 * @param station a Station object that is linked to
	 */
	public ParkingSlot(Station station) {
		super();
		this.station = station;
		this.id = uniqId++;
		station.addParkingSlot(this);
		System.out.println("A parking slot has been added to "+station+".");
	}
	
	/**
	 * 
	 * @return id of the parking slot
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @return bicycle attached to the parking slot, null if no bicycle
	 */
	public Bicycle getBicycle() {
		return this.bicycle;
		
	}
	
	/**
	 * Attaches a given bicycle to the parking slot if it is online and free.
	 * @param bicycle bicycle to attach
	 * @throws RuntimeException	Occurs when the parking slot is offline or already holds
	 * a bicycle
	 * @throws IllegalArgumentException Occurs when the bicycle state is not valid
	 */
	public void attachBicycle(Bicycle bicycle) throws RuntimeException,
	IllegalArgumentException {
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
	}
	
	/**
	 * Detaches the bicycle currently attached to the parking slot if it is online.
	 * @throws RuntimeException when the parking slot state is not valid
	 */
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
	
	/**
	 * 	
	 * @return station containing the parking slot
	 */
	public Station getStation() {
		return station;
	}

	/**
	 * Sets the parking slot in the state specified in argument.
	 * @param isOffline true to set the parking slot offline, false to set it online
	 */
	public void setOffline(boolean isOffline) {
		if (!isOffline && this.station.isOffline()) {
			throw new RuntimeException("This parking slot can't be"
					+ " set online because the station is offline.");
		}
		this.isOffline = isOffline;
		this.station.updateStatus();
	}

	/**
	 * 
	 * @return true if the parking slot is offline, false otherwise
	 */
	public boolean isOffline() {
		return this.isOffline;
		
	}
	
	/**
	 * 
	 * @return true if a bicycle is attached to the parking slot, false otherwise
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
