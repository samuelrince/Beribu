
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
	
	public void getRide() {
		this.ride = this.pathStrategy.getRide(this.source, this.destination,
				this.bicycleType);
	}
}
