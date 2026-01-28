public class Event extends Task {
    protected String start;
    protected String end;

    Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String stringDescription() {
        String status = super.getStatusIcon();
        status = status.equals("X") ? status : "N";
        return status + " event " + super.stringDescription() + " /from " + start + " /to " + end;
    }
}
