package manifest;

public class BulkRegistrar extends ManifestRegistrar {

    @Override
    protected CargoUnit createUnit(String line) {
        String[] f = line.split(";");
        if (f.length != 4 || !f[0].trim().equals("GRA")) {
            throw new RuntimeException("does not match the bulk registrar");
        }

        double tons = Double.parseDouble(f[2].trim());
        double humidity = Double.parseDouble(f[3].trim());
        double grossWeight = tons * (1 + humidity / 100);

        return new BulkUnit(f[1].trim(), tons, humidity, grossWeight);
    }

    @Override
    public String getRegistrarName() {
        return "BULK";
    }
}
