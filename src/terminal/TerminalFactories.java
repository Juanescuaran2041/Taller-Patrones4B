package terminal;

class BulkTerminalFactory implements TerminalFactory {

    @Override
    public LoadingEquipment createLoadingEquipment() {
        return new ConveyorBelt();
    }

    @Override
    public StabilityValidator createStabilityValidator() {
        return new BulkValidator();
    }

    @Override
    public ShippingDocument createShippingDocument() {
        return new BulkDocument();
    }

    @Override
    public TerminalType getType() {
        return TerminalType.BULK;
    }

    private static class ConveyorBelt implements LoadingEquipment {
        public String getName() {
            return "Conveyor belt BT-3";
        }

        public double getRateTonsPerHour() {
            return 320;
        }
    }

    private static class BulkValidator implements StabilityValidator {
        public double getMaxImbalancePercent() {
            return 8;
        }

        public double getMaxDraftMeters() {
            return 1.80;
        }
    }

    private static class BulkDocument implements ShippingDocument {
        public String getName() {
            return "Bill of lading with humidity certificate";
        }
    }
}

class ContainerTerminalFactory implements TerminalFactory {

    @Override
    public LoadingEquipment createLoadingEquipment() {
        return new GantryCrane();
    }

    @Override
    public StabilityValidator createStabilityValidator() {
        return new ContainerValidator();
    }

    @Override
    public ShippingDocument createShippingDocument() {
        return new ContainerDocument();
    }

    @Override
    public TerminalType getType() {
        return TerminalType.CONTAINER;
    }

    private static class GantryCrane implements LoadingEquipment {
        public String getName() {
            return "Mobile gantry crane GP-1";
        }

        public double getRateTonsPerHour() {
            return 180;
        }
    }

    private static class ContainerValidator implements StabilityValidator {
        public double getMaxImbalancePercent() {
            return 5;
        }

        public double getMaxDraftMeters() {
            return 2.10;
        }
    }

    private static class ContainerDocument implements ShippingDocument {
        public String getName() {
            return "Unit list with seal number per container";
        }
    }
}

class LiquidTerminalFactory implements TerminalFactory {

    @Override
    public LoadingEquipment createLoadingEquipment() {
        return new LoadingArm();
    }

    @Override
    public StabilityValidator createStabilityValidator() {
        return new LiquidValidator();
    }

    @Override
    public ShippingDocument createShippingDocument() {
        return new LiquidDocument();
    }

    @Override
    public TerminalType getType() {
        return TerminalType.LIQUID;
    }

    private static class LoadingArm implements LoadingEquipment {
        public String getName() {
            return "Loading arm BC-2";
        }

        public double getRateTonsPerHour() {
            return 240;
        }
    }

    private static class LiquidValidator implements StabilityValidator {
        public double getMaxImbalancePercent() {
            return 3;
        }

        public double getMaxDraftMeters() {
            return 1.95;
        }
    }

    private static class LiquidDocument implements ShippingDocument {
        public String getName() {
            return "Manifest with chemical compatibility certificate";
        }
    }
}
