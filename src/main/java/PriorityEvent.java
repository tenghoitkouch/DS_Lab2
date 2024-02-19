
import java.util.GregorianCalendar;
import calendar.MeetingCalendar;
import calendar.Meeting;

public class PriorityEvent extends CalendarEvent {

	public PriorityEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime) {
		super(description, location, startTime, endTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal) {
		// TODO Auto-generated method stub
		Meeting meeting = new Meeting(this.getDescription(), this.getLocation(), this.getStartTime(), this.getEndTime());
		cal.addMeeting(meeting, true);
	}

}
