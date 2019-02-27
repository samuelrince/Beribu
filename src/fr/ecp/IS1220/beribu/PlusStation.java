package fr.ecp.IS1220.beribu;

import java.util.ArrayList;

public class PlusStation implements Station {
	private int ID;
	private Localization localization;
	private ArrayList<ParkingSlot> parkingSlots;
	
	public PlusStation(int ID, Localization localization) {
		super();
		this.ID = ID;
		this.localization = localization;
	}
	
	
}
