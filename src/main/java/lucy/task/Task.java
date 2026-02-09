package lucy.task;

/**
 * Represents a generic task in the task list.
 * A task has a description, a completion status, and a task type.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Creates a task with the given description and task type.
     *
     * @param description Description of the task.
     * @param type Type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markUnDone() {
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    protected String doneFlag() {
        return isDone ? "1" : "0";
    }

    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + getStatusIcon() + "] " + description;
    }

    public abstract String toFileString();
}
