package manifest;

import java.util.ArrayList;
import java.util.List;

// Factory Method Pattern
public abstract class ManifestRegistrar {

    public RegistrationResult process(List<String> lines) {
        List<CargoUnit> accepted = new ArrayList<>();
        List<RejectedLine> rejected = new ArrayList<>();

        for (String line : lines) {
            try {
                accepted.add(createUnit(line));
            } catch (Exception ex) {
                rejected.add(new RejectedLine(line, ex.getMessage()));
            }
        }

        return new RegistrationResult(getRegistrarName(), lines.size(), accepted, rejected);
    }

    protected abstract CargoUnit createUnit(String line);

    public abstract String getRegistrarName();
}
