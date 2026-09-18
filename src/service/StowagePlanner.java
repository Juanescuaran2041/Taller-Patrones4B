package service;

import java.util.List;
import manifest.CargoUnit;
import plan.Hold;
import plan.StowagePlan;
import plan.VerificationReport;
import terminal.LoadingEquipment;
import terminal.ShippingDocument;
import terminal.StabilityValidator;
import terminal.TerminalFactory;

public class StowagePlanner {

    private final TerminalFactory factory;
    private final CargoDistributor distributor = new CargoDistributor();
    private final PlanVerifier verifier = new PlanVerifier();
    private final SequenceGenerator sequenceGenerator = new SequenceGenerator();

    public StowagePlanner(TerminalFactory factory) {
        this.factory = factory;
    }

    public StowagePlan plan(String planNumber, String departureDate, String bargeRegistration,
                             List<CargoUnit> units, String plannerName) {
        LoadingEquipment equipment = factory.createLoadingEquipment();
        StabilityValidator validator = factory.createStabilityValidator();
        ShippingDocument document = factory.createShippingDocument();

        CargoDistributor.Result distribution = distributor.distribute(units);
        List<Hold> holds = distribution.getHolds();

        VerificationReport report = verifier.verify(holds, validator, equipment);
        List<plan.CargoMove> sequence = sequenceGenerator.generate(holds);

        double totalWeight = 0;
        for (Hold h : holds) {
            totalWeight += h.getAssignedWeight();
        }

        return new StowagePlan.Builder()
                .planNumber(planNumber)
                .departureDate(departureDate)
                .bargeRegistration(bargeRegistration)
                .terminalType(factory.getType())
                .holds(holds)
                .totalWeight(totalWeight)
                .loadingSequence(sequence)
                .plannerName(plannerName)
                .equipment(equipment)
                .document(document)
                .verificationReport(report)
                .notLoaded(distribution.getNotLoaded())
                .build();
    }
}
