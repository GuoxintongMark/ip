package lucy.task;

/**
 * Represent todo task with description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toFileString() {
        return "T | " + doneFlag() + " | " + description;
    }
}
