import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
import calendar.MeetingCalendar;
import calendar.Meeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class CalendarEventTest {
	
	MeetingCalendar gcal = new MeetingCalendar();
	
	OneTimeEvent graduation;
	PriorityEvent appointment;
	WeeklyEvent laundry;
	MultiDayPerWeekEvent conference;
	
	//onetimeevent
	String description1 = "Graduation";
	String location1 = "High School";
	GregorianCalendar startA = new GregorianCalendar(2023,8,28,8,30);
	GregorianCalendar endA = new GregorianCalendar(2023,8,28,9,30);
	
	//priorityevent
	String description2 = "Appointment";
	String location2 = "Hospital";
	GregorianCalendar startB = new GregorianCalendar(2023,8,28,10,30);
	GregorianCalendar endB = new GregorianCalendar(2023,8,28,11,30);
	
	//weekly event
	String description3 = "Laundry";
	String location3 = "Home";
	GregorianCalendar startC = new GregorianCalendar(2023,8,28,6,30);
	GregorianCalendar endC = new GregorianCalendar(2023,8,28,8,30);
	GregorianCalendar repeatUntilC = new GregorianCalendar(2023,9,28,8,30);

	//multidayperweekevent
	String description4 = "Conference";
	String location4 = "City";
	GregorianCalendar startD = new GregorianCalendar(2023,8,28,12,30);
	GregorianCalendar endD = new GregorianCalendar(2023,8,28,14,30);
	GregorianCalendar repeatUntilD = new GregorianCalendar(2023,8,30,8,30);
	int[] days = {28,29};
	
	@BeforeEach
	void setUp() throws Exception {
		graduation = new OneTimeEvent(description1, location1, startA, endA);
		appointment = new PriorityEvent(description2, location2, startB, endB);
		laundry = new WeeklyEvent(description3, location3, startC, endC, repeatUntilC);
		conference = new MultiDayPerWeekEvent(description4, location4, startD, endD, repeatUntilD, days);
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
		assertEquals(repeatUntilC, laundry.getRepeatUntil());
	}
	
	@Test
	void testWeeklyEventSetRepeatUntil() {
		laundry.setRepeatUntil(new GregorianCalendar(2025,8,28));
		assertEquals(new GregorianCalendar(2025,8,28), laundry.getRepeatUntil());
	}
	
	@Test
	void testMultiDayPerWeekEventGetDays() {
		int[] days = {28,29};
		//assertEquals(days, conference.getDays());
		assertEquals(true, Arrays.equals(days, conference.getDays()));
	}
	
	@Test
	void testMultiDayPerWeekEventSetDays() {
		int[] days = {29,30};
		conference.setDays(days);
		//assertEquals(days, conference.getDays());
		assertEquals(true, Arrays.equals(days, conference.getDays()));
	}
	
	@Test
	void testOneTimeEventDoesNotDisplace() {
		
		//onetimeevent t
		String description5 = "Relax";
		String location5 = "Spa";
		GregorianCalendar startE = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar endE = new GregorianCalendar(2023,8,28,9,30);
		OneTimeEvent relax = new OneTimeEvent(description5, location5, startE, endE);
		
		graduation.scheduleEvent(gcal);
		Meeting graduationMeeting = gcal.findMeeting(startA);
		relax.scheduleEvent(gcal);
		
		assertEquals(graduationMeeting, gcal.findMeeting(startA));
		//gcal.findMeeting(startE);
	}

	@Test
	void testPriorityEventDoesDisplace() {
		String description6 = "Wedding";
		String location6 = "Ranch";
		GregorianCalendar startF = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar endF = new GregorianCalendar(2023,8,28,9,30);
		PriorityEvent wedding = new PriorityEvent(description6, location6, startF, endF);
		
		appointment.scheduleEvent(gcal);
		Meeting appointmentMeeting = gcal.findMeeting(startA);
		wedding.scheduleEvent(gcal);
		
		assertNotEquals(appointmentMeeting, gcal.findMeeting(startF));
	}
}
