package calendar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MeetingCalendarTest
{
	Meeting A;
	Meeting B;
	Meeting C;
	

	Meeting AB;
	Meeting BC;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startAB;
	GregorianCalendar endAB;
	GregorianCalendar endB;
	GregorianCalendar endC;
	
	MeetingCalendar cal;
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		cal = new MeetingCalendar();
		startA = new GregorianCalendar(2023,8,28,8,30);
		endA = new GregorianCalendar(2023,8,28,9,30);
		endB = new GregorianCalendar(2023,8,28,10,30);
		endC = new GregorianCalendar(2023,8,28,11,30);
		
		startAB = new GregorianCalendar(2023,8,28,9,00);
		endAB = new GregorianCalendar(2023,8,28,10,00);
		GregorianCalendar endBC = new GregorianCalendar(2023,8,28,11,00);
		
		
		
		A = new Meeting("A","ALoc",startA,endA);
		B = new Meeting("B","BLoc",endA,endB);
		C = new Meeting("C","CLoc",endB,endC);

		
		AB = new Meeting("AB","ABLoc",startAB,endAB);
		BC = new Meeting("BC","BCLoc",endAB,endBC);

		
		
	}

	@Test
	void testDoesMeetingConflicts()
	{
		cal.addMeeting(A);
		cal.addMeeting(C);
		
		assertFalse(cal.doesMeetingConflict(B));
		assertTrue(cal.doesMeetingConflict(AB));
		assertTrue(cal.doesMeetingConflict(BC));
		
		
		
		MeetingCalendar cal2 = new MeetingCalendar();
		cal2.addMeeting(C);
		assertFalse(cal2.doesMeetingConflict(B));
		assertFalse(cal2.doesMeetingConflict(A));
		assertFalse(cal2.doesMeetingConflict(AB));
		assertTrue(cal2.doesMeetingConflict(BC));
		
		
		MeetingCalendar cal3 = new MeetingCalendar();
		cal3.addMeeting(A);
		assertFalse(cal3.doesMeetingConflict(B));
		assertFalse(cal3.doesMeetingConflict(C));
		assertFalse(cal3.doesMeetingConflict(BC));
		assertTrue(cal3.doesMeetingConflict(AB));
	}

	@Test
	void testAddMeetingMeetingBoolean()
	{
		assertTrue(cal.addMeeting(A,false));
		assertTrue(cal.addMeeting(C,false));
		assertFalse(cal.addMeeting(C,false));//can't add twice 

		
		//A,C
		assertFalse(cal.addMeeting(AB,false));
		assertFalse(cal.addMeeting(BC,false));
		
		assertTrue(cal.addMeeting(B,false));
		GregorianCalendar tailB = (GregorianCalendar) endB.clone(); 
		tailB.add(Calendar.MINUTE, -1);
		assertEquals(B,cal.findMeeting(tailB)); //B is present
		//A,B,C
		
		assertTrue(cal.addMeeting(AB,true));
		assertNull(cal.findMeeting(startA)); //A has been removed
		assertNull(cal.findMeeting(tailB)); //B has been removed
		
		//AB,C
		assertFalse(cal.addMeeting(A,false));
		assertFalse(cal.addMeeting(B,false));
		
		assertFalse(cal.addMeeting(BC,false));
		
		assertTrue(cal.addMeeting(BC,true));
		assertNull(cal.findMeeting(endC));
		//AB,BC
		
		assertTrue(cal.addMeeting(A,true));
		//A,BC
		assertEquals(A,cal.findMeeting(startA)); 
		assertEquals(A,cal.findMeeting(endA));
		GregorianCalendar tailAB = (GregorianCalendar) endAB.clone(); 
		tailAB.add(Calendar.MINUTE, -15);
		
		assertNull(cal.findMeeting(tailAB));
		
		
		
		
	}

	
	GregorianCalendar offsetCal(GregorianCalendar gcal, int minutes)
	{
		GregorianCalendar offset = (GregorianCalendar) gcal.clone(); 
		offset.add(Calendar.MINUTE, minutes);
		return offset;
	}
	
	
	@Test
	void testFindMeeting()
	{
		cal.addMeeting(A);
		assertEquals(A,cal.findMeeting(startA));
		assertEquals(A,cal.findMeeting(endA));
		assertEquals(A,cal.findMeeting(offsetCal(startA,15)));

		assertNull(cal.findMeeting(endC));
		assertNull(cal.findMeeting(offsetCal(endB,-15)));

		
		cal.addMeeting(C);
		assertEquals(C,cal.findMeeting(endB));
		assertEquals(C,cal.findMeeting(endC));
		assertEquals(C,cal.findMeeting(offsetCal(endC,-15)));
		assertNull(cal.findMeeting(offsetCal(endB,-15)));

		
		
		cal.addMeeting(B);
		assertEquals(B,cal.findMeeting(offsetCal(endB,-15)));
		
		assertNull(cal.findMeeting(offsetCal(startA,-15)));
		assertNull(cal.findMeeting(offsetCal(endC,15)));
	}
	
	
	@Test
	void testFindMeetingBoundariesForward()
	{
		cal.addMeeting(A);
		cal.addMeeting(B);
		cal.addMeeting(C);
		
		assertEquals(A,cal.findMeeting(startA));
		assertEquals(B,cal.findMeeting(endA));
		assertEquals(C,cal.findMeeting(endB));
	}

	@Test
	void testFindMeetingBoundariesBackward()
	{
		cal.addMeeting(C);
		cal.addMeeting(B);
		cal.addMeeting(A);
		
		assertEquals(A,cal.findMeeting(startA));
		assertEquals(B,cal.findMeeting(endA));
		assertEquals(C,cal.findMeeting(endB));
	}

	@Test
	void testFindMeetingBoundariesInterleave()
	{
		cal.addMeeting(A);
		cal.addMeeting(C);
		cal.addMeeting(B);
		
		assertEquals(A,cal.findMeeting(startA));
		assertEquals(B,cal.findMeeting(endA));
		assertEquals(C,cal.findMeeting(endB));
	}
	
	
	
	
	@Test
	void testRemoveMeeting()
	{
		cal.addMeeting(A);
		cal.addMeeting(B);
		cal.addMeeting(C);
		
		cal.removeMeeting(A);
		assertNull(cal.findMeeting(startA));
		assertEquals(B,cal.findMeeting(endA));
		assertEquals(C,cal.findMeeting(endB));
		
		cal.removeMeeting(C);
		
		assertNull(cal.findMeeting(startA));
		assertEquals(B,cal.findMeeting(endA));
		assertNull(cal.findMeeting(offsetCal(endC,-15)));
		
		cal.removeMeeting(B);
		assertNull(cal.findMeeting(endA));
		
		
	}

}
