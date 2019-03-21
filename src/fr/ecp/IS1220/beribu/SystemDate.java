package fr.ecp.IS1220.beribu;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class represents a simulation of time. It is a singleton.
 * @author Valentin
 *
 */
public class SystemDate {
	private static SystemDate instance = null;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	private SystemDate(){}
	
	/**
	 * This method should be called when trying to access the SystemDate.
	 * It returns the unique instance of this class if it has been instantiated,
	 * or instantiates it before returning it.
	 * @return the unique instance of SystemDate
	 */
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
		System.out.println("Current System day has been set to "+day+"/"
				+month+"/"+year+".");
	}

	public void setTime(int hour, int minute, int second) {
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		System.out.println("Current System time has been set to "+hour+":"
				+minute+":"+second+".");
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year<1970) {
			throw new IllegalArgumentException("Please enter a year after 1970");
		}
		this.year = year;
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
	}

	@Override
	public String toString() {
		return "SystemDate: "+day+"/"+month+"/"+year+" at "+hour+":"+minute+":"+second;
	}

	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2020,02,29);
		SD.setTime(12,24,36);
		System.out.println(SD);
	}
}
