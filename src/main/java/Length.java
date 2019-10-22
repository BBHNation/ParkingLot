class Length {
    private double value;
    private LengthUnit unit;

    Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    boolean biggerThan(Length length) {
        return value > length.value;
    }

    boolean smallerThan(Length length) {
        return value < length.value;
    }

    boolean equal(Length length) {
        return value == length.value;
    }

}

