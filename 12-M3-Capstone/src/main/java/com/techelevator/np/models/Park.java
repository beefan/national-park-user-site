package com.techelevator.np.models;

import java.util.List;

public class Park {
	
	private String code;
	private String name;
	private String state;
	private int acreage;
	private int elevation;
	private double milesOfTrail;
	private int numberOfCampsites;
	private String climate;
	private int yearFounded;
	private int annualVisitorCount;
	private String inspirationalQuote;
	private String description;
	private int entryFee;
	private int numberOfSpecies;
	private List<DailyForecast> fiveDayForecast;
	
	public Park() {
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getAcreage() {
		return acreage;
	}
	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public double getMilesOfTrail() {
		return milesOfTrail;
	}
	public void setMilesOfTrail(double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}
	public int getNumberOfCampsites() {
		return numberOfCampsites;
	}
	public void setNumberOfCampsites(int numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public int getAnnualVisitorCount() {
		return annualVisitorCount;
	}
	public void setAnnualVisitorCount(int annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}
	public String getInspirationalQuote() {
		return inspirationalQuote;
	}
	public void setInspirationalQuote(String inspirationalQuote) {
		this.inspirationalQuote = inspirationalQuote;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEntryFee() {
		return entryFee;
	}
	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}
	public int getNumberOfSpecies() {
		return numberOfSpecies;
	}
	public void setNumberOfSpecies(int numberOfSpecies) {
		this.numberOfSpecies = numberOfSpecies;
	}

	public List<DailyForecast> getFiveDayForecast() {
		return fiveDayForecast;
	}

	public void setFiveDayForecast(List<DailyForecast> fiveDayForecast) {
		this.fiveDayForecast = fiveDayForecast;
	}
	
	
}
