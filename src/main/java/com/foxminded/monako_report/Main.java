package com.foxminded.monako_report;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		int topRacers = 15;
		RacerInfo racerInfo = new RacerInfo();
		List<LapTime> lapTimes = racerInfo.buildLapTimes(getFilePath("start.log"), getFilePath("end.log"));
		List<Racer> racers = racerInfo.buildRacers(getFilePath("abbreviations.txt"), lapTimes);
		System.out.println(RacersReportFormatter.format(racers, topRacers));
	}

	public static String getFilePath(String fileName) {
		return new File(Main.class.getClassLoader().getResource(fileName).getFile()).getAbsolutePath();
	}
}
