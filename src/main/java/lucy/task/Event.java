package lucy.task;

import lucy.exception.LucyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents an event task with a start and end date-time.
 * An event occurs over a specific time period.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an event task using date-time strings.
     *
     * @param description Description of the task.
     * @param fromStr Start date-time in yyyy-MM-dd HHmm format.
     * @param toStr End date-time in yyyy-MM-dd HHmm format.
     * @throws LucyException If the date-time strings are invalid, or the end time is before the start time.
     */
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

    /**
     * Creates an event task with a start and end date-time.
     *
     * @param description Description of the task.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     * @throws LucyException If the end time is before the start time.
     */
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
