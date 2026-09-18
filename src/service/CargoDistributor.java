package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import manifest.CargoUnit;
import plan.Hold;

public class CargoDistributor {

    private static final String[] HOLD_IDS = {"B1", "B2", "B3", "B4"};
    private static final double[] HOLD_CAPACITY = {600, 750, 750, 600};

    public Result distribute(List<CargoUnit> units) {
        List<Hold> holds = new ArrayList<>();
        for (int i = 0; i < HOLD_IDS.length; i++) {
            holds.add(new Hold(HOLD_IDS[i], HOLD_CAPACITY[i]));
        }

        List<CargoUnit> sorted = new ArrayList<>(units);
        sorted.sort(Comparator.comparingDouble(CargoUnit::getGrossWeightTons).reversed());

        List<CargoUnit> notLoaded = new ArrayList<>();
        for (CargoUnit unit : sorted) {
            Hold target = null;
            for (Hold h : holds) {
                if (h.hasRoomFor(unit.getGrossWeightTons())) {
                    target = h;
                    break;
                }
            }
            if (target != null) {
                target.add(unit);
            } else {
                notLoaded.add(unit);
            }
        }

        return new Result(holds, notLoaded);
    }

    public static class Result {
        private final List<Hold> holds;
        private final List<CargoUnit> notLoaded;

        Result(List<Hold> holds, List<CargoUnit> notLoaded) {
            this.holds = holds;
            this.notLoaded = notLoaded;
        }

        public List<Hold> getHolds() {
            return holds;
        }

        public List<CargoUnit> getNotLoaded() {
            return notLoaded;
        }
    }
}
