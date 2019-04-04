package fr.ecp.IS1220.myVelib.core;

import java.util.ArrayList;

import fr.ecp.IS1220.myVelib.client.UserGUI.TravelUpdate;
import fr.ecp.IS1220.myVelib.core.exception.NoSuchStationExistException;

/**
 * This class represents a planned ride.
 * @author Valentin
 *
 */
public class Travel implements java.io.Serializable {
	private User user;
	private Localization source;
	private Localization destination;
	private String bicycleType = null;
	private PathStrategy pathStrategy;
	private Station suggestedStartStation;
	private Station suggestedEndStation;
	private double previsionCost = -1;
	private Duration previsionDuration;
	private boolean ongoing = false;
	private TravelUpdate travelUpdate;
	
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide(). 
	 * The default PathStrategy is MinimalWalking.
	 * @param user	a User object
	 * @param source	a Localization corresponding to the start point
	 * @param destination	a Localization corresponding to the end point
	 */
	public Travel(User user,Localization source, Localization destination) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = new MinimalWalking();
		try {
			this.findRide();
			System.out.println(user+" has initiated a planned ride."+"\n");
		}
		catch (NoSuchStationExistException e) {
			throw new NoSuchStationExistException("No path can be found on the"
					+ " network because either no bicycle is available or all stations "
					+ "are full. Please try initializing a new planned ride with a "
					+ "different bicycle type or a different path strategy, or retry later.");
		}
	}	
	
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide(). 
	 * @param user	a User object
	 * @param source	a Localization corresponding to the start point
	 * @param destination	a Localization corresponding to the end point
	 * @param pathStrategy	the PathStrategy to apply
	 */
	public Travel(User user,Localization source, Localization destination,
			PathStrategy pathStrategy) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = pathStrategy;
		try {
			this.findRide();
			System.out.println(user+" has initiated a planned ride."+"\n");
		}
		catch (NoSuchStationExistException e) {
			throw new NoSuchStationExistException("No path can be found on the"
					+ " network because either no bicycle is available or all stations "
					+ "are full. Please try initializing a new planned ride with a "
					+ "different bicycle type or a different path strategy, or retry later.");
		}
	}
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide().
	 *  The user can specify a type of bicycle.
	 * @param user	a User object
	 * @param source	a Localization corresponding to the start point
	 * @param destination	a Localization corresponding to the end point
	 * @param pathStrategy	the PathStrategy to apply
	 * @param bicycleType	a String corresponding to the type of bicycle wanted
	 */
	public Travel(User user,Localization source, Localization destination,
			PathStrategy pathStrategy,String bicycleType) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = pathStrategy;
		this.bicycleType = bicycleType;	
		try {
			this.findRide();
			System.out.println(user+" has initiated a planned ride."+"\n");
		}
		catch (NoSuchStationExistException e) {
			throw new NoSuchStationExistException("No path can be found on the"
					+ " network because either no bicycle is available or all stations "
					+ "are full. Please try initializing a new planned ride with a "
					+ "different bicycle type or a different path strategy, or retry later.");
		}
	}	
	/**
	 * Constructor of the class Travel, called automatically when using method User.planRide().
	 *  The user can specify a type of bicycle.
	 * The default PathStrategy is MinimalWalking.
	 * @param user	a User object
	 * @param source	a Localization corresponding to the start point
	 * @param destination	a Localization corresponding to the end point
	 * @param bicycleType	a String corresponding to the type of bicycle wanted
	 */
	public Travel(User user,Localization source, Localization destination,
			String bicycleType) {
		this.user = user;
		this.source = source;
		this.destination = destination;
		this.pathStrategy = new MinimalWalking();
		this.bicycleType = bicycleType;		
		try {
			this.findRide();
			System.out.println(user+" has initiated a planned ride.");
		}
		catch (NoSuchStationExistException e) {
			throw new NoSuchStationExistException("No path can be found on the"
					+ " network because either no bicycle is available or all stations "
					+ "are full. Please try initializing a new planned ride with a "
					+ "different bicycle type or a different path strategy, or retry later.");
		}
	}
	
	/**
	 * This method calculates a ride from the Localization source to the Localization
	 * destination using a given PathStrategy. More precisely, it computes a start
	 * Station and an end Station, the duration and the cost of the ride, taking
	 * into account the type of subscription of the user. 
	 */
	public void findRide() {
		ArrayList<Station> startEnd;
		try{
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
			this.previsionDuration.add(new Duration(this.source,this.suggestedStartStation.getLocalization(),4));
			this.previsionDuration.add(new Duration(this.destination,this.suggestedEndStation.getLocalization(),4));
		}
		catch(NoSuchStationExistException e) {
			this.suggestedStartStation = null;
			this.suggestedEndStation = null;
			this.previsionDuration = null;
			this.previsionCost = -1;
			throw new NoSuchStationExistException();
		}
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
		return this.ongoing;
	}
	
	/**
	 * This method is automatically called only when a user rents a bike with a planned ride started with this.start().
	 * It sets the suggested rent station of the planned ride on the actual rent station of the user 
	 * and updates the planned ride if necessary.
	 * @param station the rent station
	 */
	public void setStartStation(Station station) {
		if (this.suggestedStartStation != null)
			this.suggestedStartStation.removeTargetOf(this);
		this.suggestedStartStation = station;
		this.update();
	}
	
	/**
	 * This method is automatically called whenever either the end station or the 
	 * start station undergo any change in status. A new ride is calculated, with
	 * the associated duration and cost. If the user has already picked up their bicycle,
	 * then only the start station is not updated. <br>
	 * This method is also automatically called by the UserGUI to refresh the planned ride.
	 */
	public void update() {
		boolean flag = false;
		if (!this.user.isOnRide()) {
			Station previousStartStation = this.suggestedStartStation;
			Station previousEndStation = this.suggestedEndStation;
			if (previousStartStation != null)
				previousStartStation.removeTargetOf(this);
			if (previousEndStation != null)
				previousEndStation.removeTargetOf(this);
			try {this.findRide();}
			catch (NoSuchStationExistException e) {}
			if ((previousStartStation != null && this.suggestedStartStation == null) || 
					(previousEndStation != null && this.suggestedEndStation == null)) {
				user.notifyUser("Your planned ride is not possible anymore because either "
						+ "no bicycle is available or all stations "
						+ "are full. Please try initializing a new planned ride with a "
						+ "different bicycle type or a different path strategy, or wait until"
						+ " this planned ride is updated.");
				flag = true;
			}
			if (previousStartStation == null && this.suggestedStartStation != null) {
				user.notifyUser("A rent station with an available bike has been found!");
				flag = true;
			}
			if (previousEndStation == null && this.suggestedEndStation != null) {
				user.notifyUser("A return station with an available parking slot has been found!");
				flag = true;
			}
			if (previousStartStation != null && this.suggestedStartStation != null) {
				if (previousStartStation.getId() != this.suggestedStartStation.getId()) {
					user.notifyUser("Your rent station has been changed because "
							+ "it was not available anymore.");					
					flag = true;
				}
			}
			if (previousEndStation != null && this.suggestedEndStation != null) {
				if (previousEndStation.getId() != this.suggestedEndStation.getId()) {
					user.notifyUser("Your return station has been changed because "
							+ "it was not available anymore.");					
					flag = true;
				}
			}
		}
		else {
			Station previousEndStation = this.suggestedEndStation;
			if (previousEndStation != null)
				this.suggestedEndStation.removeTargetOf(this);
			try {				
				this.suggestedEndStation = this.destination.getClosestAvailableStation();
				this.suggestedEndStation.addTargetOf(this);
				this.previsionDuration = new Duration(this.suggestedStartStation,
						this.suggestedEndStation, this.bicycleType);
				this.previsionCost = this.user.getCard().cost(this.previsionDuration,this.bicycleType);
				this.previsionDuration.add(new Duration(this.source,this.suggestedStartStation.getLocalization(),4));
				this.previsionDuration.add(new Duration(this.destination,this.suggestedEndStation.getLocalization(),4));
			}
			catch(NoSuchStationExistException e) {
				this.suggestedEndStation = null;
				this.previsionDuration = null;
				this.previsionCost = -1;
			}
			if (previousEndStation == null && this.suggestedEndStation != null) {
				this.user.notifyUser("A return station has been found.");
				flag = true;
			}
			if (previousEndStation != null && this.suggestedEndStation == null) {
				this.user.notifyUser("All stations of the network"
						+ " are full. We will let you know when a parking slot is available.");
				flag = true;
			}
			else
				if (previousEndStation.getId() != this.suggestedEndStation.getId()) {
					this.user.notifyUser("Your return station has been changed "
							+ "because it was not available anymore.");
					flag = true;
				}	
		}
		if (this.travelUpdate != null && flag == true) {
			this.travelUpdate.update();
			flag = false;
		}
	}

	public void addObserver(TravelUpdate travelUpdate) {
		this.travelUpdate = travelUpdate;
	}
	
	public TravelUpdate getObserver() {
		return this.travelUpdate;
	}
	
	public void removeObserver() {
		if (this.travelUpdate != null)
			this.travelUpdate = null;
	}
	
	@Override
	public String toString() {
		String rentalString = "Suggested rental station : ";
		String startStation = "not found";
		String endStation = "not found";
		String duration = "not found";
		String cost = "not found";
		String btype;
		if (this.ongoing) {
			btype = "Bicycle type : "+this.bicycleType;
			rentalString = "Rental station : ";
		}
		else
			btype = "Possible bicycle type : "+this.pathStrategy.getBicycleType();
		if (this.suggestedStartStation != null)
			startStation = this.suggestedStartStation.toString();
		if (this.suggestedEndStation != null)
			endStation = this.suggestedEndStation.toString();
		if (this.previsionDuration != null)
			duration = this.previsionDuration.toString();
		if (this.previsionCost != -1)
			cost = Double.toString(this.previsionCost)+"€";
		
		return  "----------------------"+"\n"+"Planned ride of "+this.user+" : "+"\n"+
		"\n"+"From : "+this.source+"\n"+
		"To : "+this.destination+"\n"+
		"\n"+rentalString+startStation+"\n"+
		"Suggested return station : "+ endStation+"\n"+
		btype+"\n"+
		"Estimated duration : " +duration+"\n"+
		"Estimated cost : " +cost+"\n"+"----------------------";
	}
}
