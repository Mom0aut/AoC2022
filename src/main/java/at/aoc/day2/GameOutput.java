package at.aoc.day2;

import lombok.Getter;

@Getter
public enum GameOutput {
    WIN(6),
    LOSE(0),
    DRAW(3);
    private int value;

    GameOutput(int value) {
        this.value = value;
    }


}
