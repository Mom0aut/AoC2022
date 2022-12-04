package at.aoc.day4;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day4 {


    public static void main(String[] args) throws IOException {
        Day4 day4 = new Day4();
        day4.partOne("day4/bigData.txt");
        day4.partTwo("day4/bigData.txt");
    }

    public void partOne(String filename) throws IOException {
        int fullyContainPairs = 0;
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        while (line != null) {
            String[] pairs = line.split(",");
            String leftPair = pairs[0];
            String rightPair = pairs[1];
            List<Integer> leftNumbers = createNumberSequence(leftPair);
            List<Integer> rightNumbers = createNumberSequence(rightPair);
            System.out.println("Pairs: " + leftNumbers + " " + rightNumbers + "\n");
            boolean leftContainsRight = new HashSet<>(leftNumbers).containsAll(rightNumbers);
            boolean rightContainsLeft = new HashSet<>(rightNumbers).containsAll(leftNumbers);
            if (leftContainsRight | rightContainsLeft) {
                fullyContainPairs++;
            }
            line = reader.readLine();
        }
        reader.close();
        System.out.println("FullyContainPairs: " + fullyContainPairs);
    }

    public void partTwo(String filename) throws IOException {
        int overlapPairs = 0;
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        while (line != null) {
            String[] pairs = line.split(",");
            String leftPair = pairs[0];
            String rightPair = pairs[1];
            List<Integer> leftNumbers = createNumberSequence(leftPair);
            List<Integer> rightNumbers = createNumberSequence(rightPair);
            System.out.println("Pairs: " + leftNumbers + " " + rightNumbers + "\n");
            boolean leftContainsRight = leftNumbers.stream().anyMatch(rightNumbers::contains);
            boolean rightContainsLeft = rightNumbers.stream().anyMatch(leftNumbers::contains);
            if (leftContainsRight | rightContainsLeft) {
                overlapPairs++;
            }
            line = reader.readLine();
        }
        reader.close();
        System.out.println("OverlapPairs: " + overlapPairs);
    }

    private static List<Integer> createNumberSequence(String numberPair) {
        List<Integer> pairs = new ArrayList<>();
        String[] leftRange = numberPair.split("-");
        String leftStartRange = leftRange[0];
        String leftEndRange = leftRange[1];
        for (int i = Integer.parseInt(leftStartRange); i <= Integer.parseInt(leftEndRange); i++) {
            pairs.add(i);
        }
        return pairs;
    }
}
