package at.aoc.day12;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day12 {


    String heightElevation = "abcdefghijklmnopqrstuvwxyz";
    Position lastPosition = new Position(-100, -100);


    public static void main(String[] args) throws IOException {
        Day12 day12 = new Day12();
        day12.partOne("day12/smallData.txt");
        //        day12.partTwo("day12/bigData.txt");

    }

    public void partOne(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        int lineCounter = 0;
        String[][] heightmap = new String[5][8];

        while (line != null) {
            char[] rowHeights = line.toCharArray();
            for (int i = 0; i < rowHeights.length; i++) {
                heightmap[lineCounter][i] = String.valueOf(rowHeights[i]);
            }
            lineCounter++;
            line = reader.readLine();
        }
        reader.close();

        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(0, 0);
        Position currentPosition = new Position(0, 0);


        for (int i = 0; i < heightmap.length; i++) {
            for (int j = 0; j < heightmap[i].length; j++) {
                String currentChar = heightmap[i][j];
                if (currentChar.equals("S")) {
                    startPosition.row = i;
                    startPosition.column = j;
                    currentPosition = startPosition;
                }
                if (currentChar.equals("E")) {
                    endPosition.row = i;
                    endPosition.column = j;
                }
            }
        }

        //Move from S to E
        //S has a Zero elevation
        //E has z 26 eleveation
        //Calculate next move
        //Move elevation only 1 higher

        //TODO add List of moves --> must be unuique


        while (!currentPosition.equals(endPosition)) {
            int currentHeight;
            String currentChar = heightmap[currentPosition.column][currentPosition.row];
            System.out.println("Current Height: " + currentChar + "\n");
            if (currentChar.equals("S")) {
                currentHeight = heightElevation.indexOf("a");
            } else {
                currentHeight = heightElevation.indexOf(currentChar);
            }
            calculateMoves(heightmap, currentPosition, currentHeight);
        }



        String[] startRow = heightmap[startPosition.row];
        List<String> startRowColumnToEndRowColumn =
                Arrays.stream(startRow).limit(endPosition.column + 1).toList();
        AtomicInteger rowCount = new AtomicInteger();
        startRowColumnToEndRowColumn.forEach(column -> {
            int elevation;
            if (column.equals("S")) {
                elevation = heightElevation.indexOf("a");
            } else {
                elevation = heightElevation.indexOf(column);
            }
            rowCount.addAndGet(elevation);
        });


        System.out.println("Found Start and End");



    }

    private void calculateMoves(String[][] heightmap, Position currentPosition, int currentHeight) {
        //Can Right
        if (currentPosition.row < heightmap[currentPosition.column].length - 1) {
            if (heightElevation.indexOf(
                    heightmap[currentPosition.column][currentPosition.row + 1]) <= currentHeight + 1) {
                if (!lastPosition.equals(
                        new Position(currentPosition.column, currentPosition.row + 1))) {
                    lastPosition = new Position(currentPosition.column, currentPosition.row);
                    currentPosition.row += 1;
                    System.out.println(
                            "Can climb right, new posistion is now: " + currentPosition + "\n");
                    return;
                } else {
                    System.out.println(
                            "Cant climb right because of last position: " + lastPosition + " current position: " + currentPosition + "\n");
                }
            }
        }

        //Can Left
        if (currentPosition.row > 0) {
            if (heightElevation.indexOf(
                    heightmap[currentPosition.column][currentPosition.row - 1]) <= currentHeight + 1) {
                if (!lastPosition.equals(
                        new Position(currentPosition.column, currentPosition.row - 1))) {
                    lastPosition = new Position(currentPosition.column, currentPosition.row);
                    currentPosition.row -= 1;
                    System.out.println(
                            "Can climb left, new posistion is now: " + currentPosition + "\n");
                    return;
                } else {
                    System.out.println(
                            "Cant climb left because of last position: " + lastPosition + " current position: " + currentPosition + "\n");
                }
            }
        }
        //Can Down
        if (currentPosition.column < heightmap.length - 1) {
            if (heightElevation.indexOf(
                    heightmap[currentPosition.column + 1][currentPosition.row]) <= currentHeight + 1) {
                if (!lastPosition.equals(
                        new Position(currentPosition.column + 1, currentPosition.row))) {
                    lastPosition = new Position(currentPosition.column, currentPosition.row);
                    currentPosition.column += 1;
                    System.out.println(
                            "Can climb down, new posistion is now: " + currentPosition + "\n");
                    return;
                } else {
                    System.out.println(
                            "Cant climb down because of last position: " + lastPosition + " current position: " + currentPosition + "\n");
                }
            }
        }
        //Can UP
        if (currentPosition.column > 0) {
            if (heightElevation.indexOf(
                    heightmap[currentPosition.column - 1][currentPosition.row]) <= currentHeight + 1) {
                if (!lastPosition.equals(
                        new Position(currentPosition.column - 1, currentPosition.row))) {
                    lastPosition = new Position(currentPosition.column, currentPosition.row);
                    currentPosition.column -= 1;
                    System.out.println(
                            "Can climb up, new posistion is now: " + currentPosition + "\n");
                } else {
                    System.out.println(
                            "Cant climb up because of last position: " + lastPosition + " current position: " + currentPosition + "\n");
                }
            }
        }
    }

    public void partTwo(String filename) throws IOException {

    }



}
