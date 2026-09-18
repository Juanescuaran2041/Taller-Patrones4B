package plan;

import java.util.ArrayList;
import java.util.List;
import manifest.CargoUnit;

public class Hold {

    private final String id;
    private final double capacityTons;
    private final List<CargoUnit> units = new ArrayList<>();

    public Hold(String id, double capacityTons) {
        this.id = id;
        this.capacityTons = capacityTons;
    }

    public String getId() {
        return id;
    }

    public double getCapacityTons() {
        return capacityTons;
    }

    public double getAssignedWeight() {
        double total = 0;
        for (CargoUnit u : units) {
            total += u.getGrossWeightTons();
        }
        return total;
    }

    public boolean hasRoomFor(double weightTons) {
        return getAssignedWeight() + weightTons <= capacityTons + 1e-6;
    }

    public void add(CargoUnit unit) {
        units.add(unit);
    }

    public List<CargoUnit> getUnits() {
        return units;
    }

    public double getOccupancyPercent() {
        if (capacityTons == 0) return 0;
        return getAssignedWeight() / capacityTons * 100;
    }
}
