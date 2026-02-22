package lucy.command;

import java.util.ArrayList;

import lucy.exception.LucyException;
import lucy.task.Task;

/**
 * Represents an executable user command in the Lucy chatbot.
 * Each command performs an action on the task list and interacts
 * with the user interface.
 */
public abstract class Command {
    public abstract String execute(ArrayList<Task> tasks) throws LucyException;

    public boolean isExit() {
        return false;
    }

    /**
     * Undoes the effect of this command.
     * Default: command is not undoable.
     */
    public String undo(ArrayList<Task> tasks) throws LucyException {
        throw new LucyException("Nothing to undo.");
    }

    /**
     * Indicates whether this command can be undone.
     */
    public boolean isUndoable() {
        return false;
    }
}
