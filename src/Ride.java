import java.util.Date;

public class Ride {
	private int id;
	private User user;
	private Bicycle bicyle;
	private Station startStation;
	private Date startTime;
	private Station endStation;
	private Date endTime;
	private boolean planned;
	private double price;
	
	public void takeBicycle(Station station) {
		
	}
	
	public void dropBicycle(ParkingSlot parkingSlot) {
		
	}
	
	public void pay() {
		double duration;
		//compute duration
		//choice costStrategy
		this.price = 0;
	}
}
