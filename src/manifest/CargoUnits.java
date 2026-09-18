package manifest;

class ContainerUnit extends CargoUnit {
    private final int feet;
    private final String seal;

    ContainerUnit(String plate, int feet, String seal, double grossWeightTons) {
        super(plate, grossWeightTons);
        this.feet = feet;
        this.seal = seal;
    }

    public int getFeet() {
        return feet;
    }

    public String getSeal() {
        return seal;
    }

    @Override
    public String getType() {
        return "CONTAINER";
    }

    @Override
    public String getShortLabel() {
        return getId() + " (" + feet + " ft)";
    }
}

class BulkUnit extends CargoUnit {
    private final double declaredTons;
    private final double humidityPercent;

    BulkUnit(String product, double declaredTons, double humidityPercent, double grossWeightTons) {
        super(product, grossWeightTons);
        this.declaredTons = declaredTons;
        this.humidityPercent = humidityPercent;
    }

    public double getDeclaredTons() {
        return declaredTons;
    }

    public double getHumidityPercent() {
        return humidityPercent;
    }

    @Override
    public String getType() {
        return "BULK";
    }

    @Override
    public String getShortLabel() {
        return getId();
    }
}

class LiquidUnit extends CargoUnit {
    private final double liters;
    private final double density;

    LiquidUnit(String product, double liters, double density, double grossWeightTons) {
        super(product, grossWeightTons);
        this.liters = liters;
        this.density = density;
    }

    public double getLiters() {
        return liters;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String getType() {
        return "LIQUID";
    }

    @Override
    public String getShortLabel() {
        return getId();
    }
}
