package com.example.service.aoc23;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.AdventOfCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.service.AdventOfCode.isBlankString;

@Service
public class Day1 implements AdventOfCode {
    public int sumCalibrationValues(List<String> inventory) {

        LOG.info("sumCalibrationValues - total lines: " + inventory.size());
        char firstDigit = 0;
        char lastDigit = 0;
        int sumCalibarion = 0;

        for (int i = 0; i < inventory.size(); i++) {
//            LOG.info("sumCalibrationValues - line: " + i + " content: " + inventory.get(i));

            Calibration calibration = new Calibration();
            calibration.findTotal(inventory.get(i),false);
            sumCalibarion += calibration.total;
            LOG.info(String.format("Line [%s] First [%s] Last [%s] Total [%d] Calibration [%d]",
                    inventory.get(i),
                    calibration.first,
                    calibration.last,
                    calibration.total,
                    sumCalibarion));
        }
        return sumCalibarion;
    }

    public int sumCalibrationValuesWritten(List<String> inventory) {

        LOG.info("sumCalibrationValues - total lines: " + inventory.size());
        char firstDigit = 0;
        char lastDigit = 0;
        int sumCalibarion = 0;

        for (int i = 0; i < inventory.size(); i++) {
//            LOG.info("sumCalibrationValues - line: " + i + " content: " + inventory.get(i));

            Calibration calibration = new Calibration();
            calibration.findTotal(inventory.get(i),true);
            sumCalibarion += calibration.total;
//            LOG.info(String.format("Line [%s] NewLine [%s] First [%s] Last [%s] Total [%d] " +
//                            "Calibration [%d]",
//                    inventory.get(i),
//                    calibration.newLine,
//                    calibration.first,
//                    calibration.last,
//                    calibration.total,
//                    sumCalibarion));
        }
        return sumCalibarion;
    }

    @NoArgsConstructor
    @Data
    class Calibration  {
        char first;
        char last;
        int total;
        String newLine;

        void findTotal(String line, boolean written) {
            boolean found = false;
            this.newLine = line;
            if (written) parseWritten(line);
            for (char c : this.newLine.toCharArray()) {
                try {
                    int digit = Integer.parseInt(String.valueOf(Character.valueOf(c)));
                } catch (Exception ex) {
                    continue;
                }
                if (!found) {
                    this.first = Character.valueOf(c);
                    this.last = Character.valueOf(c);
                    found = true;
                    continue;
                }
                this.last = Character.valueOf(c);
            }
            String value = String.valueOf(first) + last;
            this.total = Integer.parseInt(value);
        }

        void parseWritten (String line) {
            for (int i = 0; i < line.length(); i++) {
                final String c = String.valueOf(line.charAt(i));
                switch (c){
                    case "o":
                        line = replaceWhenFound(line, i, "one", "o1e");
                    case "e" :
                        line = replaceWhenFound(line, i, "eight", "e8t");
                    case "t" :
                        line = replaceWhenFound(line, i, "two", "t2o");
                        line = replaceWhenFound(line, i, "three", "t3e");
                    case "f" :
                        line = replaceWhenFound(line, i, "four", "f4r");
                        line = replaceWhenFound(line, i, "five", "5");
                    case "s" :
                        line = replaceWhenFound(line, i, "six", "s6x");
                        line = replaceWhenFound(line, i, "seven", "s7n");
                    case "n" :
                        line = replaceWhenFound(line, i, "nine", "9");
                }
            }
            this.newLine = line;
        }

        String replaceWhenFound(String line, int position, String word, String digit) {
            String original = line;
            if ((position+word.length())<=line.length() && (line.substring(position,
                    position+word.length()).equals(word))) {
                line = line.replaceFirst(word, digit);
                LOG.info(String.format("Line [%s] position [%d] word [%s] digit [%s] newline [%s] ",
                        original,
                        position,
                        word,
                        digit,
                        line));
            }
            return line;
        }
    }

}
