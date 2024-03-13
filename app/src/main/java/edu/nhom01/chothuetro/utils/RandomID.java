package edu.nhom01.chothuetro.utils;

import java.util.Random;

public class RandomID {

    private static Random random = new Random();

    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String get(int size) {
        StringBuilder builder = new StringBuilder(size);
        for(int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            builder.append(randomChar);
        }
        return builder.toString();
    }
}
