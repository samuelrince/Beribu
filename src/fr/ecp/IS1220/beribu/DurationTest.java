package fr.ecp.IS1220.beribu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DurationTest {
	
	/*
	 * Duration calculation Test
	 */
	@Test
	void addDuration001() {
		Date date1 = new Date(2019, 01, 31, 19, 42, 57);
		Date date2 = new Date(2019, 01, 31, 21, 33, 12);
		Duration duration = new Duration(date1, date2);
		assertTrue(duration.getDuration() == 6615);
	}
	@Test
	void addDuration002() {
		Date date1 = new Date(2019, 01, 31, 19, 42, 57);
		Date date2 = new Date(2019, 02, 01, 21, 33, 12);
		Duration duration = new Duration(date1, date2);
		assertTrue(duration.getDuration() == 93015);
	}
	/*
	@Test
	void addDuration003() throws RuntimeException {
		Date date2 = new Date(2019, 01, 31, 19, 42, 57);
		Date date1 = new Date(2019, 02, 01, 21, 33, 12);
		Duration duration = new Duration(date1, date2);
	}
	*/
	
	/*
	 * Test add methods
	 */
	@Test
	void addTest001() {
		Date date1 = new Date(2019, 01, 31, 19, 42, 57);
		Date date2 = new Date(2019, 01, 31, 21, 33, 12);
		Duration duration = new Duration(date1, date2);
		duration.add(2);
		assertTrue(duration.getDuration() == 6735);
	}
	@Test
	void addTest002() {
		Date date1 = new Date(2019, 01, 31, 19, 42, 57);
		Date date2 = new Date(2019, 01, 31, 21, 33, 12);
		Duration duration = new Duration(date1, date2);
		duration.add(2, 30);
		assertTrue(duration.getDuration() == 6765);
	}
	@Test
	void addTest003() {
		Date date1 = new Date(2019, 01, 31, 19, 42, 57);
		Date date2 = new Date(2019, 01, 31, 21, 33, 12);
		Duration duration = new Duration(date1, date2);
		duration.add(2, 30, 15);
		assertTrue(duration.getDuration() == 15630);
	}
}
