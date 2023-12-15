package com.example.service.aoc23;

import com.example.service.AdventOfCodeTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class Day2Test implements AdventOfCodeTest {

    Day2 fixture;

    @BeforeEach
    void setUp() {
        fixture = new Day2();
    }

    @Test
    @DisplayName("2.1 - sum of all of the calibration values")
    void sumIDsGamesWithExample() throws Exception {
        @NotNull List<String> lines = readFile("aoc23/day2example.txt");
        int[] sum = fixture.sumIDsGames(lines);
        int answer = 8;
        assertTrue(String.format("outcome should be [%d] not [%d]",answer, sum[0]),
                sum[0] == answer);
    }

    @Test
    @DisplayName("2.1 - sum of all of the calibration values")
    void sumIDsGamesWithRealInputFile() throws Exception {

        List<String> lines = readFile("aoc23/day2.txt");
        int[] sum = fixture.sumIDsGames(lines);
        int answer = 2551;
        assertTrue(String.format("outcome should be [%d] not [%d]",answer, sum[0]),
                sum[0] == answer);
    }

    @Test
    @DisplayName("2.2 - sumCalibrationValues")
    void totalsumCalibrationValuesWithExampleInputFile() throws Exception {

        List<String> lines = readFile("aoc23/day2example.txt");
        int[] sum = fixture.sumIDsGames(lines);
        int answer = 2286;
        assertTrue(String.format("outcome should be [%d] not [%d]",answer, sum[1]),
                sum[1] == answer);
    }
    @Test
    @DisplayName("2.2 - sumCalibrationValues")
    void totalsumCalibrationValuesWithRealInputFile() throws Exception {

        List<String> lines = readFile("aoc23/day2.txt");
        int[] sum = fixture.sumIDsGames(lines);
        int answer = 62811;
        assertTrue(String.format("outcome should be [%d] not [%d]",answer, sum[1]),
                sum[1] == answer);
    }
}