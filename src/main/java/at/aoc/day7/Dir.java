package at.aoc.day7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dir {

    String name;
    Dir parent;
    List<Dir> subDir = new ArrayList<>();

    int size;


    public Dir(String name) {
        this.name = name;
    }

    int getSubDirSize() {
        return subDir.stream().mapToInt(dir -> {
            if (dir.subDir.isEmpty()) {
                return dir.size;
            } else {
                return dir.getSubDirSize();
            }
        }).sum();
    }

}
