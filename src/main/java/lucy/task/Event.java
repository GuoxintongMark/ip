package lucy.task;

import lucy.exception.LucyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, String fromStr, String toStr) throws LucyException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(fromStr, INPUT_FORMAT);
            this.to = LocalDateTime.parse(toStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new LucyException("Invalid event time format. Use yyyy-MM-dd HHmm.");
        }
        if (to.isBefore(from)) {
            throw new LucyException("lucy.task.Event end time must be after start time.");
        }
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) throws LucyException {
        super(description, TaskType.EVENT);
        if (to.isBefore(from)) {
            throw new LucyException("lucy.task.Event end time must be after start time.");
        }
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() { return from; }
    public LocalDateTime getTo() { return to; }

    @Override
    public String toString() {
        return super.toString()
                + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + doneFlag() + " | " + description
                + " | " + from.format(INPUT_FORMAT)
                + " | " + to.format(INPUT_FORMAT);
    }
}
