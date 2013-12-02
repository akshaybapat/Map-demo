package edu.sjsu.cmpe.users.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ElementDetails {
	
	private int id;
	private String name;
	private String time;
	private String latitude;
	private String longitude;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int tempId)
	{
		id=tempId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String tempName)
	{
		name=tempName;
	}
	
	public String getTime()
	{
		return time;
	}
	
	public void setTime()
	{
		DateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
		
		Date date = new Date();
		time=dateFormat.format(date);
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public void setLatitude(String tempLatitude)
	{
		latitude=tempLatitude;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
	
	public void setLongitude(String tempLongitude)
	{
		longitude=tempLongitude;
	}

}
