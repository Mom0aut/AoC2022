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
        //        day9.partOne("day9/bigData.txt");
        day9.partTwo("day9/smallData.txt");

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

        //Wrong
        //4241
        //4242
        //9366

    }

    public void partTwo(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        HeadPosition headPosition = new HeadPosition();

        List<TailPosition> tailPositions =
                List.of(new TailPosition(), new TailPosition(), new TailPosition(),
                        new TailPosition(), new TailPosition(), new TailPosition(),
                        new TailPosition(), new TailPosition(), new TailPosition());

        TailPosition tailPosition = new TailPosition();
        Set<TailPosition> tailPositionsFromLast = new HashSet<>();
        tailPositionsFromLast.add(new TailPosition());

        while (line != null) {


            String[] commands = line.split(" ");


            if (commands[0].equals("R")) {

                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.column++;
                    //Move Tail only if it is not touching the head
                    moveTails(headPosition, tailPositions, tailPositionsFromLast);
                }
            }

            if (commands[0].equals("L")) {

                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.column--;
                    //Move Tail only if it is not touching the head
                    moveTails(headPosition, tailPositions, tailPositionsFromLast);
                }
            }

            if (commands[0].equals("U")) {
                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.row++;
                    //Move Tail only if it is not touching the head
                    moveTails(headPosition, tailPositions, tailPositionsFromLast);
                }

            }

            if (commands[0].equals("D")) {
                for (int i = 0; i < Integer.parseInt(commands[1]); i++) {
                    //Move Head
                    headPosition.lastPosition =
                            new HeadPosition(headPosition.column, headPosition.row);
                    headPosition.row--;
                    //Move Tail only if it is not touching the head
                    moveTails(headPosition, tailPositions, tailPositionsFromLast);
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

    private void moveTails(HeadPosition headPosition, List<TailPosition> tailPositions,
            Set<TailPosition> lastTailPositions) {

        for (int i = 0; i < tailPositions.size(); i++) {

            if (i == 0) {
                if (!tailPositions.get(i).isTouchingHead(headPosition)) {
                    //Move tail to tail
                    tailPositions.get(i).movementVector = new TailPosition(
                            headPosition.lastPosition.column - tailPositions.get(i).column,
                            headPosition.lastPosition.row - tailPositions.get(i).row);
                    tailPositions.get(i).row = headPosition.lastPosition.row;
                    tailPositions.get(i).column = headPosition.lastPosition.column;
                }
            } else {
                if (!tailPositions.get(i).isTouchingPosition(tailPositions.get(i - 1))) {
                    //Move tail to tail
                    tailPositions.get(i).movementVector =
                            new TailPosition(tailPositions.get(i - 1).movementVector.column,
                                    tailPositions.get(i - 1).movementVector.row);
                    tailPositions.get(i).row += tailPositions.get(i - 1).movementVector.row;
                    tailPositions.get(i).column += tailPositions.get(i - 1).movementVector.column;
                    if (i == 8) {
                        lastTailPositions.add(new TailPosition(tailPositions.get(i).column,
                                tailPositions.get(i).row));
                    }
                }
            }
        }



    }

}
