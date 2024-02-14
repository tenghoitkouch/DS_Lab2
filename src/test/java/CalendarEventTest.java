import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarEventTest {
	
	OneTimeEvent graduation;
	
	WeeklyEvent laundry;
	MultiDayPerWeekEvent conference;
	
	@BeforeEach
	void setUp() throws Exception {
		graduation = new OneTimeEvent("Graduation", "High School", new GregorianCalendar(2023,8,28,8,30), new GregorianCalendar(2023,8,28,9,30));
		laundry = new WeeklyEvent("Laundry", "Home", new GregorianCalendar(2023,8,28,10,30), new GregorianCalendar(2023,8,28,12,30), new GregorianCalendar(2024,8,28));
		
		int days[] = {28, 29};
		conference = new MultiDayPerWeekEvent("Conference", "City", new GregorianCalendar(2023,8,28,2,30), new GregorianCalendar(2023,8,28,4,30), new GregorianCalendar(2023,8,30), days);
	}

	@Test
	void testGetDescription() {
		assertEquals("Graduation", graduation.getDescription());
	}

	@Test
	void testSetDescription() {
		graduation.setDescription("This is my graduation");
		assertEquals("This is my graduation", graduation.getDescription());
	}

	@Test
	void testGetLocation() {
		assertEquals("High School", graduation.getLocation());
	}

	@Test
	void testSetLocation() {
		graduation.setLocation("School");
		assertEquals("School", graduation.getLocation());
	}

	@Test
	void testGetStartTime() {
		assertEquals(new GregorianCalendar(2023,8,28,8,30), graduation.getStartTime());
	}

	@Test
	void testSetStartTime() {
		graduation.setStartTime(new GregorianCalendar(2023,8,28,7,30));
		assertEquals(new GregorianCalendar(2023,8,28,7,30), graduation.getStartTime());
	}

	@Test
	void testGetEndTime() {
		assertEquals(new GregorianCalendar(2023,8,28,9,30), graduation.getEndTime());
	}

	@Test
	void testSetEndTime() {
		graduation.setEndTime(new GregorianCalendar(2023,8,28,10,30));
		assertEquals(new GregorianCalendar(2023,8,28,10,30), graduation.getEndTime());
	}
	
	@Test
	void testWeeklyEventGetRepeatUntil() {
		assertEquals(new GregorianCalendar(2024,8,28), laundry.getRepeatUntil());
	}
	
	@Test
	void testWeeklyEventSetRepeatUntil() {
		laundry.setRepeatUntil(new GregorianCalendar(2025,8,28));
		assertEquals(new GregorianCalendar(2025,8,28), laundry.getRepeatUntil());
	}
	
	@Test
	void testMultiDayPerWeekEventGetDays() {
		int days[] = {28,29};
		assertEquals(days, conference.getDays());
	}
	
	@Test
	void testMultiDayPerWeekEventSetDays() {
		int days[] = {29,30};
		conference.setDays(days);
		assertEquals(days, conference.getDays());
	}

}
