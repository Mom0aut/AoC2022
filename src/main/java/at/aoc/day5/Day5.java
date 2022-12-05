package at.aoc.day5;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day5 {


    public static void main(String[] args) throws IOException {
        Day5 day5 = new Day5();
        day5.partOne("day5/bigData.txt");
        day5.partTwo("day5/bigData.txt");
    }

    public void partOne(String filename) throws IOException {
        List<String> topCrates = new ArrayList<>();
        boolean readCreateSketch = true;
        List<List<String>> crateColumns = new ArrayList<>();
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        while (line != null) {
            if (readCreateSketch) {
                System.out.println("Input: " + line + "\n");
                int crateRows = 9;
                if (line.equals("")) {
                    crateRows = 0;
                }

                int position = 0;
                List<String> crateRowsList = new ArrayList<>();
                for (int i = 0; i < crateRows; i++) {
                    String substring;
                    //lastElement
                    if (i == (crateRows - 1)) {
                        substring = line.substring(position);
                    } else {
                        substring = line.substring(position);
                        substring = substring.substring(0, 3);
                        position += 4;
                    }
                    //Dont save Crate numbering 1 2 3 etc...
                    if (!Character.isDigit(substring.charAt(1))) {
                        crateRowsList.add(substring);
                    }
                }

                AtomicInteger rowCounter = new AtomicInteger();
                crateRowsList.forEach(row -> {
                    //Init ColumnsList
                    if (crateColumns.isEmpty()) {
                        for (int i = 0; i < 9; i++) {
                            crateColumns.add(i, new ArrayList<>());
                        }
                    }
                    //Add Values
                    if (!row.equals("   ")) {
                        List<String> rowList = crateColumns.get(rowCounter.get());
                        rowList.add(row);
                    }
                    rowCounter.getAndIncrement();
                });

                if (line.equals("") || line.isEmpty() || line.isBlank()) {
                    readCreateSketch = false;
                    System.out.println("NEWLine --> move instruction begins now");
                }
            } else {
                String move = line.substring(0, line.indexOf("from") - 1).replace("move ", "");
                String from = line.substring(line.indexOf("from"), line.indexOf("to") - 1)
                        .replace("from ", "");
                String to = line.substring(line.indexOf("to")).replace("to ", "");

                int moveValue = Integer.valueOf(move);
                int fromValue = Integer.valueOf(from);
                int toValue = Integer.valueOf(to);

                for (int i = 0; i < moveValue; i++) {
                    List<String> fromList = crateColumns.get(fromValue - 1);
                    String movedValue = fromList.get(0);
                    List<String> toList = crateColumns.get(toValue - 1);
                    toList.add(0, movedValue);
                    fromList.remove(movedValue);
                }


            }
            line = reader.readLine();
        }
        reader.close();
        StringBuilder stringBuilder = new StringBuilder();
        crateColumns.forEach(crateColumn -> stringBuilder.append(
                crateColumn.get(0).replace("[", "").replace("]", "")));
        System.out.println("Topcrates: " + stringBuilder + "\n");
    }

    public void partTwo(String filename) throws IOException {
        List<String> topCrates = new ArrayList<>();
        boolean readCreateSketch = true;
        List<List<String>> crateColumns = new ArrayList<>();
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        while (line != null) {
            if (readCreateSketch) {
                System.out.println("Input: " + line + "\n");
                int crateRows = 9;
                if (line.equals("")) {
                    crateRows = 0;
                }

                int position = 0;
                List<String> crateRowsList = new ArrayList<>();
                for (int i = 0; i < crateRows; i++) {
                    String substring;
                    //lastElement
                    if (i == (crateRows - 1)) {
                        substring = line.substring(position);
                    } else {
                        substring = line.substring(position);
                        substring = substring.substring(0, 3);
                        position += 4;
                    }
                    //Dont save Crate numbering 1 2 3 etc...
                    if (!Character.isDigit(substring.charAt(1))) {
                        crateRowsList.add(substring);
                    }
                }

                AtomicInteger rowCounter = new AtomicInteger();
                crateRowsList.forEach(row -> {
                    //Init ColumnsList
                    if (crateColumns.isEmpty()) {
                        for (int i = 0; i < 9; i++) {
                            crateColumns.add(i, new ArrayList<>());
                        }
                    }
                    //Add Values
                    if (!row.equals("   ")) {
                        List<String> rowList = crateColumns.get(rowCounter.get());
                        rowList.add(row);
                    }
                    rowCounter.getAndIncrement();
                });

                if (line.equals("")) {
                    readCreateSketch = false;
                    System.out.println("NEWLine --> move instruction begins now");
                }
            } else {
                String move = line.substring(0, line.indexOf("from") - 1).replace("move ", "");
                String from = line.substring(line.indexOf("from"), line.indexOf("to") - 1)
                        .replace("from ", "");
                String to = line.substring(line.indexOf("to")).replace("to ", "");

                int moveValue = Integer.valueOf(move);
                int fromValue = Integer.valueOf(from);
                int toValue = Integer.valueOf(to);

                List<String> fromList = crateColumns.get(fromValue - 1);
                List<String> moveCrateList = new ArrayList<>(fromList.subList(0, moveValue));
                List<String> toList = crateColumns.get(toValue - 1);
                toList.addAll(0, new ArrayList<>(moveCrateList));
                moveCrateList.forEach(fromList::remove);
            }
            line = reader.readLine();
        }
        reader.close();
        StringBuilder stringBuilder = new StringBuilder();
        crateColumns.forEach(crateColumn -> stringBuilder.append(
                crateColumn.get(0).replace("[", "").replace("]", "")));
        System.out.println("Topcrates: " + stringBuilder + "\n");
    }


}
