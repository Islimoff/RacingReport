package com.foxminded.monako_report;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RacersReportFormatterTest {

	private int topRacers;

	@Before
	public void setUp() throws IOException {
		topRacers = 2;
	}

	@Test
	public void givenFilePath_whenBuildLapTimes_thenRacers() throws IOException {
		StringBuilder expected = new StringBuilder();
		expected.append(" 1.Sebastian Vettel |FERRARI                  |1:04.415" + lineSeparator());
		expected.append(" 2.Fernando Alonso  |MCLAREN RENAULT          |1:12.657" + lineSeparator());
		expected.append("-------------------------------------------------------" + lineSeparator());
		expected.append(" 3.Nico Hulkenberg  |RENAULT                  |1:13.065" + lineSeparator());

		assertEquals(expected.toString(), RacersReportFormatter.format(fillRacers(fillLapTimes()), topRacers));
	}

	public static List<LapTime> fillLapTimes() {
		List<LapTime> lapTimes = new ArrayList<>();
		lapTimes.add(new LapTime("SVF", LocalDateTime.parse("2018-05-24T12:02:58.917"),
				LocalDateTime.parse("2018-05-24T12:04:03.332")));
		lapTimes.add(new LapTime("FAM", LocalDateTime.parse("2018-05-24T12:13:04.512"),
				LocalDateTime.parse("2018-05-24T12:14:17.169")));
		lapTimes.add(new LapTime("NHR", LocalDateTime.parse("2018-05-24T12:02:49.914"),
				LocalDateTime.parse("2018-05-24T12:04:02.979")));
		return lapTimes;
	}

	public static List<Racer> fillRacers(List<LapTime> lapTimes) {
		List<Racer> racers = new ArrayList<>();
		racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", lapTimes.get(0)));
		racers.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", lapTimes.get(1)));
		racers.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT", lapTimes.get(2)));
		return racers;
	}
}
