package fr.ecp.IS1220.beribu.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import fr.ecp.IS1220.beribu.SystemDate;

class SystemDateTest {

	@AfterEach
	void afterEach() {
		SystemDate.delInstance();
	}
	/*
	 * Test some wrong and correct Date
	 */
	@Test
	void correctDateTest001() {
		SystemDate SD = SystemDate.getInstance();
		assertDoesNotThrow(() -> {
			SD.setDay(2024, 2, 29);
			SD.setTime(13, 20, 10);
		});
	}
	@Test
	void correctDateTest002() {
		SystemDate SD = SystemDate.getInstance();
		assertDoesNotThrow(() -> {
			SD.setDay(2024, 3, 31);
			SD.setTime(17, 25, 15);
		});
	}
	@Test
	void correctDateTest003() {
		SystemDate SD = SystemDate.getInstance();
		assertDoesNotThrow(() -> {
			SD.setDay(2032, 2, 29);
			SD.setTime(17, 25, 15);
		});
	}
	@Test
	void correctDateTest004() {
		SystemDate SD = SystemDate.getInstance();
		assertDoesNotThrow(() -> {
			SD.setDay(2029, 12, 31);
			SD.setTime(17, 31, 59);
		});
	}
	@Test
	void wrongDateTest001() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2019, 2, 29);
			SD.setTime(17, 31, 59);
		});
	}
	@Test
	void wrongDateTest002() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2019, 4, 31);
			SD.setTime(17, 31, 59);
		});
	}
	@Test
	void wrongDateTest003() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2020, 2, 30);
			SD.setTime(17, 31, 59);
		});
	}
	@Test
	void wrongDateTest004() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2020, 13, 30);
			SD.setTime(17, 31, 59);
		});
	}
	@Test
	void wrongDateTest005() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2020, 1, 40);
			SD.setTime(17, 31, 59);
		});
	}
	@Test
	void wrongDateTest006() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(-1, 1, 1);
			SD.setTime(17, 31, 59);
		});
	}@Test
	void wrongDateTest007() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2020, 1, 1);
			SD.setTime(25, 31, 59);
		});
	}
	@Test
	void wrongDateTest008() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2020, 1, 40);
			SD.setTime(-6, 31, 59);
		});
	}
	@Test
	void wrongDateTest009() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2020, 1, 40);
			SD.setTime(23, 60, 59);
		});
	}
	@Test
	void wrongDateTest010() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2020, 1, 40);
			SD.setTime(23, 6, 72);
		});
	}
}
