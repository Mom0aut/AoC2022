package at.aoc.day10;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day10 {



    public static void main(String[] args) throws IOException {
        Day10 day10 = new Day10();
        day10.partOne("day10/bigData.txt");
        day10.partTwo("day10/bigData.txt");

    }

    public void partOne(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        int cycle = 1;
        int x = 1;
        Map<Integer, Integer> registerMap = new LinkedHashMap<>();
        registerMap.put(cycle, x);
        while (line != null) {


            if (line.startsWith("noop")) {
                Integer currentX = registerMap.get(cycle);
                cycle++;
                registerMap.put(cycle, currentX);
            }


            if (line.startsWith("addx")) {

                String[] command = line.split(" ");
                String addValue = command[1];
                Integer currentX = registerMap.get(cycle);
                cycle++;
                registerMap.put(cycle, currentX);
                cycle++;
                registerMap.put(cycle, currentX + Integer.parseInt(addValue));
            }
            line = reader.readLine();
        }
        reader.close();

        Integer signalAt20 = registerMap.get(20) * 20;
        Integer signalAt60 = registerMap.get(60) * 60;
        Integer signalAt100 = registerMap.get(100) * 100;
        Integer signalAt140 = registerMap.get(140) * 140;
        Integer signalAt180 = registerMap.get(180) * 180;
        Integer signalAt220 = registerMap.get(220) * 220;

        List<Integer> signalList = new ArrayList<>(
                List.of(signalAt20, signalAt60, signalAt100, signalAt140, signalAt180,
                        signalAt220));

        System.out.println("Sum of the signal strengths: " + signalList.stream()
                .mapToInt(Integer::intValue)
                .sum());

    }

    public void partTwo(String filename) throws IOException {

        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        int cycle = 1;
        int x = 1;
        Map<Integer, Integer> registerMap = new LinkedHashMap<>();
        registerMap.put(cycle, x);
        while (line != null) {


            if (line.startsWith("noop")) {
                Integer currentX = registerMap.get(cycle);
                cycle++;
                registerMap.put(cycle, currentX);
            }

            if (line.startsWith("addx")) {
                String[] command = line.split(" ");
                String addValue = command[1];
                Integer currentX = registerMap.get(cycle);
                cycle++;
                registerMap.put(cycle, currentX);
                cycle++;
                registerMap.put(cycle, currentX + Integer.parseInt(addValue));
            }
            line = reader.readLine();
        }
        reader.close();

        StringBuilder output = new StringBuilder();
        registerMap.forEach((currentCycle, currentX) -> {
            List<Integer> spritePositions = new ArrayList<>();
            spritePositions.add(currentX);
            spritePositions.add(currentX + 1);
            spritePositions.add(currentX + 2);
            renderSprite(output, currentCycle, spritePositions);
        });

        List<String> outputs = List.of(output.substring(0, 40), output.substring(40, 80),
                output.substring(80, 120), output.substring(120, 160), output.substring(160, 200),
                output.substring(200, 240));
        outputs.forEach(System.out::println);
    }

    private static void renderSprite(StringBuilder output, Integer currentCycle,
            List<Integer> spritePositions) {
        if (currentCycle <= 40) {
            if (spritePositions.contains(currentCycle)) {
                output.append("#");
            } else {
                output.append(".");
            }
            return;
        }

        if (currentCycle <= 80) {
            if (spritePositions.contains(currentCycle - 40)) {
                output.append("#");
            } else {
                output.append(".");
            }
            return;
        }

        if (currentCycle <= 120) {
            if (spritePositions.contains(currentCycle - 80)) {
                output.append("#");
            } else {
                output.append(".");
            }
            return;
        }

        if (currentCycle <= 160) {
            if (spritePositions.contains(currentCycle - 120)) {
                output.append("#");
            } else {
                output.append(".");
            }
            return;
        }

        if (currentCycle <= 200) {
            if (spritePositions.contains(currentCycle - 160)) {
                output.append("#");
            } else {
                output.append(".");
            }
            return;
        }

        if (currentCycle <= 240) {
            if (spritePositions.contains(currentCycle - 200)) {
                output.append("#");
            } else {
                output.append(".");
            }
        }
    }



}
