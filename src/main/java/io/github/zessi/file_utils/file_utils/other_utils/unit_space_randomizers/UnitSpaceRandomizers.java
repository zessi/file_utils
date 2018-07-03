package io.github.zessi.file_utils.file_utils.other_utils.unit_space_randomizers;

import java.lang.reflect.Array;
import java.security.SecureRandom;

public class UnitSpaceRandomizers {

    public static char[] getRandomASCIIPrintableWithSpace(int size) {
        final Character[] generatedCharacters = getRandom(UnitSpaces.getUnitSpaceOfASCIIPrintableWithSpace(), size);
        return unboxCharacterArray(generatedCharacters);
    }

    public static char[] getRandomStringOfDigits(int size) {
        final Character[] generatedCharacters = getRandom(UnitSpaces.getUnitSpaceOfDigits(), size);
        return unboxCharacterArray(generatedCharacters);
    }

    public static char[] getRandomASCIILettersAndDigitsOnly(int size) {
        final Character[] generatedCharacters = getRandom(UnitSpaces.getUnitSpaceASCIILettersAndDigitsOnly(), size);
        return unboxCharacterArray(generatedCharacters);
    }

    public static char[] getRandomASCIISmallCaseLettersAndDigits(int size) {
        final Character[] generatedCharacters = getRandom(UnitSpaces.getUnitSpaceASCIISmallCaseLettersAndDigits(), size);
        return unboxCharacterArray(generatedCharacters);
    }

    public static char[] getRandomASCIISmallCaseLettersOnly(int size) {
        final Character[] text = getRandom(UnitSpaces.getUnitSpacesASCIISmallCaseLettersOnly(), size);
        return unboxCharacterArray(text);
    }

    private static <T> T[] getRandom(UnitSpace<T> unitSpace, int resultSize) {
        final T[] result = (T[]) Array.newInstance(unitSpace.getUnitType(), resultSize);
        for (int i = 0; i < resultSize; i++) {
            result[i] = unitSpace.getUnit(new SecureRandom().nextInt(unitSpace.getSize()));
        }
        return result;
    }

    private static char[] unboxCharacterArray(Character[] input) {
        final char[] result = new char[input.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = input[i];
        }
        return result;
    }

}
