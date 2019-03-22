package fr.ecp.IS1220.myVelib.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ecp.IS1220.myVelib.app.AvoidPlus;
import fr.ecp.IS1220.myVelib.app.ElectricalBike;
import fr.ecp.IS1220.myVelib.app.FastestPath;
import fr.ecp.IS1220.myVelib.app.Localization;
import fr.ecp.IS1220.myVelib.app.MechanicalBike;
import fr.ecp.IS1220.myVelib.app.MinimalWalking;
import fr.ecp.IS1220.myVelib.app.MyVelibNetwork;
import fr.ecp.IS1220.myVelib.app.ParkingSlot;
import fr.ecp.IS1220.myVelib.app.PreferPlus;
import fr.ecp.IS1220.myVelib.app.PreserveDistribution;
import fr.ecp.IS1220.myVelib.app.Station;
import fr.ecp.IS1220.myVelib.app.SystemDate;
import fr.ecp.IS1220.myVelib.app.Travel;
import fr.ecp.IS1220.myVelib.app.User;

/**
 * This class contains Junit tests for Travel class
 * @author Samuel
 *
 */
class TravelTest {

	@AfterEach
	void afterEach() {
		SystemDate.delInstance();
	}
	@Test
	void initTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 12); SD.setTime(17, 22, 47);
		new MyVelibNetwork("Paris");
		Station s1 = new Station(new Localization(0.0, 0.0), false);
		Station s2 = new Station(new Localization(5.0, 5.0), false);
		Station s3 = new Station(new Localization(5.0000000000000001, 5.0000000000000001), true);
		try {
			new ParkingSlot(s1).attachBicycle(new MechanicalBike());
			new ParkingSlot(s1).attachBicycle(new ElectricalBike());
		} catch (Exception e) {
			fail("Failed to attach the bike");
		}
		new ParkingSlot(s2);
		new ParkingSlot(s3);
		MyVelibNetwork.getInstance().addStation(s1);
		MyVelibNetwork.getInstance().addStation(s2);
		MyVelibNetwork.getInstance().addStation(s3);
		SD.setDay(2019, 03, 13); SD.setTime(17, 22, 47);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0));
		});
	}
	@Test
	void initTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new MinimalWalking());
		});
	}
	@Test
	void initTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new FastestPath());
		});	
	}
	@Test
	void initTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new AvoidPlus());
		});
	}
	@Test
	void initTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new PreferPlus());
		});
	}
	@Test
	void initTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new PreserveDistribution());
		});
	}
	@Test
	void initTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), "Electrical");
		});
	}
	@Test
	void initTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), "Mechanical");
		});
	}
	@Test
	void initTest009() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new MinimalWalking(), "Mechanical");
		});
	}
	@Test
	void initTest010() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new FastestPath(), "Mechanical");
		});	
	}
	@Test
	void initTest011() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new AvoidPlus(), "Mechanical");
		});
	}
	@Test
	void initTest012() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new PreferPlus(), "Mechanical");
		});
	}
	@Test
	void initTest013() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new PreserveDistribution(), "Mechanical");
		});
	}
	@Test
	void initTest014() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new MinimalWalking(), "Electrical");
		});
	}
	@Test
	void initTest015() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new FastestPath(), "Electrical");
		});	
	}
	@Test
	void initTest016() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new AvoidPlus(), "Electrical");
		});
	}
	@Test
	void initTest017() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new PreferPlus(), "Electrical");
		});
	}
	@Test
	void initTest018() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 11); SD.setTime(10, 11, 11);
		assertDoesNotThrow(() -> {
			new Travel(new User("Jean"), new Localization(2.0, 3.0), new Localization(10.0, 7.0), new PreserveDistribution(), "Electrical");
		});
	}
	
	
	/*
	 * Test equality 
	 */
}
