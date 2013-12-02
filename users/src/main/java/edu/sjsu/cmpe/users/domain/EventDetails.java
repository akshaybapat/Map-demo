package edu.sjsu.cmpe.users.domain;

import java.util.UUID;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class EventDetails {
	
	//private int id;
	@NotBlank
	private String eventName;
	private String eventCategory;
	private String eventDate;
	private String eventTime;
	private String eventDescription;
	private static int num=1;
	private String latitude;
	private String longitude;
	
	private String _id = UUID.randomUUID().toString();
	 
	public EventDetails(String eventName,String eventCategory,String eventDate,String eventTime, String eventDescription, String latitide, String longitude, String latitude) {
		this.eventName = eventName;
		this.eventCategory = eventCategory;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventDescription = eventDescription;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	
	public EventDetails() {
		
	}
	
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
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
	
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}


}
