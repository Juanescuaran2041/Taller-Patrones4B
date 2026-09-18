package manifest;

public class RejectedLine {

    private final String line;
    private final String reason;

    public RejectedLine(String line, String reason) {
        this.line = line;
        this.reason = reason;
    }

    public String getLine() {
        return line;
    }

    public String getReason() {
        return reason;
    }
}
