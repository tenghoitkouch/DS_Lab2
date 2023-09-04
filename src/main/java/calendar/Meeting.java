package calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Meeting implements Cloneable
{
	private String description;
	private String location;
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;

	
	
	
	
	
	
	
	
	/**
	 * A Container to describe a meeting
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public Meeting(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime)
	{
		super();
		this.description = description;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
	}


	/**
	 * Detects if this meeting overlaps with that meeting.  It will allow one meetings ending time to overlap with the
	 * starting time of the next.
	 * @param that the other meeting to compare yourself to
	 * @return true if this Meeting overlaps with "that" meeting, and false otherwise
	 */
	public boolean overlaps(Meeting that)
	{
		boolean before = this.endTime.compareTo(that.startTime)<=0;
		boolean after  = this.startTime.compareTo(that.endTime)>=0;
		
		return ! (before || after);
	}
	
	/**
	 * Detects if that time intersects with the times of this meeting.  
	 * @param time
	 * @return true if the time is contained within the meeting times, inclusively, false otherwise.
	 */
	public boolean overlaps(GregorianCalendar time)
	{
		boolean beforeEnd = this.endTime.compareTo(time)>=0;
		boolean afterStart  = this.startTime.compareTo(time)<=0;
		
		return beforeEnd && afterStart;
	}




	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}


	/**
	 * @return the location
	 */
	public String getLocation()
	{
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location)
	{
		this.location = location;
	}


	/**
	 * @return the startTime
	 */
	public GregorianCalendar getStartTime()
	{
		return startTime;
	}


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(GregorianCalendar startTime)
	{
		this.startTime = startTime;
	}


	/**
	 * @return the endTime
	 */
	public GregorianCalendar getEndTime()
	{
		return endTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(GregorianCalendar endTime)
	{
		this.endTime = endTime;
	}


	@Override
	public String toString()
	{
		             
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mma EEEEE MMM. dd, yyyy");
		Date startDate = startTime.getTime();
		Date endDate = endTime.getTime();

		String startString = dateFormat.format(startDate);
		String endString = dateFormat.format(endDate);
		
		
		
		
		return "Meeting: " + description + " at " + location + 
				"\nFrom " + startString
				+ " to " + endString;
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(description, endTime, location, startTime);
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meeting other = (Meeting) obj;
		return Objects.equals(description, other.description) && Objects.equals(endTime, other.endTime)
				&& Objects.equals(location, other.location) && Objects.equals(startTime, other.startTime);
	}


	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		GregorianCalendar s = (GregorianCalendar) startTime.clone();
		GregorianCalendar e = (GregorianCalendar) endTime.clone();
		return new Meeting(this.description,this.location,s,e);
		
		
	}
	
	
	
	
}
