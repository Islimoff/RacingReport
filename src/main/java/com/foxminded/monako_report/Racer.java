package com.foxminded.monako_report;

public class Racer {

	private String abbreviation;
	private String fullName;
	private String team;
	private LapTime bestLapTime;

	public Racer() {
	}

	public Racer(String abbreviation, String fullName, String team, LapTime bestLapTime) {
		this.abbreviation = abbreviation;
		this.fullName = fullName;
		this.team = team;
		this.bestLapTime = bestLapTime;
	}

	public LapTime getBestLapTime() {
		return bestLapTime;
	}

	public void setBestLapTime(LapTime bestLapTime) {
		this.bestLapTime = bestLapTime;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
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
			Racer racer = (Racer) obj;
			if (racer.abbreviation.equals(this.abbreviation) && racer.team.equals(this.team)
					&& racer.fullName.equals(this.fullName) && racer.bestLapTime.equals(this.bestLapTime)) {
				return true;
			} else {
				return false;
			}
		}
	}
}
