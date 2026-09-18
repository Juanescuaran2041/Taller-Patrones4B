package plan;

import manifest.CargoUnit;

public class CargoMove {

    private final int number;
    private final String holdId;
    private final CargoUnit unit;

    public CargoMove(int number, String holdId, CargoUnit unit) {
        this.number = number;
        this.holdId = holdId;
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public String getHoldId() {
        return holdId;
    }

    public CargoUnit getUnit() {
        return unit;
    }
}
