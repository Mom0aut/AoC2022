package at.aoc.day1;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day1 {

    public static void main(String[] args) throws IOException {
        Day1 day1 = new Day1();
        day1.partOne("day1/smallData.txt");
        day1.partTwo("day1/smallData.txt");
        day1.partOne("day1/bigData.txt");
        day1.partTwo("day1/bigData.txt");
    }

    public Integer partOne(String filename) throws IOException {
        List<Integer> elfCalories = getElfCalories(filename);
        Integer maxCalory = Collections.max(elfCalories);
        System.out.println("Max Calories: " + maxCalory + "\n");
        return maxCalory;
    }

    public AtomicReference<Integer> partTwo(String filename) throws IOException {
        List<Integer> elfCalories = getElfCalories(filename);
        List<Integer> top3Calories =
                elfCalories.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
        AtomicReference<Integer> top3 = new AtomicReference<>(0);
        top3Calories.forEach(elf -> top3.updateAndGet(v -> v + elf));
        System.out.println("Top 3 Elfs Calories: " + top3 + "\n");
        return top3;
    }

    private List<Integer> getElfCalories(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String currentLine = reader.readLine();
        List<Integer> elfCalories = new ArrayList<>();
        int calory = 0;
        while (currentLine != null) {
            if (currentLine.equals("")) {
                elfCalories.add(calory);
                calory = 0;
            } else {
                calory += Integer.parseInt(currentLine);
            }
            currentLine = reader.readLine();
        }
        reader.close();
        elfCalories.add(calory);
        return elfCalories;
    }

}
