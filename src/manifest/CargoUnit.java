package manifest;

public abstract class CargoUnit {

    private final String id;
    private final double grossWeightTons;

    protected CargoUnit(String id, double grossWeightTons) {
        this.id = id;
        this.grossWeightTons = grossWeightTons;
    }

    public String getId() {
        return id;
    }

    public double getGrossWeightTons() {
        return grossWeightTons;
    }

    public abstract String getType();

    public abstract String getShortLabel();
}
