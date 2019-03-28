package fr.ecp.IS1220.myVelib.core;

public class BikeRental implements Runnable {
	private Thread thread;
	private User user;
	private Station station;
	private String bicycleType;

	public BikeRental(User user, Station station) {
		this.user = user;
		this.station = station;
	}
	
	public BikeRental(User user, Station station, String bicycleType) {
		this.user = user;
		this.station = station;
		this.bicycleType = bicycleType;
	}

	public void run() {
		synchronized(station) {
			user.newRide(station, bicycleType);
		}
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start () {
		if (thread == null) {
			thread = new Thread (this,"Bike rental of "+user.toString()
			 +" in "+station.toString());
			thread.start ();
		}
	}
	
}

