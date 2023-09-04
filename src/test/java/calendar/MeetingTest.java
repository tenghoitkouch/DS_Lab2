package calendar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MeetingTest
{

	
	Meeting A;
	Meeting B;
	Meeting C;
	

	Meeting AB;
	Meeting BC;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startAB;
	GregorianCalendar endB;
	GregorianCalendar endC;
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		
		startA = new GregorianCalendar(2023,8,28,8,30);
		endA = new GregorianCalendar(2023,8,28,9,30);
		endB = new GregorianCalendar(2023,8,28,10,30);
		endC = new GregorianCalendar(2023,8,28,11,30);
		
		startAB = new GregorianCalendar(2023,8,28,9,00);
		GregorianCalendar endAB = new GregorianCalendar(2023,8,28,10,00);
		GregorianCalendar endBC = new GregorianCalendar(2023,8,28,11,00);
		
		
		
		A = new Meeting("A","ALoc",startA,endA);
		B = new Meeting("B","BLoc",endA,endB);
		C = new Meeting("C","CLoc",endB,endC);

		
		AB = new Meeting("AB","ABLoc",startAB,endAB);
		BC = new Meeting("BC","BCLoc",endAB,endBC);

		
		
	}

	@Test
	void testMeeting()
	{
		assertEquals("A",A.getDescription());
		assertEquals("ALoc",A.getLocation());
		assertEquals(startA,A.getStartTime());
		assertEquals(endA,A.getEndTime());
	}

	@Test
	void testOverlapsMeeting()
	{
		assertFalse(A.overlaps(B));
		assertFalse(B.overlaps(A));

		assertFalse(A.overlaps(C));
		assertFalse(C.overlaps(A));

		assertTrue(A.overlaps(AB));
		assertTrue(AB.overlaps(A));
		
		assertTrue(A.overlaps(AB));
		assertTrue(AB.overlaps(A));
		
		assertTrue(C.overlaps(BC));
		assertTrue(BC.overlaps(C));
		
	}

	@Test
	void testOverlapsGregorianCalendar()
	{
		assertTrue(A.overlaps(startA));
		assertTrue(A.overlaps(endA));
		assertTrue(A.overlaps(startAB));
		
		
		assertFalse(B.overlaps(startA));
		assertFalse(B.overlaps(endC));
		
		
	}

	@Test
	void testToString()
	{
		assertEquals("Meeting: A at ALoc\n"+""
				+ "From 08:30AM Thursday Sep. 28, 2023"+
				" to 09:30AM Thursday Sep. 28, 2023"
				,A.toString());
	}

}
