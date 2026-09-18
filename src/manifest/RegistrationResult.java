package manifest;

import java.util.List;

public class RegistrationResult {

    private final String registrarName;
    private final int linesRead;
    private final List<CargoUnit> accepted;
    private final List<RejectedLine> rejected;

    public RegistrationResult(String registrarName, int linesRead, List<CargoUnit> accepted, List<RejectedLine> rejected) {
        this.registrarName = registrarName;
        this.linesRead = linesRead;
        this.accepted = accepted;
        this.rejected = rejected;
    }

    public String getRegistrarName() {
        return registrarName;
    }

    public int getLinesRead() {
        return linesRead;
    }

    public List<CargoUnit> getAccepted() {
        return accepted;
    }

    public List<RejectedLine> getRejected() {
        return rejected;
    }
}
