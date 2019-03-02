package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class StandardStation implements Station {
	private int ID;
	private Localization localization;
	private ArrayList<ParkingSlot> parkingSlots;
	
	public StandardStation(int ID, Localization localization) {
		this.ID = ID;
		this.localization = localization;
		
	}
}
