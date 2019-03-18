package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

/**
 * This class represents a planned ride.
 * @author Valentin
 *
 */
public class Travel {
	User user;
	Localization source;
	Localization destination;
	String bicycleType = null;
	PathStrategy pathStrategy;
	Station suggestedStartStation;
	Station suggestedEndStation;
	double previsionCost;
	Duration previsionDuration;
	boolean ongoing = false;
	
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide(). 
	 * The default PathStrategy is MinimalWalking.
	 * @param user
	 * @param source
	 * @param destination
	 */
	public Travel(User user,Localization source, Localization destination) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = new MinimalWalking();
		this.findRide();
	}	
	
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide(). 
	 * @param user
	 * @param source
	 * @param destination
	 */
	public Travel(User user,Localization source, Localization destination,
			PathStrategy pathStrategy) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = pathStrategy;
		this.findRide();
	}
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide().
	 *  The user can specify a type of bicycle.
	 * @param user
	 * @param source
	 * @param destination
	 */
	public Travel(User user,Localization source, Localization destination,
			PathStrategy pathStrategy,String bicycleType) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = pathStrategy;
		this.bicycleType = bicycleType;	
		this.findRide();
	}	
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide().
	 *  The user can specify a type of bicycle.
	 * The default PathStrategy is MinimalWalking.
	 * @param user
	 * @param source
	 * @param destination
	 */
	public Travel(User user,Localization source, Localization destination,
			String bicycleType) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = new MinimalWalking();
		this.bicycleType = bicycleType;		
		this.findRide();
	}
	
	/**
	 * This method calculates a ride from the Localization source to the Localization
	 * destination using a given PathStrategy. More precisely, it computes a start
	 * Station and an end Station, the duration and the cost of the ride, taking
	 * into account the type of subscription of the user. 
	 */
	public void findRide() {
		ArrayList<Station> startEnd;
		if (this.bicycleType != null) {
			startEnd = this.pathStrategy.findPath(
					this.source, this.destination, this.bicycleType);
		}
		else {
			startEnd = this.pathStrategy.findPath(
					this.source, this.destination);
		}
		this.suggestedStartStation = startEnd.get(0);
		this.suggestedEndStation = startEnd.get(1);
		this.suggestedStartStation.addTargetOf(this);
		this.suggestedEndStation.addTargetOf(this);
		this.previsionDuration = new Duration(this.suggestedStartStation,
				this.suggestedEndStation, this.pathStrategy.getBicycleType());
		this.previsionCost = this.user.getCard().cost(this.previsionDuration,this.pathStrategy.getBicycleType());		
		System.out.println(this);
	}
	
	public void start() {
		this.ongoing = true;
	}
	
	public User getUser() {
		return user;
	}
	public Localization getSource() {
		return source;
	}
	public void setSource(Localization source) {
		this.source = source;
	}
	public Localization getDestination() {
		return destination;
	}
	public void setDestination(Localization destination) {
		this.destination = destination;
	}
	public String getBicycleType() {
		return bicycleType;
	}
	public void setBicycleType(String bicycleType) {
		this.bicycleType = bicycleType;
	}
	public PathStrategy getPathStrategy() {
		return pathStrategy;
	}
	public void setPathStrategy(PathStrategy pathStrategy) {
		this.pathStrategy = pathStrategy;
	}
	public Station getSuggestedStartStation() {
		return suggestedStartStation;
	}
	public Station getSuggestedEndStation() {
		return suggestedEndStation;
	}
	public double getPrevisionCost() {
		return previsionCost;
	}
	public Duration getPrevisionDuration() {
		return previsionDuration;
	}
	public boolean isOngoing() {
		// TODO Auto-generated method stub
		return this.ongoing;
	}
	public void setSuggestedStartStation(Station station) {
		if (station.getId() != this.suggestedStartStation.getId()) {
			this.suggestedStartStation.removeTargetOf(this);
			this.suggestedStartStation = station;
			this.update();
		}
	}
	
	/**
	 * This method is automatically called whenever either the end station or the 
	 * start station undergo any change in status. A new ride is calculated, with
	 * the associated duration and cost. If the user has already picked up their bicycle,
	 * then only the end station is updated.
	 */
	public void update() {
		if (!this.user.isOnRide()) {
			this.suggestedStartStation.removeTargetOf(this);
			this.suggestedEndStation.removeTargetOf(this);
			this.user.notifyUser("Your planned ride has been updated.");
			this.findRide();
		}
		else {
			Station previousEndStation = this.suggestedEndStation;
			this.suggestedEndStation.removeTargetOf(this);
			this.suggestedEndStation = this.destination.getClosestAvailableStation();
			this.suggestedEndStation.addTargetOf(this);
			this.previsionDuration = new Duration(this.suggestedStartStation,
					this.suggestedEndStation, this.bicycleType);
			this.previsionCost = this.user.getCard().cost(this.previsionDuration,this.bicycleType);
			if (previousEndStation.getId() != this.suggestedEndStation.getId()) {
				this.user.notifyUser("The return station is not available anymore."
						+ "Your return station has been updated.");
				System.out.println(this);
			}
			else {
				this.user.notifyUser("Your planned ride has been updated.");
			}
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "User : "+this.user+"\n"+"Suggested rental station : "+
				this.suggestedStartStation+"\n"+"Suggested return station : "
				+ this.suggestedEndStation+"\n"+"Estimated duration : " +
				this.previsionDuration+"\n"+"Estimated cost : " + this.getPrevisionCost();
	}
}
