package fr.ecp.IS1220.myVelib.core.card;

import fr.ecp.IS1220.myVelib.core.system.Date;
import fr.ecp.IS1220.myVelib.core.system.Duration;
import fr.ecp.IS1220.myVelib.core.user.User;

/**
 * This interface represents a card/subscription.
 * @author Valentin
 *
 */
public interface Card {
	
	/**
	 * 
	 * @return owner of the card
	 */
	public User getUser();
	
	/**
	 * 
	 * @return date of creation of the card
	 */
	public Date getCreationDate();
	
	/**
	 * 
	 * @return type of the card
	 */
	public String getType();
	
	/**
	 * Returns the cost of a ride of given duration and bicycle type, taking
	 * into account the specifications of the type of card and the time credit
	 * balance of the user.
	 * @param duration duration of the ride
	 * @param bicycleType type of bicycle used for the ride
	 * @return cost of the ride
	 */
	public double cost(Duration duration, String bicycleType);
	
	/**
	 * Returns the algebraic amount of time credit earned by the user at the end
	 * of a ride of given duration and bicycle type, taking into account the 
	 * specifications the type of card, the time credit balance of the user
	 * and the eventual Plus bonus.
	 * @param duration duration of the ride
	 * @param bicycleType type of bicycle used for the ride
	 * @param plusStation type of the return station, true if Plus and false otherwise
	 * @return a int that correspond to the time gain with the ride
	 */
	public int timeCreditOperation(Duration duration, String bicycleType,
			boolean plusStation);
}
