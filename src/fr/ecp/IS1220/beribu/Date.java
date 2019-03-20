package fr.ecp.IS1220.beribu;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class represents a date and time.
 * @author Valentin
 *
 */
public class Date {
	private int year = -1;
	private int month = -1;
	private int day = -1;
	private int hour = -1;
	private int minute = -1;
	private int second = -1;

	/**
	 * Constructor of class Date. Creates a new instance of Date taking the
	 * current time and date of SystemDate.
	 */
	public Date() {
		this.currentDate();
		this.isValid();
	}
	
	/**
	 * Constructor of class Date.
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public Date(int year, int month, int day, int hour, int minute, int second) {
		this.currentDate();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.isValid();
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		if (year<1970) {
			throw new IllegalArgumentException("Please enter a year after 1970.");
		}
		this.year = year;
		if (this.isComplete()) {
			this.isValid();
		}
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		if (month<1 || month>12) {
			throw new IllegalArgumentException("Please enter a valid month"
					+ " number between 1 and 12.");
		}
		this.month = month;
		if (this.isComplete()) {
			this.isValid();
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if (day<1 || day>31) {
			throw new IllegalArgumentException("Please enter a valid day"
					+ " number between 1 and 31.");
		}
		this.day = day;
		if (this.isComplete()) {
			this.isValid();
		}
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		if (hour<0 || hour>23) {
			throw new IllegalArgumentException("Please enter a valid hour"
					+ " between 0 and 23.");
		}
		this.hour = hour;
		if (this.isComplete()) {
			this.isValid();
		}
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		if (minute<0 || minute>59) {
			throw new IllegalArgumentException("Please enter a valid minute"
					+ " between 0 and 59.");
		}
		this.minute = minute;
		if (this.isComplete()) {
			this.isValid();
		}
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		if (second<0 || second>59) {
			throw new IllegalArgumentException("Please enter a valid second"
					+ " between 0 and 59.");
		}
		this.second = second;
		if (this.isComplete()) {
			this.isValid();
		}
	}

	@Override
	public String toString() {
		return day+"/"+month+"/"+year+" at "+hour+":"+minute+":"+second;
	}
	
	/**
	 * Sets the time and day of the Date on the time and day of SystemDate.
	 */
	public void currentDate() {
		SystemDate currentDate = SystemDate.getInstance();
		this.setYear(currentDate.getYear());
		this.setMonth(currentDate.getMonth());
		this.setDay(currentDate.getDay());
		this.setHour(currentDate.getHour());
		this.setMinute(currentDate.getMinute());
		this.setSecond(currentDate.getSecond());
	}
	
	/**
	 * Compares the instance of Date with another given Date and returns 
	 * true if the former is after the latter, false otherwise.
	 * Returns true if the two dates are simultaneous.
	 * @param otherDate date to compare with
	 * @return true if the date passed in argument is before this date, false otherwise
	 */
	public boolean isAfter(Date otherDate) {
		if (this.year != otherDate.year) {
			if (this.year < otherDate.year)
				return false;
			if (this.year > otherDate.year)
				return true;
		}
		if (this.month != otherDate.month) {
			if (this.month < otherDate.month)
				return false;
			if (this.month > otherDate.month)
				return true;
		}
		if (this.day != otherDate.day) {
			if (this.day < otherDate.day)
				return false;
			if (this.day > otherDate.day)
				return true;
		}
		if (this.hour != otherDate.hour) {
			if (this.hour < otherDate.hour)
				return false;
			if (this.hour > otherDate.hour)
				return true;
		}
		if (this.minute != otherDate.minute) {
			if (this.minute < otherDate.minute)
				return false;
			if (this.minute > otherDate.minute)
				return true;
		}
		if (this.second != otherDate.second) {
			if (this.second < otherDate.second)
				return false;
			if (this.second > otherDate.second)
				return true;
		}
		return true;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + hour;
		result = prime * result + minute;
		result = prime * result + month;
		result = prime * result + second;
		result = prime * result + year;
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
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (hour != other.hour)
			return false;
		if (minute != other.minute)
			return false;
		if (month != other.month)
			return false;
		if (second != other.second)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	/**
	 * Checks if the current instance of Date object is fully
	 * filled with coherent values (different from -1).
	 * @return	true if the date is complete, false otherwise
	 */
	private boolean isComplete() {
		if (this.year == -1)
			return false;
		if (this.month == -1)
			return false;
		if (this.day == -1)
			return false;
		if (this.hour == -1)
			return false;
		if (this.minute == -1)
			return false;
		if (this.second == -1)
			return false;
		return true;
	}
	
	/**
	 * This method checks the sanity of a Date object. It is called at each 
	 * instantiation of a new Date. It is also called when using setters
	 * of Date. To perform date validation on object 
	 * <b>this</b> the date should be complete (see method isComplete()).
	 * @return								true if the date is valid
	 * @throws IllegalArgumentException		if the date is not valid
	 */
	private boolean isValid() throws IllegalArgumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
		sdf.setLenient(false);
		String dateStr = new String();
		dateStr = String.valueOf(this.getYear()) + '/' + 
				String.valueOf(this.getMonth()) + '/' + 
				String.valueOf(this.getDay()) + '-' +
				String.valueOf(this.getHour()) + ':' +
				String.valueOf(this.getMinute()) + ':' +
				String.valueOf(this.getSecond());
		try {
			java.util.Date date = sdf.parse(dateStr);
		} catch(ParseException e) {
			throw new IllegalArgumentException("Please enter a valid date.");
		}
		return true;
	}
	
}

