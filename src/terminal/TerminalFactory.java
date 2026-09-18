package terminal;

// Abstract Factory Pattern
public interface TerminalFactory {

    LoadingEquipment createLoadingEquipment();

    StabilityValidator createStabilityValidator();

    ShippingDocument createShippingDocument();

    TerminalType getType();

    static TerminalFactory of(TerminalType type) {
        if (type == TerminalType.BULK) {
            return new BulkTerminalFactory();
        } else if (type == TerminalType.CONTAINER) {
            return new ContainerTerminalFactory();
        } else if (type == TerminalType.LIQUID) {
            return new LiquidTerminalFactory();
        }
        throw new IllegalArgumentException("no factory for " + type);
    }
}
