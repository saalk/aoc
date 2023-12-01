package nl.saalks.springbootvuejs.service.aoc23;

import com.sun.istack.NotNull;
import nl.saalks.springbootvuejs.service.AdventOfCodeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day2Test implements AdventOfCodeTest {

    Day1 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day1();
    }

    @Test
    @DisplayName("2.1 - sum of all of the calibration values")
    void sumCalibrationValuesWithExample() {

        @NotNull List<String> lines = readFile("aoc23/day2example.txt");
        int sum = fixture.sumCalibrationValues(lines);
        assertTrue("sum of all of the calibration values should be 142", sum == 142);
    }

    @Test
    @DisplayName("2.1 - sum of all of the calibration values")
    void sumCalibrationValuesWithRealInputFile() {

        List<String> lines = readFile("aoc23/day2.txt");
        int sum = fixture.sumCalibrationValues(lines);
        assertTrue("sum of all of the calibration values should be 55002", sum == 55002);
    }

    @Test
    @DisplayName("2.2 - sumCalibrationValues")
    void totalsumCalibrationValuesWithExampleInputFile() {

        List<String> lines = readFile("aoc23/day2example.txt");
        int sum = fixture.sumCalibrationValuesWritten(lines);
        int answer = 281;
        assertTrue(String.format("outcome should be [%d] not [%d]",answer, sum),sum == answer);
    }
    @Test
    @DisplayName("2.2 - sumCalibrationValues")
    void totalsumCalibrationValuesWithRealInputFile() {

        List<String> lines = readFile("aoc23/day2.txt");
        int sum = fixture.sumCalibrationValuesWritten(lines);
        int answer = 55093;
        assertTrue(String.format("outcome should be [%d] not [%d]",answer, sum),sum == answer);
    }
}