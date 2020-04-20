package com.techelevator.np.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class SurveyEntry {

	private long id;
	@NotBlank(message = "Must Provide a Park Selection")
	private String parkCode;
	
	@Email(message = "Must Provide a Valid Email")
	@NotBlank(message = "Must Provide an Email")
	private String emailAddress;
	
	@NotBlank( message = "Must Provide a State")
	private String state;
	
	@NotNull(message = " Must Provide an Activity Level")
	private String activityLevel;
	
	private String parkName;
	
	public SurveyEntry() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
	
}
