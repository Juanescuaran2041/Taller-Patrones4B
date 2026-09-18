package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import manifest.CargoUnit;
import plan.CargoMove;
import plan.Hold;

public class SequenceGenerator {

    public List<CargoMove> generate(List<Hold> holds) {
        List<CargoMove> sequence = new ArrayList<>();
        int n = 1;
        for (Hold h : holds) {
            List<CargoUnit> units = new ArrayList<>(h.getUnits());
            units.sort(Comparator.comparingDouble(CargoUnit::getGrossWeightTons).reversed());
            for (CargoUnit u : units) {
                sequence.add(new CargoMove(n, h.getId(), u));
                n++;
            }
        }
        return sequence;
    }
}
