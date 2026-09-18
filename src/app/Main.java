package app;

import java.util.List;
import manifest.BulkRegistrar;
import manifest.ContainerRegistrar;
import manifest.ManifestRegistrar;
import manifest.RegistrationResult;
import plan.Hold;
import plan.StowagePlan;
import service.PlanPrinter;
import service.StowagePlanner;
import terminal.TerminalFactory;
import terminal.TerminalType;

public class Main {

    static PlanPrinter printer = new PlanPrinter();

    public static void main(String[] args) {
        System.out.println("=== BARRANCABERMEJA RIVER TERMINAL ===");

        containerDemo();
        bulkImbalanceDemo();
        missingRegistrationDemo();
    }

    static void containerDemo() {
        System.out.println();
        System.out.println("----- CASE 1: CONTAINER TERMINAL -----");

        List<String> manifest = List.of(
                "CNT;MSKU1234567;40;18.5;SELLO-8891",
                "CNT;TCLU8877213;40;21.9;SELLO-1123",
                "CNT;HLXU4455667;20;12.4;SELLO-4456",
                "CNT;MSCU9988776;40;19.8;SELLO-7789",
                "CNT;OOLU2233445;20;9.6;SELLO-2290",
                "CNT;CMAU5566778;40;20.2;SELLO-5541",
                "CNT;TGHU1122334;20;11.1;SELLO-9987",
                "CNT;FCIU6677889;40;17.5;SELLO-3312",
                "CNT;MAEU3344556;20;10.8;SELLO-6678",
                "CNT;APLU7788990;40;22.6;SELLO-8845",
                "GRA;CARBON;850;13.5",
                "CNT;MSKU999;XX;18.5;SELLO-0001"
        );

        ManifestRegistrar registrar = new ContainerRegistrar();
        RegistrationResult result = registrar.process(manifest);
        printer.printRegistration(result);

        TerminalFactory factory = TerminalFactory.of(TerminalType.CONTAINER);
        StowagePlanner planner = new StowagePlanner(factory);

        StowagePlan plan = planner.plan("PE-2026-0148", "2026-10-05", "BZ-4417",
                result.getAccepted(), "Diana Restrepo");

        printer.print(plan);
    }

    static void bulkImbalanceDemo() {
        System.out.println();
        System.out.println("----- CASE 2: BULK TERMINAL (imbalance expected) -----");

        List<String> manifest = List.of(
                "GRA;CARBON-TERMICO;500;10",
                "GRA;CARBON-TERMICO;480;8",
                "GRA;MINERAL-HIERRO;300;5",
                "GRA;CALIZA;150;2"
        );

        ManifestRegistrar registrar = new BulkRegistrar();
        RegistrationResult result = registrar.process(manifest);
        printer.printRegistration(result);

        TerminalFactory factory = TerminalFactory.of(TerminalType.BULK);
        StowagePlanner planner = new StowagePlanner(factory);

        StowagePlan plan = planner.plan("PE-2026-0149", "2026-10-12", "BZ-5502",
                result.getAccepted(), "Diana Restrepo");

        printer.print(plan);
    }

    static void missingRegistrationDemo() {
        System.out.println();
        System.out.println("----- CASE 3: INVALID BUILD (no barge registration) -----");

        try {
            new StowagePlan.Builder()
                    .planNumber("PE-2026-0150")
                    .departureDate("2026-11-01")
                    .terminalType(TerminalType.CONTAINER)
                    .holds(List.of(new Hold("B1", 600)))
                    .totalWeight(0)
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Caught exception while building the plan: " + e.getMessage());
        }
    }
}
