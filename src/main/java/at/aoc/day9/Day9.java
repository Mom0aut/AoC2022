package at.aoc.day9;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 {



    public static void main(String[] args) throws IOException {
        Day9 day9 = new Day9();
        day9.partOne("day9/bigData.txt");
        day9.partTwo("day9/bigData.txt");

    }

    public void partOne(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        HeadPosition headPosition = new HeadPosition();
        TailPosition tailPosition = new TailPosition();
        Set<TailPosition> tailPositions = new HashSet<>();
        tailPositions.add(new TailPosition());

        while (line != null) {


            String[] commands = line.split(" ");


            if (commands[0].equals("R")) {

                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.column++;
                    //Move Tail only if it is not touching the head
                    moveTail(headPosition, tailPosition, tailPositions);
                }
            }

            if (commands[0].equals("L")) {

                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.column--;
                    //Move Tail only if it is not touching the head
                    moveTail(headPosition, tailPosition, tailPositions);
                }
            }

            if (commands[0].equals("U")) {
                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.row++;
                    //Move Tail only if it is not touching the head
                    moveTail(headPosition, tailPosition, tailPositions);
                }

            }

            if (commands[0].equals("D")) {
                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.row--;
                    //Move Tail only if it is not touching the head
                    moveTail(headPosition, tailPosition, tailPositions);
                }

            }


            line = reader.readLine();
        }
        reader.close();
        System.out.println("Moving tail counter: " + tailPositions.size());

    }

    public void partTwo(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        Knot headPosition = new Knot(6, 12);
        List<Knot> knots = List.of(headPosition, new Knot(6, 12), new Knot(6, 12), new Knot(6, 12),
                new Knot(6, 12), new Knot(6, 12), new Knot(6, 12), new Knot(6, 12), new Knot(6, 12),
                new Knot(6, 12));
        Set<Knot> tailPositionsFromLast = new HashSet<>();
        tailPositionsFromLast.add(new Knot(6, 12));

        while (line != null) {
            String[] commands = line.split(" ");
            if (commands[0].equals("R")) {

                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition = new Knot(headPosition.column, headPosition.row);
                    headPosition.column++;
                    //Move Tail only if it is not touching the head
                    moveTails(knots, tailPositionsFromLast);
                }
            }

            if (commands[0].equals("L")) {

                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition = new Knot(headPosition.column, headPosition.row);
                    headPosition.column--;
                    //Move Tail only if it is not touching the head
                    moveTails(knots, tailPositionsFromLast);
                }
            }

            if (commands[0].equals("U")) {
                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition = new Knot(headPosition.column, headPosition.row);
                    headPosition.row++;
                    //Move Tail only if it is not touching the head
                    moveTails(knots, tailPositionsFromLast);
                }

            }

            if (commands[0].equals("D")) {
                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition = new Knot(headPosition.column, headPosition.row);
                    headPosition.row--;
                    //Move Tail only if it is not touching the head
                    moveTails(knots, tailPositionsFromLast);
                }

            }


            line = reader.readLine();
        }
        reader.close();
        System.out.println("Moving tail counter: " + tailPositionsFromLast.size());
    }


    private void moveTail(HeadPosition headPosition, TailPosition tailPosition,
            Set<TailPosition> tailPositions) {
        if (!tailPosition.isTouchingHead(headPosition)) {
            //Move tail to head
            tailPosition.row = headPosition.lastPosition.row;
            tailPosition.column = headPosition.lastPosition.column;
            tailPositions.add(new TailPosition(tailPosition.column, tailPosition.row));
        }
    }

    private void moveTails(List<Knot> knots, Set<Knot> lastKnotPositions) {

        for (int i = 0; i < knots.size(); i++) {
            //Skip Head
            if (i > 0) {
                // Check the distance.
                // If we're close enough then don't move at all.
                Knot previousKnot = knots.get(i - 1);
                Knot currentKnot = knots.get(i);
                if (Math.abs(previousKnot.row - currentKnot.row) < 2 && Math.abs(
                        previousKnot.column - knots.get(i).column) < 2) {
                    return;
                }
                if (previousKnot.row > currentKnot.row) {
                    currentKnot.row++;
                }
                if (previousKnot.row < currentKnot.row) {
                    currentKnot.row--;
                }
                if (previousKnot.column > currentKnot.column) {
                    currentKnot.column++;
                }
                if (previousKnot.column < currentKnot.column) {
                    currentKnot.column--;
                }
                if (i == 9) {
                    lastKnotPositions.add(new Knot(currentKnot.column, currentKnot.row));
                }
            }
        }
    }

}
