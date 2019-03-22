package fr.ecp.IS1220.beribu;

/**
 * This class represents a subscription of type "Standard". Factually, it means
 * the registered card is a credit card.
 * @author Valentin
 *
 */
public class Standard implements Card{
	private User user;
	private Date creationDate;
	private String type = "Standard";
	
	public Standard(User user) {
		super();
		this.user = user;
		this.creationDate = new Date();
	}
	
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return this.user;
	}
	
	public String getType() {
		return this.type;
	}

	@Override
	public Date getCreationDate() {
		// TODO Auto-generated method stub
		return this.creationDate;
	}	
	
	@Override
	public double cost(Duration duration, String bicycleType) {
		// TODO Auto-generated method stub
		double hourCost = 0;
		if (bicycleType.equalsIgnoreCase("MECHANICAL")) {
			hourCost = 1;
		}
		else if (bicycleType.equalsIgnoreCase("ELECTRICAL")) {
			hourCost = 2;
		}
		return hourCost*((duration.getDuration()-1)/3600 + 1);
	}

	@Override
	public int timeCreditOperation(Duration duration, String bicycleType, 
			boolean plusStation) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}