
public class SystemDate {
	private static SystemDate instance = null;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	private SystemDate(){}
	
	public static synchronized SystemDate getInstance() {
	if (instance==null) {
	instance = new SystemDate();
	}
	return instance;
	}

	public void setDay(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public void setTime(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	@Override
	public String toString() {
		return "SystemDate: "+day+"/"+month+"/"+year+" at "+hour+":"+minute+":"+second;
	}

}
