class Length {
    private double value;
    private LengthUnit unit;

    Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private void validateSameUnitWith(Length length) {
        if (unit != length.unit) {
            throw new RuntimeException();
        }
    }

    boolean biggerThan(Length length) {
        validateSameUnitWith(length);
        return value > length.value;
    }

    boolean smallerThan(Length length) {
        validateSameUnitWith(length);
        return value < length.value;
    }

    boolean equal(Length length) {
        validateSameUnitWith(length);
        return value == length.value;
    }

}

