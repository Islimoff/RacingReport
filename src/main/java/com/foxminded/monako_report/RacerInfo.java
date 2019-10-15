package com.foxminded.monako_report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class RacerInfo {

	private static final String FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";

	public List<Racer> buildRacers(String path, List<LapTime> lapTimes) throws IOException {
		if (path == null || lapTimes == null) {
			throw new IllegalArgumentException("Path cannot be null");
		}
		return Files.lines(Paths.get(path), StandardCharsets.UTF_8)
				.map(fileTextLine -> buildRacer(fileTextLine, lapTimes)).collect(Collectors.toList());
	}

	public List<LapTime> buildLapTimes(String startPath, String endPath) throws IOException {
		if (startPath == null || endPath == null) {
			throw new IllegalArgumentException("Path cannot be null");
		}
		List<LapTime> lapTimes = Files.lines(Paths.get(startPath), StandardCharsets.UTF_8)
				.map(RacerInfo::buildStartTime).collect(Collectors.toList());
		Files.lines(Paths.get(endPath), StandardCharsets.UTF_8)
				.forEach(fileTextLine -> buildEndTime(lapTimes, fileTextLine));
		return lapTimes;
	}

	private static LapTime buildStartTime(String fileTextLine) {
		LapTime lapTime = new LapTime();
		lapTime.setAbbreviation(getAbbreviation(fileTextLine));
		lapTime.setStartTime(getTime(fileTextLine));
		return lapTime;
	}

	private void buildEndTime(List<LapTime> lapTimes, String fileTextLine) {
		lapTimes.stream().filter(lapTime -> lapTime.getAbbreviation().equals(getAbbreviation(fileTextLine)))
				.forEach(lapTime -> lapTime.setEndTime(getTime(fileTextLine)));
	}

	private static void setBestLapTime(Racer racer, List<LapTime> lapTimes) {
		lapTimes.stream().filter(lapTime -> racer.getAbbreviation().equals(lapTime.getAbbreviation()))
				.forEach(racer::setBestLapTime);
	}

	private static LocalDateTime getTime(String fileTextLine) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
		return LocalDateTime.parse(fileTextLine.substring(3), formatter);
	}

	private static String getAbbreviation(String fileTextLine) {
		return fileTextLine.substring(0, 3);
	}

	private static Racer buildRacer(String fileTextLine, List<LapTime> lapTimes) {
		String[] result = fileTextLine.split("_");
		Racer racer = new Racer();
		racer.setAbbreviation(result[0]);
		racer.setFullName(result[1]);
		racer.setTeam(result[2]);
		setBestLapTime(racer, lapTimes);
		return racer;
	}
}