package com.foxminded.monako_report;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.*;
import static java.util.Comparator.*;

public class RacersReportFormatter {

	public static String format(List<Racer> racers, int topRacers) {
		StringBuilder result = new StringBuilder();
		AtomicInteger counter = new AtomicInteger(0);
		racers.stream().sorted(comparing(racer -> racer.getBestLapTime().getDuration()))
				.map(racer -> formatLine(racer, counter, topRacers)).forEach(result::append);
		return result.toString();
	}

	private static StringBuilder formatLine(Racer racer, AtomicInteger counter, int topRacers) {
		StringBuilder line = new StringBuilder();
		if (counter.intValue() == topRacers) {
			line.append("-------------------------------------------------------" + lineSeparator());
		}
		line.append(String.format("%2d.%-17s|%-25s|%d:%02d.%03d", counter.incrementAndGet(), racer.getFullName(),
				racer.getTeam(), racer.getBestLapTime().getDuration().toMinutes(),
				(racer.getBestLapTime().getDuration().toMillis() % 60000) / 1000,
				(racer.getBestLapTime().getDuration().toMillis() % 1000)) + lineSeparator());
		return line;
	}
}
