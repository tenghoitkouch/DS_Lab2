package calendar;

import java.util.GregorianCalendar;

public class MultiDayWeekEvent extends CalendarEvent {

	private GregorianCalendar repeatUntil;
	private int[] days;
	
	public MultiDayWeekEvent(String description, String location, GregorianCalendar startTime,
			GregorianCalendar endTime, GregorianCalendar repeatUntil, int[] days) {
		super(description, location, startTime, endTime);
		this.repeatUntil = repeatUntil;
		this.days = days;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the repeatUntil
	 */
	public GregorianCalendar getRepeatUntil() {
		return repeatUntil;
	}

	/**
	 * @param repeatUntil the repeatUntil to set
	 */
	public void setRepeatUntil(GregorianCalendar repeatUntil) {
		this.repeatUntil = repeatUntil;
	}

	/**
	 * @return the days
	 */
	public int[] getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int[] days) {
		this.days = days;
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal) {
		// TODO Auto-generated method stub

	}

}
