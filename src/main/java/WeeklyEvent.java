import java.util.Calendar;
import java.util.GregorianCalendar;
import calendar.MeetingCalendar;
import calendar.Meeting;

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
		
		GregorianCalendar currentStartTime = new GregorianCalendar();
		currentStartTime = (GregorianCalendar)this.getStartTime().clone();

		GregorianCalendar currentEndTime = new GregorianCalendar();
		currentEndTime = (GregorianCalendar)this.getEndTime().clone();

		while(currentStartTime.compareTo(this.getRepeatUntil()) < 0){

			GregorianCalendar tempStartTime = new GregorianCalendar();
			tempStartTime = (GregorianCalendar)currentStartTime.clone();
			GregorianCalendar tempEndTime = new GregorianCalendar();
			tempEndTime = (GregorianCalendar)currentEndTime.clone();

			Meeting meeting = new Meeting(this.getDescription(), this.getLocation(), tempStartTime, tempEndTime);
			//System.out.println(meeting);
			cal.addMeeting(meeting);

			currentStartTime.add(Calendar.DAY_OF_MONTH, 7);
			currentEndTime.add(Calendar.DAY_OF_MONTH, 7);

		}
		
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
