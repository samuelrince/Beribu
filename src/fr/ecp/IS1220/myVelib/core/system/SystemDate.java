package fr.ecp.IS1220.myVelib.core.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.ecp.IS1220.myVelib.core.exception.BadDateException;

/**
 * This class represents a simulation of time. It is a singleton.
 * @author Valentin
 *
 */
public class SystemDate {
	private static SystemDate instance = null;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer hour;
	private Integer minute;
	private Integer second;
	
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
	
	public static synchronized void delInstance() {
		if (instance != null) {
			instance = null;
		}
	}

	public void setDay(Integer year, Integer month, Integer day) {
		if (!(this.year == null) && !(this.month == null) && !(this.day == null) && !(this.hour == null) && !(this.minute == null) && !(this.second == null)) {
			Date d1 = new Date(this.year, this.month, this.day, this.hour, this.minute, this.second);
			Date d2 = new Date(year, month, day, this.hour, this.minute, this.second);
			if (d1.isAfter(d2) && !(d1.equals(d2))) {
				throw new BadDateException("You cannot travel back time.");
			}
		}
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = new Integer(0);
		this.minute = new Integer(0);
		this.second = new Integer(0);
		if (!this.isValid())
			throw new BadDateException("The SystemDate is not valid.");
		System.out.println("Current System day has been set to "+day+"/"
				+month+"/"+year+".");
	}

	public void setTime(Integer hour, Integer minute, Integer second) {
		if (!(this.year == null) && !(this.month == null) && !(this.day == null) && !(this.hour == null) && !(this.minute == null) && !(this.second == null)) {
			Date d1 = new Date(this.year, this.month, this.day, this.hour, this.minute, this.second);
			Date d2 = new Date(this.year, this.month, this.day, hour, minute, second);
			if (d1.isAfter(d2) && !(d1.equals(d2))) {
				throw new BadDateException("You cannot travel back time.");
			}
		}
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		if (!this.isValid())
			throw new BadDateException("The SystemDate is not valid.");
		System.out.println("Current System time has been set to "+hour+":"
				+minute+":"+second+".");
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		if (year<1970) {
			throw new BadDateException("Please enter a year after 1970.");
		}
		this.year = year;
		if (!this.isValid())
			throw new IllegalArgumentException("The SystemDate is not valid.");
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		if (month<1 || month>12) {
			throw new BadDateException("Please enter a valid month"
					+ " number between 1 and 12.");
		}
		this.month = month;
		if (!this.isValid())
			throw new BadDateException("The SystemDate is not valid.");
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		if (day<1 || day>31) {
			throw new BadDateException("Please enter a valid day"
					+ " number between 1 and 31.");
		}
		this.day = day;
		if (!this.isValid())
			throw new BadDateException("The SystemDate is not valid.");
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		if (hour<0 || hour>23) {
			throw new BadDateException("Please enter a valid hour"
					+ " between 0 and 23.");
		}
		this.hour = hour;
		if (!this.isValid())
			throw new BadDateException("The SystemDate is not valid.");
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		if (minute<0 || minute>59) {
			throw new BadDateException("Please enter a valid minute"
					+ " between 0 and 59.");
		}
		this.minute = minute;
		if (!this.isValid())
			throw new BadDateException("The SystemDate is not valid.");
	}

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		if (second<0 || second>59) {
			throw new BadDateException("Please enter a valid second"
					+ " between 0 and 59.");
		}
		this.second = second;
	}

	@Override
	public String toString() {
		return "SystemDate: "+day+"/"+month+"/"+year+" at "+hour+":"+minute+":"+second;
	}
	
	private boolean isValid() throws BadDateException {
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
			throw new BadDateException("Please enter a valid date.");
		}
		return true;
	}
}
