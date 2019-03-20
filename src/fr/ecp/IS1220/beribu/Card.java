package fr.ecp.IS1220.beribu;

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
	 * @return
	 */
	public int timeCreditOperation(Duration duration, String bicycleType,
			boolean plusStation);
	
	public static void main(String[] args) {
		User user1 = new User("Robert Downey Jr.");
		Vmax vmax1 = new Vmax(user1);
		System.out.println(user1.getCard());
	}
}
