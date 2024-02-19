
import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

public class WeeklyEvent extends CalendarEvent {
	
	private GregorianCalendar repeatUntil;

	public WeeklyEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar repeatUntil) {
		super(description, location, startTime, endTime);
		this.repeatUntil = repeatUntil;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal) {
		// TODO Auto-generated method stub
		Meeting meeting = new Meeting(description, location, startTime, endTime);
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

}
