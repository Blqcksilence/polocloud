package dev.httpmarco.pololcoud.common;

import java.util.Random;

public class RandomUtils {

    private static Random RANDOM = new Random();

    public static int getRandomNumber(int max) {
        return RANDOM.nextInt(max);
    }
}
