package fr.ecp.IS1220.beribu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class DateTest {

	/*
	 * Test initialization
	 */
	@Test
	void initTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 10);
		SD.setTime(19, 45, 21);
		Date d = new Date();
		assertTrue((SD.getYear() == d.getYear()) &&
				(SD.getMonth() == d.getMonth()) &&
				(SD.getDay() == d.getDay()) &&
				(SD.getHour() == d.getHour()) &&
				(SD.getMinute() == d.getMinute()) &&
				(SD.getSecond() == d.getSecond()));
	}
	@Test
	void initTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 10);
		SD.setTime(19, 45, 21);
		Date d = new Date(2019, 3, 10, 19, 45, 21);
		assertTrue((SD.getYear() == d.getYear()) &&
				(SD.getMonth() == d.getMonth()) &&
				(SD.getDay() == d.getDay()) &&
				(SD.getHour() == d.getHour()) &&
				(SD.getMinute() == d.getMinute()) &&
				(SD.getSecond() == d.getSecond()));
	}
	
	/*
	 * Test equals and hash code
	 */
	@Test
	void equalsTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 10);
		SD.setTime(19, 45, 21);
		Date d1 = new Date();
		Date d2 = new Date(2019, 3, 10, 19, 45, 21);
		assertEquals(d1, d2);
	}
	@Test
	void equalsTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 10);
		SD.setTime(19, 45, 21);
		Date d1 = new Date();
		Date d2 = new Date(2019, 3, 10, 19, 45, 21);
		d2.setDay(4);
		assertNotEquals(d1, d2);
	}
	@Test
	void hashCodeTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 10);
		SD.setTime(19, 45, 21);
		Date d1 = new Date();
		Date d2 = new Date(2019, 3, 10, 19, 45, 21);
		assertEquals(d1.hashCode(), d2.hashCode());
	}
	@Test
	void hashCodeTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 3, 10);
		SD.setTime(19, 45, 21);
		Date d1 = new Date();
		Date d2 = new Date(2019, 3, 10, 19, 45, 21);
		d2.setHour(3);
		assertNotEquals(d1.hashCode(), d2.hashCode());
	}
	
	/*
	 * Test some wrong and correct Date definition at instantiation
	 */
	@Test
	void correctDateAtInstantiationTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date(2018, 12, 31, 23, 59, 59);
		});
	}
	@Test
	void correctDateAtInstantiationTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date(2020, 2, 29, 10, 10, 10);
		});
	}
	@Test
	void correctDateAtInstantiationTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date(2024, 02, 29, 10, 10, 10);
		});
	}
	@Test
	void correctDateAtInstantiationTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date(2028, 04, 30, 10, 10, 10);
		});
	}
	@Test
	void correctDateAtInstantiationTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date(2028, 03, 31, 10, 10, 10);
		});
	}
	@Test
	void correctDateAtInstantiationTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date();
		});
	}
	@Test
	void correctDateAtInstantiationTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2028, 2, 29);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date();
		});
	}
	@Test
	void correctDateAtInstantiationTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2020, 2, 29);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date();
		});
	}
	@Test
	void correctDateAtInstantiationTest009() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2020, 04, 30);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date();
		});
	}
	@Test
	void correctDateAtInstantiationTest010() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2020, 03, 31);
		SD.setTime(10, 10, 10);
		assertDoesNotThrow(() -> {
			new Date();
		});
	}
	@Test
	void wrongDateAtInstantiationTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2028, 4, 31, 10, 10, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 2, 29, 10, 10, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 2, 28, 24, 10, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 2, 28, 10, -1, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 2, 28, 10, 10, 61);
		});
	}
	@Test
	void wrongDateAtInstantiationTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 2, 28, 10, 10, -1);
		});
	}
	@Test
	void wrongDateAtInstantiationTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 2, 28, 10, 61, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 2, 28, -1, 10, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest009() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(-1, 2, 28, 10, 10, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest010() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, 13, 28, 10, 10, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest011() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 1, 1);
		SD.setTime(10, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			new Date(2019, -1, 28, 10, 10, 10);
		});
	}
	@Test
	void wrongDateAtInstantiationTest012() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2019, 2, 29);
			SD.setTime(10, 10, 10);
			new Date();
		});
	}
	@Test
	void wrongDateAtInstantiationTest013() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2019, 4, 31);
			SD.setTime(10, 10, 10);
			new Date();
		});
	}
	@Test
	void wrongDateAtInstantiationTest014() {
		SystemDate SD = SystemDate.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			SD.setDay(2019, 11, 31);
			SD.setTime(10, 10, 10);
			new Date();
		});
	}
	
	/*
	 * Test some wrong and correct Date definition with setters
	 */
	@Test
	void correctDateSetTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2020, 2, 28, 19, 10, 10);
		assertDoesNotThrow(() -> {
			d.setDay(29);
		});
	}
	@Test
	void correctDateSetTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertDoesNotThrow(() -> {
			d.setYear(2021);
		});
	}
	@Test
	void correctDateSetTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertDoesNotThrow(() -> {
			d.setMonth(10);
		});
	}
	@Test
	void correctDateSetTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertDoesNotThrow(() -> {
			d.setHour(20);
		});
	}
	@Test
	void correctDateSetTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertDoesNotThrow(() -> {
			d.setMinute(20);
		});
	}
	@Test
	void correctDateSetTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertDoesNotThrow(() -> {
			d.setSecond(20);
		});
	}
	@Test
	void wrongDateSetTest001() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setDay(-1);
		});
	}
	@Test
	void wrongDateSetTest002() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setDay(29);
		});
	}
	@Test
	void wrongDateSetTest003() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setMonth(13);
		});
	}
	@Test
	void wrongDateSetTest004() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setMonth(0);
		});
	}
	@Test
	void wrongDateSetTest005() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setYear(1969);
		});
	}
	@Test
	void wrongDateSetTest006() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setMonth(-1);
		});
	}
	@Test
	void wrongDateSetTest007() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setHour(24);
		});
	}
	@Test
	void wrongDateSetTest008() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setHour(-1);
		});
	}
	@Test
	void wrongDateSetTest009() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setMinute(-1);
		});
	}
	@Test
	void wrongDateSetTest010() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setMinute(60);
		});
	}
	@Test
	void wrongDateSetTest011() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setSecond(60);
		});
	}
	@Test
	void wrongDateSetTest012() {
		SystemDate SD = SystemDate.getInstance();
		SD.setDay(2019, 11, 29);
		SD.setTime(10, 10, 10);
		Date d = new Date(2019, 2, 28, 19, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			d.setSecond(-1);
		});
	}
}
