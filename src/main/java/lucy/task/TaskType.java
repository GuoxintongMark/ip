package lucy.task;

/**
 * Represents the different types of tasks supported by the application.
 * <p>
 * Each task type is associated with a short icon string
 * used for display purposes in the user interface.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String icon;

    TaskType(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
