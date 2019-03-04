package fr.ecp.IS1220.beribu;


public class Travel {
	User user;
	Localization source;
	Localization destination;
	String bicycleType;
	Ride ride;
	PathStrategy pathStrategy;
	
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
		this.ride = this.pathStrategy.findRide(this.source, this.destination,
				this.bicycleType);
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
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	public Ride getRide() {
		return ride;
	}
	public void updateRide() {
		this.ride = this.pathStrategy.findRide(this.source, this.destination,
				this.bicycleType);
		this.user.notify();
	}
	
}
