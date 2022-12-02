package at.aoc.day2;

import lombok.Getter;

@Getter
public enum PlayerChoice {
    ROCK("X", 1),
    PAPER("Y", 2),
    SCISSORS("Z", 3);
    private String letter;
    private int value;

    PlayerChoice(String letter, int value) {
        this.letter = letter;
        this.value = value;
    }


    public static PlayerChoice fromString(String text) {
        for (PlayerChoice enemyChoice : PlayerChoice.values()) {
            if (enemyChoice.letter.equalsIgnoreCase(text)) {
                return enemyChoice;
            }
        }
        return null;
    }

}
