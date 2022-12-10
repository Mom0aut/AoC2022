package at.aoc.day9;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Knot {

    int column = 0;
    int row = 0;

    Knot movementVector;

    Knot lastPosition;

    public Knot(int column, int row) {
        this.column = column;
        this.row = row;
    }


    public boolean isTouchingHead(HeadPosition headPosition) {

        if (row == headPosition.row && column == headPosition.column) {
            return true;
        }

        //row right
        if (row + 1 == headPosition.row && column == headPosition.column) {
            return true;
        }

        //row left
        if (row - 1 == headPosition.row && column == headPosition.column) {
            return true;
        }

        //column up
        if (column + 1 == headPosition.column && row == headPosition.row) {
            return true;
        }
        //column down
        if (column - 1 == headPosition.column && row == headPosition.row) {
            return true;
        }

        //diagonally
        //Right Up diagonal
        if (column + 1 == headPosition.column && row - 1 == headPosition.row) {
            return true;
        }

        //Right Down diagonal
        if (column + 1 == headPosition.column && row + 1 == headPosition.row) {
            return true;
        }

        //Left Down diagonal
        if (column - 1 == headPosition.column && row + 1 == headPosition.row) {
            return true;
        }

        //Left Up diagonal
        if (column - 1 == headPosition.column && row - 1 == headPosition.row) {
            return true;
        }
        return false;
    }

    public boolean isTouchingPosition(Knot position) {

        if (row == position.row && column == position.column) {
            return true;
        }

        //row right
        if (row + 1 == position.row && column == position.column) {
            return true;
        }

        //row left
        if (row - 1 == position.row && column == position.column) {
            return true;
        }

        //column up
        if (column + 1 == position.column && row == position.row) {
            return true;
        }
        //column down
        if (column - 1 == position.column && row == position.row) {
            return true;
        }

        //diagonally
        //Right Up diagonal
        if (column + 1 == position.column && row - 1 == position.row) {
            return true;
        }

        //Right Down diagonal
        if (column + 1 == position.column && row + 1 == position.row) {
            return true;
        }

        //Left Down diagonal
        if (column - 1 == position.column && row + 1 == position.row) {
            return true;
        }

        //Left Up diagonal
        if (column - 1 == position.column && row - 1 == position.row) {
            return true;
        }
        return false;
    }

}
