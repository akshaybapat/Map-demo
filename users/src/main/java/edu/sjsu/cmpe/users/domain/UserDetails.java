package edu.sjsu.cmpe.users.domain;

import java.util.UUID;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class UserDetails
{
	private String password;
	private String firstName;
	private String lastName;
	private String sjsuId;
	private String email;
	private String photoURL;
	private String phoneNumber;
	private String latitude;
	private String longitude;
	
	private String _id = UUID.randomUUID().toString();
	
	public UserDetails(String sjsuId,String password,String firstName,String lastName, String phoneNumber, String email,String photoURL,String latitide, String longitude, String latitude )
	{
		this.sjsuId = sjsuId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.photoURL = photoURL;
		this.phoneNumber = phoneNumber;
		this.latitude = latitude;
		this.longitude = longitude;
		
		
	}
	
	public UserDetails() {
		
	}
	
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName= firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName= lastName;
	}
	
	public String getSjsuId()
	{
		return sjsuId;
	}
	
	public void setSjsuId(String sjsuId)
	{
		this.sjsuId=sjsuId;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber=phoneNumber;
	}
	
	public String getPhotoURL()
	{
		return photoURL;
	}
	
	public void setPhotoURL(String photoURL)
	{
		this.photoURL=photoURL;
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