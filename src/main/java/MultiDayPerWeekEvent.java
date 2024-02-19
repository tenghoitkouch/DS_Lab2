import java.util.Calendar;
import java.util.GregorianCalendar;
import calendar.MeetingCalendar;
import calendar.Meeting;
import java.util.Arrays;

public class MultiDayPerWeekEvent extends CalendarEvent {

	private GregorianCalendar repeatUntil;
	private int days[];
	
	public MultiDayPerWeekEvent(String description, String location, GregorianCalendar startTime,
			GregorianCalendar endTime, GregorianCalendar repeatUntil, int days[]) {
		super(description, location, startTime, endTime);
		this.repeatUntil = repeatUntil;
		this.days = days;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the repeatUntil
	 */
	public GregorianCalendar getRepeatUntil() {
		return this.repeatUntil;
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
		return this.days;
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

		GregorianCalendar currentStartTime = this.getStartTime();
		GregorianCalendar currentEndTime = this.getEndTime();

		while(currentStartTime.compareTo(this.getRepeatUntil()) <= 0){

			for (int day : this.getDays()) {
				if (day == currentStartTime.DAY_OF_WEEK) {
					Meeting meeting = new Meeting(this.getDescription(), this.getLocation(), currentStartTime, currentEndTime);
					cal.addMeeting(meeting);
					break;
				}
			}

			currentStartTime.add(Calendar.DAY_OF_MONTH, 1);
			currentEndTime.add(Calendar.DAY_OF_MONTH, 1);
		}

	}

}
