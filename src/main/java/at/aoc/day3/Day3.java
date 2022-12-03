package at.aoc.day3;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day3 {

    String points = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws IOException {
        Day3 day3 = new Day3();
        day3.partOne("day3/bigData.txt");
        day3.partTwo("day3/bigData.txt");
    }

    public void partOne(String filename) throws IOException {
        List<Character> commonItems = new ArrayList<>();
        AtomicInteger priorities = new AtomicInteger();
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        while (line != null) {
            String firstCompartment = line.substring(0, line.length() / 2);
            String secondCompartment = line.substring(line.length() / 2);
            char[] firstChars = firstCompartment.toCharArray();
            for (char firstChar : firstChars) {
                int i = secondCompartment.indexOf(firstChar);
                if (i >= 0) {
                    System.out.println("Found Match: " + secondCompartment.charAt(
                            i) + " " + firstCompartment + ":" + secondCompartment + "\n");
                    commonItems.add(secondCompartment.charAt(i));
                    break;
                }
            }
            line = reader.readLine();
        }
        reader.close();
        calculatePriorities(commonItems, priorities);
        System.out.println("Priorities: " + priorities + "\n");
    }

    private void calculatePriorities(List<Character> commonItems, AtomicInteger priorities) {
        commonItems.forEach(item -> {
            int delta = points.indexOf(item) + 1;
            priorities.addAndGet(delta);
        });
    }


    public void partTwo(String filename) throws IOException {
        List<Character> commonItems = new ArrayList<>();
        AtomicInteger priorities = new AtomicInteger();
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String firstLine = reader.readLine();
        String secondLine = reader.readLine();
        String thirdLine = reader.readLine();
        while (thirdLine != null) {
            char[] firstChars = firstLine.toCharArray();
            for (char firstChar : firstChars) {
                int i = secondLine.indexOf(firstChar);
                int j = thirdLine.indexOf(firstChar);
                if (i >= 0 && j >= 0) {
                    System.out.println("Found Char in 3 Lines: " + firstChar + "\n");
                    commonItems.add(firstChar);
                    break;
                }
            }
            firstLine = reader.readLine();
            secondLine = reader.readLine();
            thirdLine = reader.readLine();
        }
        reader.close();
        calculatePriorities(commonItems, priorities);
        System.out.println("Priorities: " + priorities + "\n");
    }


}
