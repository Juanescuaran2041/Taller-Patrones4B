package manifest;

public class LiquidRegistrar extends ManifestRegistrar {

    @Override
    protected CargoUnit createUnit(String line) {
        String[] f = line.split(";");
        if (f.length != 4 || !f[0].trim().equals("LIQ")) {
            throw new RuntimeException("does not match the liquid registrar");
        }

        double liters = Double.parseDouble(f[2].trim());
        double density = Double.parseDouble(f[3].trim());
        double grossWeight = liters * density / 1000;

        return new LiquidUnit(f[1].trim(), liters, density, grossWeight);
    }

    @Override
    public String getRegistrarName() {
        return "LIQUID";
    }
}
