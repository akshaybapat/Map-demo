package edu.sjsu.cmpe.users.domain;

public class EventDetails extends ElementDetails{
	
	private int eventId;
	private String eventName;
	private String eventCategory;
	private String eventDate;
	private String eventTime;
	private String eventDescription;
	private static int num=1;
	
	public String getEventName()
	{
		return eventName;
	}
	
	public void setEventName(String eventName)
	{
		this.eventName=eventName;
	}	
	
	public String getEventCategory()
	{
		return eventCategory;
	}
	
	public void setEventCategory(String eventCategory)
	{
		this.eventCategory=eventCategory;
	}
	
	public String getEventDate()
	{
		return eventDate;
	}
	
	public void setEventDate(String eventDate)
	{
		this.eventDate= eventDate;
	}
	
	public String getEventTime()
	{
		return eventTime;
	}
	
	public void setEventTime(String eventTime)
	{
		this.eventTime= eventTime;
	}
	
	public String getEventDescription()
	{
		return eventDescription;
	}
	
	public void setEventDescription(String eventDescription)
	{
		this.eventDescription = eventDescription;
	}
	
	public int getEventId()
	{
		return eventId;
	}
	
	public void setEventId(String evenId)
	{
		this.eventId = ++num;
	}

}
