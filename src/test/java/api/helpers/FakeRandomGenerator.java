package api.helpers;

import java.util.Random;

public class FakeRandomGenerator {
    private static final Random random = new Random();

    public static <E extends Enum<E>> E getRandomOption(Class<E> enumClass) {
        int randomIndex = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[randomIndex];
    }

    public static String getRandomValueAsString(int min, int max) {
        int randomInt = random.nextInt((max - min) + 1) + min;
        return String.valueOf(randomInt);
    }
}