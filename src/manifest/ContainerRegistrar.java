package manifest;

public class ContainerRegistrar extends ManifestRegistrar {

    @Override
    protected CargoUnit createUnit(String line) {
        String[] parts = line.split(";");
        if (parts.length != 5 || !parts[0].trim().equals("CNT")) {
            throw new RuntimeException("does not match the container registrar");
        }

        int feet = Integer.parseInt(parts[2].trim());
        double tare = feet == 40 ? 3.8 : feet == 20 ? 2.2 : 0;
        if (tare == 0) {
            throw new RuntimeException("unsupported feet value, only 20 or 40");
        }

        double netWeight = Double.parseDouble(parts[3].trim());
        return new ContainerUnit(parts[1].trim(), feet, parts[4].trim(), netWeight + tare);
    }

    @Override
    public String getRegistrarName() {
        return "CONTAINER";
    }
}
