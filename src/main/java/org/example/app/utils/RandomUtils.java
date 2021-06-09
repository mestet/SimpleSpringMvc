package org.example.app.utils;

import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    private static final char[] engChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',};

    private static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String nextNumericString(int size) {
        char[] result = new char[size];
        // random but not zero
        result[0] = digits[random.nextInt(digits.length - 1) + 1];
        for (int i = 1; i < size; i++) {
            result[i] = digits[random.nextInt(digits.length)];
        }
        return new String(result);
    }

    public static String nextEngString(int size) {
        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = engChars[random.nextInt(engChars.length)];
        }
        return new String(result);
    }
}
