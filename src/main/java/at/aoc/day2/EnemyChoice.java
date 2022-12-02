package at.aoc.day2;

import lombok.Getter;

@Getter
public enum EnemyChoice {
    ROCK("A"),
    PAPER("B"),
    SCISSORS("C");
    
    private String letter;


    EnemyChoice(String letter) {
        this.letter = letter;
    }


    public static EnemyChoice fromString(String text) {
        for (EnemyChoice enemyChoice : EnemyChoice.values()) {
            if (enemyChoice.letter.equalsIgnoreCase(text)) {
                return enemyChoice;
            }
        }
        return null;
    }

}
