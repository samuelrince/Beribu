package fr.ecp.IS1220.beribu;

public class Vlibre implements Card{
	private User user;
	private Date creationDate;
	private String type = "Vlibre";
	
	public Vlibre(User user) {
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
			if (bicycleType.equalsIgnoreCase("MECHANICAL")) {
				return (costDuration-1)/3600;
			}
			else if (bicycleType.equalsIgnoreCase("ELECTRICAL")) {
				return ((costDuration-1)/3600+1.0/2.0)*2;
			}
		}
		else {
			if (bicycleType.equalsIgnoreCase("MECHANICAL")) {
				return 0;
			}
			else if (bicycleType.equalsIgnoreCase("ELECTRICAL")) {
				return 1;
			}
		}
		return 0;
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
