package at.aoc.day11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monkey {

    private List<BigInteger> items = new LinkedList<>() {
    };

    private String operation;

    private int operationValue;

    private int testValue;

    private int MonkeyThrowToTrue;

    private int MonkeyThrowToFalse;

    private int itemsInspectedCounter = 0;

}
