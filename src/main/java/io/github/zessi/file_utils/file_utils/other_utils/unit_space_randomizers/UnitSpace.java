package io.github.zessi.file_utils.file_utils.other_utils.unit_space_randomizers;

import java.lang.reflect.Array;
import java.util.*;

/**
 * A class to hold all the possible units to used in random generation
 *
 * @param <T>: The type of the random unit
 */
public class UnitSpace<T> {

    private final T[] allPossibleUnits;

    private final Class<T> unitType;

    private UnitSpace(Class<T> clazz, final Set<T> allPossibleUnitsSet) {
        this.unitType = clazz;
        this.allPossibleUnits = (T[]) Array.newInstance(unitType, allPossibleUnitsSet.size());
        final List<T> allPossibleUnitsList = new ArrayList<T>(allPossibleUnitsSet);
        for (int i = 0; i < this.allPossibleUnits.length; i++) {
            this.allPossibleUnits[i] = allPossibleUnitsList.get(i);
        }
    }

    public T[] getAllPossibleUnitsCopy() {
        return Arrays.copyOf(this.allPossibleUnits, this.allPossibleUnits.length);
    }

    public Class<T> getUnitType() {
        return unitType;
    }

    public T getUnit(int index) {
        return this.allPossibleUnits[index];
    }

    public int getSize() {
        return this.allPossibleUnits.length;
    }

    /**
     * A class used to build a UnitSpace instance.
     *
     * @param <T> The type of the random unit
     */
    public static class Builder<T> {
        private final Set<T> allPossibleUnitsSet = new HashSet<>();
        private final Class<T> unitType;

        public Builder(Class<T> unitType) {
            this.unitType = unitType;
        }

        public void addUnits(T[] units) {
            allPossibleUnitsSet.addAll(Arrays.asList(units));
        }

        public void addUnits(Collection<T> units) {
            allPossibleUnitsSet.addAll(units);
        }

        public void addUnit(T unit) {
            allPossibleUnitsSet.add(unit);
        }

        public Set<T> getAllPossibleUnitsSet() {
            return allPossibleUnitsSet;
        }

        public int getSize() {
            return this.allPossibleUnitsSet.size();
        }

        private void validate() {
            if (this.allPossibleUnitsSet.size() < 1) throw new IllegalStateException("Invalid: unit count is less than 1");
            if (this.unitType == null) throw new IllegalStateException("Invalid: unit type is null");
        }

        public UnitSpace<T> build() {
            this.validate();
            return new UnitSpace<T>(this.unitType, this.allPossibleUnitsSet);
        }
    }
}
