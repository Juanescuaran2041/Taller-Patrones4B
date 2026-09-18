package plan;

import java.util.ArrayList;
import java.util.List;
import manifest.CargoUnit;
import terminal.ShippingDocument;
import terminal.LoadingEquipment;
import terminal.TerminalType;

// Builder Pattern
public class StowagePlan {

    public enum Status {
        APPROVED,
        NOT_APPROVED
    }

    private final String planNumber;
    private final String departureDate;
    private final String bargeRegistration;
    private final TerminalType terminalType;
    private final List<Hold> holds;
    private final double totalWeight;

    private final List<CargoMove> loadingSequence;
    private final String plannerName;

    private final LoadingEquipment equipment;
    private final ShippingDocument document;
    private final VerificationReport verificationReport;
    private final List<CargoUnit> notLoaded;

    private StowagePlan(Builder b) {
        this.planNumber = b.planNumber;
        this.departureDate = b.departureDate;
        this.bargeRegistration = b.bargeRegistration;
        this.terminalType = b.terminalType;
        this.holds = b.holds;
        this.totalWeight = b.totalWeight;

        this.loadingSequence = b.loadingSequence;
        this.plannerName = b.plannerName;

        this.equipment = b.equipment;
        this.document = b.document;
        this.verificationReport = b.verificationReport;
        this.notLoaded = b.notLoaded;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getBargeRegistration() {
        return bargeRegistration;
    }

    public TerminalType getTerminalType() {
        return terminalType;
    }

    public List<Hold> getHolds() {
        return holds;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public List<CargoMove> getLoadingSequence() {
        return loadingSequence;
    }

    public String getPlannerName() {
        return plannerName;
    }

    public LoadingEquipment getEquipment() {
        return equipment;
    }

    public ShippingDocument getDocument() {
        return document;
    }

    public VerificationReport getVerificationReport() {
        return verificationReport;
    }

    public List<CargoUnit> getNotLoaded() {
        return notLoaded;
    }

    public Status getStatus() {
        if (verificationReport != null && verificationReport.isApproved()) {
            return Status.APPROVED;
        }
        return Status.NOT_APPROVED;
    }

    public static class Builder {
        private String planNumber;
        private String departureDate;
        private String bargeRegistration;
        private TerminalType terminalType;
        private List<Hold> holds;
        private Double totalWeight;

        private List<CargoMove> loadingSequence = new ArrayList<>();
        private String plannerName;

        private LoadingEquipment equipment;
        private ShippingDocument document;
        private VerificationReport verificationReport;
        private List<CargoUnit> notLoaded = new ArrayList<>();

        public Builder planNumber(String planNumber) {
            this.planNumber = planNumber;
            return this;
        }

        public Builder departureDate(String departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public Builder bargeRegistration(String bargeRegistration) {
            this.bargeRegistration = bargeRegistration;
            return this;
        }

        public Builder terminalType(TerminalType terminalType) {
            this.terminalType = terminalType;
            return this;
        }

        public Builder holds(List<Hold> holds) {
            this.holds = holds;
            return this;
        }

        public Builder totalWeight(double totalWeight) {
            this.totalWeight = totalWeight;
            return this;
        }

        public Builder loadingSequence(List<CargoMove> loadingSequence) {
            this.loadingSequence = loadingSequence;
            return this;
        }

        public Builder plannerName(String plannerName) {
            this.plannerName = plannerName;
            return this;
        }

        public Builder equipment(LoadingEquipment equipment) {
            this.equipment = equipment;
            return this;
        }

        public Builder document(ShippingDocument document) {
            this.document = document;
            return this;
        }

        public Builder verificationReport(VerificationReport verificationReport) {
            this.verificationReport = verificationReport;
            return this;
        }

        public Builder notLoaded(List<CargoUnit> notLoaded) {
            this.notLoaded = notLoaded;
            return this;
        }

        public StowagePlan build() {
            boolean missingRequired = planNumber == null || departureDate == null || bargeRegistration == null
                    || terminalType == null || holds == null || holds.isEmpty() || totalWeight == null;
            if (missingRequired) {
                throw new IllegalStateException("plan is missing a required field (number, date, barge, terminal, holds or weight)");
            }

            int unitCount = 0;
            for (Hold h : holds) {
                unitCount += h.getUnits().size();
            }
            if (unitCount == 0) {
                throw new IllegalStateException("plan has no units assigned");
            }

            return new StowagePlan(this);
        }
    }
}
