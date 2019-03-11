package fr.ecp.IS1220.beribu;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SystemDate {
	private static SystemDate instance = null;
	private int year = -1;
	private int month = -1;
	private int day = -1;
	private int hour = -1;
	private int minute = -1;
	private int second = -1;
	
	private SystemDate(){}
	
	public static synchronized SystemDate getInstance() {
		if (instance==null) {
			instance = new SystemDate();
			System.out.println("Current system date must be defined.");
		}
		return instance;
	}

	public void setDay(int year, int month, int day) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		if (this.isComplete()) {
			this.isValid();
		}
	}

	public void setTime(int hour, int minute, int second) {
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		if (this.isComplete()) {
			this.isValid();
		}
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year<1970) {
			throw new IllegalArgumentException("Please enter a year after 1970");
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
		return "SystemDate: "+day+"/"+month+"/"+year+" at "+hour+":"+minute+":"+second;
	}
	
	/**
	 * This method checks if the current instance of Date object is fully
	 * filled with coherent values (different than -1).
	 * @return	True if the date is complete and false otherwise.
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
	 * This method checks the sanity of date object. It is called at each 
	 * instantiation of a new object Date. It is also called when you set 
	 * a new date (using setters). To perform date validation on object 
	 * <b>this</b> the date should be complete (see isComplete method).
	 * @return								True if the date is valid
	 * @throws IllegalArgumentException		When the date is not valid
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
			throw new IllegalArgumentException("Please enter a valid date");
		}
		return true;
	}

	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2020,02,29);
		SD.setTime(12,24,36);
		System.out.println(SD);
	}
}
