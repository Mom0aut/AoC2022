package at.aoc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class BufferReaderUtil {

    public static BufferedReader readFile(String filename) {
        ClassLoader classLoader = BufferReaderUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

}
