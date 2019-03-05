package fr.ecp.IS1220.beribu;


public class Date {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	public Date() {
		this.currentDate();
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
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
		return day+"/"+month+"/"+year+" at "+hour+":"+minute+":"+second;
	}
	
	public void currentDate() {
		SystemDate currentDate = SystemDate.getInstance();
		this.setYear(currentDate.getYear());
		this.setMonth(currentDate.getMonth());
		this.setDay(currentDate.getDay());
		this.setHour(currentDate.getHour());
		this.setMinute(currentDate.getMinute());
		this.setSecond(currentDate.getSecond());
	}
	
	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019,03,02);
		SD.setTime(12,24,36);
		Date date1 = new Date();
		System.out.println(date1);
	}
}
