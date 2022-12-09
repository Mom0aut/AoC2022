package at.aoc.day9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadPosition {

    int column = 0;
    int row = 0;
    HeadPosition lastPosition;


    public HeadPosition(int column, int row) {
        this.column = column;
        this.row = row;
    }
}
