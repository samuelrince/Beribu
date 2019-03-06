package fr.ecp.IS1220.beribu;


public class Duration {
	//duration in seconds
	private int duration;

	public Duration() {
		this.duration = 0;
	}
	public Duration(Date startDate, Date endDate) {
		int yearInSeconds = (endDate.getYear()-startDate.getYear())*32140800;
		int monthInSeconds = (endDate.getMonth()-startDate.getMonth())*2678400;
		int dayInSeconds = (endDate.getDay()-startDate.getDay())*86400;
		int hourInSeconds = (endDate.getHour()-startDate.getHour())*3600;
		int minuteInSeconds = (endDate.getMinute()-startDate.getMinute())*60;
		int secondsInSeconds = endDate.getSecond()-startDate.getSecond();
		int t = yearInSeconds+monthInSeconds+dayInSeconds+hourInSeconds+minuteInSeconds+secondsInSeconds;
		if (t>=0) {
			this.duration = yearInSeconds+monthInSeconds+dayInSeconds+hourInSeconds+minuteInSeconds+secondsInSeconds;
		} else {
			throw new RuntimeException("The duration cannot be negative");
}
	}
	public Duration(Station startStation, Station endStation, String bicycleType) {
		double distance = startStation.getLocalization().distanceTo(
				endStation.getLocalization());
		this.duration = (int) (distance/Bicycle.getSpeed(bicycleType));
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void add(int minutes) {
		this.duration += 60*minutes;
	}
	public void add(int minutes, int seconds) {
		this.duration += 60*minutes + seconds;
	}
	public void add(int hours, int minutes, int seconds) {
		this.duration += 3600*hours + 60*minutes + seconds;
	}
	
	@Override
	public String toString() {
		int hours = this.duration/3600;
		int minutes = (this.duration%3600)/60;
		int seconds = (this.duration%3600)%60;
		return hours+"h"+minutes+"min"+seconds+"s";
	}
	
	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019,03,02);
		SD.setTime(12,24,36);
		Date date1 = new Date();
		SD.setDay(2019,03,02);
		SD.setTime(13,36,50);
		Date date2 = new Date();
		Duration duration = new Duration(date1,date2);
		System.out.println(duration);
	}
}
