package io.github.zessi.file_utils.file_utils.other_utils.unit_space_randomizers;

public class UnitSpaces {

    public static UnitSpace<Character> getUnitSpaceOfASCIIPrintableWithSpace() {
        final UnitSpace.Builder<Character> charactersUnitSpaceBuilder = new UnitSpace.Builder<Character>(Character.class);
        for (int i = 32; i < 127; i++) {
            charactersUnitSpaceBuilder.addUnit((char) i);
        }
        return charactersUnitSpaceBuilder.build();
    }

    public static UnitSpace<Character> getUnitSpaceOfDigits() {
        final UnitSpace.Builder<Character> digitsUnitSpaceBuilder = new UnitSpace.Builder<Character>(Character.class);
        for (int i = 48; i < 58; i++) {
            digitsUnitSpaceBuilder.addUnit((char) i);
        }
        return digitsUnitSpaceBuilder.build();
    }

    public static UnitSpace<Character> getUnitSpaceASCIILettersAndDigitsOnly() {
        final UnitSpace.Builder<Character> charactersUnitSpaceBuilder = new UnitSpace.Builder<Character>(Character.class);
        for (int i = 48; i < 58; i++) {
            charactersUnitSpaceBuilder.addUnit((char) i);
        }
        for (int i = 65; i < 91; i++) {
            charactersUnitSpaceBuilder.addUnit((char) i);
        }
        for (int i = 97; i < 123; i++) {
            charactersUnitSpaceBuilder.addUnit((char) i);
        }
        return charactersUnitSpaceBuilder.build();
    }

    public static UnitSpace<Character> getUnitSpaceASCIISmallCaseLettersAndDigits() {
        final UnitSpace.Builder<Character> unitSpaceBuilder = new UnitSpace.Builder<Character>(Character.class);
        for (int i = 48; i < 58; i++) {
            unitSpaceBuilder.addUnit((char) i);
        }
        for (int i = 97; i < 123; i++) {
            unitSpaceBuilder.addUnit((char) i);
        }
        return unitSpaceBuilder.build();
    }

    public static UnitSpace<Character> getUnitSpacesASCIISmallCaseLettersOnly() {
        final UnitSpace.Builder<Character> unitSpaceBuilder = new UnitSpace.Builder<Character>(Character.class);
        for (int i = 97; i < 123; i++) {
            unitSpaceBuilder.addUnit((char) i);
        }
        return unitSpaceBuilder.build();
    }
}
