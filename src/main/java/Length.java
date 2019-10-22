class Length {
    private double value;

    Length(double value) {
        this.value = value;
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

