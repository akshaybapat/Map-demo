package edu.sjsu.cmpe.users.domain;

public class UserDetails extends ElementDetails
{
	private String password;
	private String firstName;
	private String lastName;
	private String sjsuId;
	private String email;
	private String phoneNumber;
	
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
}