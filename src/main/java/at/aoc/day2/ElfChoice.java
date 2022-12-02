package at.aoc.day2;

import lombok.Getter;

@Getter
public enum ElfChoice {
    LOSE("X"),
    DRAW("Y"),
    WIN("Z");
    private String letter;

    ElfChoice(String letter) {
        this.letter = letter;
    }


    public static ElfChoice fromString(String text) {
        for (ElfChoice enemyChoice : ElfChoice.values()) {
            if (enemyChoice.letter.equalsIgnoreCase(text)) {
                return enemyChoice;
            }
        }
        return null;
    }

}
