package calendar;

import java.util.GregorianCalendar;

public abstract class CalendarEvent 
{
	private String description;
	private String location;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
	
	public CalendarEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime)
	{
		this.description = description;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public abstract void scheduleEvent(MeetingCalendar cal);

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the startTime
	 */
	public GregorianCalendar getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public GregorianCalendar getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(GregorianCalendar endTime) {
		this.endTime = endTime;
	}
	
	

}