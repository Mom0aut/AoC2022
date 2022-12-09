package at.aoc.day8;

import at.aoc.util.BufferReaderUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8 {



    public static void main(String[] args) throws IOException {
        Day8 day8 = new Day8();
        day8.partOne("day8/bigData.txt");
        day8.partTwo("day8/bigData.txt");

    }

    public void partOne(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        int lineCounter = 0;
        String[][] forest = new String[99][99];
        String line = reader.readLine();
        while (line != null) {
            char[] trees = line.toCharArray();
            for (int i = 0; i < trees.length; i++) {
                forest[lineCounter][i] = String.valueOf(trees[i]);
            }
            line = reader.readLine();
            lineCounter++;
            System.out.println("Trees: " + Arrays.deepToString(forest) + "\n");
        }
        reader.close();

        int visiableTrees = 0;

        for (int i = 0; i < forest.length; i++) {

            for (int j = 0; j < forest.length; j++) {

                //First Row
                if (i == 0) {
                    System.out.println(
                            "Tree on the edge: " + i + "|" + j + " height:" + forest[i][j]);
                    visiableTrees++;
                }

                //First Column minus First and Last ROw
                if (j == 0 && i > 0 && i < forest.length - 1) {
                    System.out.println(
                            "Tree on the edge: " + i + "|" + j + " height:" + forest[i][j]);
                    visiableTrees++;
                }

                //Last Column minus First and Last ROw
                if (j == forest.length - 1 && i > 0 && i < forest.length - 1) {
                    System.out.println(
                            "Tree on the edge: " + i + "|" + j + " height:" + forest[i][j]);
                    visiableTrees++;
                }

                //Last Row
                if (i == forest.length - 1) {
                    System.out.println(
                            "Tree on the edge: " + i + "|" + j + " height:" + forest[i][j]);
                    visiableTrees++;
                }

                //Inner Forest
                if (i > 0 && j > 0 && i < forest.length - 1 && j < forest.length - 1) {

                    int currentTreeSize = Integer.parseInt(forest[i][j]);
                    boolean leftVisable = true;
                    boolean rightVisable = true;
                    boolean topVisable = true;
                    boolean bottomVisable = true;

                    for (int k = 0; k < j; k++) {
                        int leftTreeSize = Integer.parseInt(forest[i][k]);
                        if (leftTreeSize >= currentTreeSize) {
                            leftVisable = false;
                            break;
                        }
                    }

                    for (int l = j + 1; l < forest.length; l++) {
                        int rightTreeSize = Integer.parseInt(forest[i][l]);
                        if (rightTreeSize >= currentTreeSize) {
                            rightVisable = false;
                            break;
                        }
                    }

                    for (int m = 0; m < i; m++) {
                        int topTreeSize = Integer.parseInt(forest[m][j]);
                        if (topTreeSize >= currentTreeSize) {
                            topVisable = false;
                            break;
                        }
                    }

                    for (int n = i + 1; n < forest.length; n++) {
                        int bottomTreeSize = Integer.parseInt(forest[n][j]);
                        if (bottomTreeSize >= currentTreeSize) {
                            bottomVisable = false;
                            break;
                        }
                    }

                    if (leftVisable || rightVisable || topVisable || bottomVisable) {
                        visiableTrees++;
                        System.out.println(
                                "Tree at: " + i + "|" + j + " height: " + forest[i][j] + " is visable\n");
                    }
                }
            }
        }

        System.out.println("Visable trees: " + visiableTrees);



    }

    public void partTwo(String filename) throws IOException {
        BufferedReader reader = BufferReaderUtil.readFile(filename);
        int lineCounter = 0;
        String[][] forest = new String[99][99];
        String line = reader.readLine();

        while (line != null) {
            char[] trees = line.toCharArray();
            for (int i = 0; i < trees.length; i++) {
                forest[lineCounter][i] = String.valueOf(trees[i]);
            }

            line = reader.readLine();
            lineCounter++;
            System.out.println("Trees: " + Arrays.deepToString(forest) + "\n");
        }
        reader.close();

        int visiableTrees = 0;
        List<Integer> treeScore = new ArrayList<>();


        for (int i = 0; i < forest.length; i++) {

            for (int j = 0; j < forest.length; j++) {

                int currentTreeSize = Integer.parseInt(forest[i][j]);
                int leftScore = 0;
                int rightScore = 0;
                int topScore = 0;
                int bottomScore = 0;

                for (int l = j + 1; l < forest.length; l++) {
                    int rightTreeSize = Integer.parseInt(forest[i][l]);
                    rightScore++;
                    if (rightTreeSize >= currentTreeSize) {
                        break;
                    }
                }

                for (int k = j - 1; k >= 0; k--) {
                    int leftTreeSize = Integer.parseInt(forest[i][k]);
                    leftScore++;
                    if (leftTreeSize >= currentTreeSize) {
                        break;
                    }
                }

                for (int m = i - 1; m >= 0; m--) {
                    int topTreeSize = Integer.parseInt(forest[m][j]);
                    topScore++;
                    if (topTreeSize >= currentTreeSize) {
                        break;
                    }
                }

                for (int n = i + 1; n < forest.length; n++) {
                    int bottomTreeSize = Integer.parseInt(forest[n][j]);
                    bottomScore++;
                    if (bottomTreeSize >= currentTreeSize) {
                        break;
                    }
                }
                treeScore.add(leftScore * rightScore * bottomScore * topScore);
            }
        }

        System.out.println("Max treescore: " + treeScore.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt());

    }



}
