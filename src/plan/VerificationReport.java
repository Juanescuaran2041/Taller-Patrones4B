package plan;

public class VerificationReport {

    public final double bowWeight;
    public final double sternWeight;
    public final double imbalancePercent;
    public final double maxImbalancePercent;
    public final double tonsToShift;
    public final boolean balanceOk;

    public final double draftMeters;
    public final double maxDraftMeters;
    public final boolean draftOk;

    public final int loadingHours;
    public final int loadingMinutes;

    public VerificationReport(double bowWeight, double sternWeight, double imbalancePercent, double maxImbalancePercent,
                               double tonsToShift, boolean balanceOk, double draftMeters, double maxDraftMeters,
                               boolean draftOk, int loadingHours, int loadingMinutes) {
        this.bowWeight = bowWeight;
        this.sternWeight = sternWeight;
        this.imbalancePercent = imbalancePercent;
        this.maxImbalancePercent = maxImbalancePercent;
        this.tonsToShift = tonsToShift;
        this.balanceOk = balanceOk;
        this.draftMeters = draftMeters;
        this.maxDraftMeters = maxDraftMeters;
        this.draftOk = draftOk;
        this.loadingHours = loadingHours;
        this.loadingMinutes = loadingMinutes;
    }

    public boolean isApproved() {
        return balanceOk && draftOk;
    }
}
