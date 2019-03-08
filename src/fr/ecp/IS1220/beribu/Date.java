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
	
	public Date(int year, int month, int day, int hour, int minute, int second) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(year, month, day);
		SD.setTime(hour, minute, second);
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


	public static void main(String[] args) {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019,03,02);
		SD.setTime(12,24,36);
		Date date1 = new Date();
		SD.setDay(2019,04,02);
		SD.setTime(12,24,36);
		Date date2 = new Date();
		System.out.println(date1);
		System.out.println(date2.isAfter(date1));
	}
	
}

