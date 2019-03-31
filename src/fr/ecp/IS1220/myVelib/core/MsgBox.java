package fr.ecp.IS1220.myVelib.core;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class MsgBox implements java.io.Serializable {
	private User user;
	private ArrayList<String> messages = new ArrayList<String>();
	private ArrayList<Date> timeStamps = new ArrayList<Date>();
	public int uncheckedCount = 0;
	
	public MsgBox(User user) {
		if (user.getMsgBox() == null)
			this.user = user;
		else
			throw new RuntimeException(user+" already has a message box.");
	}
	
	public void add(String message) {
		this.messages.add(message);
		this.timeStamps.add(new Date());
		this.uncheckedCount++;
	}
	
	public int getUncheckedCount() {
		return this.uncheckedCount;
	}
	
	public String retrieve() {
		String res = "";
		for (int i = 0; i < this.messages.size(); i++) {
			res += "\n"+timeStamps.get(i)+"\n"+messages.get(i)+"\n";
		}
		this.messages = new ArrayList<String>();
		this.timeStamps = new ArrayList<Date>();
		this.uncheckedCount = 0;
		return res;
	}

}
