package lucy.task;

import lucy.exception.LucyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a deadline task with a specific due date.
 * A deadline task has a description and must be completed
 * by a given date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

    private final LocalDate by;

    /**
     * Creates a deadline task using a date string.
     *
     * @param description Description of the task.
     * @param byString Due date in yyyy-MM-dd format.
     * @throws LucyException If the date string is invalid.
     */
    public Deadline(String description, String byString) throws LucyException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDate.parse(byString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new LucyException("Invalid date format. Use yyyy-MM-dd (e.g. 2019-10-15).");
        }
    }

    /**
     * Creates a deadline task using a LocalDate.
     *
     * @param description Description of the task.
     * @param by Due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public LocalDate getBy() { return by; }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + doneFlag() + " | " + description + " | " + by.format(INPUT_FORMAT);
    }
}
