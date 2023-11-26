package nl.saalks.springbootvuejs.service.aoc22;

import nl.saalks.springbootvuejs.service.AdventOfCodeTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day5Test implements AdventOfCodeTest {
	
	
	Day5 fixture;
	
	@BeforeEach
	void setUp() {
		fixture = new Day5();
	}
	
	@Test
	@DisplayName("5.1 - SupplyStack example")
	void part1WithExampleFile() {
		
		List<String> lines = readFile("aoc22/day5example.txt");
		String score = fixture.solutionPartOne(lines);
		assertTrue("SupplyStack: ", score.equals("CMZ"));
	}
	
	@Test
	@DisplayName("5.1 - SupplyStack real")
	void part1WithRealFile() {
		
		List<String> lines = readFile("aoc22/day5.txt");
		String score = fixture.solutionPartOne(lines);
		assertTrue("Result: ", score.equals("QGTHFZBHV"));
	}
	
	@Test
	@DisplayName("5.2 - SupplyStack example")
	void part2WithExampleFile() {
		
		List<String> lines = readFile("aoc22/day5example.txt");
		int score = fixture.solutionPartTwo(lines);
//		assertTrue("Result: ", score == 4);
	}
	
	@Test
	@DisplayName("5.2 - SupplyStack real")
	void part2WithRealFile() {
		
		List<String> lines = readFile("aoc22/day5.txt");
		int score = fixture.solutionPartTwo(lines);
		assertTrue("sumOfThePriorities: ", score == 792);
	}
	
}