import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
import calendar.MeetingCalendar;
import calendar.Meeting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Calendar;

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
	GregorianCalendar repeatUntilC = new GregorianCalendar(2023,9,4,8,30);

	//multidayperweekevent
	String description4 = "Conference";
	String location4 = "City";
	GregorianCalendar startD = new GregorianCalendar(2023,8,28,12,30);
	GregorianCalendar endD = new GregorianCalendar(2023,8,28,14,30);
	GregorianCalendar repeatUntilD = new GregorianCalendar(2023,8,30,8,30);
	int[] days = {Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY};
	
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
		int[] testingDays = {Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY};
		//assertEquals(days, conference.getDays());
		assertEquals(true, Arrays.equals(testingDays, conference.getDays()));
	}
	
	@Test
	void testMultiDayPerWeekEventSetDays() {
		int[] testingDays = {Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY};
		conference.setDays(testingDays);
		//assertEquals(days, conference.getDays());
		assertEquals(true, Arrays.equals(testingDays, conference.getDays()));
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
	}

	@Test
	void testPriorityEventDoesDisplace() {
		String description6 = "Wedding";
		String location6 = "Ranch";
		GregorianCalendar startF = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar endF = new GregorianCalendar(2023,8,28,9,30);
		PriorityEvent wedding = new PriorityEvent(description6, location6, startF, endF);
		
		Meeting graduationMeeting = new Meeting(description1, location1, startA, endA);
		graduation.scheduleEvent(gcal);
		assertEquals(graduationMeeting, gcal.findMeeting(startA));
		
		Meeting weddingMeeting = new Meeting(description6, location6, startF, endF);
		wedding.scheduleEvent(gcal);
		
		assertEquals(weddingMeeting, gcal.findMeeting(startF));
	}
	
	@Test
	void testWeeklyEventDoesNotDisplace() {
		String description7 = "Crying";
		String location7 = "Home";
		GregorianCalendar startG = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar endG = new GregorianCalendar(2023,8,28,9,30);
		GregorianCalendar repeatUntilG = new GregorianCalendar(2023,9,28,8,30);
		WeeklyEvent crying = new WeeklyEvent(description7, location7, startG, endG, repeatUntilG);
		
		Meeting graduationMeeting = new Meeting(description1, location1, startA, endA);
		graduation.scheduleEvent(gcal);
		assertEquals(graduationMeeting, gcal.findMeeting(startA));
		
		Meeting cryingMeeting = new Meeting(description7, location7, startG, endG);
		crying.scheduleEvent(gcal);
		assertEquals(graduationMeeting, gcal.findMeeting(startG));
	}
	
	@Test
	void testMultiDayPerWeekDoesNotDisplace(){
		
		String d8 = "Gaming";
		String l8 = "Home";
		GregorianCalendar startH = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar endH = new GregorianCalendar(2023,8,28,9,30);
		GregorianCalendar repeatUntilH = new GregorianCalendar(2023,8,30,8,30);
		int[] testingDays = {Calendar.MONDAY,Calendar.TUESDAY,Calendar.WEDNESDAY};
		MultiDayPerWeekEvent gaming = new MultiDayPerWeekEvent(d8, l8, startH, endH, repeatUntilH, testingDays);
		
		Meeting graduationMeeting = new Meeting(description1, location1, startA, endA);
		graduation.scheduleEvent(gcal);
		assertEquals(graduationMeeting, gcal.findMeeting(startA));
		
		//Meeting gamingMeeting = new Meeting(d8, l8, startH, endH);
		gaming.scheduleEvent(gcal);
		assertEquals(graduationMeeting, gcal.findMeeting(startA));
		
		
		/*
		String D = "D";
		String L = "L";
		String D2 = "D2";
		String L2 = "L2";
		GregorianCalendar S = new GregorianCalendar(2023,8,28,8,30);
		GregorianCalendar E = new GregorianCalendar(2023,8,28,9,30);
		GregorianCalendar R = new GregorianCalendar(2023,8,30,8,30);
		int[] testingDays = {Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY};
		
		OneTimeEvent ONE = new OneTimeEvent(D, L, S, E);
		MultiDayPerWeekEvent WD = new MultiDayPerWeekEvent(D2, L2, S, E, R, testingDays);

		Meeting test1 = new Meeting(D, L, S, E);
		ONE.scheduleEvent(gcal);
		assertEquals(test1, gcal.findMeeting(S));

		WD.scheduleEvent(gcal);
		assertEquals(test1, gcal.findMeeting(S));
		*/
	}

	@Test
	void testRepeatMeeting() {
		String d9 = "Race";
		String l9 = "Track";
		GregorianCalendar startI = new GregorianCalendar(2023,9,15,8,30);
		GregorianCalendar endI = new GregorianCalendar(2023,9,15,9,30);
		GregorianCalendar repeatUntilI = new GregorianCalendar(2023,9,22,9,30);
		WeeklyEvent race = new WeeklyEvent(d9, l9, startI, endI, repeatUntilI);
		
		GregorianCalendar start1 = new GregorianCalendar(2023,9,15,8,30);
		GregorianCalendar end1 = new GregorianCalendar(2023,9,15,9,30);
		GregorianCalendar start2 = new GregorianCalendar(2023,9,22,8,30);
		GregorianCalendar end2 = new GregorianCalendar(2023,9,22,9,30);	
		GregorianCalendar start3 = new GregorianCalendar(2023,9,29,8,30);
		GregorianCalendar end3 = new GregorianCalendar(2023,9,29,9,30);	
		
		Meeting test1 = new Meeting(d9, l9, start1, end1);
		Meeting test2 = new Meeting(d9, l9, start2, end2);
		Meeting test3 = new Meeting(d9, l9, start3, end3);
		
		race.scheduleEvent(gcal);
		assertEquals(test1, gcal.findMeeting(startI));
		assertEquals(test2, gcal.findMeeting(start2));
		assertNull(gcal.findMeeting(start3));
	}
	
	@Test
	void testMultiDayPerWeekEventDoesNotEveryday() {
		GregorianCalendar startJ = new GregorianCalendar(2023,8,31,8,30);
		GregorianCalendar endJ = new GregorianCalendar(2023,8,31,9,30);
		
		Meeting test1 = new Meeting(description4, location4, startD, endD);
		Meeting test2 = new Meeting(description4, location4, startJ, endJ);
		conference.scheduleEvent(gcal);
		
		assertFalse(gcal.doesMeetingConflict(test1));
		assertFalse(gcal.doesMeetingConflict(test2));
	}
	
	@Test
	void testConstructorOTE() {
		assertEquals("Graduation", graduation.getDescription());
		assertEquals("High School", graduation.getLocation());
		assertEquals(startA, graduation.getStartTime());
		assertEquals(endA, graduation.getEndTime());
	}
	
	@Test
	void testConstructorPE() {
		assertEquals("Appointment", appointment.getDescription());
		assertEquals("Hospital", appointment.getLocation());
		assertEquals(startB, appointment.getStartTime());
		assertEquals(endB, appointment.getEndTime());
	}
	
	@Test
	void testConstructorWE() {
		assertEquals("Laundry", laundry.getDescription());
		assertEquals("Home", laundry.getLocation());
		assertEquals(startC, laundry.getStartTime());
		assertEquals(endC, laundry.getEndTime());
		assertEquals(repeatUntilC, laundry.getRepeatUntil());
	}
	
	@Test
	void testConstructorMDPWE() {
		assertEquals("Conference", conference.getDescription());
		assertEquals("City", conference.getLocation());
		assertEquals(startD, conference.getStartTime());
		assertEquals(endD, conference.getEndTime());
		assertEquals(repeatUntilD, conference.getRepeatUntil());
		assertEquals(days, conference.getDays());
	}
}
