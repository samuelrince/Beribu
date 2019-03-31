package fr.ecp.IS1220.myVelib.core;

import fr.ecp.IS1220.myVelib.core.exception.BadDurationException;

/**
 * This class represents a duration. It is used mainly for purposes of validity
 * check and string representation.
 * @author Valentin
 *
 */
public class Duration implements java.io.Serializable {
	//duration in seconds
	private int duration;

	/**
	 * Constructor of class Duration.
	 */
	public Duration() {
		this.duration = 0;
	}
	
	/**
	 * Constructor of class Duration. The value assigned corresponds to the
	 * duration between two given dates.
	 * @param startDate date when the duration begins
	 * @param endDate date when the duration ends
	 * @throws RuntimeException	occurs when the duration is negative (wrong
	 * dates)
	 */
	public Duration(Date startDate, Date endDate) throws BadDurationException {
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
			throw new BadDurationException("The end date should be posterior to the start date.");
		}
	}
	
	/**
	 * Constructor of class Duration. The value assigned corresponds to the 
	 * time that would be taken to go from one given station to another with a 
	 * given bicycle type.
	 * @param startStation start station
	 * @param endStation end station
	 * @param bicycleType type of bicycle used
	 */
	public Duration(Station startStation, Station endStation, String bicycleType) {
		double distance = startStation.getLocalization().distanceTo(
				endStation.getLocalization());
		this.duration = (int) (distance/Bicycle.getSpeed(bicycleType));
	}
	
	/**
	 * 
	 * @return value of duration (in s)
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Sets the value of duration.
	 * @param duration value of duration (in s)
	 */
	public void setDuration(int duration) {
		if (duration >= 0)
			this.duration = duration;
		else
			throw new BadDurationException("The duration can't be negative.");
	}

	/**
	 * Adds another duration to this duration. Their two values are summed.
	 * @param duration duration to add
	 */
	public void add(Duration duration) {
		this.duration += duration.getDuration();
	}
	
	/**
	 * Adds a given amount of minutes to the duration.
	 * @param minutes the number of minutes to add
	 */
	public void add(int minutes) {
		this.duration += 60*minutes;
	}
	
	/**
	 * Adds a given amount of minutes and seconds to the duration.
	 * @param minutes the number of minutes to add
	 * @param seconds the number of seconds to add
	 */
	public void add(int minutes, int seconds) {
		this.duration += 60*minutes + seconds;
	}
	
	/**
	 * Adds a given amount of hours, minutes and seconds to the duration.
	 * @param hours the number of hours to add
	 * @param minutes the number of minutes to add
	 * @param seconds the number of seconds to add
	 */
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duration other = (Duration) obj;
		if (this.duration != other.duration)
			return false;
		return true;
	}
}
