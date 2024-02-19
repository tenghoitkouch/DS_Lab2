
import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

public class OneTimeEvent extends CalendarEvent {

	public OneTimeEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime) {
		super(description, location, startTime, endTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal) {
		// TODO Auto-generated method stub
		Meeting meeting = new Meeting(this.description, this.location, this.startTime, this.endTime);
		cal.addMeeting(meeting);
	}

}
