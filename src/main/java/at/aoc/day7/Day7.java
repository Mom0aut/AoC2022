package at.aoc.day7;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

    //    private static final List<Dir> dirs = new ArrayList<>();
    //    private static final Dir root = new Dir("/");
    //    private static Dir currentDir;

    private static Dir currentDir;

    public static void main(String[] args) throws IOException {
        Day7 day7 = new Day7();
        day7.partOne("day7/bigData.txt");
        day7.partTwo("day7/bigData.txt");
    }

    public void partOne(String filename) throws IOException {

        List<Dir> directories = new ArrayList<>();
        Dir root = new Dir("/");
        currentDir = root;

        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        Pattern p = Pattern.compile("\\d+");

        while (line != null) {
            line = line.replace("$ ", "");
            if (line.contains("cd /")) {
                System.out.println("Cd into root dir\n");
                directories.add(root);
                currentDir = root;
                line = reader.readLine();
            }

            if (line.contains("dir")) {
                String[] dirName = line.split(" ");
                Dir addDir = new Dir(dirName[1]);
                addDir.parent = currentDir;
                currentDir.subDir.add(addDir);
                directories.add(addDir);
                System.out.println("Create dir: " + dirName[1] + "\n");
            }

            if (line.startsWith("cd")) {
                if (line.contains("cd ..")) {
                    currentDir = currentDir.parent;
                    System.out.println(
                            "Change dir .. : Current dir is now: " + currentDir.name + "\n");

                } else {
                    String[] cdDirName = line.split(" ");
                    Optional<Dir> cdDir = currentDir.subDir.stream()
                            .filter(sub -> sub.name.equals(cdDirName[1]))
                            .findFirst();
                    cdDir.ifPresent(dir -> currentDir = dir);
                    System.out.println(
                            "Change dir: " + cdDirName[1] + " Current dir is now: " + currentDir.name + "\n");
                }
            }

            Matcher fileCommand = p.matcher(line);
            if (fileCommand.find()) {
                String[] fileMetaData = line.split(" ");
                Dir fileDir = new Dir(fileMetaData[1]);
                fileDir.parent = fileDir;
                fileDir.size = Integer.parseInt(fileMetaData[0]);
                currentDir.subDir.add(fileDir);
                System.out.println(
                        "File found: " + fileMetaData[1] + " size: " + fileMetaData[0] + "\n");
            }
            line = reader.readLine();
        }
        reader.close();

        final int dirSizes =
                directories.stream().mapToInt(Dir::getSubDirSize).filter(i -> i < 100_000).sum();

        System.out.println("Dir Sum under 100000: " + dirSizes);

    }

    public void partTwo(String filename) throws IOException {
        List<Dir> directories = new ArrayList<>();
        Dir root = new Dir("/");
        currentDir = root;

        BufferedReader reader = BufferReaderUtil.readFile(filename);
        String line = reader.readLine();
        Pattern p = Pattern.compile("\\d+");

        while (line != null) {
            line = line.replace("$ ", "");
            if (line.contains("cd /")) {
                System.out.println("Cd into root dir\n");
                directories.add(root);
                currentDir = root;
                line = reader.readLine();
            }

            if (line.contains("dir")) {
                String[] dirName = line.split(" ");
                Dir addDir = new Dir(dirName[1]);
                addDir.parent = currentDir;
                currentDir.subDir.add(addDir);
                directories.add(addDir);
                System.out.println("Create dir: " + dirName[1] + "\n");
            }

            if (line.startsWith("cd")) {
                if (line.contains("cd ..")) {
                    currentDir = currentDir.parent;
                    System.out.println(
                            "Change dir .. : Current dir is now: " + currentDir.name + "\n");

                } else {
                    String[] cdDirName = line.split(" ");
                    Optional<Dir> cdDir = currentDir.subDir.stream()
                            .filter(sub -> sub.name.equals(cdDirName[1]))
                            .findFirst();
                    cdDir.ifPresent(dir -> currentDir = dir);
                    System.out.println(
                            "Change dir: " + cdDirName[1] + " Current dir is now: " + currentDir.name + "\n");
                }
            }

            Matcher fileCommand = p.matcher(line);
            if (fileCommand.find()) {
                String[] fileMetaData = line.split(" ");
                Dir fileDir = new Dir(fileMetaData[1]);
                fileDir.parent = fileDir;
                fileDir.size = Integer.parseInt(fileMetaData[0]);
                currentDir.subDir.add(fileDir);
                System.out.println(
                        "File found: " + fileMetaData[1] + " size: " + fileMetaData[0] + "\n");
            }
            line = reader.readLine();
        }
        reader.close();

        int totalSpace = 70000000;
        int requiredSpace = 30000000;
        int maxSpace = totalSpace - requiredSpace;
        int rootSize = root.getSubDirSize();
        int neededSpace = rootSize - maxSpace;


        Optional<Dir> delDir = directories.stream()
                .filter(dir -> dir.getSubDirSize() > neededSpace)
                .min(Comparator.comparingInt(Dir::getSubDirSize));

        if (delDir.isPresent()) {
            int delSum = delDir.stream().mapToInt(Dir::getSubDirSize).sum();
            System.out.println("Del Dir Sum: " + delSum);
        }

    }



}
