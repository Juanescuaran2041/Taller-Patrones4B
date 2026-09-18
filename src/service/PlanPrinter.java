package service;

import java.util.Locale;
import manifest.CargoUnit;
import manifest.RegistrationResult;
import plan.CargoMove;
import plan.Hold;
import plan.StowagePlan;
import plan.VerificationReport;

public class PlanPrinter {

    public void printRegistration(RegistrationResult result) {
        System.out.printf(Locale.US, "Registrar: %s | lines read: %d | accepted: %d | rejected: %d%n",
                result.getRegistrarName(), result.getLinesRead(), result.getAccepted().size(), result.getRejected().size());

        for (int i = 0; i < result.getRejected().size(); i++) {
            var rejected = result.getRejected().get(i);
            System.out.printf(Locale.US, "[REJECTED] %s -> %s%n", rejected.getLine(), rejected.getReason());
        }
    }

    public void print(StowagePlan plan) {
        VerificationReport report = plan.getVerificationReport();

        System.out.println();
        System.out.printf(Locale.US, "STOWAGE PLAN No. %s%n", plan.getPlanNumber());
        System.out.printf(Locale.US, "Barge: %s   Departure: %s   Terminal: %s%n",
                plan.getBargeRegistration(), plan.getDepartureDate(), plan.getTerminalType());
        System.out.printf(Locale.US, "Equipment: %s (%.0f t/h)%n",
                plan.getEquipment().getName(), plan.getEquipment().getRateTonsPerHour());
        if (plan.getPlannerName() != null) {
            System.out.println("Planner: " + plan.getPlannerName());
        }
        System.out.println();

        System.out.printf(Locale.US, "%-8s%12s%12s%12s%10s%n", "HOLD", "CARGO (t)", "CAPACITY", "OCCUPANCY", "UNITS");
        for (Hold hold : plan.getHolds()) {
            System.out.printf(Locale.US, "%-8s%12.2f%12.1f%11.1f%%%10d%n",
                    hold.getId(), hold.getAssignedWeight(), hold.getCapacityTons(),
                    hold.getOccupancyPercent(), hold.getUnits().size());
        }
        System.out.printf(Locale.US, "%-8s%12.2f%n", "TOTAL", plan.getTotalWeight());
        System.out.println();

        System.out.printf(Locale.US, "Bow: %.2f t | Stern: %.2f t | Imbalance: %.2f %% (max %.2f %%) -> %s%n",
                report.bowWeight, report.sternWeight, report.imbalancePercent, report.maxImbalancePercent,
                report.balanceOk ? "OK" : "EXCEEDS");
        if (!report.balanceOk) {
            System.out.printf(Locale.US, "  -> shift %.2f t from the heavy side to the light side to reach the limit%n",
                    report.tonsToShift);
        }
        System.out.printf(Locale.US, "Estimated draft: %.2f m (max %.2f m) -> %s%n",
                report.draftMeters, report.maxDraftMeters, report.draftOk ? "OK" : "EXCEEDS");
        System.out.printf(Locale.US, "Estimated loading time: %d h %02d min%n", report.loadingHours, report.loadingMinutes);
        System.out.println();

        System.out.printf(Locale.US, "PLAN STATUS: %s%n", plan.getStatus());
        System.out.println();

        if (plan.getLoadingSequence().size() > 0) {
            System.out.println("LOADING SEQUENCE");
            for (CargoMove move : plan.getLoadingSequence()) {
                System.out.printf(Locale.US, "%d. %s <- %s %.2f t%n",
                        move.getNumber(), move.getHoldId(), move.getUnit().getShortLabel(), move.getUnit().getGrossWeightTons());
            }
            System.out.println();
        }

        if (plan.getNotLoaded().size() > 0) {
            System.out.println("CARGO NOT LOADED (no hold had enough room)");
            for (CargoUnit unit : plan.getNotLoaded()) {
                System.out.printf(Locale.US, "  - %s %.2f t%n", unit.getShortLabel(), unit.getGrossWeightTons());
            }
            System.out.println();
        }

        System.out.println("DOCUMENT: " + plan.getDocument().getName());
    }
}
