package at.aoc.day11;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day11 {



    public static void main(String[] args) throws IOException {
        Day11 day11 = new Day11();
        day11.partOne("day11/bigData.txt");
        day11.partTwo("day10/bigData.txt");
    }

    public void partOne(String filename) throws IOException {
        int rounds = 20;

        //        Monkey monkey0 = new Monkey(Lists.newLinkedList(), "*", 19, 23, 2, 3, 0);
        //        monkey0.getItems().add(BigInteger.valueOf(79));
        //        monkey0.getItems().add(BigInteger.valueOf(98));
        //
        //        Monkey monkey1 = new Monkey(Lists.newLinkedList(), "+", 6, 19, 2, 0, 0);
        //        monkey1.getItems().add(BigInteger.valueOf(54));
        //        monkey1.getItems().add(BigInteger.valueOf(65));
        //        monkey1.getItems().add(BigInteger.valueOf(75));
        //        monkey1.getItems().add(BigInteger.valueOf(74));
        //
        //        Monkey monkey2 = new Monkey(Lists.newLinkedList(), "*", 0, 13, 1, 3, 0);
        //        monkey2.getItems().add(BigInteger.valueOf(79));
        //        monkey2.getItems().add(BigInteger.valueOf(60));
        //        monkey2.getItems().add(BigInteger.valueOf(97));
        //
        //        Monkey monkey3 = new Monkey(Lists.newLinkedList(), "+", 3, 17, 0, 1, 0);
        //        monkey3.getItems().add(BigInteger.valueOf(74));

        Monkey monkey0 = new Monkey(Lists.newLinkedList(), "*", 11, 7, 6, 7, 0);
        //Starting items: 66, 79
        monkey0.getItems().add(BigInteger.valueOf(66));
        monkey0.getItems().add(BigInteger.valueOf(79));

        Monkey monkey1 = new Monkey(Lists.newLinkedList(), "*", 17, 13, 5, 2, 0);
        //Starting items: 84, 94, 94, 81, 98, 75
        monkey1.getItems().add(BigInteger.valueOf(84));
        monkey1.getItems().add(BigInteger.valueOf(94));
        monkey1.getItems().add(BigInteger.valueOf(94));
        monkey1.getItems().add(BigInteger.valueOf(81));
        monkey1.getItems().add(BigInteger.valueOf(98));
        monkey1.getItems().add(BigInteger.valueOf(75));

        Monkey monkey2 = new Monkey(Lists.newLinkedList(), "+", 8, 5, 4, 5, 0);
        //Starting items: 85, 79, 59, 64, 79, 95, 67
        monkey2.getItems().add(BigInteger.valueOf(85));
        monkey2.getItems().add(BigInteger.valueOf(79));
        monkey2.getItems().add(BigInteger.valueOf(59));
        monkey2.getItems().add(BigInteger.valueOf(64));
        monkey2.getItems().add(BigInteger.valueOf(79));
        monkey2.getItems().add(BigInteger.valueOf(95));
        monkey2.getItems().add(BigInteger.valueOf(67));

        Monkey monkey3 = new Monkey(Lists.newLinkedList(), "+", 3, 19, 6, 0, 0);
        //Starting items: 70
        monkey3.getItems().add(BigInteger.valueOf(70));

        Monkey monkey4 = new Monkey(Lists.newLinkedList(), "+", 4, 2, 0, 3, 0);
        //Starting items: 57, 69, 78, 78
        monkey4.getItems().add(BigInteger.valueOf(57));
        monkey4.getItems().add(BigInteger.valueOf(69));
        monkey4.getItems().add(BigInteger.valueOf(78));
        monkey4.getItems().add(BigInteger.valueOf(78));

        Monkey monkey5 = new Monkey(Lists.newLinkedList(), "+", 7, 11, 3, 4, 0);
        //Starting items: 65, 92, 60, 74, 72
        monkey5.getItems().add(BigInteger.valueOf(65));
        monkey5.getItems().add(BigInteger.valueOf(92));
        monkey5.getItems().add(BigInteger.valueOf(60));
        monkey5.getItems().add(BigInteger.valueOf(74));
        monkey5.getItems().add(BigInteger.valueOf(72));

        Monkey monkey6 = new Monkey(Lists.newLinkedList(), "*", 0, 17, 1, 7, 0);
        //Starting items: 77, 91, 91
        monkey6.getItems().add(BigInteger.valueOf(77));
        monkey6.getItems().add(BigInteger.valueOf(91));
        monkey6.getItems().add(BigInteger.valueOf(91));

        Monkey monkey7 = new Monkey(Lists.newLinkedList(), "+", 6, 3, 2, 1, 0);
        //Starting items: 76, 58, 57, 55, 67, 77, 54, 99
        monkey7.getItems().add(BigInteger.valueOf(76));
        monkey7.getItems().add(BigInteger.valueOf(58));
        monkey7.getItems().add(BigInteger.valueOf(57));
        monkey7.getItems().add(BigInteger.valueOf(55));
        monkey7.getItems().add(BigInteger.valueOf(67));
        monkey7.getItems().add(BigInteger.valueOf(77));
        monkey7.getItems().add(BigInteger.valueOf(54));
        monkey7.getItems().add(BigInteger.valueOf(99));



        //        List<Monkey> monkeys = List.of(monkey0, monkey1, monkey2, monkey3);

        List<Monkey> monkeys =
                List.of(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7);


        for (int i = 1; i <= rounds; i++) {
            monkeys.forEach(monkey -> {
                List<BigInteger> itemsToRemove = new ArrayList<>();
                monkey.getItems().forEach(item -> {
                    BigInteger worryLevel = BigInteger.valueOf(0);
                    //Inspect Item
                    monkey.setItemsInspectedCounter(monkey.getItemsInspectedCounter() + 1);
                    BigInteger operationValue = BigInteger.valueOf(monkey.getOperationValue());

                    if (operationValue.equals(BigInteger.ZERO)) {
                        operationValue = item;
                    }
                    if (monkey.getOperation().equals("*")) {
                        worryLevel = item.multiply(operationValue);
                    } else {
                        worryLevel = item.add(operationValue);
                    }
                    //Monkey gets board
                    itemsToRemove.add(item);
                    worryLevel = worryLevel.divide(BigInteger.valueOf(3));
                    Monkey monkeyThrowTo;
                    //Test Item
                    if (worryLevel.mod(BigInteger.valueOf(monkey.getTestValue()))
                            .equals(BigInteger.valueOf(0))) {
                        monkeyThrowTo = monkeys.get(monkey.getMonkeyThrowToTrue());
                    } else {
                        monkeyThrowTo = monkeys.get(monkey.getMonkeyThrowToFalse());
                    }
                    monkeyThrowTo.getItems().add(worryLevel);
                });
                itemsToRemove.forEach(removeItem -> monkey.getItems().remove(removeItem));
            });
            System.out.println("Round: " + i + "\n");
        }

        int[] mostActiveMonkeys =
                monkeys.stream().mapToInt(Monkey::getItemsInspectedCounter).sorted().toArray();


        System.out.println(
                "Money First: " + mostActiveMonkeys[mostActiveMonkeys.length - 1] + "\n");
        System.out.println(
                "Money Second: " + mostActiveMonkeys[mostActiveMonkeys.length - 2] + "\n");

        BigInteger monkeyNusinessLevel =
                BigInteger.valueOf(mostActiveMonkeys[mostActiveMonkeys.length - 1])
                        .multiply(BigInteger.valueOf(
                                mostActiveMonkeys[mostActiveMonkeys.length - 2]));
        System.out.println("Level of monkey business: " + monkeyNusinessLevel);
    }

    public void partTwo(String filename) throws IOException {
        int rounds = 10000;

        //        Monkey monkey0 = new Monkey(Lists.newLinkedList(), "*", 19, 23, 2, 3, 0);
        //        monkey0.getItems().add(BigInteger.valueOf(79));
        //        monkey0.getItems().add(BigInteger.valueOf(98));
        //
        //        Monkey monkey1 = new Monkey(Lists.newLinkedList(), "+", 6, 19, 2, 0, 0);
        //        monkey1.getItems().add(BigInteger.valueOf(54));
        //        monkey1.getItems().add(BigInteger.valueOf(65));
        //        monkey1.getItems().add(BigInteger.valueOf(75));
        //        monkey1.getItems().add(BigInteger.valueOf(74));
        //
        //        Monkey monkey2 = new Monkey(Lists.newLinkedList(), "*", 0, 13, 1, 3, 0);
        //        monkey2.getItems().add(BigInteger.valueOf(79));
        //        monkey2.getItems().add(BigInteger.valueOf(60));
        //        monkey2.getItems().add(BigInteger.valueOf(97));
        //
        //        Monkey monkey3 = new Monkey(Lists.newLinkedList(), "+", 3, 17, 0, 1, 0);
        //        monkey3.getItems().add(BigInteger.valueOf(74));

        Monkey monkey0 = new Monkey(Lists.newLinkedList(), "*", 11, 7, 6, 7, 0);
        //Starting items: 66, 79
        monkey0.getItems().add(BigInteger.valueOf(66));
        monkey0.getItems().add(BigInteger.valueOf(79));

        Monkey monkey1 = new Monkey(Lists.newLinkedList(), "*", 17, 13, 5, 2, 0);
        //Starting items: 84, 94, 94, 81, 98, 75
        monkey1.getItems().add(BigInteger.valueOf(84));
        monkey1.getItems().add(BigInteger.valueOf(94));
        monkey1.getItems().add(BigInteger.valueOf(94));
        monkey1.getItems().add(BigInteger.valueOf(81));
        monkey1.getItems().add(BigInteger.valueOf(98));
        monkey1.getItems().add(BigInteger.valueOf(75));

        Monkey monkey2 = new Monkey(Lists.newLinkedList(), "+", 8, 5, 4, 5, 0);
        //Starting items: 85, 79, 59, 64, 79, 95, 67
        monkey2.getItems().add(BigInteger.valueOf(85));
        monkey2.getItems().add(BigInteger.valueOf(79));
        monkey2.getItems().add(BigInteger.valueOf(59));
        monkey2.getItems().add(BigInteger.valueOf(64));
        monkey2.getItems().add(BigInteger.valueOf(79));
        monkey2.getItems().add(BigInteger.valueOf(95));
        monkey2.getItems().add(BigInteger.valueOf(67));

        Monkey monkey3 = new Monkey(Lists.newLinkedList(), "+", 3, 19, 6, 0, 0);
        //Starting items: 70
        monkey3.getItems().add(BigInteger.valueOf(70));

        Monkey monkey4 = new Monkey(Lists.newLinkedList(), "+", 4, 2, 0, 3, 0);
        //Starting items: 57, 69, 78, 78
        monkey4.getItems().add(BigInteger.valueOf(57));
        monkey4.getItems().add(BigInteger.valueOf(69));
        monkey4.getItems().add(BigInteger.valueOf(78));
        monkey4.getItems().add(BigInteger.valueOf(78));

        Monkey monkey5 = new Monkey(Lists.newLinkedList(), "+", 7, 11, 3, 4, 0);
        //Starting items: 65, 92, 60, 74, 72
        monkey5.getItems().add(BigInteger.valueOf(65));
        monkey5.getItems().add(BigInteger.valueOf(92));
        monkey5.getItems().add(BigInteger.valueOf(60));
        monkey5.getItems().add(BigInteger.valueOf(74));
        monkey5.getItems().add(BigInteger.valueOf(72));

        Monkey monkey6 = new Monkey(Lists.newLinkedList(), "*", 0, 17, 1, 7, 0);
        //Starting items: 77, 91, 91
        monkey6.getItems().add(BigInteger.valueOf(77));
        monkey6.getItems().add(BigInteger.valueOf(91));
        monkey6.getItems().add(BigInteger.valueOf(91));

        Monkey monkey7 = new Monkey(Lists.newLinkedList(), "+", 6, 3, 2, 1, 0);
        //Starting items: 76, 58, 57, 55, 67, 77, 54, 99
        monkey7.getItems().add(BigInteger.valueOf(76));
        monkey7.getItems().add(BigInteger.valueOf(58));
        monkey7.getItems().add(BigInteger.valueOf(57));
        monkey7.getItems().add(BigInteger.valueOf(55));
        monkey7.getItems().add(BigInteger.valueOf(67));
        monkey7.getItems().add(BigInteger.valueOf(77));
        monkey7.getItems().add(BigInteger.valueOf(54));
        monkey7.getItems().add(BigInteger.valueOf(99));



        //        List<Monkey> monkeys = List.of(monkey0, monkey1, monkey2, monkey3);

        List<Monkey> monkeys =
                List.of(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7);


        for (int i = 1; i <= rounds; i++) {
            monkeys.forEach(monkey -> {
                List<BigInteger> itemsToRemove = new ArrayList<>();
                monkey.getItems().forEach(item -> {
                    BigInteger worryLevel = BigInteger.valueOf(0);
                    //Inspect Item
                    monkey.setItemsInspectedCounter(monkey.getItemsInspectedCounter() + 1);
                    BigInteger operationValue = BigInteger.valueOf(monkey.getOperationValue());

                    if (operationValue.equals(BigInteger.ZERO)) {
                        operationValue = item;
                    }
                    if (monkey.getOperation().equals("*")) {
                        worryLevel = item.multiply(operationValue);
                    } else {
                        worryLevel = item.add(operationValue);
                    }
                    //Monkey gets board
                    itemsToRemove.add(item);
                    worryLevel =
                            worryLevel.mod(BigInteger.valueOf(7 * 13 * 5 * 19 * 2 * 11 * 17 * 3));
                    Monkey monkeyThrowTo;
                    //Test Item
                    if (worryLevel.mod(BigInteger.valueOf(monkey.getTestValue()))
                            .equals(BigInteger.valueOf(0))) {
                        monkeyThrowTo = monkeys.get(monkey.getMonkeyThrowToTrue());
                    } else {
                        monkeyThrowTo = monkeys.get(monkey.getMonkeyThrowToFalse());
                    }
                    monkeyThrowTo.getItems().add(worryLevel);
                });
                itemsToRemove.forEach(removeItem -> monkey.getItems().remove(removeItem));
            });
            System.out.println("Round: " + i + "\n");
        }

        int[] mostActiveMonkeys =
                monkeys.stream().mapToInt(Monkey::getItemsInspectedCounter).sorted().toArray();


        System.out.println(
                "Money First: " + mostActiveMonkeys[mostActiveMonkeys.length - 1] + "\n");
        System.out.println(
                "Money Second: " + mostActiveMonkeys[mostActiveMonkeys.length - 2] + "\n");

        BigInteger monkeyNusinessLevel =
                BigInteger.valueOf(mostActiveMonkeys[mostActiveMonkeys.length - 1])
                        .multiply(BigInteger.valueOf(
                                mostActiveMonkeys[mostActiveMonkeys.length - 2]));


        System.out.println("Level of monkey business: " + monkeyNusinessLevel);
    }



}
