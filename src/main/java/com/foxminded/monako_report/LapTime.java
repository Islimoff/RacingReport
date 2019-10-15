package com.foxminded.monako_report;

import java.time.Duration;
import java.time.LocalDateTime;

public class LapTime {

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String abbreviation;

	public LapTime() {
	}

	public LapTime(String abbreviation, LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.abbreviation = abbreviation;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Duration getDuration() {
		return Duration.between(this.startTime, this.endTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(getClass() == obj.getClass()))
			return false;
		else {
			LapTime lapTime = (LapTime) obj;
			if (lapTime.startTime.isEqual(this.startTime) && lapTime.endTime.isEqual(this.endTime)
					&& lapTime.abbreviation.equals(this.abbreviation)) {
				return true;
			} else {
				return false;
			}
		}
	}
}
