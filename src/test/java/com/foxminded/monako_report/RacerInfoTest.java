package com.foxminded.monako_report;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RacerInfoTest {

	private RacerInfo racerInfo;

	@Before
	public void setUp() {
		racerInfo = new RacerInfo();
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenFirstArgumentNull_whenBuildLapTimes_thenException() throws IllegalArgumentException, IOException {

		racerInfo.buildLapTimes(getFilePath("startTest.log"), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenSecondArgumentNull_whenBuildLapTimes_thenException() throws IllegalArgumentException, IOException {

		racerInfo.buildLapTimes(null, getFilePath("endTest.log"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenFirstArgumentNull_whenBuildRacers_thenException() throws IllegalArgumentException, IOException {

		racerInfo.buildRacers(getFilePath("startTest.log"), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenSecondArgumentNull_whenBuildRacers_thenException() throws IllegalArgumentException, IOException {

		racerInfo.buildRacers(null, fillLapTimes());
	}

	@Test
	public void givenFilePath_whenBuildLapTimes_thenLapTimes() throws IOException {
		
		assertEquals(fillLapTimes(), racerInfo.buildLapTimes(getFilePath("startTest.log"), getFilePath("endTest.log")));
	}

	@Test
	public void givenFilePath_whenBuildRacers_thenRacers() throws IOException {
		
		assertEquals(fillRacers(fillLapTimes()),
				racerInfo.buildRacers(getFilePath("abbreviationsTest.txt"), fillLapTimes()));
	}

	public static String getFilePath(String fileName) {
		return new File(Main.class.getClassLoader().getResource(fileName).getFile()).getAbsolutePath();
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
		List<Racer> racers=new ArrayList<>();
		racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", lapTimes.get(0)));
		racers.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", lapTimes.get(1)));
		racers.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT", lapTimes.get(2)));
		return racers;
	}
}