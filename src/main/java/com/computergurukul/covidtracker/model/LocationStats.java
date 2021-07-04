package com.computergurukul.covidtracker.model;

public class LocationStats {

	private String state;
	private int latestConfirmed;
	private int latestRecovered;
	private int latestDeceased;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLatestConfirmed() {
		return latestConfirmed;
	}
	public void setLatestConfirmed(int latestConfirmed) {
		this.latestConfirmed = latestConfirmed;
	}
	public int getLatestRecovered() {
		return latestRecovered;
	}
	public void setLatestRecovered(int latestRecovered) {
		this.latestRecovered = latestRecovered;
	}
	public int getLatestDeceased() {
		return latestDeceased;
	}
	public void setLatestDeceased(int latestDeceased) {
		this.latestDeceased = latestDeceased;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", latestConfirmed=" + latestConfirmed + ", latestRecovered="
				+ latestRecovered + ", latestDeceased=" + latestDeceased + "]";
	}
	
	
}
