package service;

import java.util.List;
import plan.Hold;
import plan.VerificationReport;
import terminal.LoadingEquipment;
import terminal.StabilityValidator;

public class PlanVerifier {

    public VerificationReport verify(List<Hold> holds, StabilityValidator validator, LoadingEquipment equipment) {
        double bow = holds.get(0).getAssignedWeight() + holds.get(1).getAssignedWeight();
        double stern = holds.get(2).getAssignedWeight() + holds.get(3).getAssignedWeight();
        double total = bow + stern;

        double imbalance = total == 0 ? 0 : Math.abs(bow - stern) / total * 100;
        double maxImbalance = validator.getMaxImbalancePercent();
        boolean balanceOk = imbalance <= maxImbalance + 1e-9;

        double tonsToShift = 0;
        if (!balanceOk) {
            double heavy = Math.max(bow, stern);
            double light = Math.min(bow, stern);
            tonsToShift = ((heavy - light) - (maxImbalance / 100) * total) / 2;
        }

        double draft = 0.55 + total / 4200;
        double maxDraft = validator.getMaxDraftMeters();
        boolean draftOk = draft <= maxDraft + 1e-9;

        double hoursDecimal = equipment.getRateTonsPerHour() == 0 ? 0 : total / equipment.getRateTonsPerHour();
        int hours = (int) hoursDecimal;
        int minutes = (int) Math.round((hoursDecimal - hours) * 60);
        if (minutes == 60) {
            hours = hours + 1;
            minutes = 0;
        }

        return new VerificationReport(bow, stern, imbalance, maxImbalance, tonsToShift, balanceOk,
                draft, maxDraft, draftOk, hours, minutes);
    }
}
