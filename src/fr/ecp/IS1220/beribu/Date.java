package fr.ecp.IS1220.beribu;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class represents a date and time.
 * @author Valentin
 *
 */
public class Date {
	private Integer year = null;
	private Integer month = null;
	private Integer day = null;
	private Integer hour = null;
	private Integer minute = null;
	private Integer second = null;

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
	public Date(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
		this.currentDate();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.isValid();
	}

	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		if (year<1970) {
			throw new IllegalArgumentException("Please enter a year after 1970.");
		}
		this.year = year;
		this.isValid();
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public void setMonth(Integer month) {
		if (month<1 || month>12) {
			throw new IllegalArgumentException("Please enter a valid month"
					+ " number between 1 and 12.");
		}
		this.month = month;
		this.isValid();
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		if (day<1 || day>31) {
			throw new IllegalArgumentException("Please enter a valid day"
					+ " number between 1 and 31.");
		}
		this.day = day;
		this.isValid();
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		if (hour<0 || hour>23) {
			throw new IllegalArgumentException("Please enter a valid hour"
					+ " between 0 and 23.");
		}
		this.hour = hour;
		this.isValid();
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		if (minute<0 || minute>59) {
			throw new IllegalArgumentException("Please enter a valid minute"
					+ " between 0 and 59.");
		}
		this.minute = minute;
		this.isValid();
	}

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		if (second<0 || second>59) {
			throw new IllegalArgumentException("Please enter a valid second"
					+ " between 0 and 59.");
		}
		this.second = second;
		this.isValid();
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
		if (!this.day.equals(other.getDay()))
			return false;
		if (!this.hour.equals(other.getHour()))
			return false;
		if (!this.minute.equals(other.getMinute())) 
			return false;
		if (!this.month.equals(other.getMonth()))
			return false;
		if (!this.second.equals(other.getSecond()))
			return false;
		if (!this.year.equals(other.getYear()))
			return false;
		return true;
	}
	
	/**
	 * This method checks the sanity of a Date object. It is called at each 
	 * instantiation of a new Date. It is also called when using setters
	 * of Date.
	 * @return								true if the date is valid
	 * @throws IllegalArgumentException		if the date is not valid
	 */
	private boolean isValid() throws IllegalArgumentException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
		sdf.setLenient(false);
		String dateStr = new String();
		dateStr = String.valueOf(this.year == null ? 2000 : this.year) + '/' + 
				String.valueOf(this.month == null ? 1 : this.month) + '/' + 
				String.valueOf(this.day == null ? 1 : this.day) + '-' +
				String.valueOf(this.hour == null ? 1 : this.hour) + ':' +
				String.valueOf(this.minute == null ? 1 : this.minute) + ':' +
				String.valueOf(this.second == null ? 1 : this.second);
		try {
			java.util.Date date = sdf.parse(dateStr);
		} catch(ParseException e) {
			throw new IllegalArgumentException("Please enter a valid date.");
		}
		return true;
	}
	
}

