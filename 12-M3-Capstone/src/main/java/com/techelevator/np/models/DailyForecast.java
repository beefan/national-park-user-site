package com.techelevator.np.models;

public class DailyForecast {

	private String parkCode;
	private int day;
	private int dailyLow;
	private int dailyHigh;
	private String forecast;
	
	public DailyForecast() {
		
	}
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getDailyLow() {
		return dailyLow;
	}
	public void setDailyLow(int dailyLow) {
		this.dailyLow = dailyLow;
	}
	public int getDailyHigh() {
		return dailyHigh;
	}
	public void setDailyHigh(int dailyHigh) {
		this.dailyHigh = dailyHigh;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	
	
}
