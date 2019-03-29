package fr.ecp.IS1220.myVelib.core;

/**
 * This class represents a subscription of type "Vmax".
 * @author Valentin
 *
 */
public class Vmax implements Card, java.io.Serializable{
	private User user;
	private Date creationDate;
	private String type = "Vmax";
	
	public Vmax(User user) {
		super();
		this.user = user;
		//user.subscribe(this);
	}
	
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return this.user;
	}
	@Override
	public Date getCreationDate() {
		// TODO Auto-generated method stub
		return this.creationDate;
	}
	public String getType() {
		return this.type;
	}
	
	@Override
	public double cost(Duration duration, String bicycleType) {
		// TODO Auto-generated method stub
		int costDuration = duration.getDuration();
		if (costDuration>3600) {
			if (costDuration-3600 > this.user.getTimeCreditBalance().getDuration()) {
				costDuration -= this.user.getTimeCreditBalance().getDuration();
			}
			else {
				costDuration = 3600;
			}
			return (costDuration-1)/3600;
		}
		else {
			return 0;
		}
	}

	@Override
	public int timeCreditOperation(Duration duration, String bicycleType, 
			boolean plusStation) {
		// TODO Auto-generated method stub
		int costDuration = duration.getDuration();
		int timeCreditOperation = 0;
		if (costDuration>3600) {
			if (costDuration-3600 > this.user.getTimeCreditBalance().getDuration()) {
				timeCreditOperation = -this.user.getTimeCreditBalance().getDuration();
			}
			else {
				timeCreditOperation = 3600-costDuration;
			}
		}
		
		if (plusStation) {
			timeCreditOperation += 5*60;
		}
		return timeCreditOperation;
	}
	
}


