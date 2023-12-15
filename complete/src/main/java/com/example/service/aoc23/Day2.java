package com.example.service.aoc23;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.service.AdventOfCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
public class Day2 implements AdventOfCode {
    public int[] sumIDsGames(List<String> inventory) throws Exception {

        LOG.info("sumIDsGames - total lines: " + inventory.size());
        List<Game> games = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++) {
            LOG.info("Games - line: " + i + " content: " + inventory.get(i));
            Game game = new Game();
            game.processLine(inventory.get(i));
            game.totalSubGameCubes();
            game.maxSubGameCubes();
            games.add(game);

            LOG.info(String.format("Game [%d] Subgames [%d] Red [%d] Blue [%d] Green [%d]",
                    game.number,
                    game.subGames.size(),
                    game.totalSubGamesRed,
                    game.totalSubGamesBlue,
                    game.totalSubGamesGreen));
        }

        int possibleRed = 12;
        int possibleGreen = 13;
        int possibleBlue = 14;

        int minimumRed = 0;
        int minimumGreen = 0;
        int minimumBlue = 0;

        int red = 0;
        int green = 0;
        int blue = 0;

        int sumIDsGames = 0;
        int power = 0;
        LOG.info(String.format("sumID [%d] start - Red [%d] Blue [%d] Green [%d]",
                sumIDsGames,
                possibleRed,
                possibleBlue,
                possibleGreen));
        game:
        for (Game game : games) {
            LOG.info(String.format("sumID [%d] power game is [%d] Red [%d] Blue [%d] Green [%d]",
                    sumIDsGames,
                    game.powerMaxIndividual,
                    game.maxIndividualSubGamesRed,
                    game.maxIndividualSubGamesBlue,
                    game.maxIndividualSubGamesGreen));

            power += (game.powerMaxIndividual);

            for (SubGame subGame : game.subGames) {

                if (possibleRed - subGame.totalSubGameRed <0 ) continue game;
                if (possibleGreen - subGame.totalSubGameGreen <0 ) continue game;
                if (possibleBlue - subGame.totalSubGameBlue <0 ) continue game;
            }
//           if (possibleRed - game.totalSubGamesRed <0 ) continue;
//           if (possibleGreen - game.totalSubGamesGreen <0 ) continue;
//           if (possibleBlue - game.totalSubGamesBlue <0 ) continue;
            sumIDsGames += game.number;
            LOG.info(String.format("sumID [%d] possible game [%d] Red [%d] Blue [%d] Green [%d]",
                    sumIDsGames,
                    game.number,
                    game.maxIndividualSubGamesRed,
                    game.maxIndividualSubGamesBlue,
                    game.maxIndividualSubGamesGreen));
        }
        return new int[]{sumIDsGames,power};
    }

    public int sumCalibrationValuesWritten(List<String> inventory) {
        return 0;
    }

    @NoArgsConstructor
    @Data
    class Game  {
        String line;
        int number;
        int totalSubGamesRed;
        int totalSubGamesGreen;
        int totalSubGamesBlue;

        int maxIndividualSubGamesRed;
        int maxIndividualSubGamesGreen;
        int maxIndividualSubGamesBlue;

        int powerMaxIndividual;
        List<SubGame> subGames = new ArrayList<>();

        void processLine(String line) {
            this.line = line;
            // split logic
            List<String> lineSplit = Pattern
                    .compile(":")
                    .splitAsStream(line)
                    .collect(Collectors.toList());
            List<String> gameSplit = Pattern
                    .compile(" ")
                    .splitAsStream(lineSplit.get(0))
                    .collect(Collectors.toList());
            this.number = Integer.parseInt(gameSplit.get(1));

            List<String> subgameSplit = Pattern
                    .compile(";")
                    .splitAsStream(lineSplit.get(1))
                    .collect(Collectors.toList());

            int number = 1;
            for (String subgameString : subgameSplit) {
                SubGame subgame = new SubGame();
                subgame.processSubgameLine(subgameString, number);
                subgame.processTotal();
//                LOG.info(String.format("Subgame [%d] colors [%d] Red [%d] Blue [%d] Green [%d]",
//                        subgame.getSubGameOrder(),
//                        subgame.getCubes().size(),
//                        subgame.getTotalSubGameRed(),
//                        subgame.getTotalSubGameBlue(),
//                        subgame.getTotalSubGameGreen()));
                this.subGames.add(number++ - 1, subgame);
            }

        }
        void totalSubGameCubes() {

            this.totalSubGamesRed = this.subGames.stream()
                    .collect(summingInt(SubGame::getTotalSubGameRed));
            this.totalSubGamesGreen = this.subGames.stream()
                    .collect(summingInt(SubGame::getTotalSubGameGreen));
            this.totalSubGamesBlue = this.subGames.stream()
                    .collect(summingInt(SubGame::getTotalSubGameBlue));

        }

        void maxSubGameCubes() throws Exception {

            this.maxIndividualSubGamesRed = this.subGames.stream()
                    .mapToInt(v -> v.totalSubGameRed)
                    .max()
                    .orElseThrow(Exception::new);
            this.maxIndividualSubGamesGreen = this.subGames.stream()
                    .mapToInt(v -> v.totalSubGameGreen)
                    .max()
                    .orElseThrow(Exception::new);
            this.maxIndividualSubGamesBlue = this.subGames.stream()
                    .mapToInt(v -> v.totalSubGameBlue)
                    .max()
                    .orElseThrow(Exception::new);

            this.powerMaxIndividual =
                    (this.maxIndividualSubGamesRed *
                            this.maxIndividualSubGamesGreen *
                            this.maxIndividualSubGamesBlue);
        }
    }

    @NoArgsConstructor
    @Data
    class SubGame  {
        String line;
        int subGameOrder;
        ArrayList<Cube> cubes = new ArrayList<>();
        int totalSubGameRed;
        int totalSubGameGreen;
        int totalSubGameBlue;
        void processSubgameLine(String processSubgameLine, int order) {
            this.line = processSubgameLine;
            this.subGameOrder = order;
            // split logic
            List<String> cubesSplit = Pattern
                    .compile(",")
                    .splitAsStream(this.line)
                    .collect(Collectors.toList());
            for( String cubeString : cubesSplit) {
                // split logic
                List<String> split = Pattern
                        .compile(" ")
                        .splitAsStream(cubeString.substring(1,cubeString.length()))
                        .collect(Collectors.toList());
                Cube cube = new Cube();
                cube.setCube(Integer.parseInt(split.get(0)),split.get(1));
                cubes.add(cube);
            }
        }
        void processTotal() {

            Map<Colour, Integer> cubesPerColour = this.cubes.stream()
                    .collect(groupingBy(Cube::getColour, summingInt(Cube::getTotal)));
            if (cubesPerColour.get(Colour.RED) != null) {
                this.totalSubGameRed = cubesPerColour.get(Colour.RED);
            }
            if (cubesPerColour.get(Colour.GREEN) != null) {
                this.totalSubGameGreen = cubesPerColour.get(Colour.GREEN);
            }
            if (cubesPerColour.get(Colour.BLUE) != null) {
                this.totalSubGameBlue = cubesPerColour.get(Colour.BLUE);
            }

        }
    }

    @NoArgsConstructor
    @Data
    class Cube  {
        Colour colour;
        int total;

        record CubeColourTotal(Colour colour, int total) {};
        void setCube (int total, String color) {
            this.total = total;
            this.colour = Colour.getColorEnum(color);
        }
    }

    @AllArgsConstructor
    public enum Colour {
        RED(1), GREEN(2), BLUE(3);

        int colorCode;
        static public Colour getColorEnum(String color) {
            switch (color) {
                case "red": return Colour.RED;
                case "green": return Colour.GREEN;
                case "blue": return Colour.BLUE;
                default:
                    throw new IllegalStateException("Unexpected value: " + color);
            }
        }
        public int getColorCode() {
            return this.colorCode;
        }

    }
}
