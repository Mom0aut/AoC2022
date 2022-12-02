package at.aoc.day2;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.MessageFormat;

public class Day2 {

    public static void main(String[] args) throws IOException {
        Day2 day2 = new Day2();
        day2.partOne("day2/bigData.txt");
        day2.partTwo("day2/bigData.txt");

    }

    public void partOne(String filename) throws IOException {
        int gameScore = 0;
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        while (line != null) {
            String[] selectedShapes = line.split(" ");
            EnemyChoice enemyChoice = EnemyChoice.fromString(selectedShapes[0]);
            PlayerChoice playerChoice = PlayerChoice.fromString(selectedShapes[1]);
            GameOutput gameOutput = caluclateGameOutput(enemyChoice, playerChoice);
            gameScore += gameOutput.getValue() + playerChoice.getValue();
            System.out.println(MessageFormat.format(
                    "Gameoutput: {0}|Enemy Choice {1}|Player Choice {2}|Round Score: {3} Gameoutput({4}) + Played({5})",
                    gameOutput, enemyChoice, playerChoice,
                    gameOutput.getValue() + playerChoice.getValue(), gameOutput.getValue(),
                    playerChoice.getValue()));
            line = reader.readLine();
        }
        reader.close();
        System.out.println("TotalScore: " + gameScore + "\n");
    }

    public void partTwo(String filename) throws IOException {
        int gameScore = 0;
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        while (line != null) {
            String[] selectedShapes = line.split(" ");
            EnemyChoice enemyChoice = EnemyChoice.fromString(selectedShapes[0]);
            ElfChoice elfChoice = ElfChoice.fromString(selectedShapes[1]);
            PlayerChoice playerChoice = caluclatePlayerChoice(enemyChoice, elfChoice);
            GameOutput gameOutput = caluclateGameOutput(enemyChoice, playerChoice);
            gameScore += gameOutput.getValue() + playerChoice.getValue();
            System.out.println(MessageFormat.format(
                    "Gameoutput: {0}|Enemy Choice {1}|Player Choice {2}|Round Score: {3} Gameoutput({4}) + Played({5})",
                    gameOutput, enemyChoice, playerChoice,
                    gameOutput.getValue() + playerChoice.getValue(), gameOutput.getValue(),
                    playerChoice.getValue()));
            line = reader.readLine();
        }
        reader.close();
        System.out.println("TotalScore: " + gameScore + "\n");
    }

    private GameOutput caluclateGameOutput(EnemyChoice opponentChoice, PlayerChoice elfChoice) {
        switch (opponentChoice) {
            case ROCK -> {
                return switch (elfChoice) {
                    case ROCK -> GameOutput.DRAW;
                    case PAPER -> GameOutput.WIN;
                    case SCISSORS -> GameOutput.LOSE;
                };
            }
            case PAPER -> {
                return switch (elfChoice) {
                    case ROCK -> GameOutput.LOSE;
                    case PAPER -> GameOutput.DRAW;
                    case SCISSORS -> GameOutput.WIN;
                };
            }
            case SCISSORS -> {
                return switch (elfChoice) {
                    case ROCK -> GameOutput.WIN;
                    case PAPER -> GameOutput.LOSE;
                    case SCISSORS -> GameOutput.DRAW;
                };
            }
        }
        return null;
    }

    private PlayerChoice caluclatePlayerChoice(EnemyChoice opponentChoice, ElfChoice elfChoice) {
        switch (opponentChoice) {
            case ROCK -> {
                return switch (elfChoice) {
                    case LOSE -> PlayerChoice.SCISSORS;
                    case DRAW -> PlayerChoice.ROCK;
                    case WIN -> PlayerChoice.PAPER;
                };
            }
            case PAPER -> {
                return switch (elfChoice) {
                    case LOSE -> PlayerChoice.ROCK;
                    case DRAW -> PlayerChoice.PAPER;
                    case WIN -> PlayerChoice.SCISSORS;
                };
            }
            case SCISSORS -> {
                return switch (elfChoice) {
                    case LOSE -> PlayerChoice.PAPER;
                    case DRAW -> PlayerChoice.SCISSORS;
                    case WIN -> PlayerChoice.ROCK;
                };
            }
        }
        return null;
    }

}
