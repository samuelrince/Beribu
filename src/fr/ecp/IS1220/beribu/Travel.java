package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class Travel {
	User user;
	Localization source;
	Localization destination;
	String bicycleType;
	PathStrategy pathStrategy;
	Station suggestedStartStation;
	Station suggestedEndStation;
	double previsionCost;
	Duration previsionDuration;
	
	//If no pathStrategy is given, then MinimalWalking by default.
	public Travel(User user,Localization source, Localization destination) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = new MinimalWalking();
	}	
	public Travel(User user,Localization source, Localization destination,
			PathStrategy pathStrategy) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = pathStrategy;
	}
	public Travel(User user,Localization source, Localization destination,
			PathStrategy pathStrategy,String bicycleType) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = pathStrategy;
		this.bicycleType = bicycleType;		
	}	
	public Travel(User user,Localization source, Localization destination,
			String bicycleType) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = new MinimalWalking();
		this.bicycleType = bicycleType;		
	}
	
	public void findRide() {
		ArrayList<Station> startEnd = this.pathStrategy.findPath(
				this.source, this.destination, this.bicycleType);
		this.suggestedStartStation = startEnd.get(0);
		this.suggestedEndStation = startEnd.get(1);
		this.suggestedStartStation.addTargetOf(this);
		this.suggestedEndStation.addTargetOf(this);
		this.previsionDuration = new Duration(this.suggestedStartStation,
				this.suggestedEndStation, this.bicycleType);
		this.previsionCost = this.user.getCard().cost(this.previsionDuration,this.bicycleType);		
				
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
	
	public void update() {
		if (!this.user.isOnRide()) {
			this.suggestedStartStation.removeTargetOf(this);
			this.suggestedEndStation.removeTargetOf(this);
			ArrayList<Station> startEnd = this.pathStrategy.findPath(
					this.source, this.destination, this.bicycleType);
			this.suggestedStartStation = startEnd.get(0);
			this.suggestedEndStation = startEnd.get(1);
			this.suggestedStartStation.addTargetOf(this);
			this.suggestedEndStation.addTargetOf(this);
			this.previsionDuration = new Duration(this.suggestedStartStation,
					this.suggestedEndStation, this.bicycleType);
			this.previsionCost = this.user.getCard().cost(this.previsionDuration,this.bicycleType);

			this.user.notifyUser("Your planned ride has been updated.");
		}
		else {
			this.suggestedStartStation.removeTargetOf(this);
			this.suggestedEndStation.removeTargetOf(this);
			this.suggestedEndStation = this.destination.getClosestAvailableStation();
			this.suggestedEndStation.addTargetOf(this);
			this.user.notifyUser("The destination station is not available anymore."
					+ "Your destination station has been recalculated.");
		}
	}
	
}
