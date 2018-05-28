package bhge.test.model;

public enum Priotity {
    HIGH(0),
    MEDIUM(1),
    LOW(2);

    private final Integer value;

    Priotity(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Priotity getPriorityByValue(final int number) {
        int value = number % 3;
        switch (value) {
            case 0:
                return HIGH;
            case 1:
                return MEDIUM;
            case 2:
            default:
                return LOW;
        }
    }
}
